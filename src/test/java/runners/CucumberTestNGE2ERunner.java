package runners;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/java/features",
        glue = "stepsDefinitions",
        plugin = {
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "pretty"
        },
        monochrome = true,
        tags = "@TC-005"
)
public class CucumberTestNGE2ERunner extends    AbstractTestNGCucumberTests{

    @Override
    @DataProvider(parallel = true) //This is to run the scenarios in parallel
    public Object[][] scenarios() {
        return super.scenarios();
    }

    @BeforeSuite
    public static void setup() {
        String tags = System.getProperty("cucumber.filter.tags");
        if (tags != null) {
            System.setProperty("cucumber.filter.tags", tags);
        }
    }

}
