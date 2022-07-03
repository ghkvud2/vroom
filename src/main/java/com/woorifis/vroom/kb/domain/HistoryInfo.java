package com.woorifis.vroom.kb.domain;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class HistoryInfo {

	private String isTotalLoss;          //전손이력	(boolean)
	private String isFlooded;            //침수이력	(boolean)
	private String isUsageChanged;       //용도이력	(boolean)
	private String ownerChangeCnt;       //소유자변경 (int)
}
