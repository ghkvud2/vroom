package com.woorifis.vroom.kb.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Service;

import com.woorifis.vroom.kb.domain.BasicInfo;
import com.woorifis.vroom.kb.domain.Car;
import com.woorifis.vroom.kb.domain.HistoryInfo;
import com.woorifis.vroom.kb.repository.KBCarRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class KBCarService {

	private final KBCarRepository repository;

	public void save(Car car) {
		repository.save(car);
	}

	public Car getCarTotalInfo(WebElement element) {
		Map<String, String> param = new HashMap<>();
		setCarBasicInfo(element, param);
		setCarHistoryInfo(element, param);
		return convertToCar(param);
	}

	//차량 기본정보
	private void setCarBasicInfo(WebElement element, Map<String, String> param) {

		for (int i = 1; i <= 6; i++) {
			for (int j = 1; j <= 2; j++) {
				String key = element.findElement(
					By.xpath("//table[@class='detail-info-table']/tbody/tr[" + i + "]/th[" + j + "]")).getText();

				String value = element.findElement(
					By.xpath("//table[@class='detail-info-table']/tbody/tr[" + i + "]/td[" + j + "]")).getText();

				param.put(key, value);
			}
		}
	}

	//차량 성능점검, 보험사고이력정보
	private void setCarHistoryInfo(WebElement element, Map<String, String> param) {
		for (int i = 1; i <= 4; i++) {
			String key = element.findElement(By.xpath("//div[@class='detail-info02']/div/dl/dt[" + i + "]")).getText();
			String value = element.findElement(By.xpath("//div[@class='detail-info02']/div/dl/dd[" + i + "]"))
				.getText();
			param.put(key, value);
		}
	}

	private Car convertToCar(Map<String, String> param) {
		Car car = new Car();
		BasicInfo basicInfo = getBasicInfo(car, param);
		HistoryInfo historyInfo = getHistoryInfo(car, param);
		car.setBasicInfo(basicInfo);
		car.setHistoryInfo(historyInfo);
		return car;
	}

	private BasicInfo getBasicInfo(Car car, Map<String, String> param) {
		BasicInfo basicInfo = new BasicInfo();
		basicInfo.setLicense(param.get(CarColumn.LICENSE.value()));
		basicInfo.setYear(param.get(CarColumn.YEAR.value()));
		basicInfo.setMileage(param.get(CarColumn.MILEAGE.value()));
		basicInfo.setFuel(param.get(CarColumn.FUEL.value()));
		basicInfo.setGear(param.get(CarColumn.GEAR.value()));
		basicInfo.setFuelEfficiency(param.get(CarColumn.FUEL_EFFICIENCY.value()));
		basicInfo.setModel(param.get(CarColumn.MODEL.value()));
		basicInfo.setDisplacement(param.get(CarColumn.DISPLACEMENT.value()));
		basicInfo.setColor(param.get(CarColumn.COLOR.value()));
		basicInfo.setIsTaxNotPaid(param.get(CarColumn.IS_TAX_NOT_PAID.value()));
		basicInfo.setIsSeized(param.get(CarColumn.IS_SEIZED.value()));
		basicInfo.setIsMortgaged(param.get(CarColumn.IS_MORTGAGED.value()));
		return basicInfo;
	}

	private HistoryInfo getHistoryInfo(Car car, Map<String, String> param) {
		HistoryInfo historyInfo = new HistoryInfo();
		historyInfo.setIsTotalLoss(param.get(CarColumn.IS_TOTAL_LOSS.value()));
		historyInfo.setIsFlooded(param.get(CarColumn.IS_FLOODED.value()));
		historyInfo.setIsUsageChanged(param.get(CarColumn.IS_USAGE_CHANGED.value()));
		historyInfo.setOwnerChangeCnt(param.get(CarColumn.OWNER_CHANGE_CNT.value()));
		return historyInfo;
	}

	public List<WebElement> getCarThumbnailList(WebElement element) {
		return element.findElements(By.cssSelector(".generalRegist .list-in .area"));
	}

	private enum CarColumn {

		LICENSE("차량정보"),
		YEAR("연식"),
		MILEAGE("주행거리"),
		FUEL("연료"),
		GEAR("변속기"),
		FUEL_EFFICIENCY("연비"),
		MODEL("차종"),
		DISPLACEMENT("배기량"),
		COLOR("색상"),
		IS_TAX_NOT_PAID("세금미납"),
		IS_SEIZED("압류"),
		IS_MORTGAGED("저당"),
		IS_TOTAL_LOSS("전손이력"),
		IS_FLOODED("침수이력"),
		IS_USAGE_CHANGED("용도이력"),
		OWNER_CHANGE_CNT("소유자변경");

		private String columnName;

		CarColumn(String columnName) {
			this.columnName = columnName;
		}

		public String value() {
			return this.columnName;
		}
	}
}
