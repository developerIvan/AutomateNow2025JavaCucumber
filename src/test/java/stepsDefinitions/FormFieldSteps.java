package stepsDefinitions;

import PageObjectModel.FormFieldsSection;
import ResultPattern.Result;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.testng.Assert;
import utils.ErrorLogManager;

public class FormFieldSteps {
    private FormFieldsSection formFieldsSection;

    public FormFieldSteps(Hooks configHooks) {
        formFieldsSection = new FormFieldsSection();
        formFieldsSection.setWebDriver(configHooks.getWebDriver());
    }

    @And("the user enter the password {string} in the password input field")
    public void userSetThePasswordValueInFormFieldInput(String value) {
        String errorMessage = "";
        String stepName = "And the user enter the password "+value+" in the password input field";
        Result<Boolean> setValueResult = formFieldsSection.fillInputFieldByText("Password ", value, formFieldsSection.getFormFieldsSectionErrorCode());
        boolean expectedValue = false;
        if(setValueResult.isSuccess()){
            expectedValue = setValueResult.getValue().get();
        }else{
            errorMessage = setValueResult.getError().get();
        }
        ErrorLogManager.saveScreenShotToAllure(stepName,formFieldsSection.getWebDriver());
        Assert.assertTrue(expectedValue,  errorMessage);
    }

    @And("the user select the checkbox value {string} under the label {string}")
    public void userSelectsTheInputValueWithLabel(String value, String label) {
        String errorMessage = "";
        String stepName = "And the user select the checkbox value "+value+" under the label "+label;
        Result<Boolean> setValueResult = formFieldsSection.selectOptionByValueAttribute(label, formFieldsSection.getFormFieldsSectionErrorCode(),value);
        boolean expectedValue = false;
        if(setValueResult.isSuccess()){
            expectedValue = setValueResult.getValue().get();
        }else{
            errorMessage = setValueResult.getError().get();
        }
        ErrorLogManager.saveScreenShotToAllure(stepName,formFieldsSection.getWebDriver());
        Assert.assertTrue(expectedValue,  errorMessage);
    }


    @When("the user enters {string} in the field name")
    public void theUserEntersInTheFieldName(String value) {
        String errorMessage = "";
        String stepName = "When the user enters "+value+" in the field name";
        Result<Boolean> setValueResult = formFieldsSection.fillInputFieldByDataSetId(formFieldsSection.getNameCssSelectorId(),formFieldsSection.getFormFieldsSectionErrorCode(),value);
        boolean expectedValue = false;
        if(setValueResult.isSuccess()){
            expectedValue = setValueResult.getValue().get();
        }else{
            errorMessage = setValueResult.getError().get();
        }
        ErrorLogManager.saveScreenShotToAllure(stepName,formFieldsSection.getWebDriver());
        Assert.assertTrue(expectedValue,  errorMessage);
    }



    @And("the user selects the {string} option in the automation dropdown")
    public void theUserSelectsTheOptionInTheAutomationDropdown(String option) {
        String errorMessage = "";
        String stepName = "When the user selects the "+option+" option in the automation dropdown";
        boolean expectedValue = false;

        Result<Boolean> setValueResult = formFieldsSection.scrollsToElement(By.cssSelector(formFieldsSection.getAutomationDropdownCssSelectorId()),formFieldsSection.getErrorCode());
        if(setValueResult.isFailure()){
            ErrorLogManager.saveScreenShotToAllure(stepName,formFieldsSection.getWebDriver());
            Assert.fail(setValueResult.getError().toString());
        }

        setValueResult = formFieldsSection.chooseOptionFromSelectWebElement(formFieldsSection.getAutomationDropdownCssSelectorId(), option, formFieldsSection.getFormFieldsSectionErrorCode());

        if(setValueResult.isSuccess()){
            expectedValue = setValueResult.getValue().get();
        }else{
            errorMessage = setValueResult.getError().get();
        }
        ErrorLogManager.saveScreenShotToAllure(stepName,formFieldsSection.getWebDriver());
        Assert.assertTrue(expectedValue,  errorMessage);
    }

    @And("the user enters the email {string} in the email input field")
    public void theUserEntersTheEmailInTheEmailInputField(String email){
        String errorMessage = "";
        String stepName = "And the user enters the email "+email+" in the email input field";
        Result<Boolean> setValueResult = formFieldsSection.fillInputFieldByDataSetId(formFieldsSection.getEmailCssSelectorId(), formFieldsSection.getFormFieldsSectionErrorCode(),email);
        boolean expectedValue = false;
        if(setValueResult.isSuccess()){
            expectedValue = setValueResult.getValue().get();
        }else{
            errorMessage = setValueResult.getError().get();
        }
        ErrorLogManager.saveScreenShotToAllure(stepName,formFieldsSection.getWebDriver());
        Assert.assertTrue(expectedValue,  errorMessage);
    }


