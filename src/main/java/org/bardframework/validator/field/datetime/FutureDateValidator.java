package org.bardframework.validator.field.datetime;

import org.bardframework.validator.FieldValueHolder;
import org.bardframework.validator.field.SingleFieldValidatorAbstract;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zafari on 10/7/2015.
 */
public class FutureDateValidator extends SingleFieldValidatorAbstract<LocalDate> {

    public FutureDateValidator() {
        super("must_after_today_date");
    }

    public FutureDateValidator(String errorCode) {
        super(errorCode);
    }

    @Override
    protected boolean isValid(LocalDate value) {
        return LocalDate.now().isBefore(value);
    }

    @Override
    protected List<Object> getArgs(FieldValueHolder<LocalDate> fieldValue) {
        return Arrays.asList(fieldValue.translateFieldName(messageSource, this.getLocale()));
    }
}
