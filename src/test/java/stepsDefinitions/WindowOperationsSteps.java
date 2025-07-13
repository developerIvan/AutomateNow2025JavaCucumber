package stepsDefinitions;
import ResultPattern.Result;
import io.cucumber.java.en.*;
import org.testng.Assert;
import utils.ErrorLogManager;

import PageObjectModel.WindowOperationsSection;

public class WindowOperationsSteps {

    private WindowOperationsSection windowOperationsSection;
    public WindowOperationsSteps(Hooks configHooks) {
        windowOperationsSection = new WindowOperationsSection();
        windowOperationsSection.setWebDriver(configHooks.getWebDriver());
    }

    @And("the user switch to a new window")
    public void userSwitchToNewWindow(){
        String errorMessage = "";
        boolean IsTheNewWindowOpen = false;
        String stepName = "the user switch to a new window";

        Result<Boolean> openNewWindowResult = windowOperationsSection.switchToNewWindow(windowOperationsSection.getErrorCode());
        if(openNewWindowResult.isSuccess()){
            IsTheNewWindowOpen = openNewWindowResult.getValue().get();
        }else if(openNewWindowResult.isFailure()){
            errorMessage = openNewWindowResult.getError().get();
        }
        ErrorLogManager.saveScreenShotToAllure(stepName, windowOperationsSection.getWebDriver());
        Assert.assertTrue(IsTheNewWindowOpen, errorMessage);
    }

    @And("the user switch to a new tab or window with url {string}")
    public void userSwitchToNewTab(String expectedUrl){
        String errorMessage = "";
        boolean IsTheNewTabOpen = false;
        String stepName = "the user switch to a new tab";
        Result<Object[]> currentWindows = windowOperationsSection.getCurrentWindows(windowOperationsSection.getErrorCode());
        if(currentWindows.isFailure()){
            errorMessage = currentWindows.getError().get();
            Assert.fail(errorMessage);
        }

        Result<Boolean> openNewWindowResult = windowOperationsSection.switchToWindowOrTab(currentWindows.getValue().get(),expectedUrl,windowOperationsSection.getErrorCode());
        if(openNewWindowResult.isSuccess()){
            IsTheNewTabOpen = openNewWindowResult.getValue().get();
        }else if(openNewWindowResult.isFailure()){
            errorMessage = openNewWindowResult.getError().get();
        }
        ErrorLogManager.saveScreenShotToAllure(stepName, windowOperationsSection.getWebDriver());
        Assert.assertTrue(IsTheNewTabOpen, errorMessage);
    }


    @But("the user closes the current tab or window")
    public void userClosesTheCurrentTab(){
        String errorMessage = "";
        boolean IsTheNewTabClosed = false;
        String stepName = "the user closes the current tab";


        Result<Boolean> CloseCurrentTab = windowOperationsSection.closeCurrentWindowOrTab(windowOperationsSection.getErrorCode());
        if(CloseCurrentTab.isSuccess()){
            IsTheNewTabClosed = CloseCurrentTab.getValue().get();
        }else if(CloseCurrentTab.isFailure()){
            errorMessage = CloseCurrentTab.getError().get();
        }
        ErrorLogManager.saveScreenShotToAllure(stepName, windowOperationsSection.getWebDriver());
        Assert.assertTrue(IsTheNewTabClosed, errorMessage);
    }



}
