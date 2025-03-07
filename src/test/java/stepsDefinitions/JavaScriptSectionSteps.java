package stepsDefinitions;

import PageObjectModel.JavaScriptSection;
import PageObjectModel.Practice;
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

    @When("the user clicks on the {string} link")
    public void userClicksOnTheLink(String linkText) {
        Boolean clickResult = javaScriptSection.clickOnLinkSection(linkText);
        Assert.assertTrue(clickResult, "Error clicking on link: "+linkText+ " Error code" + javaScriptSection.getJavaScriptSectionErrorCode());
    }

    @Then("the user validates if Javascript section {string} is visible")
    public void validateStringIsVisible (String stringParam) {
        Result<WebElement> element = javaScriptSection.findElementByXpathText(stringParam,javaScriptSection.getJavaScriptSectionErrorCode());
        Assert.assertTrue(element.isSuccess(), "Element not found with text: "+stringParam + " error code: "+javaScriptSection.getJavaScriptSectionErrorCode() + "");
        WebElement elementToEvaluate = element.getValue().get();
        Assert.assertTrue(elementToEvaluate.isDisplayed(), "Element "+stringParam+" from page section not displayed, error code: "+javaScriptSection.getJavaScriptSectionErrorCode());
    }


}
