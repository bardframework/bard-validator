package org.bardframework.validator.field.common;

import org.bardframework.validator.FieldValueHolder;
import org.bardframework.validator.field.SingleFieldValidatorAbstract;
import org.springframework.validation.Errors;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by zafari on 10/7/2015.
 */
public class RegexValidator extends SingleFieldValidatorAbstract<Object> {

    private final String regex;

    public RegexValidator(String regex) {
        this(regex, "regex_not_match");
    }

    public RegexValidator(String regex, String errorCode) {
        super(errorCode);
        this.regex = regex;
    }

    @Override
    public void validate(FieldValueHolder<Object> fieldValue, List<FieldValueHolder<?>> args, Errors errors) {
        if (null == fieldValue.getValue()) {
            return;
        }
        if (!isValid(fieldValue.getValue())) {
            reject(fieldValue.getField(), errors, getArgs(fieldValue).toArray());
        }
    }

    @Override
    protected boolean isValid(Object value) {
        return Pattern.matches(regex, value.toString());
    }

    @Override
    protected List<Object> getArgs(FieldValueHolder<Object> fieldValue) {
        return Arrays.asList(fieldValue.translateFieldName(messageSource, this.getLocale()));
    }
}
