package org.bardframework.validator.field.datetime.epoch;

import org.bardframework.commons.utils.DateTimeUtils;
import org.bardframework.validator.FieldValueHolder;
import org.bardframework.validator.field.SingleFieldValidatorAbstract;

import java.util.Collections;
import java.util.List;

/**
 * Created by zafari on 10/7/2015.
 */
public class UntilTodayEpochDateValidator extends SingleFieldValidatorAbstract<Long> {

    public UntilTodayEpochDateValidator() {
        super("less_than_today_date");
    }

    public UntilTodayEpochDateValidator(String errorCode) {
        super(errorCode);
    }

    @Override
    protected boolean isValid(Long value) {
        Long today = DateTimeUtils.getTodayUtc();
        return value.equals(today) || value < today;
    }

    @Override
    protected List<Object> getArgs(FieldValueHolder<Long> fieldValue) {
        return Collections.singletonList(fieldValue.translateFieldName(messageSource, this.getLocale()));
    }
}
