package org.bardframework.validator.field.number;

import org.bardframework.validator.FieldValueHolder;
import org.bardframework.validator.field.SingleFieldValidatorAbstract;

import java.util.List;

/**
 * Created by v.zafari on 30/01/2016.
 */
public class IntegerBetweenValidator extends SingleFieldValidatorAbstract<Integer> {
    protected final int min;
    protected final int max;

    public IntegerBetweenValidator(int max, int min) {
        this(min, max, "min_max.error");
    }

    public IntegerBetweenValidator(int min, int max, String errorCode) {
        super(errorCode);
        this.min = min;
        this.max = max;
    }

    @Override
    protected boolean isValid(Integer value) {
        return value >= min && value <= max;
    }

    @Override
    protected List<Object> getArgs(FieldValueHolder<Integer> fieldValue) {
        return List.of(fieldValue.translateFieldName(messageSource, this.getLocale()), min, max);
    }
}
