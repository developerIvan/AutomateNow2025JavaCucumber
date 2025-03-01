package runners;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
        features = "src/test/java/features",
        glue = "steps",
        plugin = {"pretty", "html:target/cucumber-reports.html"},
        monochrome = true
)
public class CucumberTestNGRunner extends AbstractTestNGCucumberTests {
}
