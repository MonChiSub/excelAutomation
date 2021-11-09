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

public class abc {
    public static String title = "";
    public static String firstName = "";
    public static String lastName = "";
    public static String email = "";

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
                ArrayList<String> testArray = new ArrayList<>();
                Iterator cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {
                    Cell cell = (Cell) cellIterator.next();
                    testArray.add(cell.getStringCellValue());
                }
                System.out.println(testArray.toString());

                for(int x = 0; x < testArray.size(); x++) {
                    testArray.get(x);
                    switch (x) {
                        default:
                            break;
                        case 7:
                            title = testArray.get(x);
                            break;
                        case 8:
                            firstName = testArray.get(x);
                            break;
                        case 9:
                            lastName = testArray.get(x);
                            break;
                        case 10:
                            email = testArray.get(x);
                            break;
                    }
                }
                System.out.println(title);
                System.out.println(firstName);
                System.out.println(lastName);
                System.out.println(email);
            }
        }
    }
}
