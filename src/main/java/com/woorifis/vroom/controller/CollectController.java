package com.woorifis.vroom.controller;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
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

	private final CollectService collectService;
	private final NavigateService navigateService;
	private final WaitProcessor waitProcessor;
	private final WebDriver driver;

	private WebElement getDocumentOnCurrentPage() {
		return driver.findElement(By.tagName("body"));
	}

	private String createTargetUrl(String baseUrl, int page) {
		return baseUrl + page;
	}

	public void run(String baseUrl, int startPage, int endPage) {

		IntStream.rangeClosed(startPage, endPage)
			.forEach(currentPage -> {

				try {
					Thread.sleep(INTERVAL_OF_CLICK);
					String url = createTargetUrl(baseUrl, currentPage);
					navigateService.navigateTo(url);
					log.info("[move to] : {}", url);

					Thread.sleep(INTERVAL_OF_CLICK); // StaleElementReferenceException 방지 시도

					try {
						By thumbnailLocator = By.cssSelector(".generalRegist .list-in .area");
						waitProcessor.numberOfElementsToBeMoreThan(thumbnailLocator, NUMBER_OF_THUMBNAIL - 1,
							Duration.ofSeconds(10));
					} catch (StaleElementReferenceException e) {
						log.error("[StaleElementReferenceException]", e);
						navigateService.refresh(); //새로고침
						Thread.sleep(INTERVAL_OF_CLICK); //wait
					}

					List<WebElement> carThumbnails = collectService.getCarThumbnails(getDocumentOnCurrentPage());
					int[] index = {0};

					carThumbnails.forEach(thumbnail -> {

						try {
							waitProcessor.elementToBeClickable(thumbnail, Duration.ofSeconds(5));
							thumbnail.click(); //섬네일 클릭
							log.info("{}th of {} page", ++index[0], currentPage);
							List<String> tabs = new ArrayList<>(driver.getWindowHandles());//탭 목록 얻기

							navigateService.switchTab(tabs.get(1));//신규 탭으로 이동

							By carInfoTableLocator = By.xpath("//table[@class='detail-info-table']/tbody/tr");
							waitProcessor.visibilityOfElementLocated(carInfoTableLocator,
								Duration.ofSeconds(5));//렌더링 wait

							collectService.collect(getDocumentOnCurrentPage());//스크래핑
							Thread.sleep(INTERVAL_OF_CLICK);

							navigateService.close();//신규 탭 닫기

							navigateService.switchTab(tabs.get(0));//기존 탭으로 이동
							Thread.sleep(INTERVAL_OF_CLICK);

						} catch (InterruptedException e) {
							log.error("[InterruptedException]", e);
						}
					});

				} catch (InterruptedException e) {
					log.error("[InterruptedException]", e);
				} catch (Exception e) {
					log.error("[Exception]", e);
					throw new RuntimeException(e);
				}
			});
	}

}
