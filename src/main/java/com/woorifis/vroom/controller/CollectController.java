package com.woorifis.vroom.controller;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

import com.woorifis.vroom.config.WaitProcessor;
import com.woorifis.vroom.service.CollectService;
import com.woorifis.vroom.service.NavigateService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class CollectController {

    private static final int NUMBER_OF_THUMBNAIL = 20;
    private static final long INTERVAL_OF_CLICK = 3 * 1000L;
    private static final long INTERVAL_OF_MOVE_PAGE = 2 * 1000L;

    private final CollectService collectService;
    private final NavigateService navigateService;
    private final WaitProcessor waitProcessor;
    private final WebDriver driver;

    private WebElement getDocumentOnCurrentPage() {
        return driver.findElement(By.tagName("body"));
    }

    private String createTargetUrl(String baseUrl, String markerCode, String classCode, int page) {
        return String.format(baseUrl, markerCode, classCode, page);
    }

    public void run(String baseUrl, String markerCode, String classCode, int startPage, int endPage) throws InterruptedException {

        for (int currentPage = startPage; currentPage <= endPage; currentPage++) {

            try {

                Thread.sleep(INTERVAL_OF_MOVE_PAGE);
                String url = createTargetUrl(baseUrl, markerCode, classCode, currentPage);
                log.info("[move to] : {}", url);
                navigateService.navigateTo(url);

                Thread.sleep(INTERVAL_OF_MOVE_PAGE); // StaleElementReferenceException 방지 시도
                By thumbnailLocator = By.xpath("//div[contains(@class,'generalRegist')]/div[@class='list-in']/div[contains(@class, 'area')]/div[@class='thumnail']/a");
                waitProcessor.numberOfElementsToBeMoreThan(thumbnailLocator, NUMBER_OF_THUMBNAIL - 1,
                        Duration.ofSeconds(10));

                // List<WebElement> carThumbnails = collectService.getCarThumbnails(getDocumentOnCurrentPage());
                List<String> carLinks = collectService.getCarLink(getDocumentOnCurrentPage());
                int size = carLinks.size();
                log.info("[thumbnail size of current page]={}", size);

                for (int i = 0; i < size; i++) {

                    url = carLinks.get(i);
                    navigateService.navigateTo(url);
                    Thread.sleep(INTERVAL_OF_CLICK);

                    log.info("[move to] : {}", url);
                    log.info("{}th of {} page", (i + 1), currentPage);
                    By carInfoTableLocator = By.xpath("//table[@class='detail-info-table']/tbody/tr");
                    waitProcessor.visibilityOfElementLocated(carInfoTableLocator,
                            Duration.ofSeconds(10));//렌더링 wait

                    collectService.collect(getDocumentOnCurrentPage(), markerCode, classCode);//스크래핑

                    Thread.sleep(INTERVAL_OF_CLICK);
                    navigateService.back();
                    Thread.sleep(INTERVAL_OF_CLICK);
                }

            } catch (StaleElementReferenceException e) {
                log.error("[StaleElementReferenceException-1]", e);
                navigateService.refresh(); //새로고침
                Thread.sleep(INTERVAL_OF_CLICK); //wait
            } catch (ElementClickInterceptedException e) {
                log.error("[ElementClickInterceptedException-1]", e);
                navigateService.refresh(); //새로고침
                Thread.sleep(INTERVAL_OF_CLICK); //wait
            } catch (Exception e) {
                log.error("[Exception-1]", e);
                return;
            }
        }

        // IntStream.rangeClosed(startPage, endPage)
        // 	.forEach(currentPage -> {
        //
        // 		try {
        // 			Thread.sleep(INTERVAL_OF_CLICK);
        // 			String url = createTargetUrl(baseUrl, currentPage);
        // 			navigateService.navigateTo(url);
        // 			log.info("[move to] : {}", url);
        //
        // 			Thread.sleep(INTERVAL_OF_CLICK); // StaleElementReferenceException 방지 시도
        //
        // 			try {
        // 				By thumbnailLocator = By.cssSelector(".generalRegist .list-in .area");
        // 				waitProcessor.numberOfElementsToBeMoreThan(thumbnailLocator, NUMBER_OF_THUMBNAIL - 1,
        // 					Duration.ofSeconds(10));
        // 			} catch (StaleElementReferenceException e) {
        // 				log.error("[StaleElementReferenceException-1]", e);
        // 				navigateService.refresh(); //새로고침
        // 				Thread.sleep(INTERVAL_OF_CLICK); //wait
        // 			} catch (ElementClickInterceptedException e) {
        // 				log.error("[ElementClickInterceptedException-1]", e);
        // 				navigateService.refresh(); //새로고침
        // 				Thread.sleep(INTERVAL_OF_CLICK); //wait
        // 			} catch (Exception e) {
        // 				log.error("[Exception-1]", e);
        // 				return;
        // 			}
        //
        // 			List<WebElement> carThumbnails = collectService.getCarThumbnails(getDocumentOnCurrentPage());
        // 			int[] index = {0};
        // 			log.info("[thumbnail size of current page]={}", carThumbnails.size());
        //
        // 			carThumbnails.forEach(thumbnail -> {
        //
        // 				try {
        // 					waitProcessor.elementToBeClickable(By.cssSelector(".generalRegist .list-in .area a.item"),
        // 						Duration.ofSeconds(5));
        // 					thumbnail.click(); //섬네일 클릭
        // 					log.info("{}th of {} page", ++index[0], currentPage);
        // 					List<String> tabs = new ArrayList<>(driver.getWindowHandles());//탭 목록 얻기
        //
        // 					navigateService.switchTab(tabs.get(1));//신규 탭으로 이동
        //
        // 					By carInfoTableLocator = By.xpath("//table[@class='detail-info-table']/tbody/tr");
        // 					waitProcessor.visibilityOfElementLocated(carInfoTableLocator,
        // 						Duration.ofSeconds(5));//렌더링 wait
        //
        // 					collectService.collect(getDocumentOnCurrentPage());//스크래핑
        // 					Thread.sleep(INTERVAL_OF_CLICK);
        //
        // 					navigateService.close();//신규 탭 닫기
        //
        // 					navigateService.switchTab(tabs.get(0));//기존 탭으로 이동
        // 					Thread.sleep(INTERVAL_OF_CLICK);
        //
        // 				} catch (InterruptedException e) {
        // 					log.error("[InterruptedException]", e);
        // 				} catch (ElementClickInterceptedException e) {
        // 					try {
        // 						log.error("[ElementClickInterceptedException-2]", e);
        // 						navigateService.refresh(); //새로고침
        // 						Thread.sleep(INTERVAL_OF_CLICK); //wait
        // 					} catch (InterruptedException ex) {
        // 						ex.printStackTrace();
        // 					}
        // 				}
        // 			});
        //
        // 		} catch (InterruptedException e) {
        // 			log.error("[InterruptedException]", e);
        // 		} catch (Exception e) {
        // 			log.error("[Exception-2]", e);
        // 			throw new RuntimeException(e);
        // 		}
        // 	});
    }

}
