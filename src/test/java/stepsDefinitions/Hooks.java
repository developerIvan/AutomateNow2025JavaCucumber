package stepsDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import utils.DynamicDriverManager;
import utils.ErrorLogManager;
import io.github.cdimascio.dotenv.Dotenv;

public class Hooks {
    private WebDriver driver;
    private boolean isHeadlessBrowser = true;
   private String scenarioName ="";

    private DynamicDriverManager driverManager = new DynamicDriverManager();
    @Before
    public void setup(Scenario scenario) {
        Dotenv dotenv = Dotenv.load();
        String browserName = dotenv.get("browserName");
        String browserHeight =  dotenv.get("browserHeight");
        String browserWidth =  dotenv.get("browserWidth");
        String isHeadlessParam = dotenv.get("isHeadLessBrowser");
        scenarioName = scenario.getName();

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
    public void teardown(Scenario scenario) {

        if(scenario.getName().contains("Iframe")){
            driver.switchTo().defaultContent();
        }
        driverManager .quitDriver();

    }

    public String getScenarioName() {
        return scenarioName;
    }
}
