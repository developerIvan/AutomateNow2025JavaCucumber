package stepsDefinitions;

import PageObjectModel.Practice;
import ResultPattern.Result;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
public class PracticeSteps {

    private Practice practicePage;

    public PracticeSteps(Hooks configHooks) {
        practicePage = new Practice();
        practicePage.setWebDriver(configHooks.getWebDriver());
    }

    @Then("the user validates if section {string} from Practice page is visible")
    public void validateStringIsVisible (String stringParam) {
        Result<WebElement> element = practicePage.findElementByXpathText(stringParam,practicePage.getPracticeErrorCode());
        Assert.assertTrue(element.isSuccess(), "Element not found with text: "+stringParam + " error code: "+practicePage.getPracticeErrorCode() + "");
        WebElement elementToEvaluate = element.getValue().get();
        Assert.assertTrue(elementToEvaluate.isDisplayed(), "Element "+stringParam+" from page section not displayed, error code: "+practicePage.getPracticeErrorCode());
    }
}
