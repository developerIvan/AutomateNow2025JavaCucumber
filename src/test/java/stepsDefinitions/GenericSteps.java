package stepsDefinitions;
import PageObjectModel.GeneralSelectorActions;
import ResultPattern.Result;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.ErrorLogManager;
public class GenericSteps {

    public GeneralSelectorActions generalSelectorActions;

   private Boolean isAnIframeScenario ;

    public GenericSteps(Hooks configHooks) {
        generalSelectorActions = new GeneralSelectorActions();
        generalSelectorActions.setWebDriver(configHooks.getWebDriver());
        ErrorLogManager.setWebDriver(configHooks.getWebDriver());
        String scenarioName = configHooks.getScenarioName();
        isAnIframeScenario =scenarioName.toLowerCase().contains("iframe")?true:false;
    }

    @And("the user waits for text {string} to be visible")
    public void userWaitsForTheTextToBeVisible(String text) {
        String errorMessage = "";

        Result<WebElement> elementIsVisible = generalSelectorActions.waitElementByXpathText(text,generalSelectorActions.getErrorCode());
        Boolean expectedTextIsVisible = false;
        if(elementIsVisible.isSuccess()){
            expectedTextIsVisible = elementIsVisible.getValue().get().isDisplayed();
        }else if(elementIsVisible.isFailure()){
            errorMessage = elementIsVisible.getError().get();
        }
        String stepName =String.format("the user waits for text %s to be visible",text);


        if(this.isAnIframeScenario && generalSelectorActions.isInIframe()) {

            ErrorLogManager.saveScreenShotToAllure(stepName, generalSelectorActions.getWebDriver(),GeneralSelectorActions.currentIframeName);
        }else{
            ErrorLogManager.saveScreenShotToAllure(stepName,generalSelectorActions.getWebDriver());
        }
        Assert.assertTrue(expectedTextIsVisible, "Element not found with text: "+text + " "+errorMessage + "");
    }

    @Given("the user is able to see the section {string}")
    public void userValidatesTheSectionIsVisbile(String text) {
        String errorMessage = "";
        Result<WebElement> elementIsVisible = generalSelectorActions.findElementByExactXpathText(text,generalSelectorActions.getErrorCode());
        Boolean expectedTextIsVisible = false;
        if(elementIsVisible.isSuccess()){
            expectedTextIsVisible = elementIsVisible.getValue().get().isDisplayed();
        }else if(elementIsVisible.isFailure()){
            errorMessage = elementIsVisible.getError().get();
        }
        String stepName =String.format("the user is able to see the section %s",text);
        ErrorLogManager.saveScreenShotToAllure(stepName,generalSelectorActions.getWebDriver());
        Assert.assertTrue(expectedTextIsVisible, "Element not found with text: "+text + " "+errorMessage + "");
    }

    @When("the user clicks on {string}")
    public void userClicksOnTheText(String text) {
        String errorMessage = "";
        Boolean expecteClickResult = false;
        String stepName = String.format("the user clicks on %s",text);
        Result<Boolean> clickResult = generalSelectorActions.clickElementByXpathText(text,generalSelectorActions.getErrorCode());
        if(clickResult.isSuccess()){
            expecteClickResult = clickResult.getValue().get();
        }else if(clickResult.isFailure()){
            errorMessage = clickResult.getError().get();
        }

        ErrorLogManager.saveScreenShotToAllure(stepName,generalSelectorActions.getWebDriver());
        Assert.assertTrue(expecteClickResult, "Error clicking on text: "+text+ " " +errorMessage);
    }

    @And("the user clicks on text like {string}")
    public void userClicksOnText(String text) {
        String errorMessage = "";
        Boolean expecteClickResult = false;
        String stepName = String.format("the user clicks on text %s",text);
        Result<Boolean> clickResult = generalSelectorActions.clickElementByContainsXpathText(text,generalSelectorActions.getErrorCode());
        if(clickResult.isSuccess()){
            expecteClickResult = clickResult.getValue().get();
        }else if(clickResult.isFailure()){
            errorMessage = clickResult.getError().get();
        }

        if(!generalSelectorActions.isInIframe()) {
            ErrorLogManager.saveScreenShotToAllure(stepName,generalSelectorActions.getWebDriver());
        }else{
            ErrorLogManager.saveScreenShotToAllure(stepName, generalSelectorActions.getWebDriver(),GeneralSelectorActions.currentIframeName);
        }

        Assert.assertTrue(expecteClickResult, "Error clicking on text: "+text+ " " +errorMessage);
    }

    @Then("the user validates if the string {string} is visible")
    public void validateStringIsVisible (String stringParam) {
        String errorMessage = "Element with : "+stringParam + " is found but is not visible";
        Result<WebElement> elementResult = generalSelectorActions.findElementByExactXpathText(stringParam,generalSelectorActions.getErrorCode());
        Boolean stringIsVisible = false;
        if(elementResult.isSuccess()){
            stringIsVisible = elementResult.getValue().get().isDisplayed();

        }else if(elementResult.isFailure()){
            errorMessage = elementResult.getError().get().toString();
        }
        String stepName =String.format("the user validates if the string %s is visible",stringParam);

        if(!generalSelectorActions.isInIframe()) {
            ErrorLogManager.saveScreenShotToAllure(stepName,generalSelectorActions.getWebDriver());
        }else{
            ErrorLogManager.saveScreenShotToAllure(stepName, generalSelectorActions.getWebDriver(),GeneralSelectorActions.currentIframeName);
        }
        Assert.assertTrue(stringIsVisible, "Element "+stringParam+" not displayed "+errorMessage);
    }

    @And("the user scrolls to {string}")
    public void theUserScrollsTo(String text) {
        String errorMessage = "";
        Result<Boolean> setValueResult = generalSelectorActions.scrollsToText( text, generalSelectorActions.getErrorCode());
        boolean expectedValue = false;
        if(setValueResult.isSuccess()){
            expectedValue = setValueResult.getValue().get();
        }else{
            errorMessage = setValueResult.getError().get();
        }
        String stepName=String.format("the user scrolls to %s",text);
        ErrorLogManager.saveScreenShotToAllure(stepName,generalSelectorActions.getWebDriver());
        Assert.assertTrue(expectedValue,  errorMessage);
    }

    @Then("the user validates the current url is not the same as {string}")
    public void validateNewWindowUrl(String parentUrl){
        String errorMessage = "";
        String stepName=String.format("the user validates the current url is not the same as %s",parentUrl);

        Result<String> currentUrl = generalSelectorActions.getCurrentPageUrl(generalSelectorActions.getErrorCode());
        if(currentUrl.isFailure()){
            errorMessage = currentUrl.getError().get();
        }
        ErrorLogManager.saveScreenShotToAllure(stepName, generalSelectorActions.getWebDriver());
        Assert.assertNotEquals(parentUrl,currentUrl.getValue().get(), errorMessage);
    }

    @Then("the user validates the current url is the same as {string}")
    public void validateUrlIsTheSame(String parentUrl){
        String errorMessage = "";
        String stepName=String.format("the user validates the current url is  the same as %s",parentUrl);

        Result<String> currentUrl = generalSelectorActions.getCurrentPageUrl(generalSelectorActions.getErrorCode());
        if(currentUrl.isFailure()){
            errorMessage = currentUrl.getError().get();
        }
        ErrorLogManager.saveScreenShotToAllure(stepName, generalSelectorActions.getWebDriver());
        Assert.assertEquals(parentUrl,currentUrl.getValue().get(), errorMessage);
    }

}
