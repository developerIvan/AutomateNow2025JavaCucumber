package PageObjectModel;
import ResultPattern.Result;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.WheelInput;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import utils.ErrorLogManager;

public class GeneralSelectorActions {
    protected WebDriver mainDriver;
    private String errorCode = "GeneralStepsError-";

    public static String currentIframeName="";
    protected Wait<WebDriver> wait;
    JavascriptExecutor js = null;

    public GeneralSelectorActions(){
    }
    public WebDriver getWebDriver(){
        return mainDriver;
    }

    public void setWebDriver(WebDriver driver){
        this.mainDriver =  driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        js = (JavascriptExecutor) mainDriver;
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

    public Result <String> getCurrentPageUrl(String errorCode){
        try {
            return Result.success(mainDriver.getCurrentUrl());
        } catch (Exception e) {
            String errorId = ErrorLogManager.getUniqueErrorCode(errorCode);
            ErrorLogManager.logError(errorId,e,"Error on retrieving current page title");
            return Result.failure("Error on retrieving current page title error code: "+errorId);
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
            inputField.click();
            return Result.success(true);
        } catch (Exception e) {
            String errorId = ErrorLogManager.getUniqueErrorCode(errorCode);
            ErrorLogManager.logError(errorId,e,"Error on filling input field");
            return Result.failure("Error selecting input with value: "+value+"  error code: "+errorId);
        }
    }

    public Result<WebElement> findElementByExactXpathText(String textParam,String errorCode) {
        String xpathSelector = "";
        xpathSelector = "//*[text()='"+textParam+"']";
        Result<WebElement> foundedElement = this.retryFindingElement(By.xpath(xpathSelector),errorCode);
        return foundedElement;
    }

    public Result<WebElement> waitElementByXpathText(String textParam, String errorCode) {
        String xpathSelector = "//*[contains(text(),'"+textParam+"')]";
        return retryWaitingForElement(By.xpath(xpathSelector),errorCode);
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


    public Result<WebElement> findElementBySpecificCSSClass(String cssClass,String errorCodeParam) {
        try {
            return Result.success(mainDriver.findElement(By.cssSelector(cssClass)));
        }  catch (Exception e) {
            String errorId = ErrorLogManager.getUniqueErrorCode(errorCodeParam);
            ErrorLogManager.logError(errorId,e,"Error on find WebElement");
            return Result.failure("Element not found with cssClass  "+cssClass + " Error Code:"+errorId);
        }
    }

    public List<WebElement> findElements(By bySelector) {
      return mainDriver.findElements(bySelector);
    }

    public WebElement findElement(By bySelector) {
        return mainDriver.findElement(bySelector);
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
            xpathSelector = String.format( "//*[text()='%s']",textParam);
            mainDriver.findElement(By.xpath(xpathSelector)).click();
            return Result.success(true);
        } catch (Exception e) {
            String errorId = ErrorLogManager.getUniqueErrorCode(errorCode);
            ErrorLogManager.logError(errorId,e,"Error on clicking WebElement");
            return Result.failure("Element not found with xpath selector  "+xpathSelector + " Error Code:"+errorId);

        }
    }

    public Result<Boolean> clickElementByContainsXpathText(String textParam,String errorCode) {
        String xpathSelector = "";
        try {
            xpathSelector = String.format( "//*[contains(text(),'%s')]",textParam);
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

    public Result<Boolean> clickElementByCssSelector(String cssSelector,String errorCode) {
        String errorId ="";
        try {
            Result<WebElement> clickableElement = findElementBySpecificCSSClass(cssSelector,errorCode);
            this.wait.until(ExpectedConditions.visibilityOf(mainDriver.findElement(By.cssSelector(cssSelector))));
            if(clickableElement.isFailure()){
                return Result.failure(clickableElement.getError().toString().replace("Optional",""));
            }
            clickableElement.getValue().get().click();
            return Result.success(true);
        } catch (Exception e) {
            errorId = ErrorLogManager.getUniqueErrorCode(errorCode);
            ErrorLogManager.logError(errorId,e,"Error on clicking WebElement");;
            return Result.failure(String.format("Could not be able to click on WebElement found with given cssClass selector %s Error Code: %s ",cssSelector,errorId));
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
            ((JavascriptExecutor) mainDriver).executeScript("arguments[0].scrollIntoView(true);",element );
            //Defined on purpose to simulate scrolling time
            Thread.sleep(500);
            this.wait.until(ExpectedConditions.visibilityOf(element));
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
            ((JavascriptExecutor) mainDriver).executeScript("arguments[0].scrollIntoView(true);",element );
            //Defined on purpose to simulate scrolling time
            Thread.sleep(500);
            this.wait.until(ExpectedConditions.visibilityOf(mainDriver.findElement(bySelector)));
            if(element.isDisplayed()){
                return Result.success(true);
            }else {
                return Result.failure(String.format("Error scrolling to element: %s because is not visible",bySelector.toString()));
            }

        } catch (Exception e) {
            String errorId = ErrorLogManager.getUniqueErrorCode(errorCode);
            ErrorLogManager.logError(errorId,e,"Error on scrolling to text");
            return Result.failure(String.format("Error scrolling to element: %s error code: %s ",bySelector.toString(),errorId));
        }
    }

    public Result<Boolean> scrollsFromToElement(By bySelector,int xAxis,int yAxis, String errorCode) {
        try {
            WebElement element = mainDriver.findElement(bySelector);
            WheelInput.ScrollOrigin scrollOrigin = WheelInput.ScrollOrigin.fromElement( mainDriver.findElement(bySelector));
            new Actions(mainDriver)
                    .scrollFromOrigin(scrollOrigin, xAxis, yAxis)
                    .perform();

            this.wait.until(ExpectedConditions.visibilityOf(element));
            if(element.isDisplayed()){
                return Result.success(true);
            }else {
                return Result.failure(String.format("Error scrolling from element: %s because is not visible",bySelector.toString()));
            }

        } catch (Exception e) {
            String errorId = ErrorLogManager.getUniqueErrorCode(errorCode);
            ErrorLogManager.logError(errorId,e,"Error on scrolling to text");
            return Result.failure(String.format("Error scrolling from element: %s error code: %s ",bySelector.toString(),errorId));
        }
    }

    public Result<Object[]> getCurrentWindows(String errorCode){
        try{
            Object[] windowHandles=mainDriver.getWindowHandles().toArray();
            return Result.success(windowHandles);
        }catch (Exception e) {
            String errorId = ErrorLogManager.getUniqueErrorCode(errorCode);
            ErrorLogManager.logError(errorId,e,"Error on retrieving windows  ");
            return Result.failure(String.format("Error on retrieving current open  windows. Error Id: %s",errorId));
        }
    }

    public Result<Boolean> switchToWindowOrTab(Object[] windowHandles,String expectedUrl,  String errorCode){
        try{
            mainDriver.switchTo().window((String) windowHandles[windowHandles.length-1]);
            this.wait.until(ExpectedConditions.urlContains(expectedUrl));
            return Result.success(true);
        }catch (Exception e) {
            String errorId = ErrorLogManager.getUniqueErrorCode(errorCode);
            ErrorLogManager.logError(errorId,e,"Error on retrieving windows  ");
            return Result.failure(String.format("Error on retrieving current open  windows with url %s. Error Id: %s",expectedUrl,errorId));
        }
    }

    public Result<Boolean> closeCurrentWindowOrTab(String errorCode){
        try{

            mainDriver.close();
            Object[] windowHandles=mainDriver.getWindowHandles().toArray();
            mainDriver.switchTo().window((String) windowHandles[windowHandles.length-1]);
            return Result.success(true);
        }catch (Exception e) {
            String errorId = ErrorLogManager.getUniqueErrorCode(errorCode);
            ErrorLogManager.logError(errorId,e,"Error on retrieving current windows  ");
            return Result.failure(String.format("Error on retrieving current open  windows. Error Id: %s",errorId));
        }
    }

    public Result<Boolean> switchToNewTab( String errorCode){
        try{
            mainDriver.switchTo().newWindow(WindowType.TAB);
            return Result.success(true);
        }catch (Exception e) {
            String errorId = ErrorLogManager.getUniqueErrorCode(errorCode);
            String errorMessage = "Error on switching to new tab.";
            ErrorLogManager.logError(errorId,e,errorMessage);
            return Result.failure(String.format(errorMessage.concat("Error Id: %s"),errorId));
        }
    }

    public Result<Boolean> switchToIframe(String iframeName, String errorCode){
        try{
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name(iframeName)));
            currentIframeName = iframeName;
            return Result.success(this.isInIframe());
        }catch (Exception e) {
            String errorId = ErrorLogManager.getUniqueErrorCode(errorCode);
            String errorMessage = String.format("Error on switching to frame with name: %s",iframeName);
            ErrorLogManager.logError(errorId,e,errorMessage);
            return Result.failure(String.format(errorMessage.concat("Error Id: %s"),errorId));
        }
    }

    public Result<Boolean> switchFromIframeToCurrentWebPage( String errorCode){
        try{

            mainDriver.switchTo().defaultContent();
            currentIframeName="";
            return Result.success(true);
        }catch (Exception e) {
            String errorId = ErrorLogManager.getUniqueErrorCode(errorCode);
            String errorMessage = String.format("Error on switching from frame %s to current webpage :"+ getCurrentIframeName());
            ErrorLogManager.logError(errorId,e,errorMessage);
            return Result.failure(String.format(errorMessage.concat("Error Id: %s"),errorId));
        }
    }

    public Result<Boolean> switchToNewWindow( String errorCode){
        try{
            mainDriver.switchTo().newWindow(WindowType.WINDOW);
            return Result.success(true);
        }catch (Exception e) {
            String errorId = ErrorLogManager.getUniqueErrorCode(errorCode);
            String errorMessage = "Error on switching to new window.";
            ErrorLogManager.logError(errorId,e,errorMessage);
            return Result.failure(String.format(errorMessage.concat("Error Id: %s"),errorId));
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
            ErrorLogManager.logError(errorId,e,"Error on retrieving text from alert ");
            return Result.failure("No alert present, error code: "+errorId);
        }
    }

    public Result<Boolean> validateElementIsNotVisible(By bySelector, String errorCode){
        try{
            List<WebElement> elementList = this.findElements(bySelector);
            Boolean doesListContainsWebElements = elementList.size()>0?false:true;
            return Result.success(doesListContainsWebElements);
        }catch(Exception e){
            String errorId = ErrorLogManager.getUniqueErrorCode(errorCode);
            ErrorLogManager.logError(errorId,e,String.format("Error on  retrieving web elements using selector  %s",bySelector));
            return Result.failure(String.format("Error on  retrieving web elements. error Id: ",errorId));
        }

    }


    public Result<WebElement> retryFindingElement(By bySelector, String errorCode){
        int retries = 3;
        String errorId = ErrorLogManager.getUniqueErrorCode(errorCode);

        do{
            try{
                WebElement elementList = this.findElement(bySelector);
                return Result.success(elementList);
            }catch(Exception e){
                ErrorLogManager.logError(errorId,e,String.format("Error on  retrieving web elements using selector  %s",bySelector));
            }
            retries--;
        }while(retries>0);
        return Result.failure(String.format("Error on  retrieving web elements. error Id: %s ",errorId));
    }

    public Result<WebElement> retryWaitingForElement(By bySelector, String errorCode){
        int retries = 3;
        String errorId = ErrorLogManager.getUniqueErrorCode(errorCode);

        do{
            try{
                wait.until(ExpectedConditions.visibilityOfElementLocated(bySelector));
                WebElement element = this.findElements(bySelector).get(0);
                return Result.success(element);
            }catch(Exception e){
                ErrorLogManager.logError(errorId,e,String.format("Error on  retrieving web element using selector  %s",bySelector));
            }
            retries--;
        }while(retries>0);
        return Result.failure(String.format("Error on  retrieving web element. error Id: %s ",errorId));
    }

    public String getCurrentIframeName() {
        return currentIframeName;
    }


    public Boolean isInIframe(){
        return !(Boolean) js.executeScript("return window.self === window.top;");
    }
}
