package stepsDefinitions;
import PageObjectModel.Home;
import ResultPattern.Result;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class HomeSteps {

    private Home homePage ;
    private Hooks hooks;
    public HomeSteps(Hooks configHooks) {
        homePage = new Home();
        hooks = configHooks;
        homePage.setWebDriver(hooks.getWebDriver());

    }

    @Given("User goes to web page {string}")
    public void userGoesToWebPage(String url) {
        boolean pageIsLoaded = homePage.openHomePage(url);
        Assert.assertTrue(pageIsLoaded, "Error opening page, error code : "+homePage.getHomeErrorCode());
    }
    @Then("the user validates if the string {string} is visible")
    public void validateStringIsVisible (String stringParam) {
       Result<WebElement> element = homePage.findElementByXpathText(stringParam,homePage.getHomeErrorCode());
         Assert.assertTrue(element.isSuccess(), "Element not found with text: "+stringParam + " error code: "+homePage.getHomeErrorCode());
         WebElement elementToEvaluate = element.getValue().get();
         Assert.assertTrue(elementToEvaluate.isDisplayed());
    }
}
