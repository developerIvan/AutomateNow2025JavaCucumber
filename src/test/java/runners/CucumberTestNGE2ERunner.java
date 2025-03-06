package runners;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/java/features",
        glue = "stepsDefinitions",
        plugin = {"pretty", "html:target/cucumber-reports.html"},
        monochrome = true,
        tags = "@E2E"
)
public class CucumberTestNGE2ERunner extends    AbstractTestNGCucumberTests{
    @Override
    @DataProvider(parallel = true) // Permite ejecución en paralelo
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
