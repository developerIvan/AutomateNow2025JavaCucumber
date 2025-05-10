package stepsDefinitions;
import PageObjectModel.GeneralSelectorActions;
import ResultPattern.Result;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.ErrorLogManager;
public class GenericSteps {

    public GeneralSelectorActions generalSelectorActions;


    public GenericSteps(Hooks configHooks) {
        generalSelectorActions = new GeneralSelectorActions();
        generalSelectorActions.setWebDriver(configHooks.getWebDriver());
        ErrorLogManager.setWebDriver(configHooks.getWebDriver());
    }

    public GenericSteps(WebDriver driver){
        generalSelectorActions = new GeneralSelectorActions();
        generalSelectorActions.setWebDriver(driver);
        ErrorLogManager.setWebDriver(driver);
        ErrorLogManager.logInfo("GenericSteps driver session " + driver);
    }

    public void userWaitsForTextToBeVisible(String text,String stepName) {
        String errorMessage = "";
        Result<WebElement> elementIsVisible = generalSelectorActions.waitElementByXpathText(text,generalSelectorActions.getErrorCode());
        Boolean expectedTextIsVisible = false;
        if(elementIsVisible.isSuccess()){
            expectedTextIsVisible = elementIsVisible.getValue().get().isDisplayed();
        }else if(elementIsVisible.isFailure()){
            errorMessage = elementIsVisible.getError().get();
        }
        ErrorLogManager.saveScreenShotToAllure(stepName);
        Assert.assertTrue(expectedTextIsVisible, "Element not found with text: "+text + " "+errorMessage + "");
    }

    @And("the user waits for text {string} to be visible")
    public void userWaitsForTheTextToBeVisible(String text) {
        String errorMessage = "";
        Result<WebElement> elementIsVisible = generalSelectorActions.waitElementByXpathText(text,generalSelectorActions.getErrorCode());
        Boolean expectedTextIsVisible = false;
        if(elementIsVisible.isSuccess()){
            expectedTextIsVisible = elementIsVisible.getValue().get().isDisplayed();
        }else if(elementIsVisible.isFailure()){
            errorMessage = elementIsVisible.getError().get();
        }
        String stepName =String.format("the user waits for text %s to be visible",text);
        ErrorLogManager.saveScreenShotToAllure(stepName);
        Assert.assertTrue(expectedTextIsVisible, "Element not found with text: "+text + " "+errorMessage + "");
    }

    @When("the user clicks on the {string} link")
    public void userClicksOnTheLink(String linkText) {
        String errorMessage = "";
        Boolean expecteClickResult = false;
        String stepName = "When the user clicks on the "+linkText+" link";
        Result<Boolean> clickResult = generalSelectorActions.clickElementByXpathText(linkText,generalSelectorActions.getErrorCode());
        if(clickResult.isSuccess()){
            expecteClickResult = clickResult.getValue().get();
        }else if(clickResult.isFailure()){
            errorMessage = clickResult.getError().get();
        }
        ErrorLogManager.saveScreenShotToAllure(stepName);
        Assert.assertTrue(expecteClickResult, "Error clicking on link: "+linkText+ " " +errorMessage);
    }

    @Then("the user validates if the string {string} is visible")
    public void validateStringIsVisible (String stringParam) {
        String errorMessage = "Element with : "+stringParam + " is found but is not visible";
        Result<WebElement> elementResult = generalSelectorActions.findElementByExactXpathText(stringParam,generalSelectorActions.getErrorCode());
        Boolean stringIsVisible = false;
        if(elementResult.isSuccess()){
            stringIsVisible = elementResult.getValue().get().isDisplayed();

        }else if(elementResult.isFailure()){
            errorMessage = elementResult.getError().get();
        }
        String stepName =String.format("the user validates if the string %s is visible",stringParam);
        ErrorLogManager.saveScreenShotToAllure(stepName);
        Assert.assertTrue(stringIsVisible, "Element "+stringParam+" not displayed "+errorMessage);
    }

    @When("the user scrolls to {string}")
    public void theUserScrollsTo(String text) {
        String errorMessage = "";
        Result<Boolean> setValueResult = generalSelectorActions.scrollsToText( text, generalSelectorActions.getErrorCode());
        boolean expectedValue = false;
        if(setValueResult.isSuccess()){
            expectedValue = setValueResult.getValue().get();
        }else{
            errorMessage = setValueResult.getError().get();
        }
        String stepName=String.format("the user scrolls to %s",text);
        ErrorLogManager.saveScreenShotToAllure(stepName,generalSelectorActions.getWebDriver());
        Assert.assertTrue(expectedValue,  errorMessage);
    }

}
