package PageObjectModel;

import ResultPattern.Result;

public class Practice extends GeneralSelectorActions  {

    private final String errorCode = "Practice01";
    public Practice() {
        super();
    }

    public String getPracticeErrorCode() {
        return errorCode;
    }
}
