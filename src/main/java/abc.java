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

    public static void main(String args[]) throws IOException {
        FileInputStream fis = new FileInputStream(new File("C://Users//patri//Desktop//tsiEmployeeInfo.xlsx"));
        XSSFWorkbook workbook = new XSSFWorkbook(fis); // XSSFWorkbook for .xlsx file
        XSSFSheet sheet = workbook.getSheetAt(1); // open sheet 1

        Iterator<Row> rowIterator = sheet.iterator();

        // Traversing over each row of XLSX file
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            if (row.getRowNum() != 0) // skip title row
            {
                ArrayList<String> testArray = new ArrayList<>();
                Iterator cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {
                    Cell cell = (Cell) cellIterator.next();
                    testArray.add(cell.getStringCellValue());
                }
                System.out.println(testArray.toString());
            }
        }
    }
}
