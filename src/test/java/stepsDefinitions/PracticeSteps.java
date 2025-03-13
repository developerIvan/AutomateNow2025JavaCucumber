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

    @Given("User goes to web page {string}")
    public void userGoesToWebPage(String url) {
        String errorMessage = "";
        boolean pageIsLoaded = false;
        Result<Boolean> openPageResult = practicePage.openPage(url,practicePage.getPracticeErrorCode());
        if(openPageResult.isSuccess()){
            pageIsLoaded = openPageResult.getValue().get();
        }else if(openPageResult.isFailure()){
            errorMessage = openPageResult.getError().get();
        }
        Assert.assertTrue(pageIsLoaded, "Error opening page"+errorMessage);
    }

    @Then("the user validates if section {string} from Practice page is visible")
    public void validateStringIsVisible (String stringParam) {
        String errorMessage = "";
        Result<WebElement> element = practicePage.findElementByXpathText(stringParam,practicePage.getPracticeErrorCode());
        Boolean epectedStringIsVisible = false;
        if(element.isSuccess()){
            epectedStringIsVisible = element.getValue().get().isDisplayed();
        }else if(element.isFailure()){
            errorMessage = element.getError().get();
        }

        Assert.assertTrue(epectedStringIsVisible, "Element "+stringParam+" from page section not displayed, "+errorMessage);
    }
}
