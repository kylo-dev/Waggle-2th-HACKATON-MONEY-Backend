package com.waggle.moneyti.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MoneyTI {
    A("자수성가족"),
    B("온라인소비족"),
    C("스크루지족"),
    D("욜로족"),
    E("파이어족"),
    F("생존족"),
    G("천재일우족");

    private final String moneyTI;
}