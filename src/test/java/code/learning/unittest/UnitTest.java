package code.learning.unittest;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = { "code.learning.unittest.apistepdef" },
        features = { "src/test/resources/features/string" })
public class UnitTest {
}
