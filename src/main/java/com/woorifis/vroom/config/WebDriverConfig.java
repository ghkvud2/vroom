package com.woorifis.vroom.config;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebDriverConfig {

	@Value("${driver.name}")
	private String DRIVER_NAME;

	@Value("${driver.path}")
	private String DRIVER_PATH;

	@Bean
	public WebDriver chromeDriver() {
		System.setProperty(DRIVER_NAME, DRIVER_PATH);
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		return driver;
	}
}
