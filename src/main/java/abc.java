package seleniumWentBrr;

import org.apache.hc.core5.http.io.SessionOutputBuffer;
import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * Literally a useless class that was used for testing, so we could yoink the code to the selenium class hehe
 */
public class abc {
    protected static ArrayList<String> fullDatas = new ArrayList<>();
    protected static ArrayList<String> employeeFirstNames = new ArrayList<>();
    protected static ArrayList<String> employeeLastNames = new ArrayList<>();
    protected static ArrayList<String> employeeEmails = new ArrayList<>();
    protected static ArrayList<String> employeePrevJobTitles = new ArrayList<>();
    protected static ArrayList<String> employeePrevJobCompanyNames = new ArrayList<>();
    protected static ArrayList<String> employeePrevJobStartDates = new ArrayList<>();
    protected static ArrayList<String> employeePrevJobEndDates = new ArrayList<>();
    protected static ArrayList<String> refTitles = new ArrayList<>();
    protected static ArrayList<String> refFirstNames = new ArrayList<>();
    protected static ArrayList<String> refLastNames = new ArrayList<>();
    protected static ArrayList<String> refEmails = new ArrayList<>();

    public static void main(String args[]) throws IOException {
        FileInputStream fis = new FileInputStream(new File("C://Users//patri//Desktop//tsiEmployeeInfo.xlsx"));
        XSSFWorkbook workbook = new XSSFWorkbook(fis); // XSSFWorkbook for .xlsx file
        XSSFSheet sheet = workbook.getSheetAt(1); // open sheet 1

        Iterator<Row> rowIterator = sheet.iterator();

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            if (row.getRowNum() != 0) {
                Iterator cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {
                    Cell cell = (Cell) cellIterator.next();
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    if (!cell.getStringCellValue().isEmpty()) {
                        if (!cell.getStringCellValue().equals("0")) {
                            if (!cell.getStringCellValue().equals(",")) {
                                fullDatas.add(cell.getStringCellValue());
                            }
                        }
                    }
                }
            }
        }
        for (int x = 0; x < (fullDatas.size()); x++) {
            int y = x + 11;
            if (y % 11 == 0) {
                employeeFirstNames.add(fullDatas.get(x));
            } else if (y % 11 == 1) {
                employeeLastNames.add(fullDatas.get(x));
            } else if (y % 11 == 2) {
                employeeEmails.add(fullDatas.get(x));
            } else if (y % 11 == 3) {
                employeePrevJobTitles.add(fullDatas.get(x));
            } else if (y % 11 == 4) {
                employeePrevJobCompanyNames.add(fullDatas.get(x));
            } else if (y % 11 == 5) {
                employeePrevJobStartDates.add(fullDatas.get(x));
            } else if (y % 11 == 6) {
                employeePrevJobEndDates.add(fullDatas.get(x));
            } else if (y % 11 == 7) {
                refTitles.add(fullDatas.get(x));
            } else if (y % 11 == 8) {
                refFirstNames.add(fullDatas.get(x));
            } else if (y % 11 == 9) {
                refLastNames.add(fullDatas.get(x));
            } else if (y % 11 == 10) {
                refEmails.add(fullDatas.get(x));
            }
        }

        for (int x = 0; x < employeeFirstNames.size(); x++) {
            System.out.println(employeeFirstNames.get(x));
            System.out.println(employeeLastNames.get(x));
            System.out.println(employeeEmails.get(x));
            System.out.println(employeePrevJobTitles.get(x));
            System.out.println(employeePrevJobCompanyNames.get(x));
            System.out.println(employeePrevJobStartDates.get(x));
            System.out.println(employeePrevJobEndDates.get(x));
            System.out.println(refTitles.get(x));
            System.out.println(refFirstNames.get(x));
            System.out.println(refLastNames.get(x));
            System.out.println(refEmails.get(x));
            System.out.println();
        }
    }
}
