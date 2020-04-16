package org.bardframework.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.NotReadablePropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.Errors;

import java.util.Locale;

/**
 * Created by zafari on 10/7/2015.
 */
public abstract class ValidatorAbstract<T> implements Validator<T> {

    protected final String errorCode;
    protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    @Autowired
    protected MessageSource messageSource;

    public ValidatorAbstract(String errorCode) {
        this.errorCode = errorCode;
    }

    protected Locale getLocale() {
        return LocaleContextHolder.getLocale();
    }

    protected void reject(String field, Errors errors, Object... args) {
        try {
            errors.rejectValue(field, errorCode, args, errorCode);
        } catch (NotReadablePropertyException e) {
            errors.reject(errorCode, field);
        }
    }
}
