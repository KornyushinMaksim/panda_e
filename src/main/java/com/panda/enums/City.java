package com.panda.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum City {

    RZN("Рязань"),

    MSK("Москва"),

    SPB("Санкт-Петербург"),

    EKB("Екатеринбург"),

    NN("Нижний Новгород"),

    KZN("Казань");

    private final String value;
}
