package org.bardframework.validator.field.common;

import org.bardframework.validator.FieldValueHolder;
import org.bardframework.validator.field.SingleFieldValidatorAbstract;

import java.util.Arrays;
import java.util.List;

/**
 * Created by v.zafari on 30/01/2016.
 */
public class TextLengthValidator extends SingleFieldValidatorAbstract<String> {
    protected final int min;
    protected final int max;

    public TextLengthValidator(int max, int min) {
        this(min, max, "min_max_length.error");
    }

    public TextLengthValidator(int min, int max, String errorCode) {
        super(errorCode);
        this.min = min;
        this.max = max;
    }

    @Override
    protected boolean isValid(String value) {
        int length = value.trim().length();
        return length >= min && length <= max;
    }

    @Override
    protected List<Object> getArgs(FieldValueHolder<String> fieldValue) {
        return List.of(fieldValue.translateFieldName(messageSource, this.getLocale()), min, max);
    }
}
