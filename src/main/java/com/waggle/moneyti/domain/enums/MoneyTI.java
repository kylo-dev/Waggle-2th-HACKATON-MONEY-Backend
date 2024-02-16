package com.waggle.moneyti.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MoneyTI {
    SELFMADE("BTS"),
    SCROOGE("스크루지"),
    YOLO("김카다시안"),
    SURVIVOR("금잔디"),
    GOLDEN("일론머스크"),
    GREEDY("성기훈");

    private final String moneyTI;
}