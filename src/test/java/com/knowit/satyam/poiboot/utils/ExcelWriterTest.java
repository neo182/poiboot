package com.knowit.satyam.poiboot.utils;

import static org.assertj.core.api.Assertions.*;

import com.knowit.satyam.poiboot.annotation.ExcelData;
import com.knowit.satyam.poiboot.validator.ValidationException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

class ExcelWriterTest {

    private Person person1 = new Person("Ola", "Oslo", LocalDate.of(2000, 01, 01), 1002323.3232d);
    private Person person2 = new Person("Tor", "Trondheim", LocalDate.of(2010, 01, 01), 2002323.2323d);

    @Test
    public void testWriteToFile() {
        List<Person> list = Arrays.asList(person1, person2);
        ExcelWriter.writeTo(list, "/tmp/test.xlsx");
    }

    @ExcelData
    class InvaliClass {
        String name = "test";
    }

    @Test
    public void testWriteToFile_InvalidClass() {
        assertThatThrownBy(()-> {
            List<InvaliClass> list = Arrays.asList(new InvaliClass());
            ExcelWriter.writeTo(list, "/tmp/test.xlsx");
        }).isInstanceOf(ValidationException.class);
    }
}