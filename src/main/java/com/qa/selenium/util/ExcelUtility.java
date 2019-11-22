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
    private static String xlsFilePath;


    public ExcelUtility(String excelFilePath) {
        this.xlsFilePath = excelFilePath;
    }

    // Apache POI API Reference : https://poi.apache.org/apidocs/dev/index.html?org/apache



    /**
     * Load Excel File With Work Book
     * @param sheetName Sheet Name of Excel Work Book To Read
     */
    public void LoadExcelFileForRead(String sheetName) {

        try {

            // Load File as Input Stream
            FileInputStream xlsInputStream = new FileInputStream(xlsFilePath);

            // Load WorkBook
            xBook = new XSSFWorkbook(xlsInputStream);

            // Load Sheet
            xSheet = xBook.getSheet(sheetName);


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Write Data To Excel.
     */
    public void WriteDataToExcel() {

        try {

            // File output stream
            FileOutputStream fOut = new FileOutputStream(xlsFilePath);

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

    /**
     * Get Number Of Rows In Excel
     * @return Number Of Rows
     */
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

    /**
     * Get Cell Data Based on Row & Column Index.
     * @param rowNum Row Index
     * @param colNum Column Index
     * @return Cell Value
     */
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

    /**
     * Get Excel Data As A Multi Dimensional Array.
     * @return Excel data as String Array
     */
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

    /**
     * Set Cell Data
     * @param rowNum Row Index
     * @param colNum Column Index
     * @param cellValue Value to be saved in Cell
     */
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

    /**
     * Checks if the value of a given {@link XSSFCell} is empty.
     *
     * @param cell
     *            The {@link XSSFCell}.
     * @return {@code true} if the {@link XSSFCell} is empty. {@code false}
     *         otherwise.
     */
    public static boolean isCellEmpty(final XSSFCell cell) {
        if (cell == null) { // use row.getCell(x, Row.CREATE_NULL_AS_BLANK) to avoid null cells
            return true;
        }

        if (cell.getCellType() == CellType.BLANK) {
            return true;
        }

        if (cell.getCellType() == CellType.STRING && cell.getStringCellValue().trim().isEmpty()) {
            return true;
        }

        return false;
    }
}
