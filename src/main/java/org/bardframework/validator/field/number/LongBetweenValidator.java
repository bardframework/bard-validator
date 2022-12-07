package org.bardframework.validator.field.number;

import org.bardframework.validator.FieldValueHolder;
import org.bardframework.validator.field.SingleFieldValidatorAbstract;

import java.util.List;

/**
 * Created by v.zafari on 30/01/2016.
 */
public class LongBetweenValidator extends SingleFieldValidatorAbstract<Long> {
    protected final long min;
    protected final long max;

    public LongBetweenValidator(long max, long min) {
        this(min, max, "min_max.error");
    }

    public LongBetweenValidator(long min, long max, String errorCode) {
        super(errorCode);
        this.min = min;
        this.max = max;
    }

    @Override
    protected boolean isValid(Long value) {
        return value >= min && value <= max;
    }

    @Override
    protected List<Object> getArgs(FieldValueHolder<Long> fieldValue) {
        return List.of(fieldValue.translateFieldName(messageSource, this.getLocale()), min, max);
    }
}
