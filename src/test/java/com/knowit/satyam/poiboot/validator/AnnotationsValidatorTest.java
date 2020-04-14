package com.knowit.satyam.poiboot.validator;

import com.knowit.satyam.poiboot.annotation.ExcelColumn;
import com.knowit.satyam.poiboot.annotation.ExcelData;
import org.junit.jupiter.api.Test;
import org.springframework.util.CollectionUtils;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AnnotationsValidatorTest {

    class ClassWithoutClassAnnotation {
        @ExcelColumn
        String name;
    }

    @ExcelData
    class ClassWithoutAnyFieldAnnotation {
        String name;
    }

    @ExcelData
    class ValidClass {
        @ExcelColumn
        String name;
    }

    @Test
    void testValidate_ClassWithoutClassAnnotation() {
        AnnotationsValidator validator = new AnnotationsValidator(ClassWithoutClassAnnotation.class);
        boolean validity = validator.validate();
        assertThat(validity).isFalse();
    }

    @Test
    void testValidate_ClassWithoutFieldAnnotation() {
        AnnotationsValidator validator = new AnnotationsValidator(ClassWithoutAnyFieldAnnotation.class);
        boolean validity = validator.validate();
        assertThat(validity).isFalse();
    }

    @Test
    void testValidate_ValidClass() {
        AnnotationsValidator validator = new AnnotationsValidator(ValidClass.class);
        boolean validity = validator.validate();
        assertThat(validity).isTrue();
    }

    @Test
    void testGetError_ClassWithoutClassAnnotation() {
        AnnotationsValidator validator = new AnnotationsValidator(ClassWithoutAnyFieldAnnotation.class);
        boolean validity = validator.validate();
        assertThat(validity).isFalse();

        List<String> errors = validator.getErrors();
        assertThat(!CollectionUtils.isEmpty(errors));
        assertThat(errors.stream().anyMatch(s -> s.equals("No field in Class " + ClassWithoutAnyFieldAnnotation.class.getSimpleName() + " contains @ExcelColumn!")));
    }

    @Test
    void testGetError_ValidClass() {
        AnnotationsValidator validator = new AnnotationsValidator(ValidClass.class);
        validator.validate();

        List<String> errors = validator.getErrors();
        assertThat(CollectionUtils.isEmpty(errors));
    }
}