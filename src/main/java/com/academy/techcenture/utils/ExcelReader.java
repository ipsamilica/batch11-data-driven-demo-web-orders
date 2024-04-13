package com.academy.techcenture.utils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tairovich_jr on 2022-01-27.
 */
public class ExcelReader {

    private XSSFWorkbook workbook; //think of workbook as an entire excel file
    private XSSFSheet worksheet; //represents a particular sheet of an excel file

    public ExcelReader (String filePath, String sheetName){

        File file = new File(filePath); //create a java file object
        try {
            FileInputStream fis = new FileInputStream(file); //file input stream object
            workbook = new XSSFWorkbook(fis);
            worksheet = workbook.getSheet(sheetName);
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object[][] getData(){
        int rows = worksheet.getLastRowNum();
        int cols = worksheet.getRow(0).getLastCellNum();

        // Count the number of rows with "yes" in the "run" column
        int validRowCount = 0;
        for (int i = 1; i <= rows; i++) {
            XSSFCell runCell = worksheet.getRow(i).getCell(0);
            if (runCell != null && "yes".equalsIgnoreCase(runCell.toString())) {
                validRowCount++;
            }
        }

        // Create a data array with the valid row count
        Object[][] data = new Object[validRowCount][1];

        int dataIndex = 0;
        for (int i = 1; i <= rows; i++) {
            XSSFCell runCell = worksheet.getRow(i).getCell(0);
            if (runCell != null && "yes".equalsIgnoreCase(runCell.toString())) {
                Map<String, String> map = new HashMap<>();
                for (int j = 0; j < cols; j++) {
                    XSSFCell cell = worksheet.getRow(i).getCell(j);
                    map.put(worksheet.getRow(0).getCell(j).toString(),
                            cell == null ? "" : cell.toString());
                }
                data[dataIndex][0] = map;
                dataIndex++;
            }
        }
        return data;
    }
}