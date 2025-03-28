package utils;

import PageObjectModel.GeneralSelectorActions;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

public class ErrorLogManager {

    private static final Logger logger = LoggerFactory.getLogger(GeneralSelectorActions.class);
    private static WebDriver driver;
    private static String getFormatterDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        return now.format(formatter);
    }

    public static void setWebDriver(WebDriver driver) {
        ErrorLogManager.driver = driver;
    }
    private static String getCurrentDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return now.format(formatter);
    }

    public static String getUniqueErrorCode(String errorCode) {
        return errorCode+Math.random()*1000+"-"+ getFormatterDate();
    }

    public static void logError(String errorCode, Exception e, String errorTitle) {
        logger.error("---------------------"+errorTitle+"------------------- \n");
        logger.error("Error code : "+errorCode+ " Date:"+ getCurrentDate() +"\n");
        logger.error("exception trace: "+e );
    }

    public static void logInfo(String dataToLog) {
        logger.error("Data : "+dataToLog+ " Date:"+ getCurrentDate() +"\n");
    }

    public static String  captureScreenshot(WebDriver driver,String fileName) throws IOException {
        TakesScreenshot takeScreenshot = (TakesScreenshot) driver;
        File source = takeScreenshot.getScreenshotAs(OutputType.FILE);
        String destination = System.getProperty("user.dir") + "/target/images/" +fileName.replaceAll(" ","_")+ getCurrentDate().toString().replaceAll("","_").replaceAll(":","_") + ".png";
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);
        return destination;
    }

    public static File  captureScreenshot(String fileName) throws IOException {
        TakesScreenshot takeScreenshot = (TakesScreenshot) driver;
        File source = takeScreenshot.getScreenshotAs(OutputType.FILE);
        String destination = System.getProperty("user.dir") + "/target/images/" +fileName.replaceAll(" ","_")+ getCurrentDate().toString().replaceAll("","_").replaceAll(":","_") + ".png";
        File fileDestination = new File(destination);
        FileUtils.copyFile(source, fileDestination);
        return fileDestination;
    }

    public static void saveScreenShotToAllure(String testStepName, WebDriver driver) {
        try  {
            testStepName = testStepName.replaceAll("[\\\\^!()&%$#.'¡*?+$\\\"]", "");
            ErrorLogManager.logger.info("Saving screenshot to allure with step name: "+testStepName+" Date:"+ getCurrentDate());
            String screenshotPath = ErrorLogManager.captureScreenshot(driver, testStepName);
            Allure.attachment(testStepName, new FileInputStream(screenshotPath));
        } catch (IOException e) {
            logInfo("Error saving screenshot to allure with step name: "+testStepName+" Date:"+ getCurrentDate());
            logError("Cause", e, "----------------Error on saving screenshot to allure-------------------");
        }
    }

    public static void saveScreenShotToAllure(String testStepName) {
        try  {
            testStepName = testStepName.replaceAll("[\\\\^!()&%$#.'¡*?+$\\\"]", "");
            File screenshotFile = ErrorLogManager.captureScreenshot(testStepName);
            Allure.attachment(testStepName,new FileInputStream(screenshotFile));
        } catch (IOException e) {
            logInfo("Error saving screenshot file to allure with step name: "+testStepName+" Date:"+ getCurrentDate());
            logError("Cause", e, "----------------Error on saving screenshot to allure-------------------");
        }
    }

  }
