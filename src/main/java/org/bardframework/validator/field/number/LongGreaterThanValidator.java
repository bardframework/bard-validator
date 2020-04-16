package org.bardframework.validator.field.number;

import org.bardframework.validator.FieldValueHolder;
import org.bardframework.validator.field.SingleFieldValidatorAbstract;

import java.util.Arrays;
import java.util.List;

/**
 * Created by v.zafari on 30/01/2016.
 */
public class LongGreaterThanValidator extends SingleFieldValidatorAbstract<Long> {
    protected final long min;

    public LongGreaterThanValidator(long min) {
        this(min, "greater_than_error");
    }

    public LongGreaterThanValidator(long min, String errorCode) {
        super(errorCode);
        this.min = min;
    }

    @Override
    protected boolean isValid(Long value) {
        return value > min;
    }

    @Override
    protected List<Object> getArgs(FieldValueHolder<Long> fieldValue) {
        return Arrays.asList(fieldValue.translateFieldName(messageSource, this.getLocale()), min);
    }
}
