package com.knowit.satyam.poiboot.validator;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ExcelValidator implements Validate {
    private Object obj;
    private List<String> errors;

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public String getErrors() {
        return null;
    }
}
