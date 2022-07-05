package com.woorifis.vroom.kb.domain;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
//@SequenceGenerator(
//	name="CAR_SEQ_GENERATOR"
//	,sequenceName = "CAR_SEQ"
//	,initialValue = 1
//)
@Table(name = "car")
public class Car extends BaseEntity {

    @Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CAR_SEQ_GENERATOR")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "car_id")
    private Long id;

    @Embedded
    private TitleInfo titleInfo;

    @Embedded
    private BasicInfo basicInfo;    //기본정보

    @Embedded
    private HistoryInfo historyInfo; //보험 사고이력


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "car", cascade = CascadeType.ALL)
    private List<CarOption> carOptions = new ArrayList<>();


    public void addOption(List<CarOption> options) {

        this.carOptions = options;
        options.stream().forEach(carOption -> carOption.addCar(this));

    }


}
