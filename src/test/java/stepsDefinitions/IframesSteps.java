package stepsDefinitions;
import PageObjectModel.IFrameSection;
import PageObjectModel.WindowOperationsSection;
import ResultPattern.Result;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.ErrorLogManager;

public class IframesSteps {

    private IFrameSection iFrameSection;
    public IframesSteps(Hooks configHooks) {
        iFrameSection = new IFrameSection();
        iFrameSection.setWebDriver(configHooks.getWebDriver());
    }

    @When("the user switch to a a Iframe with name {string}")
    public void userSwitchToIframe(String name){
        String errorMessage = "";
        boolean doesBrowserSwitchToIframe = false;
        String stepName = "the user switch to a a Iframe with name {string}";

        Result<Boolean> swtichToIframeResult = iFrameSection.switchToIframe(name,iFrameSection.getIFrameSectionErrorCode());

        if(swtichToIframeResult.isSuccess()){
            doesBrowserSwitchToIframe = swtichToIframeResult.getValue().get();
        }else if(swtichToIframeResult.isFailure()){
            errorMessage = swtichToIframeResult.getError().get();
        }
        ErrorLogManager.saveScreenShotToAllure(stepName, iFrameSection.getWebDriver(),iFrameSection.getCurrentIframeName());
        Assert.assertTrue(doesBrowserSwitchToIframe, errorMessage);
    }

}

