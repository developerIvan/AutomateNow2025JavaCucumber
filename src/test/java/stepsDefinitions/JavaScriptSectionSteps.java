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

    @When("the user clicks on the {string} link in javascript section")
    public void userClicksOnTheLink(String linkText) {
        Boolean expectedClickResult = false;
        String errorMessage = "";

        Result<Boolean> clickResult = javaScriptSection.clickElementByXpathText(linkText,javaScriptSection.getJavaScriptSectionErrorCode());
        if(clickResult.isSuccess()){
            expectedClickResult = clickResult.getValue().get();
        }else{
            errorMessage = clickResult.getError().get();
        }
        Assert.assertTrue(expectedClickResult, "Error clicking on link: "+linkText+ ":" + errorMessage);
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


}
