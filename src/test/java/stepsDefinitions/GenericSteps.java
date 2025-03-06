package stepsDefinitions;
import PageObjectModel.GeneralSelectorActions;
import PageObjectModel.Home;
import ResultPattern.Result;
import io.cucumber.java.en.*;
import io.cucumber.messages.types.Hook;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestContext;
public class GenericSteps {

    public GeneralSelectorActions generalSelectorActions;

    public GenericSteps(Hooks configHooks) {
        generalSelectorActions = new GeneralSelectorActions();
        generalSelectorActions.setWebDriver(configHooks.getWebDriver());
    }

    @When("the user waits for text {string} to be visible")
    public void userWaitsForTextToBeVisible(String text) {
        Result<WebElement> elementIsVisible = generalSelectorActions.waitElementByXpathText(text);
        Assert.assertTrue(elementIsVisible.getValue().get().isDisplayed(), "Element not found with text: "+text);
    }
}
