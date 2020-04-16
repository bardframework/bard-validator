package org.bardframework.validator.field.common;

import org.bardframework.validator.FieldValueHolder;
import org.bardframework.validator.field.SingleFieldValidatorAbstract;
import org.springframework.validation.Errors;

import java.util.Collections;
import java.util.List;

/**
 * Created by zafari on 10/7/2015.
 */
public class MustNotNullValidator extends SingleFieldValidatorAbstract<Object> {

    public MustNotNullValidator() {
        this("must_not_null");
    }

    public MustNotNullValidator(String errorCode) {
        super(errorCode);
    }

    @Override
    public void validate(FieldValueHolder<Object> fieldValue, List<FieldValueHolder<?>> args, Errors errors) {
        if (!this.isValid(fieldValue.getValue())) {
            this.reject(fieldValue.getField(), errors, getArgs(fieldValue).toArray());
        }
    }

    @Override
    protected boolean isValid(Object value) {
        return null != value;
    }

    @Override
    protected List<Object> getArgs(FieldValueHolder<Object> fieldValue) {
        return Collections.singletonList(fieldValue.translateFieldName(messageSource, this.getLocale()));
    }
}
