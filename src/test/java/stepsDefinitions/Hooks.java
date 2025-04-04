package stepsDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import utils.DriverManager;
import utils.ErrorLogManager;
import io.github.cdimascio.dotenv.Dotenv;

public class Hooks {
    private WebDriver driver;
    @Before
    public void setup() {
        Dotenv dotenv = Dotenv.load();
        String browserName = dotenv.get("browserName");
        String browserHeight =  dotenv.get("browserHeight");
        String browserWidth =  dotenv.get("browserWidth");

        if (browserName == null) {
            browserName = "chrome";
        }
        driver = DriverManager.getDriver(browserName.toLowerCase(), browserHeight.toLowerCase(), browserWidth.toLowerCase());
    }

    public String getSession() {
        return DriverManager.getSessionInfo();
    }

    public WebDriver getWebDriver(){
        return driver;
    }

    @After
    public void teardown() {
        DriverManager.quitDriver();
    }
}
