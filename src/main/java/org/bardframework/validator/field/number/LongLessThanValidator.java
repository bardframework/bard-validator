package org.bardframework.validator.field.number;

import org.bardframework.validator.FieldValueHolder;
import org.bardframework.validator.field.SingleFieldValidatorAbstract;

import java.util.Arrays;
import java.util.List;

/**
 * Created by v.zafari on 30/01/2016.
 */
public class LongLessThanValidator extends SingleFieldValidatorAbstract<Long> {
    protected final long max;

    public LongLessThanValidator(long max) {
        this(max, "less_than_error");
    }

    public LongLessThanValidator(long max, String errorCode) {
        super(errorCode);
        this.max = max;
    }

    @Override
    protected boolean isValid(Long value) {
        return value < max;
    }

    @Override
    protected List<Object> getArgs(FieldValueHolder<Long> fieldValue) {
        return Arrays.asList(fieldValue.translateFieldName(messageSource, this.getLocale()), max);
    }

}
