package code.learning.api.set;

import code.learning.domain.DataKeeperInterface;
import code.learning.domain.object.list.ListObject;
import code.learning.domain.object.set.SetObject;
import code.learning.exceptions.ExceptionHandler;
import code.learning.exceptions.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Set;

@RestController("smembers")
@RequestMapping(path = "/set/smembers")
public class Smembers {
    @Autowired
    DataKeeperInterface dataKeeper;

    SetObject value;

    @GetMapping("/{key}")
    public Set exec(@PathVariable String key) throws Throwable {
        try {
            this.value = (SetObject) dataKeeper.getByKey(key);
            if (value != null) {
                return value.getter();
            }

            throw new UserException("keyNotExist", "Key Not Exist", HttpStatus.NOT_FOUND);
        } catch (Throwable tr) {
            ExceptionHandler.exceptionHandler(tr);
            return null;
        }
    }
}
