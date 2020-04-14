package com.knowit.satyam.poiboot.utils;

import java.lang.reflect.Field;

public class ColumnMetaData {
    private Field field;
    private String name;
    private String orderNo;
    private String formatter;
    private Object type;

    public ColumnMetaData(Field field, String name, String orderNo, String formatter, Object type) {
        this.field = field;
        this.name = name;
        this.orderNo = orderNo;
        this.formatter = formatter;
        this.type = type;
    }

}
