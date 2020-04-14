package com.knowit.satyam.poiboot.utils;

import com.knowit.satyam.poiboot.annotation.ExcelColumn;

import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReflectionUtil {
    public static List<Field> getOrderedFields(final Object object) {
        return Stream.of(object.getClass().getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(ExcelColumn.class))
                .sorted(Comparator.comparingInt(f -> f.getAnnotation(ExcelColumn.class).colNum()))
                .collect(Collectors.toList());
    }
}