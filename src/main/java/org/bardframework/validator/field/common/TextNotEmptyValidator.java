package org.bardframework.validator.field.common;

import org.bardframework.validator.FieldValueHolder;
import org.bardframework.validator.field.SingleFieldValidatorAbstract;
import org.springframework.validation.Errors;

import java.util.Arrays;
import java.util.List;

/**
 * Created by zafari on 10/7/2015.
 */
public class TextNotEmptyValidator extends SingleFieldValidatorAbstract<String> {

    public TextNotEmptyValidator() {
        this("must_not_empty");
    }

    public TextNotEmptyValidator(String errorCode) {
        super(errorCode);
    }

    @Override
    public void validate(FieldValueHolder<String> fieldValue, List<FieldValueHolder<?>> args, Errors errors) {
        if (!isValid(fieldValue.getValue())) {
            reject(fieldValue.getField(), errors, getArgs(fieldValue).toArray());
        }
    }

    @Override
    protected boolean isValid(String value) {
        return null != value && !value.trim().isEmpty();
    }

    @Override
    protected List<Object> getArgs(FieldValueHolder<String> fieldValue) {
        return Arrays.asList(fieldValue.translateFieldName(messageSource, this.getLocale()));
    }
}
