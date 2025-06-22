package testing;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features   = "src/test/resources",
        glue       = "testing",                          // ← here
        plugin     = {
                "pretty",
                "html:reports/test-report.html"
        },
        monochrome = true
)

public class CucumberTest {

}
