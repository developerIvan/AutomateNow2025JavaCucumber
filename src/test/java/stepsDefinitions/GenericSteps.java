package stepsDefinitions;
import PageObjectModel.GeneralSelectorActions;
import ResultPattern.Result;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.ErrorLogManager;
public class GenericSteps {

    public GeneralSelectorActions generalSelectorActions;

    public GenericSteps(Hooks configHooks) {
        generalSelectorActions = new GeneralSelectorActions();
        generalSelectorActions.setWebDriver(configHooks.getWebDriver());
    }

    @And("the user waits for text {string} to be visible")
    public void userWaitsForTextToBeVisible(String text) {
        String errorMessage = "";
        Result<WebElement> elementIsVisible = generalSelectorActions.waitElementByXpathText(text,generalSelectorActions.getErrorCode());
        Boolean expectedTextIsVisible = false;

        if(elementIsVisible.isSuccess()){
            expectedTextIsVisible = elementIsVisible.getValue().get().isDisplayed();
        }else if(elementIsVisible.isFailure()){
            errorMessage = elementIsVisible.getError().get();
        }

        Assert.assertTrue(expectedTextIsVisible, "Element not found with text: "+text + " "+errorMessage + "");
    }

    @When("the user clicks on the {string} link")
    public void userClicksOnTheLink(String linkText) {
        String errorMessage = "";
        Boolean expecteClickResult = false;
        Result<Boolean> clickResult = generalSelectorActions.clickElementByXpathText(linkText,generalSelectorActions.getErrorCode());
        if(clickResult.isSuccess()){
            expecteClickResult = clickResult.getValue().get();
        }else if(clickResult.isFailure()){
            errorMessage = clickResult.getError().get();
        }

        Assert.assertTrue(expecteClickResult, "Error clicking on link: "+linkText+ " " +errorMessage);
    }

    @Then("the user validates if the string {string} is visible")
    public void validateStringIsVisible (String stringParam) {
        String errorMessage = "Element with : "+stringParam + " is found but is not visible";
        Result<WebElement> elementResult = generalSelectorActions.findElementByExactXpathText(stringParam,generalSelectorActions.getErrorCode());
        Boolean stringIsVisible = false;

        if(elementResult.isSuccess()){
            ErrorLogManager.logInfo("Element found: "+elementResult.getValue().get().toString());
            ErrorLogManager.logInfo("Element is visible: "+elementResult.getValue().get().isDisplayed());
            stringIsVisible = elementResult.getValue().get().isDisplayed();

        }else if(elementResult.isFailure()){
            errorMessage = elementResult.getError().get();
        }

        Assert.assertTrue(stringIsVisible, "Element "+stringParam+" not displayed "+errorMessage);
    }

    @Then("the user scrolls to {string} text")
    public void theUserScrollsToText(String text) {
        String errorMessage = "";
        Result<Boolean> setValueResult = generalSelectorActions.scrollsToText( text, generalSelectorActions.getErrorCode());
        boolean expectedValue = false;
        if(setValueResult.isSuccess()){
            expectedValue = setValueResult.getValue().get();
        }else{
            errorMessage = setValueResult.getError().get();
        }
        Assert.assertTrue(expectedValue,  errorMessage);
    }

    @And("^the user waits for (\\d+) seconds$")
    public void theUserWaitsForSeconds(int seconds) throws InterruptedException {
       Thread.sleep(seconds*1000);
    }
}
