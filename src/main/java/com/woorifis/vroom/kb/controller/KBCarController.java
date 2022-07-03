package com.woorifis.vroom.kb.controller;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import com.woorifis.vroom.kb.domain.Car;
import com.woorifis.vroom.kb.service.KBCarService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class KBCarController {

	private final Long SLEEP = 5 * 1000L;
	private final String url = "https://www.kbchachacha.com/public/search/main.kbc#!?countryOrder=1&page=1";
	private final KBCarService service;
	private final WebDriver driver;

	private final String SELECT_CAR_BASIC_INFO_COLUMN = "//table[@class='detail-info-table']/tbody/tr[%d]/th[%d]";
	private final String SELECT_CAR_BASIC_INFO_VALUE = "//table[@class='detail-info-table']/tbody/tr[%d]/td[%d]";
	private final String SELECT_CAR_HISTORY_INFO_KEY = "//div[@class='detail-info02']/div/dl/dt[%d]";
	private final String SELECT_CAR_HISTORY_INFO_VALUE = "//div[@class='detail-info02']/div/dl/dd[%d]";

	public void run() {

		driver.get(url);

		WebElement body = driver.findElement(By.tagName("body"));

		service.getCarThumbnailList(body)
			.stream()
			.map(thumbnail -> {
				Car car = null;
				try {
					Thread.sleep(SLEEP);
					thumbnail.click();
					List<String> newTab = new ArrayList<>(driver.getWindowHandles());
					driver.switchTo().window(newTab.get(1));

					WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
					webDriverWait.until(ExpectedConditions.visibilityOf(
						driver.findElement(By.xpath(String.format("//table[@class='detail-info-table']/tbody/tr")))));
					car = service.getCarTotalInfo(driver.findElement(By.tagName("body")));
					driver.close();
					driver.switchTo().window(newTab.get(0));
				} catch (StaleElementReferenceException e) {
					log.error("StaleElementReferenceException");
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
				return car;
			})
			.filter(Objects::nonNull)
			.forEach(service::save);
	}
}
