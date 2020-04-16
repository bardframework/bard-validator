package org.bardframework.validator.field.datetime;

import org.bardframework.validator.FieldValueHolder;
import org.bardframework.validator.field.SingleFieldValidatorAbstract;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zafari on 10/7/2015.
 */
public class FromNowDateTimeValidator extends SingleFieldValidatorAbstract<LocalDateTime> {

    public FromNowDateTimeValidator() {
        super("from_now_date_time.error");
    }

    public FromNowDateTimeValidator(String errorCode) {
        super(errorCode);
    }

    @Override
    protected boolean isValid(LocalDateTime value) {
        LocalDateTime now = LocalDateTime.now();
        return value.equals(now) || value.isAfter(now);
    }

    @Override
    protected List<Object> getArgs(FieldValueHolder<LocalDateTime> fieldValue) {
        return Arrays.asList(fieldValue.translateFieldName(messageSource, this.getLocale()));
    }
}
