package com.knowit.satyam.poiboot.validator;

import java.util.List;

public class ValidationException extends RuntimeException {
    List<String> errors;

    public ValidationException(List<String> errors) {
        this.errors = errors;
    }
}
