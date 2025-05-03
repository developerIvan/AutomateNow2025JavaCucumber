package stepsDefinitions;

import io.cucumber.java.*;
import org.openqa.selenium.WebDriver;
import utils.*;
import io.github.cdimascio.dotenv.Dotenv;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Hooks {
    private WebDriver driver;



    private Path currentPath = Paths.get(System.getProperty("user.dir"));
    private Path finalPath = currentPath.resolve("Recordings");
    private static ScreenRecorderMonte screenRecorder;

    @BeforeAll
    public static void before_or_after_all()
    {
        screenRecorder.deleteRecords();
    }
    @Before
    public void setup(Scenario scenario) throws Exception {
        Dotenv dotenv = Dotenv.load();
        String browserName = dotenv.get("browserName");
        String browserHeight =  dotenv.get("browserHeight");
        String browserWidth =  dotenv.get("browserWidth");

        if (browserName == null) {
            browserName = "chrome";
        }
        driver = DriverManager.getDriver(browserName.toLowerCase(), browserHeight.toLowerCase(), browserWidth.toLowerCase());

      //  DevTools devTools = ((HasDevTools) driver).getDevTools();
       // screenRecorder.setDevTools(devTools);
;
      //  finalPath = finalPath.resolve(sanitizeFileName(scenario.getName().toString()));

      //  screenRecorder.setFileOutpuDir(new File(finalPath.toString()));
     //   screenRecorder.startRecording(Integer.parseInt(browserWidth),Integer.parseInt(browserHeight));
        String videoName = sanitizeFileName(scenario.getName().toString());
        screenRecorder = DriverManager.getRecorder(videoName);
        screenRecorder.startRecording();

    }

    public String getSession() {
        return DriverManager.getSessionInfo();
    }

    public WebDriver getWebDriver(){
        return driver;
    }

    @After
    public void teardown(Scenario scenario) throws Exception {
          DriverManager.quitDriver();
          screenRecorder.stopRecord();
      //  screenRecorder.combineFramesToVideo(finalPath.toString(), scenarioName+".mp4");
     //   DriverManager.stopRecording();

    }

    private String sanitizeFileName(String name) {
        return name.replaceAll("[^a-zA-Z0-9.-]", "_");
    }
}
