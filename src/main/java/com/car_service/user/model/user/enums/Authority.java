package com.car_service.user.model.user.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Authority {
    ROLE_USER("USER"),
    ROLE_ADMIN("ADMIN");

    private final String displayText;
}
