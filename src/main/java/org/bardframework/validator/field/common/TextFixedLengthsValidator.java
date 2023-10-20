package org.bardframework.validator.field.common;

import org.bardframework.validator.FieldValueHolder;
import org.bardframework.validator.field.SingleFieldValidatorAbstract;

import java.util.List;

public class TextFixedLengthsValidator extends SingleFieldValidatorAbstract<String> {
    protected final List<Integer> validLengths;

    public TextFixedLengthsValidator(List<Integer> validLengths) {
        this(validLengths, "valid_lengths.error");
    }

    public TextFixedLengthsValidator(List<Integer> validLengths, String errorCode) {
        super(errorCode);
        this.validLengths = validLengths;
    }

    @Override
    protected boolean isValid(String value) {
        int length = value.trim().length();
        return validLengths.contains(length);
    }

    @Override
    protected List<Object> getArgs(FieldValueHolder<String> fieldValue) {
        return List.of(fieldValue.translateFieldName(messageSource, this.getLocale()), validLengths);
    }
}
