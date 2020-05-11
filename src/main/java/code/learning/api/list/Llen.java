package code.learning.api.list;

import code.learning.domain.DataKeeperInterface;
import code.learning.domain.object.list.ListObject;
import code.learning.exceptions.ExceptionHandler;
import code.learning.exceptions.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController("llen")
@RequestMapping(path = "/list/llen")
public class Llen {
    @Autowired
    DataKeeperInterface dataKeeper;

    @GetMapping("/{key}")
    public String exec(@PathVariable String key) throws Throwable {
        try {
            ListObject value = (ListObject) dataKeeper.getByKey(key);
            String message = "Key not exist";
            if (value != null) {
                ArrayList content = value.getter();
                Integer len = content.size();
                message = String.format("Key %s contains list has length = %s", key, len);
                return message;
            }

            throw new UserException("keyNotExist", message, HttpStatus.NOT_FOUND);
        } catch (Throwable tr) {
            ExceptionHandler.exceptionHandler(tr);
            return null;
        }
    }
}
