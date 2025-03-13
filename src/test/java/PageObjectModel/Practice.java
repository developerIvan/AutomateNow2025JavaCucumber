package PageObjectModel;

public class Practice extends GeneralSelectorActions  {

    private final String errorCode = "PracticeError-";
    public Practice() {
        super();
    }


    public String getPracticeErrorCode() {
        return errorCode;
    }
}
