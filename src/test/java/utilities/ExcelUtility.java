package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

    private String excelPath;
    private Workbook workbook;

    public ExcelUtility(String excelPath) {
        this.excelPath = excelPath;
        try {
            FileInputStream fis = new FileInputStream(excelPath);
            workbook = new XSSFWorkbook(fis);
            fis.close();
        } catch (IOException e) {
            throw new RuntimeException("Could not open Excel file: " + e.getMessage());
        }
    }

    // ======================================
    // GET ROW COUNT
    // ======================================
    public int getRowCount(String sheetName) {
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) throw new RuntimeException("Sheet not found: " + sheetName);

        return sheet.getLastRowNum() + 1; // total rows including header
    }

    // ======================================
    // GET COLUMN COUNT of FIRST ROW
    // ======================================
    public int getColumnCount(String sheetName) {
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) throw new RuntimeException("Sheet not found: " + sheetName);

        Row row = sheet.getRow(0);
        if (row == null) return 0;

        return row.getLastCellNum();
    }

    // ======================================
    // GET CELL COUNT OF SPECIFIC ROW
    // ======================================
    public int getCellCount(String sheetName, int rowIndex) {
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) throw new RuntimeException("Sheet not found: " + sheetName);

        Row row = sheet.getRow(rowIndex);
        if (row == null) return 0;

        return row.getLastCellNum();
    }

    // ======================================
    // NEW METHOD: GET CELL DATA (Safe + Clean)
    // ======================================
    public String getCellData(String sheetName, int rowNum, int colNum) {
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) throw new RuntimeException("Sheet not found: " + sheetName);

        Row row = sheet.getRow(rowNum);
        if (row == null) return "";

        Cell cell = row.getCell(colNum);
        if (cell == null) return "";

        DataFormatter formatter = new DataFormatter();
        return formatter.formatCellValue(cell);
    }

    // ======================================
    // READ CELL (Alias)
    // ======================================
    public String readCell(String sheetName, int rowNum, int colNum) {
        return getCellData(sheetName, rowNum, colNum);
    }

    // ======================================
    // WRITE CELL
    // ======================================
    public void writeCell(String sheetName, int rowNum, int colNum, String value) {
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) sheet = workbook.createSheet(sheetName);

        Row row = sheet.getRow(rowNum);
        if (row == null) row = sheet.createRow(rowNum);

        Cell cell = row.getCell(colNum);
        if (cell == null) cell = row.createCell(colNum);

        cell.setCellValue(value);
        save();
    }

    // ======================================
    // READ ENTIRE SHEET INTO 2D ARRAY
    // ======================================
    public String[][] readSheet(String sheetName) {
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) throw new RuntimeException("Sheet not found: " + sheetName);

        int totalRows = getRowCount(sheetName);
        int totalCols = getColumnCount(sheetName);

        String[][] data = new String[totalRows][totalCols];
        DataFormatter formatter = new DataFormatter();

        for (int i = 0; i < totalRows; i++) {
            Row row = sheet.getRow(i);

            for (int j = 0; j < totalCols; j++) {
                Cell cell = (row == null) ? null : row.getCell(j);
                data[i][j] = (cell == null) ? "" : formatter.formatCellValue(cell);
            }
        }

        return data;
    }

    // ======================================
    // SAVE EXCEL FILE
    // ======================================
    private void save() {
        try {
            FileOutputStream fos = new FileOutputStream(excelPath);
            workbook.write(fos);
            fos.close();
        } catch (IOException e) {
            throw new RuntimeException("Could not save Excel file: " + e.getMessage());
        }
    }

    // ======================================
    // CLOSE WORKBOOK
    // ======================================
    public void close() {
        try {
            workbook.close();
        } catch (IOException e) {
            throw new RuntimeException("Could not close Excel file: " + e.getMessage());
        }
    }
}