    @Then("the user scrolls to automation select option")
    public void scrollToSelector(){
        String errorMessage = "";
        Result<Boolean> setValueResult = formFieldsSection.scrollsToElement(By.cssSelector(formFieldsSection.getAutomationDropdownCssSelectorId()),formFieldsSection.getErrorCode());
        boolean expectedValue = false;
        if(setValueResult.isSuccess()){
            expectedValue = setValueResult.getValue().get();
        }else{
            errorMessage = setValueResult.getError().get();
        }
        String stepName=String.format("the user scrolls to automation select option");
        ErrorLogManager.saveScreenShotToAllure(stepName,formFieldsSection.getWebDriver());
        Assert.assertTrue(expectedValue,  errorMessage);
    }

    @And("the user enters the message {string}")
    public void theUserEntersTheMessageInTheMessageInputField(String message) {
        String errorMessage = "";
        String stepName = "When the user enters the message "+message+" in the message input field";
        Result<Boolean> setValueResult = formFieldsSection.fillInputFieldByDataSetId(formFieldsSection.getMessageCssSelectorId(), formFieldsSection.getFormFieldsSectionErrorCode(),message);
        boolean expectedValue = false;
        if(setValueResult.isSuccess()){
            expectedValue = setValueResult.getValue().get();
        }else{
            errorMessage = setValueResult.getError().get();
        }
        ErrorLogManager.saveScreenShotToAllure(stepName,formFieldsSection.getWebDriver());
        Assert.assertTrue(expectedValue,  errorMessage);
    }

    @And("the user clicks on the submit button")
    public void theUserClicksOnTheSubmitButton() throws InterruptedException {
        String errorMessage = "";

        Result<Boolean> scrollToSubmitButton = formFieldsSection.scrollsToElement(By.cssSelector(formFieldsSection.getSubmitButtonSelectorId()),formFieldsSection.getErrorCode());
        if(scrollToSubmitButton.isFailure()){
           Assert.fail(scrollToSubmitButton.getError().toString());
        }
        Thread.sleep(4*1000);
        Result<Boolean> clickResult = formFieldsSection.clickElementByCssSelector(formFieldsSection.getSubmitButtonSelectorId(), formFieldsSection.getFormFieldsSectionErrorCode());
        String stepName = "And the user clicks on the submit button";
        boolean expectedValue = false;
        if(clickResult.isSuccess()){
            expectedValue = clickResult.getValue().get();
        }else{
            errorMessage = clickResult.getError().get();
        }
      //Commented line
      //  ErrorLogManager.saveScreenShotToAllure(stepName,formFieldsSection.getWebDriver());
        Assert.assertTrue(expectedValue,  errorMessage);

    }

    @Then("the user should see the following confirmation message {string}")
    public void validateConfirmationMessageIsDisplayed(String message) {
        String errorMessage = "";
        String actualMessage="";
        Result<String> messageResult = formFieldsSection.getAlertText(formFieldsSection.getFormFieldsSectionErrorCode());
        String stepName = "Then the user should see the following confirmation message "+message;
        if(messageResult.isSuccess()){
            actualMessage = messageResult.getValue().get();
        }else{
            errorMessage = messageResult.getError().get();
        }
        ErrorLogManager.saveScreenShotToAllure(stepName,formFieldsSection.getWebDriver());
        Assert.assertEquals(actualMessage, message, errorMessage);
    }


    @Then("the user should not see the following confirmation message {string}")
    public void validateConfirmationMessageIsNotDisplayed(String message) {
        String errorMessage = "";
        String actualMessage="";

        Result<Boolean> webElementFindResult = formFieldsSection.validateElementIsNotVisible( By.xpath(String.format("//*[contains(text(),'%s')]",message)),formFieldsSection.getFormFieldsSectionErrorCode());
        String stepName = String.format("Then the user should see the following confirmation message %s",message);
        if(webElementFindResult.isFailure()){
            Assert.fail(webElementFindResult.getError().get());
        }
        ErrorLogManager.saveScreenShotToAllure(stepName,formFieldsSection.getWebDriver());
        Assert.assertFalse(webElementFindResult.getValue().get(), errorMessage);
    }

    @And("^the user waits for (\\d+) seconds$")
    public void theUserWaitsForSeconds(int seconds) throws InterruptedException {
        Thread.sleep(seconds*1000);
    }


}
