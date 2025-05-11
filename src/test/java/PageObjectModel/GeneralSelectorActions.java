package PageObjectModel;
import ResultPattern.Result;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import utils.ErrorLogManager;

public class GeneralSelectorActions {
    protected WebDriver mainDriver;
    private String errorCode = "GeneralStepsError-";

    protected Wait<WebDriver> wait;

    public GeneralSelectorActions(){
    }
    public WebDriver getWebDriver(){
        return mainDriver;
    }

    public void setWebDriver(WebDriver driver){
        this.mainDriver =  driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
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

    public Result<Boolean> fillInputFieldByDataSetId(String inputFieldId, String errorCode,String value) {
        try {
            String cssSelector ="[data-testid='"+inputFieldId+"']";
            WebElement inputField = mainDriver.findElement(By.cssSelector(cssSelector));
            inputField.sendKeys(value);
            return Result.success(true);
        } catch (Exception e) {
            String errorId = ErrorLogManager.getUniqueErrorCode(errorCode);
            ErrorLogManager.logError(errorId,e,"Error on filling input field");
            return Result.failure("Error filling input field: "+inputFieldId+",  error code: "+errorId);
        }
    }

    public Result<Boolean> fillInputFieldByText(String inputText, String errorCode,String value) {
        try {
            String xpathSelector ="//*[text()='"+inputText+"']/input[1]";
            WebElement inputField = mainDriver.findElement(By.xpath(xpathSelector));
            inputField.sendKeys(value);
            return Result.success(true);
        } catch (Exception e) {
            String errorId = ErrorLogManager.getUniqueErrorCode(errorCode);
            ErrorLogManager.logError(errorId,e,"Error on filling input field");
            return Result.failure("Error filling input field with text: "+inputText+" error code: "+errorId);
        }
    }

    public Result<Boolean> selectOptionByValueAttribute(String label, String errorCode,String value) {
        try {
            String xpathSelector ="//*[text()='"+label+"']//following::input[@value='"+value+"']";

            WebElement inputField = mainDriver.findElement(By.xpath(xpathSelector));
            inputField.sendKeys(value);
            return Result.success(true);
        } catch (Exception e) {
            String errorId = ErrorLogManager.getUniqueErrorCode(errorCode);
            ErrorLogManager.logError(errorId,e,"Error on filling input field");
            return Result.failure("Error selecting input with value: "+value+"  error code: "+errorId);
        }
    }

    public Result<WebElement> findElementByXpathText(String textParam,String errorCode) {
        String xpathSelector = "";
        try {
             xpathSelector = "//*[contains(text(),'"+textParam+"')]";
            return Result.success(mainDriver.findElement(By.xpath(xpathSelector)));
        } catch (Exception e) {
            String errorId = ErrorLogManager.getUniqueErrorCode(errorCode);
            ErrorLogManager.logError(errorId,e,"Error on finding WebElement");
            return Result.failure("Element not found: with xpath selector  "+xpathSelector + " Error Code:"+errorId);
        }
    }

    public Result<WebElement> findElementByExactXpathText(String textParam,String errorCode) {
        String xpathSelector = "";
        try {
            xpathSelector = "//*[text()='"+textParam+"']";
            return Result.success(mainDriver.findElement(By.xpath(xpathSelector)));
        } catch (Exception e) {
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

    public String getErrorCode() {
        return errorCode;
    }

    public Result<WebElement> findElementBySpecificId(String IdParam,String errorCodeParam) {
        try {
            return Result.success(mainDriver.findElement(By.id(IdParam)));
        } catch (Exception e) {
            String errorId = ErrorLogManager.getUniqueErrorCode(errorCodeParam);
            ErrorLogManager.logError(errorId,e,"Error on find WebElement");
            return Result.failure("Element not found with id  "+IdParam + " Error Code:"+errorId);
        }
    }


    public Result<WebElement> findElementBySpecificCSSClass(String cssClass) {
        try {
            return Result.success(mainDriver.findElement(By.cssSelector(cssClass)));
        } catch (Exception e) {
            return Result.failure("Element not found with css selector  "+cssClass );
        }
    }

    public Result<ArrayList<WebElement>> findElementsByXpathText(String textParam) {
        String xpathSelector = "";
        try {
            xpathSelector = "//*[contains(text(),"+textParam+")]";
            ArrayList<WebElement> elements =(ArrayList<WebElement>) mainDriver.findElements(By.xpath(xpathSelector));
            return Result.success(elements);
        } catch (Exception e) {
            return Result.failure("Elements not found: with selector  "+xpathSelector );
        }
    }

    public Result<ArrayList<WebElement>> findElementsByCss(String cssSelector) {
        try {
            ArrayList<WebElement> elements =(ArrayList<WebElement>) mainDriver.findElements(By.cssSelector(cssSelector));
            return Result.success(elements);
        } catch (Exception e) {
            return Result.failure("Elements not found: with css selector  "+cssSelector );
        }
    }

    public Result<Boolean> clickElementByXpathText(String textParam,String errorCode) {
        String xpathSelector = "";
        try {
            xpathSelector = "//*[text()='"+textParam+"']";
            mainDriver.findElement(By.xpath(xpathSelector)).click();
            return Result.success(true);
        } catch (Exception e) {
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

    public Result<Boolean> selectOptionByValue(String cssSelector,String errorCode) {
        String errorId ="";
        try {
            Result<WebElement> clickableElement = findElementBySpecificCSSClass(cssSelector);
            if(clickableElement.isFailure()){
                return Result.failure(clickableElement.getError().toString().replace("Optional",""));
            }
            clickableElement.getValue().get().click();
            return Result.success(true);
        } catch (Exception e) {
            errorId = ErrorLogManager.getUniqueErrorCode(errorCode);
            ErrorLogManager.logError(errorId,e,"Error on clicking WebElement");;
            return Result.failure("Could not be able to select an option with selector:"+cssSelector+" Error Code: "+errorId);
        }
    }

    public Result<Boolean> chooseOptionFromSelectWebElement(String selectCssSelector, String optionValue, String errorCode) {
        try {
            Select select = new Select(mainDriver.findElement(By.cssSelector(selectCssSelector)));
            select.selectByValue(optionValue);
            return Result.success(true);
        } catch (Exception e) {
            String errorId = ErrorLogManager.getUniqueErrorCode(errorCode);
            ErrorLogManager.logError(errorId,e,"Error on selecting option from select");
            return Result.failure("Error selecting option from select using selector: "+selectCssSelector+" with value: "+optionValue+" error code: "+errorId);
        }
    }

    public Result<Boolean> scrollsToText(String textParam, String errorCode) {
        try {
            WebElement element = mainDriver.findElement(By.xpath("//*[contains(text(),'"+textParam+"')]"));
            ((JavascriptExecutor) mainDriver).executeScript("arguments[0].scrollIntoView(true);", element);
            return Result.success(true);
        } catch (Exception e) {
            String errorId = ErrorLogManager.getUniqueErrorCode(errorCode);
            ErrorLogManager.logError(errorId,e,"Error on scrolling to text");
            return Result.failure("Error scrolling to text: "+textParam+" error code: "+errorId);
        }
    }

    public Result<Boolean> scrollsToElement(By bySelector, String errorCode) {
        try {
            WebElement element = mainDriver.findElement(bySelector);
            ((JavascriptExecutor) mainDriver).executeScript("arguments[0].scrollIntoView(true);", element);
            return Result.success(true);
        } catch (Exception e) {
            String errorId = ErrorLogManager.getUniqueErrorCode(errorCode);
            ErrorLogManager.logError(errorId,e,"Error on scrolling to text");
            return Result.failure(String.format("Error scrolling to element: %s error code: %s ",bySelector.toString(),errorId));
        }
    }

    public Result<String> getAlertText(String errorCode) {
        try {
            String actualAlertText ="";
            Alert alert = this.mainDriver.switchTo().alert();
            actualAlertText = alert.getText();
            alert.dismiss();
            return Result.success(actualAlertText);
        } catch (Exception e) {
            String errorId = ErrorLogManager.getUniqueErrorCode(errorCode);
            ErrorLogManager.logError(errorId,e,"Error on scrolling to text");
            return Result.failure("No alert present, error code: "+errorId);
        }
    }


}
