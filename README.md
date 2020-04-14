# poiboot
A demo project to encapsulate the mundane tasks in the use of poi library

### Simple usage

```
// Step 1. Define a class to be written into file (.csv, or .xlsx file) using proper annotations
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

// Step 2. Use a simple method into the file
ExcelWriter.writeTo(list, "/tmp/test.xlsx");

```
