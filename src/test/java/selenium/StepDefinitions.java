package selenium;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class StepDefinitions {
    WebDriver driver;
    private String employeeFirstName, employeeLastName, employeePrevJobTitle, employeePrevJobCompanyName, employeePrevJobStartDate,
            employeePrevJobEndDate, refTitle, refFirstName, refLastName, refEmail = "";
    private ArrayList<String> testArray = new ArrayList<>();

    @Given("The employee has provided their details to me")
    public void detailCheck() throws IOException {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--user-data-dir=C:\\Users\\patri\\AppData\\Local\\Google\\Chrome\\User Data");
        driver = new ChromeDriver(options);
        driver.get("");
        driver.manage().window().maximize();

        FileInputStream fis = new FileInputStream(new File("C://Users//patri//Desktop//tsiEmployeeInfo.xlsx"));
        XSSFWorkbook workbook = new XSSFWorkbook(fis); // XSSFWorkbook for .xlsx file
        XSSFSheet sheet = workbook.getSheetAt(1); // open sheet 1

        Iterator<Row> rowIterator = sheet.iterator();

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
            }
        }
    }

    @When("I am writing an email for the reference")
    public void writeEmail() {
        driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div[2]/div/div/div/div/div[1]/div/div/div/div/div/div[1]/div[2]/div/div/button")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div[2]/div/div/div/div/div[2]/div/div[3]/div[1]/div/div/div/div[1]/div[1]/div[1]/div[1]/div[1]/div/div[1]/div/div/div[2]/div[2]/input")).sendKeys(refEmail);
        driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div[2]/div/div/div/div/div[2]/div/div[3]/div[1]/div/div/div/div[1]/div[2]/div/div[1]/div[1]/div"))
                .sendKeys("Hello, " + refTitle + " " + refLastName + "\n\n"
                + "I am writing to you today to ask for a reference on behalf of " + employeeFirstName + " " + employeeLastName
                        + ", who has claimed to have previously worked for you from the years of " + employeePrevJobStartDate
                        + " - " + employeePrevJobEndDate + ", as a " + employeePrevJobTitle + " at " + employeePrevJobCompanyName
                        + ".\n\n" + "Kind Regards, \nThomas Hooson");
    }

    @Then("Send the email to the recipient")
    public void sendEmail() throws InterruptedException {
//        driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/div[2]/div/div/div/div/div[2]/div/div[3]/div[1]/div/div/div/div[1]/div[3]/div[2]/div[1]/div/div/span/button[1]")).click();
//        driver.quit();
    }
}
