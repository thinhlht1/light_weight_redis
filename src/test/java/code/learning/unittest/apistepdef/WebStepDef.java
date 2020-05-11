package code.learning.unittest.apistepdef;

import code.learning.domain.SpringContainer;
import code.learning.unittest.ApplicationTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.StatusResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = ApplicationTest.class, initializers = ConfigFileApplicationContextInitializer.class)
@WebAppConfiguration
public class WebStepDef extends BaseStepDef {
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @When("^web client make api call to \"([^\"]*)\":\"([^\"]*)\" with request")
    public void web_client_make_api_call_to_with_request(String method, String path, String body) throws JSONException {
        try {
            HashMap<String, Object> requestJson = new ObjectMapper().readValue(body, HashMap.class);
            MockHttpServletRequestBuilder requestBuilder =
                    MockMvcRequestBuilders.request(method, new URI(path))
                            .contentType(MediaType.APPLICATION_JSON);

            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

            if (!method.equalsIgnoreCase("get")) {
                String requestBody = ow.writeValueAsString(requestJson.get("body"));
                requestBuilder = requestBuilder.content(requestBody);
            } else {
                Map<String, Object> params = (Map<String, Object>) requestJson.get("params");
                for (Map.Entry<String, Object> param : params.entrySet()) {
                    requestBuilder = requestBuilder.param(param.getKey(), String.valueOf(param.getValue()));
                }
            }

            Map<String, String> headers = (Map<String, String>) requestJson.get("header");
            for (Map.Entry<String, String> header : headers.entrySet()) {
                requestBuilder.header(header.getKey(), header.getValue());
            }

            resultStore.putWebApiResult(path, method, this.mockMvc.perform(requestBuilder));

        } catch (Throwable tr) {
            System.out.println(String.format("Error: %s", tr));
        }
    }

    @Then("\"([^\"]*)\":\"([^\"]*)\" response with body$")
    public void response_with_body(String method, String path, String expectedResponse) throws Exception {
        MvcResult response = resultStore.getWebApiResult(path, method).andExpect(status().isOk()).andReturn();
        String actualResponse = response.getResponse().getContentAsString();


        try {
            JSONObject expRes = new JSONObject(expectedResponse);
            String expectedBody = (String) expRes.get("body");
            assertEquals(expectedBody, actualResponse);
        } catch (Throwable e) {
            String error = "\n--------------------------------"
                    + "\nExpected: " + expectedResponse
                    + "\nbut: " + actualResponse
                    + "\nbecause: " + e.getMessage();
            AssertionError tr = new AssertionError(error);
            tr.printStackTrace();
            throw tr;
        }

    }
}