package com.woorifis.vroom.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.woorifis.vroom.domain.Car;
import com.woorifis.vroom.domain.CarOption;
import com.woorifis.vroom.domain.Column;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ActiveProfiles("test")
@SpringBootTest
class CollectServiceTest {

	private String url;

	@Autowired
	private WebDriver driver;

	@Autowired
	private CollectService service;

	@Value("${driver.name}")
	private String driverName;

	@Value("${driver.path}")
	private String driverPath;

	private Car expectedCar;

	@BeforeEach
	void setUp() {
		System.setProperty(driverName, driverPath);
		expectedCar = getFixture();
	}

	@Test
	@DisplayName("상세 페이지에서 차량 정보(기본, 이력)")
	void testCreateCar() {
		url = "https://www.kbchachacha.com/public/car/detail.kbc?carSeq=22930656";
		driver.get(url);

		Car createdCar = service.createCar(getDocument());
		assertEquals(expectedCar, createdCar);
	}

	@Test
	@DisplayName("페이지 일반 매물 차량 정보 섬네일")
	void testCarThumbnail() {
		url = "https://www.kbchachacha.com/public/search/main.kbc#!?countryOrder=1&page=1";
		driver.get(url);

		List<WebElement> carThumbnails = service.getCarThumbnails(getDocument());
		assertEquals(20, carThumbnails.size());
	}

	private WebElement getDocument() {
		return driver.findElement(By.tagName("body"));
	}

	private Car getFixture() {

		Map<String, String> param = new HashMap<>() {
			{
				put(Column.KIND.getName(), "(13나0714)한국GM 더 넥스트 스파크\nLTZ C-TECH");
				put(Column.PRICE.getName(), "870만원");
				put(Column.REGION.getName(), "서울");
				put(Column.LICENSE.getName(), "13나0714");
				put(Column.MODEL_YEAR.getName(), "16년05월(16년형)");
				put(Column.MILEAGE.getName(), "35,100km");
				put(Column.FUEL.getName(), "가솔린");
				put(Column.GEAR.getName(), "오토");
				put(Column.FUEL_EFFICIENCY.getName(), "정보없음");
				put(Column.MODEL.getName(), "경차");
				put(Column.DISPLACEMENT.getName(), "999cc");
				put(Column.COLOR.getName(), "흰색");
				put(Column.IS_TAX_NOT_PAID.getName(), "없음");
				put(Column.IS_SEIZED.getName(), "없음");
				put(Column.IS_MORTGAGED.getName(), "없음");
				put(Column.IS_TOTAL_LOSS.getName(), "없음");
				put(Column.IS_FLOODED.getName(), "없음");
				put(Column.IS_USAGE_CHANGED.getName(), "없음");
				put(Column.OWNER_CHANGE_CNT.getName(), "4회");
			}
		};

		List<CarOption> carOptions = Arrays.asList(
			new CarOption("option3", "스마트키"),
			new CarOption("option4", "오토라이트"),
			new CarOption("option5", "주차감지센서"),
			new CarOption("option6", "가죽시트"),
			new CarOption("option7", "가죽시트"),
			new CarOption("option9", "사이드&커튼에어백"),
			new CarOption("option11", "차선이탈경보\n(LDSW)"),
			new CarOption("option12", "자동긴급제동\n(AEB)")
		);
		expectedCar = Car.of(param);
		expectedCar.addCarOptions(carOptions);
		return expectedCar;
	}

}