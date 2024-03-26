package com.carservice.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class TaxNumberAlreadyExistsException extends RuntimeException {
    private String message;
    private String fieldName;
    private String fieldValue;

    public TaxNumberAlreadyExistsException(String message) {
        super(message);
        log.info("====>>>> TaxNumberAlreadyExistsException() execution.");
    }

    public TaxNumberAlreadyExistsException(String fieldName, String fieldValue) {
        super(String.format("%s already exists for: %s", fieldName, fieldValue));
        log.info("====>>>> TaxNumberAlreadyExistsException() execution.");
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
