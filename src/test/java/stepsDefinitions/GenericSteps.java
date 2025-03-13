package stepsDefinitions;
import PageObjectModel.GeneralSelectorActions;
import ResultPattern.Result;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

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
        String errorCode =generalSelectorActions.getUniqueErrorCode( generalSelectorActions.getErrorCode()) + " " + generalSelectorActions.getCurrentDate();
        String errorMessage = "";
        Boolean expecteClickResult = false;
        Result<Boolean> clickResult = generalSelectorActions.clickElementByXpathText(linkText,errorCode);
        if(clickResult.isSuccess()){
            expecteClickResult = clickResult.getValue().get();
        }else if(clickResult.isFailure()){
            errorMessage = clickResult.getError().get();
        }

        Assert.assertTrue(expecteClickResult, "Error clicking on link: "+linkText+ " " +errorMessage);
    }

    @Then("the user validates if the string {string} is visible")
    public void validateStringIsVisible (String stringParam) {
        String errorCode =generalSelectorActions.getUniqueErrorCode( generalSelectorActions.getErrorCode()) + " " + generalSelectorActions.getCurrentDate();
        String errorMessage = "";
        Result<WebElement> elementResult = generalSelectorActions.findElementByXpathText(stringParam,errorCode);
        Boolean stringIsVisible = false;

        if(elementResult.isSuccess()){
            stringIsVisible = elementResult.getValue().get().isDisplayed();
        }else if(elementResult.isFailure()){
            errorMessage = elementResult.getError().get();
        }

        Assert.assertTrue(stringIsVisible, "Element "+stringParam+" not displayed "+errorMessage);
    }
}
