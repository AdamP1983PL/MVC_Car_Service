package com.carservice.customer.model.enums;


public enum PaymentMethod {
    CASH("cash"),
    THREE_DAY_TRANSFER("3 day transfer"),
    SEVEN_DAY_TRANSFER("7 day transfer"),
    FOURTEEN_DAY_TRANSFER("14 day transfer"),
    BY_CARD("by card"),
    CONFIRMED_TRANSFER("confirmed transfer");

    private final String displayText;

    PaymentMethod(String displayText) {
        this.displayText = displayText;
    }

    public String getDisplayText() {
        return displayText;
    }
}
