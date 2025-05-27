package PageObjectModel;

public class PracticeSection extends GeneralSelectorActions  {

    private final String errorCode = "PracticeError-";
    public PracticeSection() {
        super();
    }


    public String getPracticeErrorCode() {
        return errorCode;
    }
}
