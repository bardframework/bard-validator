package org.bardframework.validator;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;

import java.util.Locale;


/**
 * Created by Vahid Zafari on 5/27/2017.
 */
@Getter
@Slf4j
public class FieldValueHolder<T> {

    private final String field;
    private final Class<?> clazz;
    private final T value;

    public FieldValueHolder(Class<?> clazz, String field, T value) {
        this.field = field;
        this.value = value;
        this.clazz = clazz;

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

}
