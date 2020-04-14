package com.knowit.satyam.poiboot.utils;

import com.knowit.satyam.poiboot.annotation.ExcelColumn;
import com.knowit.satyam.poiboot.annotation.ExcelData;
import com.knowit.satyam.poiboot.validator.AnnotationsValidator;
import com.knowit.satyam.poiboot.validator.ValidationException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.List;

public class ExcelWriter {
    public static void writeTo(List<? extends Object> list, String fileName) throws ValidationException {
        Object excelDataObject = list.get(0);

        AnnotationsValidator validator = new AnnotationsValidator(excelDataObject.getClass());
        if (!validator.validate()) {
            List<String> errors = validator.getErrors();
            throw new ValidationException(errors);
        }

        List<Field> fieldList = ReflectionUtil.getOrderedFields(excelDataObject);

        try (FileOutputStream outputStream = new FileOutputStream(new File(fileName));
             Workbook workbook = new HSSFWorkbook();) {

            Sheet sheet = workbook.createSheet("Date Formats");
            CreationHelper creationHelper = workbook.getCreationHelper();
            createHeader(workbook, sheet, fieldList, excelDataObject);

            for (int rowNum = 0; rowNum < list.size(); rowNum++) {
                Row row = sheet.createRow(rowNum + 1);

                for (int colIndex = 0; colIndex < fieldList.size(); colIndex++) {
                    Field field = fieldList.get(colIndex);
                    ExcelColumn column = field.getAnnotation(ExcelColumn.class);
                    fieldList.get(colIndex).setAccessible(true);
                    final Object value = fieldList.get(colIndex).get(list.get(rowNum));
                    Cell cell = row.createCell(colIndex);
                    String formatter = column.formatter();

                    if (formatter != null || formatter != "") {
                        CellStyle dataStyle = workbook.createCellStyle();
                        dataStyle.setDataFormat(creationHelper.createDataFormat().getFormat(formatter));
                        cell.setCellStyle(dataStyle);
                    }

                    if (value instanceof LocalDate) {
                        cell.setCellValue((LocalDate) value);
                    } else if (value instanceof Double) {
                        cell.setCellValue((Double) value);
                    } else if (value instanceof Long) {
                        cell.setCellValue((Long) value);
                    } else if (value instanceof Integer) {
                        cell.setCellValue((Integer) value);
                    } else {
                        cell.setCellValue(value.toString());
                    }
                }
            }
            workbook.write(outputStream);
            System.out.println("Excel File created and written !!!!");
        } catch (Exception e) {
            System.out.println("An Exception occured while writing Excel" + e);
        }
    }

    private static void createHeader(Workbook workbook, Sheet sheet, List<Field> fieldList, Object excelDataObj) {
        ExcelData excelData = excelDataObj.getClass().getAnnotation(ExcelData.class);
        boolean isBold = excelData.headerBold();
        boolean isItalic = excelData.headerItalic();
        boolean isUnderlined = excelData.headerUnderline();
        String fontName = excelData.headerFontType();

        Row row = sheet.createRow(0);
        for (int colIndex = 0; colIndex < fieldList.size(); colIndex++) {
            Cell cell = row.createCell(colIndex);
            ExcelColumn column = fieldList.get(colIndex).getAnnotation(ExcelColumn.class);
            cell.setCellValue(column.name());

            CellStyle style = workbook.createCellStyle();
            style.setBorderTop(BorderStyle.DOUBLE);
            style.setBorderBottom(BorderStyle.THIN);
            style.setFillForegroundColor(HSSFColor.HSSFColorPredefined.GREY_25_PERCENT.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            Font font = workbook.createFont();
            font.setFontName(fontName);
            font.setBold(isBold);
            font.setUnderline(isUnderlined ? Font.U_DOUBLE : Font.U_NONE);
            font.setItalic(isItalic);
            font.setColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
            style.setFont(font);

            // Setting cell style
            cell.setCellStyle(style);
        }
    }
}
