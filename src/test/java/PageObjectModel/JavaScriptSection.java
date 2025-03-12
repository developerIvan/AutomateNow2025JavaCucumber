package PageObjectModel;

import ResultPattern.Result;

public class JavaScriptSection extends GeneralSelectorActions  {
    private final String errorCode = "JavaScriptSection01";
    public JavaScriptSection() {
        super();
    }




    public String getJavaScriptSectionErrorCode() {
        return errorCode;
    }
}
