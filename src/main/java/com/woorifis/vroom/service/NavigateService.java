package com.woorifis.vroom.service;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class NavigateService {

	private final WebDriver driver;

	public void navigateTo(String url) {
		driver.navigate().to(url);
	}

	public void switchTab(String tab) {
		driver.switchTo().window(tab);
	}

	public void close() {
		driver.close();
	}

	public void refresh(){
		driver.navigate().refresh();
	}
}
