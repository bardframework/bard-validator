
package org.bardframework.validator.field.common;

import org.apache.commons.lang3.EnumUtils;
import org.bardframework.validator.FieldValueHolder;
import org.bardframework.validator.field.SingleFieldValidatorAbstract;

import java.util.List;

public class StringEnumValidator<E extends Enum<E>> extends SingleFieldValidatorAbstract<String> {

    protected final Class<E> clazz;

    public StringEnumValidator(Class<E> clazz) {
        this("value_is_not_valid", clazz);
    }

    public StringEnumValidator(String errorCode, Class<E> clazz) {
        super(errorCode);
        this.clazz = clazz;
    }

    protected boolean isValid(String value) {
        return EnumUtils.isValidEnum(clazz, value);
    }

    @Override
    protected List<Object> getArgs(FieldValueHolder<String> fieldValue) {
        return List.of(fieldValue.translateFieldName(this.messageSource, this.getLocale()));
    }
}
