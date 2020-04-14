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

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public Object getType() {
        return type;
    }

    public void setType(Object type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getFormatter() {
        return formatter;
    }

    public void setFormatter(String formatter) {
        this.formatter = formatter;
    }
}
