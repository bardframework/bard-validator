package org.bardframework.validator.field.datetime;

import org.bardframework.validator.FieldValueHolder;
import org.bardframework.validator.field.SingleFieldValidatorAbstract;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * Created by zafari on 10/7/2015.
 */
public class PastDateTimeValidator extends SingleFieldValidatorAbstract<LocalDateTime> {

    public PastDateTimeValidator() {
        super("before_date_time.error");
    }

    public PastDateTimeValidator(String errorCode) {
        super(errorCode);
    }

    @Override
    protected boolean isValid(LocalDateTime value) {
        return LocalDateTime.now().isAfter(value);
    }

    @Override
    protected List<Object> getArgs(FieldValueHolder<LocalDateTime> fieldValue) {
        return Collections.singletonList(fieldValue.translateFieldName(messageSource, this.getLocale()));
    }
}
