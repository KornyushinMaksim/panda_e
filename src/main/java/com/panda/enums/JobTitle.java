package com.panda.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum JobTitle {

    BOSS("начальник отдела"),

    DEV("разработчик"),

    MGR("менеджер"),

    ED("редактор");


    private final String value;
}
