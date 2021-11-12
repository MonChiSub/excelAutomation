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
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class StepDefinitions {
    private static ArrayList<String> fullDatas = new ArrayList<>();
    private static ArrayList<String> employeeFirstNames = new ArrayList<>();
    private static ArrayList<String> employeeLastNames = new ArrayList<>();
    private static ArrayList<String> employeeEmails = new ArrayList<>();
    private static ArrayList<String> employeePrevJobTitles = new ArrayList<>();
    private static ArrayList<String> employeePrevJobCompanyNames = new ArrayList<>();
    private static ArrayList<String> employeePrevJobStartDates = new ArrayList<>();
    private static ArrayList<String> employeePrevJobEndDates = new ArrayList<>();
    private static ArrayList<String> refTitles = new ArrayList<>();
    private static ArrayList<String> refFirstNames = new ArrayList<>();
    private static ArrayList<String> refLastNames = new ArrayList<>();
    private static ArrayList<String> refEmails = new ArrayList<>();
    private String keysPressed =  Keys.chord(Keys.CONTROL, Keys.RETURN);
    WebDriver driver;


    @Given("The employee has provided their details to me")
    public void detailCheck() throws IOException {
        //Method to read the excel sheet and compile the information.
        compileDetails();

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--user-data-dir=C:\\Users\\patri\\AppData\\Local\\Google\\Chrome\\User Data");
        driver = new ChromeDriver(options);
        driver.get("https://outlook.office.com/mail/inbox");
        driver.manage().window().maximize();
    }

    @When("I am writing an email for the reference")
    public void writeEmail() throws InterruptedException {
        for(int x = 0; x < employeeFirstNames.size(); x++) {
            //Click Compose
            driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div[2]/div/div/div/div[1]/div[1]/div[2]/div/div/button/span/span/span")).click();
            Thread.sleep(5000);
            //Type Recipient Email
            driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div[2]/div/div/div/div[3]/div[2]/div/div[3]/div[1]/div/div/div/div[1]/div[1]/div[1]/div[1]/div[1]/div/div[1]/div/div/div[2]/div[2]/input")).sendKeys(refEmails.get(x));
            Thread.sleep(3000);
            //Type the Subject
            driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div[2]/div/div/div/div[3]/div[2]/div/div[3]/div[1]/div/div/div/div[1]/div[1]/div[2]/div[2]/div/div/div/input")).sendKeys("Hello test test 123 xd");
            Thread.sleep(2000);
            //Type the message
            driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div[2]/div/div/div/div[3]/div[2]/div/div[3]/div[1]/div/div/div/div[1]/div[2]/div/div/div[1]"))
                    .sendKeys("Hello, " + refTitles.get(x) + " " + refLastNames.get(x) + "\n\n"
                            + "I am writing to you today to ask for a reference on behalf of " + employeeFirstNames.get(x) + " " + employeeLastNames.get(x)
                            + ", who has claimed to have previously worked for you from the years of " + employeePrevJobStartDates.get(x)
                            + " - " + employeePrevJobEndDates.get(x) + ", as a " + employeePrevJobTitles.get(x) + " at " + employeePrevJobCompanyNames.get(x)
                            + ".\n\n" + "Kind Regards, \nThomas Hooson");
            Thread.sleep(2000);
            driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div[2]/div/div/div/div[3]/div[2]/div/div[3]/div[1]/div/div/div/div[1]/div[3]/div[2]/div[1]/div/div/span/button[1]/span/span"));
        }
    }

    @Then("Send the email to the recipient")
    public void sendEmail() throws InterruptedException {
        driver.quit();
    }

    public void compileDetails() throws IOException {
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
                    if (!cell.getStringCellValue().isEmpty() &&
                            !cell.getStringCellValue().equals("0") &&
                            !cell.getStringCellValue().equals(",")) {
                        fullDatas.add(cell.getStringCellValue());
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
    }
}
