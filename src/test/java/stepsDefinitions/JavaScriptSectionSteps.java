package stepsDefinitions;

import PageObjectModel.JavaScriptSection;
import PageObjectModel.Practice;
import ResultPattern.Result;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.time.format.DateTimeFormatter;

public class JavaScriptSectionSteps {
    private JavaScriptSection javaScriptSection;

    public JavaScriptSectionSteps(Hooks configHooks) {
        javaScriptSection = new JavaScriptSection();
        javaScriptSection.setWebDriver(configHooks.getWebDriver());
    }



    @Then("the user validates if Javascript section {string} is visible")
    public void validateStringIsVisible (String stringParam) {
        String errorCode = javaScriptSection.getErrorCode()+" "+ javaScriptSection.getCurrentDate();
        Result<WebElement> element = javaScriptSection.findElementByXpathText(stringParam,errorCode);
        Assert.assertTrue(element.isSuccess(), "Element not found with text: "+stringParam + " error code: "+errorCode + "");
        WebElement elementToEvaluate = element.getValue().get();
        Assert.assertTrue(elementToEvaluate.isDisplayed(), "Element "+stringParam+" from page section not displayed, error code: "+errorCode);
    }

    @When("the user clicks on the {string} link in javascript section")
    public void userClicksOnTheLink(String linkText) {
        String errorCode = javaScriptSection.getJavaScriptSectionErrorCode()+" "+ javaScriptSection.getCurrentDate();
        Boolean clickResult = javaScriptSection.clickOnLinkSection(linkText,errorCode);
        Assert.assertTrue(clickResult, "Error clicking on link: "+linkText+ " Error code" + errorCode);
    }

    @When("the user clicks on the button with id {string} in javascript section")
    public void userClicksOnTheJavascriptSectionButton(String buttonId) {
        String errorCode = javaScriptSection.getJavaScriptSectionErrorCode()+" "+ javaScriptSection.getCurrentDate();
        String errorMessage = "";
        Result<Boolean> clickResult = javaScriptSection.clickElementById(buttonId,errorCode);
        boolean expectedValue = true;
        if(clickResult.isFailure()){
            errorMessage = clickResult.getError().get();
            expectedValue = false;
        }else{
            expectedValue = clickResult.getValue().get();
        }

        Assert.assertTrue(expectedValue, "Error clicking on link: "+buttonId+ " " + errorMessage);
    }


}
