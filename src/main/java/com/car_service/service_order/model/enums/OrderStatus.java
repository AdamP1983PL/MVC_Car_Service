package com.car_service.service_order.model.enums;

import lombok.Getter;

@Getter
public enum OrderStatus {
    OPEN("open"),
    IN_PROGRESS("in progress"),
    FOR_INVOICING("for invoicing"),
    INVOICED("invoiced");

    private final String displayText;

    OrderStatus(String displayText) {
        this.displayText = displayText;
    }

}
