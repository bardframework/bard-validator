package org.bardframework.validator.exception;

import org.springframework.validation.Errors;

/**
 * Created by v.zafari on 1/26/2016.
 */
public class BardValidationException extends RuntimeException {

    private final transient Errors errors;

    public BardValidationException(Errors errors) {
        this.errors = errors;
    }

    public BardValidationException rejectValue(String field, String errorCode, String defaultMessage, Object... errorArgs) {
        this.errors.rejectValue(field, errorCode, errorArgs, defaultMessage);
        return this;
    }

    public Errors getErrors() {
        return errors;
    }
}
