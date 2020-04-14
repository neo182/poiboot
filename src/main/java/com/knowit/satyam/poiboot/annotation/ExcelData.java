package com.knowit.satyam.poiboot.annotation;

import org.apache.poi.hssf.usermodel.HSSFFont;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ExcelData {
    boolean headerBold() default true;

    boolean headerItalic() default false;

    boolean headerUnderline() default false;

    /**
     * N.B. Use constants defined in HSSFFont
     *
     * @return
     */
    String headerFontType() default HSSFFont.FONT_ARIAL;
}
