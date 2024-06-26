package com.car_service.customer.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TaxValue {
    ZERO("0"),
    TWENTY_THREE("23");

    private final String displayText;

}
