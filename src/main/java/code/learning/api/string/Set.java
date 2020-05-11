package code.learning.api.string;

import com.fasterxml.jackson.annotation.JsonProperty;
import code.learning.domain.object.string.StringObject;
import org.springframework.web.bind.annotation.*;

@RestController("set")
@RequestMapping(path = "/string/set")
public class Set {
    @PostMapping
    public String exec(@RequestBody Request req) {
        StringObject stringObject = new StringObject(req.key, req.value);
        stringObject.setter();
        return String.format("set key: %s with value: %s successfully", req.key, req.value);
    }

    private static class Request {
        @JsonProperty("key")
        public String key;

        @JsonProperty("value")
        public String value;
    }
}
