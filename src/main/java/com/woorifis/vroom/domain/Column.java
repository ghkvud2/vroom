package com.woorifis.vroom.domain;

import java.util.Arrays;

public enum Column {

	KIND("종류"),
	PRICE("가격"),
	REGION("지역"),
	LICENSE("차량정보"),
	MODEL_YEAR("연식"),
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

	private final String name;

	Column(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public static Column of(String column) {
		return Arrays.stream(values())
			.filter(e -> e.getName().equals(column))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("매핑되지 않는 컬럼 이름입니다."));
	}
}
