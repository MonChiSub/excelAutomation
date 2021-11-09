package selenium;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class StepDefinitions {
    WebDriver driver;

    @Given("The employee has provided their details to me")
    public void detailCheck() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("");
        driver.manage().window().maximize();
    }

    @When("I am writing an email for the reference")
    public void writeEmail() throws InterruptedException {

    }

    @Then("Send the email to the recipient")
    public void sendEmail() throws InterruptedException {
        driver.quit();
    }
}
