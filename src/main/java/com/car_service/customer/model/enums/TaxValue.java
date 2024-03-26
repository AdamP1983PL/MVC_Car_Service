package com.car_service.customer.model.enums;


public enum TaxValue {
    ZERO("0"),
    TWENTY_THREE("23");

    private final String displayText;

    TaxValue(String displayText) {
        this.displayText = displayText;
    }

    public String getDisplayText() {
        return displayText;
    }
}
