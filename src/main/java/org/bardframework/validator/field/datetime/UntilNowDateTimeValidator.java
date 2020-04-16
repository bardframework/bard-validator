package org.bardframework.validator.field.datetime;

import org.bardframework.validator.FieldValueHolder;
import org.bardframework.validator.field.SingleFieldValidatorAbstract;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zafari on 10/7/2015.
 */
public class UntilNowDateTimeValidator extends SingleFieldValidatorAbstract<LocalDateTime> {

    public UntilNowDateTimeValidator() {
        super("from_today_date_time_error");
    }

    public UntilNowDateTimeValidator(String errorCode) {
        super(errorCode);
    }

    @Override
    protected boolean isValid(LocalDateTime value) {
        LocalDateTime now = LocalDateTime.now();
        return value.equals(now) || value.isBefore(now);
    }

    @Override
    protected List<Object> getArgs(FieldValueHolder<LocalDateTime> fieldValue) {
        return Arrays.asList(fieldValue.translateFieldName(messageSource, this.getLocale()));
    }
}
