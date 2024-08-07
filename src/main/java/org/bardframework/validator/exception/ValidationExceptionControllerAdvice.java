package org.bardframework.validator.exception;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

public interface ValidationExceptionControllerAdvice {

    @ExceptionHandler(BardValidationException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ResponseBody
    default List<String> handle(BardValidationException ex) {
        List<String> messages = new ArrayList<>();
        if (null != ex.getErrors()) {
            ex.getErrors().getFieldErrors().forEach(fieldError -> messages.add(fieldError.getField() + " " + fieldError.getDefaultMessage()));
        }
        if (null != ex.getMessage()) {
            messages.add(ex.getMessage());
        }
        LoggerFactory.getLogger(this.getClass()).debug("validation error:\n{}", messages);
        return messages;
    }
}
