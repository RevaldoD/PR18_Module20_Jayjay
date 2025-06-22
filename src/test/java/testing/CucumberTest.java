package testing;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/testing/TestApi",
        glue     = "testing",
        plugin   = {
                "pretty",
                "html:reports/api-cucumber-report.html"
        },
        monochrome = true
)
public class CucumberTest { }

