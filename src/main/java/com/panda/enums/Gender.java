package com.panda.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Gender {

    MALE("муж"),

    FEMALE("жен");

    private final String value;
}
