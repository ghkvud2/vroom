package com.woorifis.vroom.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "car_option")
public class CarOption {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "car_option_id")
	private Long id;

	private String code;
	private String name;

	public CarOption(String code, String name) {
		this.code = code;
		this.name = name;
	}

	@ManyToOne
	@JoinColumn(name = "car_id")
	private Car car;

	public void addCar(Car car) {
		this.car = car;
	}

	@Override
	public String toString() {
		return "CarOption{" +
			"id=" + id +
			", code='" + code + '\'' +
			", name='" + name + '\'' +
			'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		CarOption carOption = (CarOption)o;
		return Objects.equals(id, carOption.id) && Objects.equals(code, carOption.code)
			&& Objects.equals(name, carOption.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, code, name);
	}


}