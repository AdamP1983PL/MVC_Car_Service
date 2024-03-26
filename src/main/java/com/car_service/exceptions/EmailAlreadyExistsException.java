package com.car_service.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EmailAlreadyExistsException extends RuntimeException {
    private String message;
    private String fieldName;
    private String fieldValue;

    public EmailAlreadyExistsException(String message) {
        super(message);
        log.info("====>>>> EmailAlreadyExistsException() execution.");
    }

    public EmailAlreadyExistsException(String fieldName, String fieldValue) {
        super(String.format("%s already exists for %s, email must be unique.", fieldName, fieldValue));
        log.info("====>>>> EmailAlreadyExistsException() execution.");
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
