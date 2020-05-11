package code.learning.api.list;

import code.learning.domain.DataKeeperInterface;
import code.learning.domain.object.list.ListObject;
import code.learning.exceptions.ExceptionHandler;
import code.learning.exceptions.UserException;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

@RestController("pop")
@RequestMapping(path = "/list/pop")
public class Pop {
    @Autowired
    DataKeeperInterface dataKeeper;

    ListObject value;

    String key;

    @PostMapping
    public String exec(@RequestBody Request req) throws Throwable {
        try {
            this.key = req.key;
            this.value = (ListObject) dataKeeper.getByKey(key);
            String message = "Key does not exist";
            Object returnValue;
            if (value != null) {
                value.setUpdateMode(ListObject.UpdateMode.remove);
                value.setPopDirection(req.direction);
                value.update(null);
                returnValue = value.getRemoveValue();
                message = String.format("Remove %s from list with key: %s", returnValue, key);
                return message;
            }

            throw new UserException("keyNotExist", message, HttpStatus.NOT_FOUND);
        } catch (Throwable tr) {
            ExceptionHandler.exceptionHandler(tr);
            return null;
        }

    }

    private boolean isEmptyList(ArrayList list) {
        if (list.isEmpty()) {
            this.value.remove(key);
            return true;
        }

        return false;
    }

    private static class Request {
        @JsonProperty("key")
        public String key;

        @JsonProperty("direction")
        public String direction;
    }
}

