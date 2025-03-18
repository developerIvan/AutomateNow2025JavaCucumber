package stepsDefinitions;

import PageObjectModel.JavaScriptSection;
import ResultPattern.Result;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebElement;
import org.testng.Assert;


public class JavaScriptSectionSteps {
    private JavaScriptSection javaScriptSection;

    public JavaScriptSectionSteps(Hooks configHooks) {
        javaScriptSection = new JavaScriptSection();
        javaScriptSection.setWebDriver(configHooks.getWebDriver());
    }



    @Then("the user validates if Javascript section {string} is visible")
    public void validateStringIsVisible (String stringParam) {
        Boolean epectedStringIsVisible = false;
        String errorMessage = "";
        Result<WebElement> element = javaScriptSection.findElementByXpathText(stringParam,javaScriptSection.getJavaScriptSectionErrorCode());
           if(element.isSuccess()){
                epectedStringIsVisible = element.getValue().get().isDisplayed();
           }else{
               errorMessage = element.getError().get();
           }
        Assert.assertTrue(epectedStringIsVisible, "Element "+stringParam+" from page section not displayed,  "+errorMessage);
    }



    @When("the user clicks on the button with id {string} in javascript section")
    public void userClicksOnTheJavascriptSectionButton(String buttonId) {
        String errorMessage = "";
        Result<Boolean> clickResult = javaScriptSection.clickElementById(buttonId,javaScriptSection.getJavaScriptSectionErrorCode());
        boolean expectedValue = true;
        if(clickResult.isFailure()){
            errorMessage = clickResult.getError().get();
            expectedValue = false;
        }else{
            expectedValue = clickResult.getValue().get();
        }

        Assert.assertTrue(expectedValue, "Error clicking on link " + errorMessage);
    }


    @Then("the user validates if the delay input contains the value {string}")
    public void validateDelayInputValue(String expectedValue) {
        String errorMessage = "";
        Result<WebElement> delayInputValue = javaScriptSection.findElementBySpecificId(javaScriptSection.getDelayInputId(),javaScriptSection.getJavaScriptSectionErrorCode());
        String actualValue = "";
        if(delayInputValue.isSuccess()){
            actualValue = delayInputValue.getValue().get().getAttribute("value");
        }else{
            errorMessage = delayInputValue.getError().get();
        }
        Assert.assertEquals(actualValue, expectedValue, "Error validating delay input value: "+errorMessage);
    }


    @And("the user waits for text {string} is visible in the input field after some seconds")
    public void theUserWaitsForTextIsVisibleInTheInputFieldAfterSomeSeconds(String text) {
        String errorMessage = "";
        Result<Boolean> attributeResult = javaScriptSection.waitElementAttributeContainsTheExpectedText("value",text,javaScriptSection.getJavaScriptSectionErrorCode());
        boolean expectedValue = false;

        if(attributeResult.isFailure()){
            errorMessage = attributeResult.getError().get();
        }else{
            expectedValue = attributeResult.getValue().get();
        }
        Assert.assertTrue(expectedValue, "Error waiting for text attribute in input field with id "+javaScriptSection.getDelayInputId()+" contains the expected value "+text+" : "+errorMessage);

    }
}
