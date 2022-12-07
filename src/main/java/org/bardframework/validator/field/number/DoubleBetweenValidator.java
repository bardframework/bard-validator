package org.bardframework.validator.field.number;

import org.bardframework.validator.FieldValueHolder;
import org.bardframework.validator.field.SingleFieldValidatorAbstract;

import java.util.List;

/**
 * Created by v.zafari on 30/01/2016.
 */
public class DoubleBetweenValidator extends SingleFieldValidatorAbstract<Double> {
    protected final double min;
    protected final double max;

    public DoubleBetweenValidator(double max, double min) {
        this(min, max, "min_max.error");
    }

    public DoubleBetweenValidator(double min, double max, String errorCode) {
        super(errorCode);
        this.min = min;
        this.max = max;
    }

    @Override
    protected boolean isValid(Double value) {
        return value >= min && value <= max;
    }

    @Override
    protected List<Object> getArgs(FieldValueHolder<Double> fieldValue) {
        return List.of(fieldValue.translateFieldName(messageSource, this.getLocale()), min, max);
    }
}
