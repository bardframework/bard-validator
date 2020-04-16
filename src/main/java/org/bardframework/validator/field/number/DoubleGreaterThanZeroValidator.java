package org.bardframework.validator.field.number;

import org.bardframework.validator.FieldValueHolder;
import org.bardframework.validator.field.SingleFieldValidatorAbstract;

import java.util.Collections;
import java.util.List;

/**
 * Created by v.zafari on 30/01/2016.
 */
public class DoubleGreaterThanZeroValidator extends SingleFieldValidatorAbstract<Double> {

    public DoubleGreaterThanZeroValidator() {
        this("must_grater_than_zero");
    }

    public DoubleGreaterThanZeroValidator(String errorCode) {
        super(errorCode);
    }

    @Override
    protected boolean isValid(Double value) {
        return value > 0;
    }

    @Override
    protected List<Object> getArgs(FieldValueHolder<Double> fieldValue) {
        return Collections.singletonList(fieldValue.translateFieldName(messageSource, this.getLocale()));
    }
}
