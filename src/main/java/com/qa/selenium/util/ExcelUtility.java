package com.qa.selenium.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
    private static XSSFWorkbook xBook;
    private static XSSFSheet xSheet;
    private static XSSFRow xRow;
    private static XSSFCell xCell;

    public void LoadExcelFileForRead(String excelFilePath, String sheetName) {

        try {

            // Load File as Input Stream
            FileInputStream xlsInputStream = new FileInputStream(excelFilePath);

            // Load WorkBook
            xBook = new XSSFWorkbook(xlsInputStream);

            // Load Sheet
            xSheet = xBook.getSheet(sheetName);


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void LoadExcelFileForWrite(String excelFilePath) {

        try {

            // File output stream
            FileOutputStream fOut = new FileOutputStream(excelFilePath);

            // Write Data to XSSFBook
            xBook.write(fOut);

            // Flush Data
            fOut.flush();

            // Close Output stream
            fOut.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public int GetTotalRows() {
        try {

            // Get total rows
            int totalRows = xSheet.getLastRowNum();

            return totalRows;

        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public String GetCellData(int rowNum, int colNum) {
        try {

            // Get Cell
            xCell = xSheet.getRow(rowNum).getCell(colNum);

            // Get Cell value as String
            String cellValue = xCell.getStringCellValue();

            return cellValue;


        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return "";
    }

    public String[][] GetExcelDataAsArray() {
        try {

            // Total Rows
            int totalRows = GetTotalRows();

            // Total Col
            int totalCols = xSheet.getRow(0).getLastCellNum();

            // Store Excel Values in Array
            String [][] excelData = new String[totalRows][totalCols];

            for(int i=1; i<=totalRows; i++) {

                for(int j=0; j< totalCols; j++) {

                    // Get Cell
                    xCell = xSheet.getRow(i).getCell(j);


                    if(xCell != null && xCell.getCellType() == CellType.STRING) {

                        String cellValue = xCell.getStringCellValue();
                        excelData[i-1][j] = cellValue;

                    } else if(xCell != null && xCell.getCellType() == CellType.NUMERIC)  {

                        String cellValue = String.valueOf(xCell.getNumericCellValue());
                        excelData[i-1][j] = cellValue;

                    } else if(xCell != null && xCell.getCellType() == CellType.BOOLEAN) {

                        String cellValue = String.valueOf(xCell.getBooleanCellValue());
                        excelData[i-1][j] = cellValue;
                    } else  {
                        excelData[i-1][j] = null;
                    }
                }
            }
            return excelData;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void SetCellData(int rowNum, int colNum, String cellValue) {
        try {

            // Get Row
            xRow = xSheet.getRow(rowNum);

            // Get Cell
            xCell = xRow.getCell(colNum, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);

            // Check if cell is null
            if(xCell == null) {
                xCell = xRow.createCell(colNum);
                xCell.setCellValue(cellValue);
            } else {
                xCell.setCellValue(cellValue);
            }

            // Write Data to file

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
