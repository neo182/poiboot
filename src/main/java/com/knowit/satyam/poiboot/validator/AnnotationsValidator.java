package com.knowit.satyam.poiboot.validator;

import com.knowit.satyam.poiboot.annotation.ExcelColumn;
import com.knowit.satyam.poiboot.annotation.ExcelData;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class AnnotationsValidator implements Validate {
    private Object obj;
    private List<String> errors = new ArrayList<>();

    @Override
    public boolean validate() {
        if (!obj.getClass().isAnnotationPresent(ExcelData.class)) {
            errors.add("Class " + obj.getClass() + " is not annotated with @ExcelData.");
        }

        Stream.of(obj.getClass().getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(ExcelColumn.class))
                .forEach(f -> errors.add("Field " + f.getName() + " is not annoted with @ExcelColumn"));

        return !errors.isEmpty();
    }

    @Override
    public String getErrors() {
        return errors.stream()
                .collect(Collectors.joining("/n"));
    }

    public static void main(String[] args) {
        AnnotationsValidator validator = new AnnotationsValidator();

        List<String> orderColumnNames = Stream.of(validator.getClass().getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(ExcelColumn.class))
                .sorted((f1, f2) -> f1.getAnnotation(ExcelColumn.class).colNum()
                        - f2.getAnnotation(ExcelColumn.class).colNum())
                .map(f -> f.getAnnotation(ExcelColumn.class).name())
                .collect(Collectors.toCollection(ArrayList::new));

        orderColumnNames.forEach(System.out::println);
    }
}
