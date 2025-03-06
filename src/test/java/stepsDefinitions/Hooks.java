package stepsDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import utils.DriverManager;
import org.testng.ITestContext;

public class Hooks {
    private WebDriver driver;
    @Before
    public void setup() {
        String browserName = System.getenv("browserName");
        if (browserName == null) {
            browserName = "chrome";
        }
        driver = DriverManager.getDriver(browserName.toLowerCase());
    }

    public WebDriver getWebDriver(){
        return driver;
    }

    @After
    public void teardown() {
        DriverManager.quitDriver();
    }
}
