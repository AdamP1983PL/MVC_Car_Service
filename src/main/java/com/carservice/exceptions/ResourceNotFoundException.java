package com.carservice.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    private String message;
    private String resourceName;
    private String fieldName;
    private Long fieldValue;

    public ResourceNotFoundException(String message) {
        super(message);
        log.info("====>>>> ResourceNotFoundException() execution");
        this.message = message;
    }

    public ResourceNotFoundException(String resourceName, String fieldName) {
        super(String.format("%s not found for: %s", resourceName, fieldName));
        log.info("====>>>> ResourceNotFoundException() execution");
        this.resourceName = resourceName;
        this.fieldName = fieldName;
    }

    public ResourceNotFoundException(String resourceName, String fieldName, Long fieldValue) {
        super(String.format("%s not found for %s : %s", resourceName, fieldName, fieldValue));
        log.info("====>>>> ResourceNotFoundException() execution");
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

}
