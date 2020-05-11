package code.learning.api.string;

import code.learning.domain.DataKeeperInterface;
import code.learning.domain.object.string.StringObject;
import code.learning.exceptions.ExceptionHandler;
import code.learning.exceptions.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController("get")
@RequestMapping(path = "/string/get")
public class Get {
    @Autowired
    DataKeeperInterface dataKeeper;

    @GetMapping("/{key}")
    public String exec(@PathVariable String key) throws Throwable {
        try {
            StringObject value = (StringObject) dataKeeper.getByKey(key);
            if (value != null) {
                return (String) value.getter();
            }

            throw new UserException("keyNotExist", "Key does not exist", HttpStatus.NOT_FOUND);
        } catch (Throwable tr) {
            ExceptionHandler.exceptionHandler(tr);
            return null;
        }
    }
}
