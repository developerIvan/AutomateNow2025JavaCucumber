package PageObjectModel;

import ResultPattern.Result;

public class JavaScriptSection extends GeneralSelectorActions  {
    private final String errorCode = "JavaScriptSection01";
    public JavaScriptSection() {
        super();
    }


    public Boolean clickOnLinkSection(String linkText) {
        Result<Boolean> clickElement = clickElementByXpathText(linkText,getJavaScriptSectionErrorCode());

        if(clickElement.isFailure()){
            return false;
        }
        return true;
    }

    public String getJavaScriptSectionErrorCode() {
        return errorCode;
    }
}
