package com.knowit.satyam.poiboot.utils;

import com.knowit.satyam.poiboot.annotation.ExcelColumn;
import com.knowit.satyam.poiboot.annotation.ExcelData;
import org.apache.poi.hssf.usermodel.HSSFFont;

import java.time.LocalDate;

@ExcelData(headerBold = false, headerFontType = HSSFFont.FONT_ARIAL)
public class Person {

    @ExcelColumn(name = "Name", colNum = 1)
    private String name;

    @ExcelColumn(name = "Address", colNum = 2)
    private String address;

    @ExcelColumn(name = "DOB", colNum = 3, formatter = "yyyy-dd-MM")
    private LocalDate dateOfBirth;

    @ExcelColumn(name = "salary", colNum = 4, formatter = "#,##0.000")
    private Double salary;

    public Person(String name, String address, LocalDate dateOfBirth, Double salary) {
        this.name = name;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.salary = salary;
    }

}
