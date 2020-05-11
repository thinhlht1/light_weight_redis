package code.learning.api.set;

import code.learning.domain.DataKeeperInterface;
import code.learning.domain.object.set.SetObject;
import code.learning.exceptions.ExceptionHandler;
import code.learning.exceptions.UserException;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@RestController("scard")
@RequestMapping(path = "/set/scard")
public class Scard {
    @Autowired
    DataKeeperInterface dataKeeper;

    SetObject value;

    @GetMapping("/{key}")
    public String exec(@PathVariable String key) throws Throwable {
        try {
            this.value = (SetObject) dataKeeper.getByKey(key);
            String message = "Key Not Exist";
            if (value != null) {
                Set content = value.getter();
                Integer len = content.size();
                message = String.format("Key: %s has length = %s", key, len);
                return message;
            }

            throw new UserException("keyNotExist", message, HttpStatus.NOT_FOUND);
        } catch (Throwable tr) {
            ExceptionHandler.exceptionHandler(tr);
            return null;
        }
    }
}
