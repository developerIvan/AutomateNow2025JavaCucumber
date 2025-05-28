package stepsDefinitions;

import PageObjectModel.PracticeSection;
import ResultPattern.Result;
import io.cucumber.java.en.*;
import org.testng.Assert;
import utils.ErrorLogManager;

public class PracticeSteps {

    private PracticeSection practiceSectionPage;

    public PracticeSteps(Hooks configHooks) {
        practiceSectionPage = new PracticeSection();
        practiceSectionPage.setWebDriver(configHooks.getWebDriver());
    }

    @Given("User goes to web page {string}")
    public void userGoesToWebPage(String url) {
        String errorMessage = "";
        boolean pageIsLoaded = false;
        String stepName = "Given User goes to web page";

        Result<Boolean> openPageResult = practiceSectionPage.openPage(url, practiceSectionPage.getPracticeErrorCode());
        if(openPageResult.isSuccess()){
            pageIsLoaded = openPageResult.getValue().get();
        }else if(openPageResult.isFailure()){
            errorMessage = openPageResult.getError().get();
        }
        ErrorLogManager.saveScreenShotToAllure(stepName, practiceSectionPage.getWebDriver());
        Assert.assertTrue(pageIsLoaded, "Error opening page"+errorMessage);
    }

    @And("the user clicks on the button {string} in practice section")
    public void userClicksOnTheLink(String linkText) {
        Boolean expectedClickResult = false;
        String errorMessage = "";
        String testStepName= "When the user clicks on the button "+linkText+" in practice section";
        Result<Boolean> clickResult = practiceSectionPage.clickElementByXpathText(linkText, practiceSectionPage.getPracticeErrorCode());
        if(clickResult.isSuccess()){
            expectedClickResult = clickResult.getValue().get();
        }else{
            errorMessage = clickResult.getError().get();
        }
        ErrorLogManager.saveScreenShotToAllure(testStepName, practiceSectionPage.getWebDriver());
        Assert.assertTrue(expectedClickResult, "Error clicking on link: "+linkText+ ":" + errorMessage);
    }

}
