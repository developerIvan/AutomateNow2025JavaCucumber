package PageObjectModel;
import ResultPattern.Result;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import utils.ErrorLogManager;

public class GeneralSelectorActions {
    private static final Logger logger = LoggerFactory.getLogger(GeneralSelectorActions.class);
    public WebDriver mainDriver;
    private String errorCode = "GeneralStepsError-";
    private Date currentDate = new Date();
    private Wait<WebDriver> wait;
    public GeneralSelectorActions(){
    }

    public void setWebDriver(WebDriver driver){
        this.mainDriver =  driver;
        logger.info("Driver initialized",mainDriver.toString());
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public static Logger getLogger() {
        return logger;
    }

    public Result <Boolean> openPage(String url,String errorCode){
        try {
            mainDriver.get(url);
            return Result.success(true);
        } catch (Exception e) {
            String errorId = ErrorLogManager.getUniqueErrorCode(errorCode);
            ErrorLogManager.logError(errorId,e,"Error on Opening WebBrowser");
            return Result.failure("Error opening page: "+url+" error code: "+errorId);
        }
    }
    public Result<WebElement> findElementByXpathText(String textParam,String errorCode) {
        String xpathSelector = "";
        try {
             xpathSelector = "//*[contains(text(),'"+textParam+"')]";
            return Result.success(mainDriver.findElement(By.xpath(xpathSelector)));
        } catch (NoSuchElementException e) {
            String errorId = ErrorLogManager.getUniqueErrorCode(errorCode);
            ErrorLogManager.logError(errorId,e,"Error on finding WebElement");
            return Result.failure("Element not found: with xpath selector  "+xpathSelector + " Error Code:"+errorId);
        }
    }

    public Result<WebElement> waitElementByXpathText(String textParam, String errorCode) {
        String xpathSelector = "";
        try {
            xpathSelector = "//*[contains(text(),'"+textParam+"')]";
            WebElement expectedElement = mainDriver.findElement(By.xpath(xpathSelector));
            wait.until(ExpectedConditions.visibilityOf(expectedElement));
            return Result.success(expectedElement);
        } catch (Exception e) {
            String errorId = ErrorLogManager.getUniqueErrorCode(errorCode);
            ErrorLogManager.logError(errorId,e,"Error on waiting WebElement");
            return Result.failure("Element not found: with xpath selector  "+xpathSelector +" after waiting 30 seconds Error Code:"+errorId);
        }
    }

    public String getDateTime() {
        return currentDate.toString();
    }

    public String getCurrentDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        return now.format(formatter);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getUniqueErrorCode(String errorCode) {
        return errorCode+Math.random()*1000;
    }


    public Result<WebElement> findElementBySpecificId(String IdParam,String errorCodeParam) {
        try {
            return Result.success(mainDriver.findElement(By.id(IdParam)));
        } catch (NoSuchElementException e) {
            String errorId = ErrorLogManager.getUniqueErrorCode(errorCodeParam);
            ErrorLogManager.logError(errorId,e,"Error on find WebElement");
            return Result.failure("Element not found with id  "+IdParam + " Error Code:"+errorId);
        }
    }


    public Result<WebElement> findElementBySpecificCSSClass(String cssClass) {
        try {
            return Result.success(mainDriver.findElement(By.cssSelector(cssClass)));
        } catch (NoSuchElementException e) {
            return Result.failure("Element not found with css selector  "+cssClass );
        }
    }

    public Result<ArrayList<WebElement>> findElementsByXpathText(String textParam) {
        String xpathSelector = "";
        try {
            xpathSelector = "//*[contains(text(),"+textParam+")]";
            ArrayList<WebElement> elements =(ArrayList<WebElement>) mainDriver.findElements(By.xpath(xpathSelector));
            return Result.success(elements);
        } catch (NoSuchElementException e) {
            return Result.failure("Elements not found: with selector  "+xpathSelector );
        }
    }

    public Result<ArrayList<WebElement>> findElementsByCss(String cssSelector) {
        try {
            ArrayList<WebElement> elements =(ArrayList<WebElement>) mainDriver.findElements(By.cssSelector(cssSelector));
            return Result.success(elements);
        } catch (NoSuchElementException e) {
            return Result.failure("Elements not found: with css selector  "+cssSelector );
        }
    }

    public Result<Boolean> clickElementByXpathText(String textParam,String errorCode) {
        String xpathSelector = "";
        try {
            xpathSelector = "//*[text()='"+textParam+"']";
            mainDriver.findElement(By.xpath(xpathSelector)).click();
            return Result.success(true);
        } catch (NoSuchElementException e) {
            String errorId = ErrorLogManager.getUniqueErrorCode(errorCode);
            ErrorLogManager.logError(errorId,e,"Error on clicking WebElement");
            return Result.failure("Element not found with xpath selector  "+xpathSelector + " Error Code:"+errorId);

        }
    }

    public Result<Boolean> clickElementById(String id,String errorCode) {
        String errorId ="";
        try {
            Result<WebElement> clickableElement = findElementBySpecificId(id,errorCode);
            if(clickableElement.isFailure()){
                return Result.failure(clickableElement.getError().toString().replace("Optional",""));
            }
            clickableElement.getValue().get().click();
            return Result.success(true);
        } catch (Exception e) {
            errorId = ErrorLogManager.getUniqueErrorCode(errorCode);
            ErrorLogManager.logError(errorId,e,"Error on clicking WebElement");;
            return Result.failure("Could not be able to click on WebElement found with given id:"+id+" Error Code: "+errorId);

        }
    }

}
