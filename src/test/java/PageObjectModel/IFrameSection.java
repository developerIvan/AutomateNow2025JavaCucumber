package PageObjectModel;

public class IFrameSection extends GeneralSelectorActions {

    private final String errorCode = "IframeSectionError-";

    public IFrameSection(){super();}

    public String getIFrameSectionErrorCode() {
        return errorCode;
    }
}
