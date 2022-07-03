package com.woorifis.vroom.kb.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@SequenceGenerator(
	name="CAR_SEQ_GENERATOR"
	,sequenceName = "CAR_SEQ"
	,initialValue = 1
)
@Table(name = "car")
public class Car extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CAR_SEQ_GENERATOR")
	private Long id;

	@Embedded
	private BasicInfo basicInfo;

	@Embedded
	private HistoryInfo historyInfo;

}
