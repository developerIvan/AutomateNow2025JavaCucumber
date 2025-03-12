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

public class GeneralSelectorActions {
    private static final Logger logger = LoggerFactory.getLogger(GeneralSelectorActions.class);
    public WebDriver mainDriver;
    private String errorCode = "GeneralSteps00";
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

    public Result <Boolean> openPage(String url){
        try {
            mainDriver.get(url);
            return Result.success(true);
        } catch (Exception e) {
            return Result.failure("Error opening page: "+url);
        }
    }
    public Result<WebElement> findElementByXpathText(String textParam,String errorCode) {
        String xpathSelector = "";
        try {
             xpathSelector = "//*[contains(text(),'"+textParam+"')]";
            return Result.success(mainDriver.findElement(By.xpath(xpathSelector)));
        } catch (NoSuchElementException e) {
            this.getLogger().error("---------------------Error on finding WebElement------------------- \n");
            this.getLogger().error("Error code : "+errorCode+ " Date:"+ getDateTime() +"\n");
            this.getLogger().error("exception trace: "+e );

            return Result.failure("Element not found: with xpath selector  "+xpathSelector );
        }
    }

    public Result<WebElement> waitElementByXpathText(String textParam) {
        String xpathSelector = "";
        try {
            xpathSelector = "//*[contains(text(),'"+textParam+"')]";
            WebElement expectedElement = mainDriver.findElement(By.xpath(xpathSelector));
            wait.until(ExpectedConditions.visibilityOf(expectedElement));
            return Result.success(expectedElement);
        } catch (Exception e) {
            this.getLogger().error("---------------------Error on waiting WebElement------------------- \n");
            this.getLogger().error("Error code : "+getErrorCode()+ " Date:"+ getDateTime() +"\n");
            this.getLogger().error("exception trace: "+e );

            return Result.failure("Element not found: with xpath selector  "+xpathSelector +" after waiting 30 seconds");
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



    public Result<WebElement> findElementBySpecificId(String IdParam) {
        try {
            return Result.success(mainDriver.findElement(By.id(IdParam)));
        } catch (NoSuchElementException e) {
            return Result.failure("Element not found with id  "+IdParam );
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
            this.getLogger().error("---------------------Error on clicking WebElement------------------- \n");
            this.getLogger().error("Error code : "+errorCode+ " Date:"+ getDateTime() +"\n");
            this.getLogger().error("exception trace: "+e );
            return Result.failure("Element not found with xpath selector  "+xpathSelector );

        }
    }

    public Result<Boolean> clickElementById(String id,String errorCode) {
        try {
            mainDriver.findElement(By.id(id)).click();
            return Result.success(true);
        } catch (NoSuchElementException e) {
            this.getLogger().error("---------------------Error on clicking WebElement------------------- \n");
            this.getLogger().error("Error code : "+errorCode+ " Date:"+ getDateTime() +"\n");
            this.getLogger().error("exception trace: "+e );
            return Result.failure("Element not found with given id: "+id +" Error code: "+errorCode);

        }
    }

    public Boolean clickOnLinkSection(String linkText,String errorCode) {
        Result<Boolean> clickElement = clickElementByXpathText(linkText,errorCode);

        if(clickElement.isFailure()){
            return false;
        }
        return true;
    }


}
