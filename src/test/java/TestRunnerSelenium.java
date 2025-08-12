import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        plugin = {"pretty",
                "html:target/cucumber-reports/html-report.html",
                "json:target/cucumber-reports/cucumber.json" },
        features = {"src/test/resources/feature"},
        glue = {"/selenium_step_definition"}
)

public class TestRunnerSelenium extends AbstractTestNGCucumberTests {
}
