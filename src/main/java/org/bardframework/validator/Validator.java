package org.bardframework.validator;

import org.springframework.validation.Errors;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zafari on 10/7/2015.
 */
public interface Validator<T> {

    void validate(FieldValueHolder<T> fieldValue, List<FieldValueHolder<?>> args, Errors errors);

    default List<String> getArgsNames() {
        return new ArrayList<>();
    }
}
