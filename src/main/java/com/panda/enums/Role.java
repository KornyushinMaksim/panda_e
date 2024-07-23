package com.panda.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Getter
@RequiredArgsConstructor
public enum Role implements GrantedAuthority{

    ROLE_ADM("администратор"),

    ROLE_USR("пользователь"),

    ROLE_MGR("менеджер"),

    ROLE_CTR("заказчик"),

    ROLE_GST("гость");

    private final String value;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
