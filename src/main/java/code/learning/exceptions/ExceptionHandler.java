package code.learning.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ExceptionHandler {
    public static void exceptionHandler(Throwable tr) {
        if (tr instanceof UserException) {
            UserException e = (UserException) tr;
            throw new ResponseStatusException(e.getHttpErrCode(), e.getErrMessage(), e);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, tr.getMessage(), tr);
        }
    }
}
