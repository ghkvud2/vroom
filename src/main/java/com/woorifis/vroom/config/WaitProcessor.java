package com.woorifis.vroom.config;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class WaitProcessor {

	private final WebDriver driver;

	public void numberOfElementsToBeMoreThan(By locator, Integer numberOfElements, Duration duration) {
		WebDriverWait webDriverWait = new WebDriverWait(driver, duration);
		webDriverWait.until(
			ExpectedConditions.and(
				ExpectedConditions.numberOfElementsToBeMoreThan(locator, numberOfElements),
				ExpectedConditions.visibilityOfElementLocated(locator),
				ExpectedConditions.presenceOfAllElementsLocatedBy(locator)
				));
	}

	public void visibilityOfElementLocated(By locator, Duration duration) {
		WebDriverWait webDriverWait = new WebDriverWait(driver, duration);
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
}
