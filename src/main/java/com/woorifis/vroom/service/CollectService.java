package com.woorifis.vroom.service;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.woorifis.vroom.config.WaitProcessor;
import com.woorifis.vroom.domain.Car;
import com.woorifis.vroom.domain.CarOption;
import com.woorifis.vroom.domain.Column;
import com.woorifis.vroom.repository.CollectRepository;
import com.woorifis.vroom.util.CollectUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class CollectService {

	private final CollectRepository repository;
	private final WaitProcessor waitProcessor;

	public void collect(WebElement element, String brandId, String classId) {
		Car car = createCar(element);
		car.setBrandId(brandId);
		car.setClassId(classId);
		repository.save(car);

		log.info("{}", car);
		log.info("---------------------------------------");
	}

	public List<WebElement> getCarThumbnails(WebElement element) {
		By locator = By.cssSelector(".generalRegist .list-in .area a.item");
		// waitProcessor.numberOfElementsToBeMoreThan(locator, 19, Duration.ofSeconds(5));
		return element.findElements(locator);
	}

	public List<String> getCarLink(WebElement element){
		By locator = By.xpath("//div[contains(@class,'generalRegist')]/div[@class='list-in']/div[contains(@class, 'area')]/div[@class='thumnail']/a");
		return element.findElements(locator).stream().map(e -> e.getAttribute("href")).collect(Collectors.toList());
	}

	protected Car createCar(WebElement element) {
		Map<String, String> param = getCarParameter(element);
		Car car = Car.of(param);

		List<CarOption> carOptions = getCarOptions(element);
		car.addCarOptions(carOptions);
		return car;
	}

	protected List<CarOption> getCarOptions(WebElement element) {
		return element.findElements(
				By.xpath("//ul[@class='car-option-list']/li[not(@class='option_more cursor-pointer')]"))
			.stream()
			.map(this::convertToCarOption)
			.collect(Collectors.toList());
	}

	private CarOption convertToCarOption(WebElement element) {
		CarOption carOption = new CarOption();
		carOption.setCode(CollectUtils.remove(element.getAttribute("class"), "option"));
		carOption.setName(CollectUtils.remove(element.getText(), "\\R"));
		return carOption;
	}

	//?????? ????????????
	protected Map<String, String> getCarParameter(WebElement element) {

		Map<String, String> param = new HashMap<>();

		//??????
		param.put(Column.KIND.getName(), element.findElement(By.cssSelector(".car-buy-name")).getText());

		//??????
		waitProcessor.visibilityOfElementLocated(By.xpath("//span[@class='price-sum']/strong"), Duration.ofSeconds(5));
		param.put(Column.PRICE.getName(), element.findElement(By.xpath("//span[@class='price-sum']/strong")).getText());

		//??????
		param.put(Column.REGION.getName(),
			element.findElement(By.xpath("//div[@class='txt-info']//span[4]")).getText());

		//????????????
		int rows = element.findElements(By.xpath("//table[@class='detail-info-table']/tbody/tr")).size();
		IntStream.range(1, rows)
			.forEach(i -> IntStream.rangeClosed(1, 2)
				.forEach(j -> {
					String title = element.findElement(
						By.xpath("//table[@class='detail-info-table']/tbody/tr[" + i + "]/th[" + j + "]")).getText();
					String value = element.findElement(
						By.xpath("//table[@class='detail-info-table']/tbody/tr[" + i + "]/td[" + j + "]")).getText();
					param.put(title, value);
				}));

		//????????????
		rows = element.findElements(By.xpath("//div[@class='detail-info02']/div/dl/dt")).size();
		IntStream.rangeClosed(1, rows)
			.forEach(i -> {
				String title = element.findElement(By.xpath("//div[@class='detail-info02']/div/dl/dt[" + i + "]"))
					.getText();
				String value = element.findElement(By.xpath("//div[@class='detail-info02']/div/dl/dd[" + i + "]"))
					.getText();
				param.put(title, value);
			});

		return param;
	}

}
