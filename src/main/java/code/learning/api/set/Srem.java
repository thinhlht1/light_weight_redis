package code.learning.api.set;

import code.learning.domain.DataKeeperInterface;
import code.learning.domain.object.list.ListObject;
import code.learning.domain.object.set.SetObject;
import code.learning.exceptions.ExceptionHandler;
import code.learning.exceptions.UserException;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@RestController("srem")
@RequestMapping(path = "/set/srem")
public class Srem {
    @Autowired
    DataKeeperInterface dataKeeper;

    SetObject value;

    @PostMapping
    public String exec(@RequestBody Request req) throws Throwable {
        try {
            this.value = (SetObject) dataKeeper.getByKey(req.key);
            String message = "Key does not exist";
            if (value != null) {
                Set removeValue = req.valueToSet();
                value.setUpdateMode(SetObject.UpdateMode.remove);
                value.update(removeValue);
                message = String.format("Remove these elements: %s from value of key: %s", req.value, req.key);
                return message;
            }

            throw new UserException("keyNotExist", message, HttpStatus.NOT_FOUND);
        } catch (Throwable tr) {
            ExceptionHandler.exceptionHandler(tr);
            return null;
        }

    }

    private static class Request {
        @JsonProperty("key")
        public String key;

        @JsonProperty("value")
        String value;
        public Set valueToSet() {
            String[] tokens = value.split(" ");
            Set<String> result = new HashSet<String>(Arrays.asList(tokens));
            return result;
        }
    }
}

