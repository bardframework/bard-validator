package org.bardframework.validator.field.common;

import org.bardframework.validator.FieldValueHolder;
import org.bardframework.validator.field.TwoFieldCompareValidatorAbstract;

import java.util.List;

/**
 * Created by zafari on 10/7/2015.
 */
public class LessThanFieldValidator extends TwoFieldCompareValidatorAbstract {


    public LessThanFieldValidator(String other) {
        this(other, "should_less_than");
    }

    public LessThanFieldValidator(String other, String errorCode) {
        super(other, errorCode);
    }

    @Override
    protected boolean isValid(Comparable value, Comparable other) {
        return value.compareTo(other) < 0;
    }

    @Override
    protected List<Object> getArgs(FieldValueHolder<?> fieldValue, FieldValueHolder<?> otherValue) {
        return List.of(fieldValue.translateFieldName(messageSource, this.getLocale()), otherValue.translateFieldName(messageSource, this.getLocale()));
    }
}
