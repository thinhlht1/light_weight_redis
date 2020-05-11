package code.learning.api.set;

import code.learning.domain.DataKeeperInterface;
import code.learning.domain.object.list.ListObject;
import code.learning.domain.object.set.SetObject;
import code.learning.exceptions.ExceptionHandler;
import code.learning.exceptions.UserException;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController("sadd")
@RequestMapping(path = "/set/sadd")
public class Sadd {
    @Autowired
    DataKeeperInterface dataKeeper;

    SetObject value;

    @PostMapping
    public String exec(@RequestBody Request req) throws Throwable {
        try {
            this.value = (SetObject) dataKeeper.getByKey(req.key);
            Set newContent = req.valueToSet();
            String message = String.format("Create new set with key = %s", req.key);
            if (value != null) {
                value.setUpdateMode(SetObject.UpdateMode.append);
                value.update(newContent);
                message = String.format("Append new set to key = %s", req.key);
                return message;
            }

            SetObject newValue = new SetObject(req.key, newContent);
            newValue.setter();
            return message;
        } catch (Throwable tr) {
            ExceptionHandler.exceptionHandler(tr);
            return null;
        }
    }

    private static class Request {
        @JsonProperty("key")
        public String key;

        @JsonProperty("value")
        public String value;

        public Set valueToSet() {
            String[] tokens = value.split(" ");
            Set<String> result = new HashSet<String>(Arrays.asList(tokens));
            return result;
        }
    }
}