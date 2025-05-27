package stepsDefinitions;
import ResultPattern.Result;
import io.cucumber.core.gherkin.Step;
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
    public void userSwitchToNewWindow(Step step){
        String errorMessage = "";
        boolean IsTheNewWindowOpen = false;
        String stepName = step.toString();

        Result<Boolean> openNewWindowResult = windowOperationsSection.switchToNewWindow(windowOperationsSection.getErrorCode());
        if(openNewWindowResult.isSuccess()){
            IsTheNewWindowOpen = openNewWindowResult.getValue().get();
        }else if(openNewWindowResult.isFailure()){
            errorMessage = openNewWindowResult.getError().get();
        }
        ErrorLogManager.saveScreenShotToAllure(stepName, windowOperationsSection.getWebDriver());
        Assert.assertTrue(IsTheNewWindowOpen, errorMessage);
    }

    @And("the user switch to a new tab")
    public void userSwitchToNewTab(){
        String errorMessage = "";
        boolean IsTheNewTabOpen = false;
        String stepName = "the user switch to a new tab";
        Result<Object[]> currentWindows = windowOperationsSection.getCurrentWindows(windowOperationsSection.getErrorCode());
        if(currentWindows.isFailure()){
            errorMessage = currentWindows.getError().get();
            Assert.fail(errorMessage);
        }

        Result<Boolean> openNewWindowResult = windowOperationsSection.switchToWindowByIndex(currentWindows.getValue().get(),1,windowOperationsSection.getErrorCode());
        if(openNewWindowResult.isSuccess()){
            IsTheNewTabOpen = openNewWindowResult.getValue().get();
        }else if(openNewWindowResult.isFailure()){
            errorMessage = openNewWindowResult.getError().get();
        }
        ErrorLogManager.saveScreenShotToAllure(stepName, windowOperationsSection.getWebDriver());
        Assert.assertTrue(IsTheNewTabOpen, errorMessage);
    }




}
