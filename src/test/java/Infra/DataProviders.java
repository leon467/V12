package Infra;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import java.io.FileInputStream;
import java.io.IOException;

public class DataProviders
{
    @DataProvider(name = "loginCredentials")
    public Object[][] getLoginData() throws IOException {
        FileInputStream fis = new FileInputStream("../V12/src/test/resources/TestData.xlsm");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(1);

        int rows = sheet.getLastRowNum();
        int cols = sheet.getRow(0).getLastCellNum();

        Object[][] data = new Object[rows][cols];
        for (int i = 1; i <= rows; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                data[i-1][j] = sheet.getRow(i).getCell(j).toString();
            }
        }

        workbook.close();
        fis.close();

        return data;
    }

    @DataProvider(name = "getSecretAnswer")
    public Object[] getSecretAnswer() throws IOException {
        FileInputStream fis = new FileInputStream("../V12/src/test/resources/TestData.xlsm");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(2);

        int rows = sheet.getLastRowNum();
        int cols = sheet.getRow(0).getLastCellNum();

        Object[][] data = new Object[rows][cols];
        for (int i = 1; i <= rows; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                data[i-1][j] = sheet.getRow(i).getCell(j).toString();
            }
        }

        workbook.close();
        fis.close();

        return data;
    }

    @DataProvider(name = "resetPassDetails")
    public Object[][] getResetPassDetails() throws IOException {
        FileInputStream fis = new FileInputStream("../V12/src/test/resources/TestData.xlsm");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(3);

        int rows = sheet.getLastRowNum();
        int cols = sheet.getRow(0).getLastCellNum();

        Object[][] data = new Object[rows][cols];
        for (int i = 1; i <= rows; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                data[i-1][j] = sheet.getRow(i).getCell(j).toString();
            }
        }

        workbook.close();
        fis.close();

        return data;
    }

    @DataProvider(name = "newPassInvalidCharLimit")
    public Object[][] getInvalidCharLimitNewPass() throws IOException {
        FileInputStream fis = new FileInputStream("../V12/src/test/resources/TestData.xlsm");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(4);

        int rows = sheet.getLastRowNum();

        Object[][] data = new Object[rows][1]; // Changed the column count to 1

        for (int i = 1; i <= rows; i++) {
            data[i-1][0] = sheet.getRow(i).getCell(0).toString(); // Retrieve only the first column
        }

        workbook.close();
        fis.close();

        return data;
    }

    @DataProvider(name = "newPassExcludingCapitals")
    public Object[][] getNewPassExcludingCapitals() throws IOException {
        FileInputStream fis = new FileInputStream("../V12/src/test/resources/TestData.xlsm");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(4);

        int rows = sheet.getLastRowNum();

        Object[][] data = new Object[rows][1]; // Changed the column count to 1

        for (int i = 1; i <= rows; i++) {
            data[i-1][0] = sheet.getRow(i).getCell(1).toString(); // Retrieve only the second column (index 1)
        }

        workbook.close();
        fis.close();

        return data;
    }

    @DataProvider(name = "newPassExcludingSimples")
    public Object[][] getNewPassExcludingSimples() throws IOException {
        FileInputStream fis = new FileInputStream("../V12/src/test/resources/TestData.xlsm");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(4);

        int rows = sheet.getLastRowNum();

        Object[][] data = new Object[rows][1]; // Changed the column count to 1

        for (int i = 1; i <= rows; i++) {
            data[i-1][0] = sheet.getRow(i).getCell(2).toString(); // Retrieve only the 3rd column (index 1)
        }

        workbook.close();
        fis.close();

        return data;
    }

    @DataProvider(name = "newPassExcludingNumbers")
    public Object[][] getNewPassExcludingNumbers() throws IOException {
        FileInputStream fis = new FileInputStream("../V12/src/test/resources/TestData.xlsm");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(4);

        int rows = sheet.getLastRowNum();

        Object[][] data = new Object[rows][1]; // Changed the column count to 1

        for (int i = 1; i <= rows; i++) {
            data[i-1][0] = sheet.getRow(i).getCell(3).toString(); // Retrieve only the 4rd column (index 1)
        }

        workbook.close();
        fis.close();

        return data;
    }

    @DataProvider(name = "newPassExcludingSymbols")
    public Object[][] getNewPassExcludingSymbols() throws IOException {
        FileInputStream fis = new FileInputStream("../V12/src/test/resources/TestData.xlsm");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(4);

        int rows = sheet.getLastRowNum();

        Object[][] data = new Object[rows][1]; // Changed the column count to 1

        for (int i = 1; i <= rows; i++) {
            data[i-1][0] = sheet.getRow(i).getCell(4).toString(); // Retrieve only the 5th column (index 1)
        }

        workbook.close();
        fis.close();

        return data;
    }

    @DataProvider(name = "passMismatchDetails")
    public Object[][] getMismatchPassDetails() throws IOException {
        FileInputStream fis = new FileInputStream("../V12/src/test/resources/TestData.xlsm");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(5);

        int rows = sheet.getLastRowNum();
        int cols = sheet.getRow(0).getLastCellNum();

        Object[][] data = new Object[rows][cols];
        for (int i = 1; i <= rows; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                data[i-1][j] = sheet.getRow(i).getCell(j).toString();
            }
        }

        workbook.close();
        fis.close();

        return data;
    }

    @DataProvider(name = "correctResetPassDetails")
    public Object[][] getCorrectResetPassDetails() throws IOException {
        FileInputStream fis = new FileInputStream("../V12/src/test/resources/TestData.xlsm");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(6);

        int rows = sheet.getLastRowNum();
        int cols = sheet.getRow(0).getLastCellNum();

        Object[][] data = new Object[rows][cols];
        for (int i = 1; i <= rows; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                data[i-1][j] = sheet.getRow(i).getCell(j).toString();
            }
        }

        workbook.close();
        fis.close();

        return data;
    }
}
