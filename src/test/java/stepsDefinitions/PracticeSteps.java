package stepsDefinitions;

import PageObjectModel.Practice;
import ResultPattern.Result;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.ErrorLogManager;

public class PracticeSteps {

    private Practice practicePage;

    public PracticeSteps(Hooks configHooks) {
        practicePage = new Practice();
        practicePage.setWebDriver(configHooks.getWebDriver());
        ErrorLogManager.logInfo("GenericSteps driver session " + configHooks.getSession());
    }

    @Given("User goes to web page {string}")
    public void userGoesToWebPage(String url) {
        String errorMessage = "";
        boolean pageIsLoaded = false;
        String stepName = "Given User goes to web page";

        Result<Boolean> openPageResult = practicePage.openPage(url,practicePage.getPracticeErrorCode());
        if(openPageResult.isSuccess()){
            pageIsLoaded = openPageResult.getValue().get();
        }else if(openPageResult.isFailure()){
            errorMessage = openPageResult.getError().get();
        }
        ErrorLogManager.saveScreenShotToAllure(stepName,practicePage.getWebDriver());
        Assert.assertTrue(pageIsLoaded, "Error opening page"+errorMessage);
    }

    @Then("the user validates if section {string} from Practice page is visible")
    public void validateStringIsVisible (String stringParam) {
        String errorMessage = "";
        Result<WebElement> element = practicePage.findElementByXpathText(stringParam,practicePage.getPracticeErrorCode());
        Boolean epectedStringIsVisible = false;
        String testStepName = "Then the user validates if section "+stringParam+" from Practice page is visible";
        if(element.isSuccess()){
            epectedStringIsVisible = element.getValue().get().isDisplayed();
        }else if(element.isFailure()){
            errorMessage = element.getError().get();
        }
        ErrorLogManager.saveScreenShotToAllure(testStepName,practicePage.getWebDriver());


        Assert.assertTrue(epectedStringIsVisible, "Element "+stringParam+" from page section not displayed, "+errorMessage);
    }

    @When("the user clicks on the button {string} in practice section")
    public void userClicksOnTheLink(String linkText) {
        Boolean expectedClickResult = false;
        String errorMessage = "";
        String testStepName= "When the user clicks on the button "+linkText+" in practice section";
        Result<Boolean> clickResult = practicePage.clickElementByXpathText(linkText,practicePage.getPracticeErrorCode());
        if(clickResult.isSuccess()){
            expectedClickResult = clickResult.getValue().get();
        }else{
            errorMessage = clickResult.getError().get();
        }
        ErrorLogManager.saveScreenShotToAllure(testStepName,practicePage.getWebDriver());
        Assert.assertTrue(expectedClickResult, "Error clicking on link: "+linkText+ ":" + errorMessage);
    }
}
