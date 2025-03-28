package runners;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/java/features",
        glue = "stepsDefinitions",
        plugin = {
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "pretty"
        },
        monochrome = true,
        tags = "@E2E"
)
public class CucumberTestNGE2ERunner extends    AbstractTestNGCucumberTests{

    /*Todo implement the setup class to run the scenarios in parallel */
    /*  @Override
    public void setUpClass(ITestContext context) {

     super.setUpClass(context);
    }*/

    @Override
    @DataProvider(parallel = true) //This is to run the scenarios in parallel
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
