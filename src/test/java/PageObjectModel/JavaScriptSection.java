package PageObjectModel;

import ResultPattern.Result;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.ErrorLogManager;

public class JavaScriptSection extends GeneralSelectorActions  {
    private final String errorCode = "JavaScriptSectionError-";
    public JavaScriptSection() {
        super();
    }


    private final String delayInputId = "delay";

    public String getJavaScriptSectionErrorCode() {
        return errorCode;
    }

    public String getDelayInputId() {
        return delayInputId;
    }

    public Result<Boolean> waitElementAttributeContainsTheExpectedText(String attribute,String expectedValue, String errorCode) {

        try {

            Result<WebElement> element = this.findElementBySpecificId(delayInputId,errorCode);

            if(element.isFailure()){
                return Result.failure("Element not found: with id selector  "+delayInputId +" Error Code:"+errorCode);
            }
            WebElement expectedElement = element.getValue().get();


            this.wait.until(ExpectedConditions.attributeContains(expectedElement,attribute,expectedValue));
            return Result.success(true);
        } catch (Exception e) {
            String errorId = ErrorLogManager.getUniqueErrorCode(errorCode);
            ErrorLogManager.logError(errorId,e,"Error on waiting WebElement");
            return Result.failure("Element with it  "+delayInputId +" with the attribute "+attribute+" does not contains the "+expectedValue+" after waiting 30 seconds Error Code:"+errorId);
        }
    }
}
