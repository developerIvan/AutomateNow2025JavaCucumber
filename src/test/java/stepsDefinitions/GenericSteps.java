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
        Result<WebElement> elementIsVisible = generalSelectorActions.waitElementByXpathText(text);
        Assert.assertTrue(elementIsVisible.isSuccess(), "Element not found with text: "+text + " error code: "+generalSelectorActions.getErrorCode() + "");
        Assert.assertTrue(elementIsVisible.getValue().get().isDisplayed(), "Element not found with text: "+text + " error code: "+generalSelectorActions.getErrorCode() + "");
    }

    @Then("the user validates if the string {string} is visible")
    public void validateStringIsVisible (String stringParam) {
        Result<WebElement> element = generalSelectorActions.findElementByXpathText(stringParam,generalSelectorActions.getErrorCode());
        Assert.assertTrue(element.isSuccess(), "Element not found with text: "+stringParam);
        WebElement elementToEvaluate = element.getValue().get();
        Assert.assertTrue(elementToEvaluate.isDisplayed(), "Element "+stringParam+" not displayed Error code: "+generalSelectorActions.getErrorCode());
    }
}
