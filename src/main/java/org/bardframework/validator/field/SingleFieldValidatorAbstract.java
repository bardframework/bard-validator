package org.bardframework.validator.field;

import org.bardframework.validator.FieldValueHolder;
import org.bardframework.validator.ValidatorAbstract;
import org.springframework.validation.Errors;

import java.util.List;

/**
 * Created by zafari on 10/7/2015.
 */
public abstract class SingleFieldValidatorAbstract<T> extends ValidatorAbstract<T> {

    public SingleFieldValidatorAbstract(String errorCode) {
        super(errorCode);
    }

    @Override
    public void validate(FieldValueHolder<T> fieldValue, List<FieldValueHolder<?>> args, Errors errors) {
        if (null == fieldValue.getValue()) {
            return;
        }
        if (!this.isValid(fieldValue.getValue())) {
            this.reject(fieldValue.getField(), errors, getArgs(fieldValue).toArray());
        }
    }

    protected abstract boolean isValid(T value);

    protected abstract List<Object> getArgs(FieldValueHolder<T> fieldValue);
}
