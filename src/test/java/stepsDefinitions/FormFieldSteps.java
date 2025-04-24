package stepsDefinitions;

import PageObjectModel.FormFieldsSection;
import ResultPattern.Result;
import io.cucumber.java.en.*;
import org.testng.Assert;
import utils.ErrorLogManager;

public class FormFieldSteps {
    private FormFieldsSection formFieldsSection;
    private GenericSteps genericSteps;
    public FormFieldSteps(Hooks configHooks) {
        formFieldsSection = new FormFieldsSection();
        formFieldsSection.setWebDriver(configHooks.getWebDriver());
        genericSteps = new GenericSteps(formFieldsSection.getWebDriver());
        ErrorLogManager.logInfo("FormFieldsSectionSteps driver session " + configHooks.getSession());
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



    @When("the user selects the {string} option in the automation dropdown")
    public void theUserSelectsTheOptionInTheAutomationDropdown(String option) {
        String errorMessage = "";
        String stepName = "When the user selects the "+option+" option in the automation dropdown";
        Result<Boolean> setValueResult = formFieldsSection.chooseOptionFromSelectWebElement(formFieldsSection.getAutomationDropdownCssSelectorId(), option, formFieldsSection.getFormFieldsSectionErrorCode());
        boolean expectedValue = false;
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

    @When("the user enters the message {string} in the message input field")
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
    public void theUserClicksOnTheSubmitButton() {
        String errorMessage = "";
        Result<Boolean> clickResult = formFieldsSection.clickElementByXpathText("Submit", formFieldsSection.getFormFieldsSectionErrorCode());
        String stepName = "And the user clicks on the submit button";
        boolean expectedValue = false;
        if(clickResult.isSuccess()){
            expectedValue = clickResult.getValue().get();
        }else{
            errorMessage = clickResult.getError().get();
        }

      //  ErrorLogManager.saveScreenShotToAllure(stepName,formFieldsSection.getWebDriver());
        Assert.assertTrue(expectedValue,  errorMessage);

    }

    @Then("the user should see the following confirmation message {string}")
    public void validateConfirmationMessage(String message) {
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

    @Then("the user validates if the form page displays the expected text {string}")
    public void validateIfFormPageContainsTehExpectedText(String message) {
        String stepName = "Then the user validates if form page display the expected text "+message;
        this.genericSteps.validateStringIsVisible(message,stepName);
    }


    @And("^the user waits for (\\d+) seconds$")
    public void theUserWaitsForSeconds(int seconds) throws InterruptedException {
        Thread.sleep(seconds*1000);
    }

    @When("the user scrolls to {string}")
    public void theUserScrollsTo(String text) {
        String stepName = "When the user scrolls to "+text;
        this.genericSteps.theUserScrollsToText(text,stepName);
    }
}
