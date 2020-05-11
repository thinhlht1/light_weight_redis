package code.learning.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class UserException extends Exception {
    String errCode;
    String errMessage;
    HttpStatus httpErrCode;

    public UserException(String errCode, String errMessage, HttpStatus httpErrCode) {
        super(errMessage);
        this.errCode = errCode;
        this.httpErrCode = httpErrCode;
    }

    public String getErrCode() {
        return this.errCode;
    }

    public String getErrMessage() {
        return this.errMessage;
    }

    public HttpStatus getHttpErrCode() {
        return this.httpErrCode;
    }
}
