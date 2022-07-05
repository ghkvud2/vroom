package com.woorifis.vroom.kb.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Getter
@Setter
@Embeddable
public class TitleInfo {

    private String kind;    //차종
    private String price;   //가격
    private String region;  //지역

}
