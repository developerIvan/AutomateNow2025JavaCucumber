package stepsDefinitions;

import PageObjectModel.Practice;
import ResultPattern.Result;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.ErrorLogManager;

public class PracticeSteps {

    private Practice practicePage;
    private GenericSteps genericSteps;
    public PracticeSteps(Hooks configHooks) {
        practicePage = new Practice();
        practicePage.setWebDriver(configHooks.getWebDriver());
        genericSteps = new GenericSteps(practicePage.getWebDriver());
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
    public void validateSectionIsVisible (String stringParam) {
        String testStepName = "Then the user validates if section "+stringParam+" from Practice page is visible";
        this.genericSteps.validateStringIsVisible(stringParam);

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

    @Then("the user validates if the welcome message {string} is visible")
    public void validateIfWelcomeStringIsVisible (String stringParam) {
       String testSteps =  "the user validates if the welcome message "+stringParam+" is visible";
       this.genericSteps.validateStringIsVisible(stringParam);
    }

    @And("the user waits for text {string} to be visible in Practice Section")
    public void waitsForTextTobeVisibleInPracticeSection (String stringParam) {
        String testSteps =  "the user validates if the welcome message "+stringParam+" is visible";
        this.genericSteps.userWaitsForTextToBeVisible(stringParam,testSteps);
    }
}
