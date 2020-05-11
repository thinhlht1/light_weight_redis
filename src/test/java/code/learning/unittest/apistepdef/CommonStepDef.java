package code.learning.unittest.apistepdef;

import cucumber.api.java.After;
import cucumber.api.java.Before;

public class CommonStepDef extends BaseStepDef {
    @Before(value = "@cleanUp", order = 1000)
    public synchronized void cleanUp() throws Exception {
        resultStore.cleanup();
    }

    @After
    public synchronized void shutdown() {
    }
}