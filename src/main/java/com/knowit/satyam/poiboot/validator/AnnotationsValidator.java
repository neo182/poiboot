package com.knowit.satyam.poiboot.validator;

import com.knowit.satyam.poiboot.annotation.ExcelColumn;
import com.knowit.satyam.poiboot.annotation.ExcelData;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class AnnotationsValidator implements Validate {
    private Class clazz;
    private List<String> errors = new ArrayList<>();

    public AnnotationsValidator(Class clazz) {
        this.clazz = clazz;
    }

    @Override
    public boolean validate() {
        if (!clazz.isAnnotationPresent(ExcelData.class)) {
            errors.add("Class " + clazz.getSimpleName() + " is not annotated with @ExcelData.");
            return false;
        }

        int totalNoOfField = clazz.getDeclaredFields().length;
        if (totalNoOfField == 0) {
            errors.add("Class " + clazz.getSimpleName() + " does not contain any field!");
            return false;
        }

        long noOfFieldsWithAnnotation = Stream.of(clazz.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(ExcelColumn.class))
                .count();
        if (noOfFieldsWithAnnotation == 0) {
            errors.add("No field in Class " + clazz.getSimpleName() + " contains @ExcelColumn!");
            return false;
        }


        return errors.size() == 0;
    }

    @Override
    public List<String> getErrors() {
        return errors;
    }
}
