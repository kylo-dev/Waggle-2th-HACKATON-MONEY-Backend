package com.waggle.moneyti.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MoneyTI {
    SELFMADE("자수성가족"),
    SCROOGE("스크루지족"),
    YOLO("욜로족"),
    FIRE("파이어족"),
    SURVIVOR("생존족"),
    GOLDEN("천재일우족");

    private final String moneyTId;
}