package stepsDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import utils.DynamicDriverManager;
import utils.ErrorLogManager;
import io.github.cdimascio.dotenv.Dotenv;

public class Hooks {
    private WebDriver driver;
    private boolean isHeadlessBrowser = true;
    private DynamicDriverManager driverManager = new DynamicDriverManager();
    @Before
    public void setup() {
        Dotenv dotenv = Dotenv.load();
        String browserName = dotenv.get("browserName");
        String browserHeight =  dotenv.get("browserHeight");
        String browserWidth =  dotenv.get("browserWidth");
        String isHeadlessParam = dotenv.get("isHeadLessBrowser");

        if (browserName == null) {
            browserName = "chrome";
        }

        if(isHeadlessParam == null){
            isHeadlessParam = "True";
        }
        isHeadlessBrowser = Boolean.parseBoolean(isHeadlessParam);
        driver = driverManager.getDriver(browserName.toLowerCase(), browserHeight.toLowerCase(), browserWidth.toLowerCase(),isHeadlessBrowser);
    }


    public WebDriver getWebDriver(){
        return driver;
    }


    @After
    public void teardown() {
        driverManager .quitDriver();
    }
}
