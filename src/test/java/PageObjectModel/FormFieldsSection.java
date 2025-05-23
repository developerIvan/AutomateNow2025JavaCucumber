package PageObjectModel;

public class FormFieldsSection extends GeneralSelectorActions {
    private final String errorCode = "FormFieldsSectionError-";
    public FormFieldsSection() {
        super();
    }



    private final String nameCssSelectorId = "name-input";
    private final String automationDropdownCssSelectorId = "[data-testid='automation']";
    private final String emailCssSelectorId ="email";
    private  final String messageCssSelectorId ="message";

    private final String submitButtonSelectorId = "[data-testid='submit-btn']";


    public String getFormFieldsSectionErrorCode() {
        return errorCode;
    }

    public String getNameCssSelectorId() {
        return nameCssSelectorId;
    }

    public String getAutomationDropdownCssSelectorId() {
        return automationDropdownCssSelectorId;
    }

    public String getEmailCssSelectorId() {
        return emailCssSelectorId;
    }

    public String getMessageCssSelectorId() {
        return messageCssSelectorId;
    }

    public String getSubmitButtonSelectorId() {
        return submitButtonSelectorId;
    }
}
