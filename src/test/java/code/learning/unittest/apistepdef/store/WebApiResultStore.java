package code.learning.unittest.apistepdef.store;

import org.springframework.test.web.servlet.ResultActions;

public class WebApiResultStore extends BasicStore<Object> {
    public static final String WEB_API_RESULT = "web_api_result";

    public void putWebApiResult(String path, String method, ResultActions resultActions) {
        register(constructWebApiKey(path, method), resultActions);
    }

    public ResultActions getWebApiResult(String path, String method) {
        return (ResultActions) get(constructWebApiKey(path, method));
    }

    private String constructWebApiKey(String path, String method) {
        return String.format("%s:%s:%s", WEB_API_RESULT, method, path);
    }
}