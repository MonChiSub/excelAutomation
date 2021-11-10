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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class StepDefinitions {
    WebDriver driver;
    private String employeeFirstName, employeeLastName, employeePrevJobTitle, employeePrevJobCompanyName, employeePrevJobStartDate,
            employeePrevJobEndDate, refTitle, refFirstName, refLastName, refEmail = "";
    private ArrayList<String> testArray = new ArrayList<>();
    private String keysPressed =  Keys.chord(Keys.CONTROL, Keys.RETURN);

    @Given("The employee has provided their details to me")
    public void detailCheck() throws IOException {
        FileInputStream fis = new FileInputStream(new File("C://Users//micha//Desktop//tsiEmployeeInfo.xlsx"));
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
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--user-data-dir=C:\\Users\\micha\\AppData\\Local\\Google\\Chrome\\User Data");
        driver = new ChromeDriver(options);
        driver.get("https://mail.google.com/mail/");
        driver.manage().window().maximize();
    }

    @When("I am writing an email for the reference")
    public void writeEmail() throws InterruptedException{
        driver.findElement(By.xpath("/html/body/div[7]/div[3]/div/div[2]/div[1]/div[1]/div[1]/div/div/div/div[1]/div/div")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("/html/body/div[23]/div/div/div/div[1]/div[3]/div[1]/div[1]/div/div/div/div[2]/div/div[2]/div/div/div/div/table/tbody/tr/td[2]/img[2]")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("/html/body/div[24]/div/div/div/div/div[3]/div/div/div[4]/table/tbody/tr/td[2]/form/div[1]/table/tbody/tr[1]/td[2]/div/div/textarea")).sendKeys(refEmail);
        Thread.sleep(3000);
        driver.findElement(By.xpath("/html/body/div[24]/div/div/div/div/div[3]/div/div/div[4]/table/tbody/tr/td[2]/form/div[1]/table/tbody/tr[1]/td[2]/div/div/textarea")).sendKeys(Keys.ENTER);
        Thread.sleep(3000);
        driver.findElement(By.xpath("/html/body/div[24]/div/div/div/div/div[3]/div/div/div[4]/table/tbody/tr/td[2]/form/div[3]/div/input")).sendKeys("Hello test test 123 xd");
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div[24]/div/div/div/div/div[3]/div/div/div[4]/table/tbody/tr/td[2]/table/tbody/tr[1]/td/div/div[1]/div[2]/div[1]/div/table/tbody/tr/td[2]/div[2]/div"))
                .sendKeys("Hello, " + refTitle + " " + refLastName + "\n\n"
                + "I am writing to you today to ask for a reference on behalf of " + employeeFirstName + " " + employeeLastName
                        + ", who has claimed to have previously worked for you from the years of " + employeePrevJobStartDate
                        + " - " + employeePrevJobEndDate + ", as a " + employeePrevJobTitle + " at " + employeePrevJobCompanyName
                        + ".\n\n" + "Kind Regards, \nThomas Hooson");
        Thread.sleep(3000);
        driver.findElement(By.xpath("/html/body/div[24]/div/div/div/div/div[2]/div/div[2]/div/div/div/div/table/tbody/tr/td[2]")).click();
//        driver.findElement(By.xpath("/html/body/div[24]/div/div/div/div/div[3]/div/div/div[4]/table/tbody/tr/td[2]/table/tbody/tr[1]/td/div/div[1]/div[2]/div[1]/div/table/tbody/tr/td[2]/div[2]/div")).sendKeys(keysPressed);
        Thread.sleep(3000);
        driver.findElement(By.xpath("/html/body/div[7]/div[3]/div/div[2]/div[1]/div[1]/div[1]/div/div/div/div[2]/div/div/div[1]/div[1]/div/div[6]/div")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("/html/body/div[7]/div[3]/div/div[2]/div[1]/div[2]/div/div/div/div/div[2]/div/div[1]/div/div[2]/div[4]/div[2]/div/table/tbody/tr[1]/td[5]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div[23]/div/div/div/div[1]/div[3]/div[1]/div[1]/div/div/div/div[3]/div/div/div[4]/table/tbody/tr/td[2]/table/tbody/tr[1]/td/div/div[1]/div[2]/div[1]/div/table/tbody/tr/td[2]/div[2]/div")).sendKeys(keysPressed);
        Thread.sleep(5000);
    }

    @Then("Send the email to the recipient")
    public void sendEmail() {
        driver.quit();
    }
}
