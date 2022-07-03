package com.woorifis.vroom;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class VroomApplicationTests {

	@Test
	void contextLoads() {
		//C:\Users\ghkvud-home\Downloads\chromedriver_win32\chromedriver.exe
		System.setProperty("webdriver.chrome.driver",
			"C:\\Users\\ghkvud-home\\Downloads\\chromedriver_win32\\chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.kbchachacha.com/public/search/main.kbc#!?page=1");

		while (true) {

			try {
				WebElement element = driver.findElement(By.className("next"));
				log.info("current page={}", element.getAttribute("xpath"));
				element.click();
				Thread.sleep(2 * 1000);
			} catch (NoSuchElementException e) {
				break;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
