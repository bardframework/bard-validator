package org.bardframework.validator.field.datetime;

import org.bardframework.validator.FieldValueHolder;
import org.bardframework.validator.field.SingleFieldValidatorAbstract;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zafari on 10/7/2015.
 */
public class FromTodayDateValidator extends SingleFieldValidatorAbstract<LocalDate> {

    public FromTodayDateValidator() {
        super("from_today_date_error");
    }

    public FromTodayDateValidator(String errorCode) {
        super(errorCode);
    }

    @Override
    protected boolean isValid(LocalDate value) {
        LocalDate today = LocalDate.now();
        return value.equals(today) || value.isAfter(today);
    }

    @Override
    protected List<Object> getArgs(FieldValueHolder<LocalDate> fieldValue) {
        return Arrays.asList(fieldValue.translateFieldName(messageSource, this.getLocale()));
    }
}
