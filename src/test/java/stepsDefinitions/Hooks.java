package stepsDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import utils.DriverManager;
import utils.ErrorLogManager;

public class Hooks {
    private WebDriver driver;
    @Before
    public void setup() {
        String browserName = System.getenv("browserName");
        if (browserName == null) {
            browserName = "chrome";
        }
        driver = DriverManager.getDriver(browserName.toLowerCase());
        int availableThreads = Runtime.getRuntime().availableProcessors();
        System.out.println("Número de núcleos disponibles: " + availableThreads);
        ErrorLogManager.logInfo("Número de núcleos disponibles: " + availableThreads);

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
/*
    @AfterAll
    public static void afterAll() {
        DriverManager.quitDriver();
    }*/


}
