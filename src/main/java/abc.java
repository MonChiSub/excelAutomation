import org.apache.hc.core5.http.io.SessionOutputBuffer;
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
    public static String employeeFirstName = "";
    public static String employeeLastName = "";
    public static String employeePrevJobTitle = "";
    public static String employeePrevJobCompanyName = "";
    public static String employeePrevJobStartDate = "";
    public static String employeePrevJobEndDate = "";
    public static String refTitle = "";
    public static String refFirstName = "";
    public static String refLastName = "";
    public static String refEmail = "";
    public static ArrayList<String> testArray = new ArrayList<>();

    public static void main(String args[]) throws IOException {
        FileInputStream fis = new FileInputStream(new File("C://Users//patri//Desktop//tsiEmployeeInfo.xlsx"));
        XSSFWorkbook workbook = new XSSFWorkbook(fis); // XSSFWorkbook for .xlsx file
        XSSFSheet sheet = workbook.getSheetAt(1); // open sheet 1

        Iterator<Row> rowIterator = sheet.iterator();

        // Traversing over each row of XLSX file
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            if (row.getRowNum() == 1) // skip title row
            {
                Iterator cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = (Cell) cellIterator.next();
                    testArray.add(cell.getStringCellValue());
                }

                for(int x = 0; x < testArray.size(); x++) {
                    testArray.get(x);
                    switch (x) {
                        default:
                            break;
                        case 0:
                            employeeFirstName = testArray.get(x);
                            break;
                        case 1:
                            employeeLastName = testArray.get(x);
                            break;
                        case 3:
                            employeePrevJobTitle = testArray.get(x);
                            break;
                        case 4:
                            employeePrevJobCompanyName= testArray.get(x);
                            break;
                        case 5:
                            employeePrevJobStartDate = testArray.get(x);
                            break;
                        case 6:
                            employeePrevJobEndDate = testArray.get(x);
                            break;
                        case 7:
                            refTitle = testArray.get(x);
                            break;
                        case 8:
                            refFirstName = testArray.get(x);
                            break;
                        case 9:
                            refLastName = testArray.get(x);
                            break;
                        case 10:
                            refEmail = testArray.get(x);
                            break;
                    }
                }
                System.out.println(employeeFirstName);
                System.out.println(employeeLastName);
                System.out.println(employeePrevJobTitle);
                System.out.println(employeePrevJobCompanyName);
                System.out.println(employeePrevJobStartDate);
                System.out.println(employeePrevJobEndDate);
                System.out.println(refTitle);
                System.out.println(refFirstName);
                System.out.println(refLastName);
                System.out.println(refEmail);
            }
        }
    }
}
