package stepsDefinitions;

import io.cucumber.java.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import utils.DynamicDriverManager;
import utils.ErrorLogManager;
import io.github.cdimascio.dotenv.Dotenv;
import utils.ScreenRecorderManager;
import utils.ScreenRecorderMonte;

import java.awt.*;
import java.io.IOException;

public class Hooks {
    private WebDriver driver;
    private ScreenRecorderMonte screenRecorder;
    private DynamicDriverManager driverManager = new DynamicDriverManager();
    private ScreenRecorderManager recorderManager = new ScreenRecorderManager();

    private boolean isHeadlessBrowser = true;
    @BeforeAll
    public static void before_or_after_all()
    {
        ScreenRecorderManager.deleteRecords();
    }
    @Before
    public void setup(Scenario scenario) throws Exception {
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
        if(!isHeadlessBrowser) {
            screenRecorder = recorderManager.getRecorder(scenario.getName().toString());
            screenRecorder.startRecording();
        }
    }


    public WebDriver getWebDriver(){
        return driver;
    }

    @After
    public void teardown() throws Exception {
        if(!isHeadlessBrowser) {screenRecorder.stopRecord();}

        driverManager .quitDriver();
    }
}
