package PageObjectModel;

import ResultPattern.Result;

public class JavaScriptSection extends GeneralSelectorActions  {
    private final String errorCode = "JavaScriptSectionError-";
    public JavaScriptSection() {
        super();
    }




    public String getJavaScriptSectionErrorCode() {
        return errorCode;
    }
}
