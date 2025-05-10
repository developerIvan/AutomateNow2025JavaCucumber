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
