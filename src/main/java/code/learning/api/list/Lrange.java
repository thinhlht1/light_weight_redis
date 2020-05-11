package code.learning.api.list;

import code.learning.domain.DataKeeperInterface;
import code.learning.domain.object.list.ListObject;
import code.learning.exceptions.ExceptionHandler;
import code.learning.exceptions.UserException;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController("lrange")
@RequestMapping(path = "/list/lrange")
public class Lrange {
    @Autowired
    DataKeeperInterface dataKeeper;

    ArrayList content;

    ListObject value;
    @GetMapping("/{key}/{start}/{stop}")
    public ArrayList exec(@PathVariable String key,
                       @PathVariable Integer start,
                       @PathVariable Integer stop) throws Throwable {
        try {
            this.value = (ListObject) dataKeeper.getByKey(key);
            String message = "Key does not exist";
            if (value != null) {
                this.content = value.getter();
                validateUserInput(start, stop);
                return new ArrayList(content.subList(start, stop));
            }

            throw new UserException("keyNotExist", message, HttpStatus.NOT_FOUND);
        } catch (Throwable tr) {
            ExceptionHandler.exceptionHandler(tr);
            return null;
        }
    }

    private void validateUserInput(int start, int stop) throws UserException {
        if (value == null) {
            throw new UserException("keyNotExist", "Key Not Exist", HttpStatus.NOT_FOUND);
        }

        if (start < 0 || stop <= 0 || start > stop || start == stop) {
            throw new UserException("badInput", "Bad Input", HttpStatus.BAD_REQUEST);
        }

        if (start >= content.size() || stop >= content.size()) {
            throw new UserException("outOfRange", "Index out of range", HttpStatus.NOT_FOUND);
        }
    }
}

