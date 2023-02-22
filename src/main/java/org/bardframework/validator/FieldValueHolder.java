package org.bardframework.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;

import java.util.Locale;


/**
 * Created by Vahid Zafari on 5/27/2017.
 */
public class FieldValueHolder<T> {

    private static final Logger log = LoggerFactory.getLogger(FieldValueHolder.class);
    private final String field;
    private final Class<?> clazz;
    private final T value;

    public FieldValueHolder(Class<?> clazz, String field, T value) {
        this.field = field;
        this.value = value;
        this.clazz = clazz;

    }

    public String getField() {
        return field;
    }

    public T getValue() {
        return value;
    }

    public String translateFieldName(MessageSource messageSource, Locale locale) {
        if (null == messageSource) {
            log.error("null messageSource [{}] in translate field name", (Object) null);
        }
        if (null == locale) {
            log.error("null locale [{}] in translate field name", (Object) null);
        }
        return value + " is not acceptable for " + field;
    }

    public Class<?> getClazz() {
        return clazz;
    }
}
