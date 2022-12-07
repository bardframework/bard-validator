package org.bardframework.validator.field.number;

import org.bardframework.validator.FieldValueHolder;
import org.bardframework.validator.field.SingleFieldValidatorAbstract;

import java.util.List;

/**
 * Created by v.zafari on 30/01/2016.
 */
public class IntegerGreaterThanValidator extends SingleFieldValidatorAbstract<Integer> {
    protected final int min;

    public IntegerGreaterThanValidator(int min) {
        this(min, "greater_than_error");
    }

    public IntegerGreaterThanValidator(int min, String errorCode) {
        super(errorCode);
        this.min = min;
    }

    @Override
    protected boolean isValid(Integer value) {
        return value > min;
    }

    @Override
    protected List<Object> getArgs(FieldValueHolder<Integer> fieldValue) {
        return List.of(fieldValue.translateFieldName(messageSource, this.getLocale()), min);
    }
}
