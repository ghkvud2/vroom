package com.woorifis.vroom.kb.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class BasicInfo {

	@Column(unique = true)
	private String license;                //차량정보(번호판)
	private String year;                    //연식
	private String mileage;                 //주행거리
	private String fuel;                    //연료
	private String gear;                    //변속기
	private String fuelEfficiency;          //연비
	private String model;                   //차종
	private String displacement;            //배기량
	private String color;                   //색상
	private String isTaxNotPaid;            //세금미납여부(boolean)
	private String isSeized;                //압류여부	(boolean)
	private String isMortgaged;             //저당여부	(boolean)

}
