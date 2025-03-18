package stepsDefinitions;

import PageObjectModel.FormFieldsSection;
import ResultPattern.Result;
import io.cucumber.java.en.*;
import org.testng.Assert;

public class FormFieldSteps {
    private FormFieldsSection formFieldsSection;

    public FormFieldSteps(Hooks configHooks) {
        formFieldsSection = new FormFieldsSection();
        formFieldsSection.setWebDriver(configHooks.getWebDriver());
    }

    @And("the user enter the password {string} in the password input field")
    public void userSetThePasswordValueInFormFieldInput(String value) {
        String errorMessage = "";
        Result<Boolean> setValueResult = formFieldsSection.fillInputFieldByText("Password ", value, formFieldsSection.getFormFieldsSectionErrorCode());
        boolean expectedValue = false;
        if(setValueResult.isSuccess()){
            expectedValue = setValueResult.getValue().get();
        }else{
            errorMessage = setValueResult.getError().get();
        }
        Assert.assertTrue(expectedValue,  errorMessage);
    }

    @And("the user select the checkbox value {string} under the label {string}")
    public void userSelectsTheInputValueWithLabel(String value, String label) {
        String errorMessage = "";
        Result<Boolean> setValueResult = formFieldsSection.selectOptionByValueAttribute(label, formFieldsSection.getFormFieldsSectionErrorCode(),value);
        boolean expectedValue = false;
        if(setValueResult.isSuccess()){
            expectedValue = setValueResult.getValue().get();
        }else{
            errorMessage = setValueResult.getError().get();
        }
        Assert.assertTrue(expectedValue,  errorMessage);
    }


    @When("the user enters {string} in the field name")
    public void theUserEntersInTheFieldName(String value) {
        String errorMessage = "";
        Result<Boolean> setValueResult = formFieldsSection.fillInputFieldByDataSetId(formFieldsSection.getNameCssSelectorId(), value, formFieldsSection.getFormFieldsSectionErrorCode());
        boolean expectedValue = false;
        if(setValueResult.isSuccess()){
            expectedValue = setValueResult.getValue().get();
        }else{
            errorMessage = setValueResult.getError().get();
        }
        Assert.assertTrue(expectedValue,  errorMessage);
    }



    @When("the user selects the {string} option in the automation dropdown")
    public void theUserSelectsTheOptionInTheAutomationDropdown(String option) {
        String errorMessage = "";
        Result<Boolean> setValueResult = formFieldsSection.chooseOptionFromSelectWebElement(formFieldsSection.getAutomationDropdownCssSelectorId(), option, formFieldsSection.getFormFieldsSectionErrorCode());
        boolean expectedValue = false;
        if(setValueResult.isSuccess()){
            expectedValue = setValueResult.getValue().get();
        }else{
            errorMessage = setValueResult.getError().get();
        }
        Assert.assertTrue(expectedValue,  errorMessage);
    }

    @And("the user enters the email {string} in the email input field")
    public void theUserEntersTheEmailInTheEmailInputField(String email){
        String errorMessage = "";
        Result<Boolean> setValueResult = formFieldsSection.fillInputFieldByDataSetId(formFieldsSection.getEmailCssSelectorId(), formFieldsSection.getFormFieldsSectionErrorCode(),email);
        boolean expectedValue = false;
        if(setValueResult.isSuccess()){
            expectedValue = setValueResult.getValue().get();
        }else{
            errorMessage = setValueResult.getError().get();
        }
        Assert.assertTrue(expectedValue,  errorMessage);
    }

    @When("the user enters the message {string} in the message input field")
    public void theUserEntersTheMessageInTheMessageInputField(String message) {
        String errorMessage = "";
        Result<Boolean> setValueResult = formFieldsSection.fillInputFieldByDataSetId(formFieldsSection.getMessageCssSelectorId(), formFieldsSection.getFormFieldsSectionErrorCode(),message);
        boolean expectedValue = false;
        if(setValueResult.isSuccess()){
            expectedValue = setValueResult.getValue().get();
        }else{
            errorMessage = setValueResult.getError().get();
        }
        Assert.assertTrue(expectedValue,  errorMessage);
    }

    @And("the user clicks on the submit button")
    public void theUserClicksOnTheSubmitButton() {
        String errorMessage = "";
        Result<Boolean> clickResult = formFieldsSection.clickElementByXpathText("Submit", formFieldsSection.getFormFieldsSectionErrorCode());
        boolean expectedValue = false;
        if(clickResult.isSuccess()){
            expectedValue = clickResult.getValue().get();
        }else{
            errorMessage = clickResult.getError().get();
        }
        Assert.assertTrue(expectedValue,  errorMessage);
    }

    @Then("the user should see the following confirmation message {string}")
    public void validateConfirmationMessage(String message) {
        String errorMessage = "";
        String actualMessage="";
        Result<String> messageResult = formFieldsSection.getAlertText(formFieldsSection.getFormFieldsSectionErrorCode());

        if(messageResult.isSuccess()){
            actualMessage = messageResult.getValue().get();
        }else{
            errorMessage = messageResult.getError().get();
        }

        Assert.assertEquals(actualMessage, message, errorMessage);
    }
}
