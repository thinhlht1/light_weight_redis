package code.learning.unittest;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = { "code.learning.unittest.apistepdef" },
        features = { "src/test/resources/features/string" })
public class UnitTest {
}
