package org.bardframework.validator.field.number;

import org.bardframework.validator.FieldValueHolder;
import org.bardframework.validator.field.SingleFieldValidatorAbstract;

import java.util.Arrays;
import java.util.List;

/**
 * Created by v.zafari on 30/01/2016.
 */
public class IntegerLessThanValidator extends SingleFieldValidatorAbstract<Integer> {
    protected final int max;

    public IntegerLessThanValidator(int max) {
        this(max, "less_than_error");
    }

    public IntegerLessThanValidator(int max, String errorCode) {
        super(errorCode);
        this.max = max;
    }

    @Override
    protected boolean isValid(Integer value) {
        return value < max;
    }

    @Override
    protected List<Object> getArgs(FieldValueHolder<Integer> fieldValue) {
        return Arrays.asList(fieldValue.translateFieldName(messageSource, this.getLocale()), max);
    }

}
