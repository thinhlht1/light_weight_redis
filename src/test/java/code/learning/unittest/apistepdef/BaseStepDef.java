package code.learning.unittest.apistepdef;

import code.learning.unittest.apistepdef.store.WebApiResultStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

public class BaseStepDef {
    @Autowired
    protected WebApplicationContext wac;

    protected WebApiResultStore resultStore = new WebApiResultStore();
}
