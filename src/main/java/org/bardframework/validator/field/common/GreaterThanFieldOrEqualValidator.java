package org.bardframework.validator.field.common;

import org.bardframework.validator.FieldValueHolder;
import org.bardframework.validator.field.TwoFieldCompareValidatorAbstract;

import java.util.List;

/**
 * Created by zafari on 10/7/2015.
 */
public class GreaterThanFieldOrEqualValidator extends TwoFieldCompareValidatorAbstract {

    public GreaterThanFieldOrEqualValidator(String other) {
        super(other, "must_greater_than_or_equal");
    }

    public GreaterThanFieldOrEqualValidator(String other, String errorCode) {
        super(other, errorCode);
    }

    @Override
    protected boolean isValid(Comparable value, Comparable other) {
        return value.compareTo(other) >= 0;
    }

    @Override
    protected List<Object> getArgs(FieldValueHolder<?> fieldValue, FieldValueHolder<?> otherValue) {
        return List.of(fieldValue.translateFieldName(messageSource, this.getLocale()), otherValue.translateFieldName(messageSource, this.getLocale()));
    }
}
