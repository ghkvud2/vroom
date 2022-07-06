package com.woorifis.vroom.domain;

import static com.woorifis.vroom.domain.Column.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.woorifis.vroom.util.CollectUtils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "car")
public class Car {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "car_id")
	private Long id;

	private String kind;                //종류 (모닝, 그랜저)
	private String price;                //가격
	private String region;                //지역

	private String license;             //차량정보(번호판)
	private String modelYear;           //연식
	private String mileage;             //주행거리
	private String fuel;                //연료
	private String gear;                //변속기
	private String fuelEfficiency;      //연비
	private String model;               //차종
	private String displacement;        //배기량
	private String color;               //색상
	private String isTaxNotPaid;        //세금미납여부
	private String isSeized;            //압류여부
	private String isMortgaged;         //저당여부

	private String isTotalLoss;         //전손이력
	private String isFlooded;           //침수이력
	private String isUsageChanged;      //용도이력
	private String ownerChangeCnt;      //소유자변경

	@OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
	private List<CarOption> carOptions = new ArrayList<>();

	public void addCarOptions(List<CarOption> carOptions) {
		this.carOptions = carOptions;
		this.carOptions.forEach(carOption -> carOption.addCar(this));
	}

	public static Car of(Map<String, String> param) {
		Car car = new Car();
		car.setKind(CollectUtils.removeRange(CollectUtils.remove(param.get(KIND.getName()), "\\R"), '(', ')'));
		car.setPrice(CollectUtils.remove(param.get(PRICE.getName()), ",", "만원"));
		car.setRegion(param.get(REGION.getName()));
		car.setLicense(param.get(LICENSE.getName()));
		car.setModelYear(CollectUtils.includeRange(param.get(MODEL_YEAR.getName()), 0, 1));
		car.setMileage(CollectUtils.remove(param.get(MILEAGE.getName()), ",", "km"));
		car.setFuel(param.get(FUEL.getName()));
		car.setGear(param.get(GEAR.getName()));
		car.setFuelEfficiency(CollectUtils.remove(param.get(FUEL_EFFICIENCY.getName()), "Km"));
		car.setModel(param.get(MODEL.getName()));
		car.setDisplacement(CollectUtils.remove(param.get(DISPLACEMENT.getName()), ",", "cc"));
		car.setColor(param.get(COLOR.getName()));
		car.setIsTaxNotPaid(param.get(IS_TAX_NOT_PAID.getName()));
		car.setIsSeized(param.get(IS_SEIZED.getName()));
		car.setIsMortgaged(param.get(IS_MORTGAGED.getName()));
		car.setIsTotalLoss(param.get(IS_TOTAL_LOSS.getName()));
		car.setIsFlooded(param.get(IS_FLOODED.getName()));
		car.setIsUsageChanged(param.get(IS_USAGE_CHANGED.getName()));
		car.setOwnerChangeCnt(CollectUtils.remove(param.get(OWNER_CHANGE_CNT.getName()), "회"));
		return car;
	}

	@Override
	public String toString() {
		return "Car{" +
			"id=" + id +
			", kind='" + kind + '\'' +
			", license='" + license + '\'' +
			'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Car car = (Car)o;
		return Objects.equals(id, car.id) && Objects.equals(kind, car.kind) && Objects.equals(
			price, car.price) && Objects.equals(region, car.region) && Objects.equals(license,
			car.license) && Objects.equals(modelYear, car.modelYear) && Objects.equals(mileage, car.mileage)
			&& Objects.equals(fuel, car.fuel) && Objects.equals(gear, car.gear)
			&& Objects.equals(fuelEfficiency, car.fuelEfficiency) && Objects.equals(model, car.model)
			&& Objects.equals(displacement, car.displacement) && Objects.equals(color, car.color)
			&& Objects.equals(isTaxNotPaid, car.isTaxNotPaid) && Objects.equals(isSeized, car.isSeized)
			&& Objects.equals(isMortgaged, car.isMortgaged) && Objects.equals(isTotalLoss,
			car.isTotalLoss) && Objects.equals(isFlooded, car.isFlooded) && Objects.equals(
			isUsageChanged, car.isUsageChanged) && Objects.equals(ownerChangeCnt, car.ownerChangeCnt);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, kind, price, region, license, modelYear, mileage, fuel, gear, fuelEfficiency, model,
			displacement, color, isTaxNotPaid, isSeized, isMortgaged, isTotalLoss, isFlooded, isUsageChanged,
			ownerChangeCnt);
	}
}
