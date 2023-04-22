package org.bardframework.validator.field.number;

import org.bardframework.validator.FieldValueHolder;
import org.bardframework.validator.field.SingleFieldValidatorAbstract;

import java.util.List;

/**
 * Created by v.zafari on 30/01/2016.
 */
public class ShortBetweenValidator extends SingleFieldValidatorAbstract<Short> {
    protected final short min;
    protected final short max;

    public ShortBetweenValidator(short max, short min) {
        this(min, max, "min_max.error");
    }

    public ShortBetweenValidator(short min, short max, String errorCode) {
        super(errorCode);
        this.min = min;
        this.max = max;
    }

    @Override
    protected boolean isValid(Short value) {
        return value > min && value < max;
    }

    @Override
    protected List<Object> getArgs(FieldValueHolder<Short> fieldValue) {
        return List.of(fieldValue.translateFieldName(messageSource, this.getLocale()), min, max);
    }
}
