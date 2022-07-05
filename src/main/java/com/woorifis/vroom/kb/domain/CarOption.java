package com.woorifis.vroom.kb.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "car_option")
public class CarOption {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "car_option_id")
    private Long id;

    private String code;
    private String optionName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    private Car car;


    public void addCar(Car car) {
        this.car = car;
    }
}
