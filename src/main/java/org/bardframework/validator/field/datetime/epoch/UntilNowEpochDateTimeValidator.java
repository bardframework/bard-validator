package org.bardframework.validator.field.datetime.epoch;

import org.bardframework.commons.utils.DateTimeUtils;
import org.bardframework.validator.FieldValueHolder;
import org.bardframework.validator.field.SingleFieldValidatorAbstract;

import java.util.Collections;
import java.util.List;

/**
 * Created by zafari on 10/7/2015.
 */
public class UntilNowEpochDateTimeValidator extends SingleFieldValidatorAbstract<Long> {

    public UntilNowEpochDateTimeValidator() {
        super("from_today_date_time_error");
    }

    public UntilNowEpochDateTimeValidator(String errorCode) {
        super(errorCode);
    }

    @Override
    protected boolean isValid(Long value) {
        Long now = DateTimeUtils.getNowUtc();
        return value.equals(now) || value < now;
    }

    @Override
    protected List<Object> getArgs(FieldValueHolder<Long> fieldValue) {
        return Collections.singletonList(fieldValue.translateFieldName(messageSource, this.getLocale()));
    }
}
