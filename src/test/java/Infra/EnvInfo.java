package Infra;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class EnvInfo
{
    public static String getEnv()
    {
        String env = "";

        try {
            //Reading the 1st sheet of the Excel to get the environment which is in the 1st col 1st row
            FileInputStream fis = new FileInputStream("../V12/src/test/resources/TestData.xlsm");
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheetAt(0);

            int columnIndex = 0;

            Cell cell = sheet.getRow(1).getCell(columnIndex);
            if (cell != null)
            {
                env = cell.getStringCellValue();
            }

            workbook.close();
            fis.close();

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return env;
    }

    public static String getUrl()
    {
        String url = "";

        try {
            //Reading the 1st sheet of the Excel to get the URL which is in the 2nd col 1st row
            FileInputStream fis = new FileInputStream("../V12/src/test/resources/TestData.xlsm");
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheetAt(0);

            int columnIndex = 1;

            Cell cell = sheet.getRow(1).getCell(columnIndex);
            if (cell != null)
            {
                url = cell.getStringCellValue();
            }

            workbook.close();
            fis.close();

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return url;
    }

    public static String getHomePageUrl()
    {
        String url = "";

        try {
            //Reading the 1st sheet of the Excel to get the home page URL which is in the 4th col 1st row
            FileInputStream fis = new FileInputStream("../V12/src/test/resources/TestData.xlsm");
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheetAt(0);

            int columnIndex = 3;

            Cell cell = sheet.getRow(1).getCell(columnIndex);
            if (cell != null)
            {
                url = cell.getStringCellValue();
            }

            workbook.close();
            fis.close();

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return url;
    }

    public static String getSecretQuestionPageURL()
    {
        String url = "";

        try {
            //Reading the 1st sheet of the Excel to get the Secret Question page URL which is in the 5th col 1st row
            FileInputStream fis = new FileInputStream("../V12/src/test/resources/TestData.xlsm");
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheetAt(0);

            int columnIndex = 4;

            Cell cell = sheet.getRow(1).getCell(columnIndex);
            if (cell != null)
            {
                url = cell.getStringCellValue();
            }

            workbook.close();
            fis.close();

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return url;
    }

    public static String getNewPasswordMailURL()
    {
        String title = "";

        try {
            //Reading the 3rd sheet of the Excel to get the URL which is in the 2nd col 1st row
            FileInputStream fis = new FileInputStream("../V12/src/test/resources/TestData.xlsm");
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheetAt(2);

            int columnIndex = 1;

            Cell cell = sheet.getRow(1).getCell(columnIndex);
            if (cell != null)
            {
                title = cell.getStringCellValue();
            }

            workbook.close();
            fis.close();

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return title;
    }

}
