package runners;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
        features = "src/test/java/features",
        glue = "steps",
        plugin = {"pretty", "html:target/cucumber-reports.html"},
        monochrome = true,
        tags = "@cucumberDemo"
)
public class CucumberTestNGRunner extends AbstractTestNGCucumberTests {
}
