package org.bardframework.validator.field.common;

import lombok.extern.slf4j.Slf4j;
import org.bardframework.validator.FieldValueHolder;
import org.bardframework.validator.field.SingleFieldValidatorAbstract;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Created by zafari on 10/7/2015.
 */
@Slf4j
public class UuidValidator extends SingleFieldValidatorAbstract<String> {

    public UuidValidator() {
        this("must_uuid");
    }

    public UuidValidator(String messageKey) {
        super(messageKey);
    }

    @Override
    protected boolean isValid(String value) {
        try {
            UUID.fromString(value);
            return true;
        } catch (Exception e) {
            log.debug("invalid uuid [{}].", value);
            return false;
        }
    }

    @Override
    protected List<Object> getArgs(FieldValueHolder<String> fieldValue) {
        return Collections.singletonList(fieldValue.translateFieldName(messageSource, this.getLocale()));
    }
}
