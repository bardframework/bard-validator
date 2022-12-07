package org.bardframework.validator.field.common;

import org.bardframework.validator.FieldValueHolder;
import org.bardframework.validator.field.TwoFieldCompareValidatorAbstract;

import java.util.Collections;
import java.util.List;

/**
 * Created by zafari on 10/7/2015.
 */
public class GreaterThanFieldValidator extends TwoFieldCompareValidatorAbstract {

    private final String other;

    public GreaterThanFieldValidator(String other) {
        this("must_greater_than", other);
    }

    public GreaterThanFieldValidator(String errorCode, String other) {
        super(errorCode);
        this.other = other;
    }

    @Override
    public List<String> getArgsNames() {
        return Collections.singletonList(other);
    }

    @Override
    protected boolean isValid(Comparable value, Comparable other) {
        return value.compareTo(other) > 0;
    }

    @Override
    protected List<Object> getArgs(FieldValueHolder<?> fieldValue, FieldValueHolder<?> otherValue) {
        return List.of(fieldValue.translateFieldName(messageSource, this.getLocale()), otherValue.translateFieldName(messageSource, this.getLocale()));
    }
}
