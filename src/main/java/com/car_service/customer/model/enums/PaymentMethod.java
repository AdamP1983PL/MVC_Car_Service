package com.car_service.customer.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentMethod {
    CASH("cash"),
    THREE_DAY_TRANSFER("3 day transfer"),
    SEVEN_DAY_TRANSFER("7 day transfer"),
    FOURTEEN_DAY_TRANSFER("14 day transfer"),
    BY_CARD("by card"),
    CONFIRMED_TRANSFER("confirmed transfer");

    private final String displayText;

}
