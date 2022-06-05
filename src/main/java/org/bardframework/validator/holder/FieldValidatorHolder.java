package org.bardframework.validator.holder;

import org.bardframework.commons.utils.AssertionUtils;
import org.bardframework.validator.Validator;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Vahid Zafari on 5/12/2016.
 */
public class FieldValidatorHolder implements ValidatorHolder {

    private final Class<?> group;
    private final Map<String, List<Validator<?>>> validators;

    public FieldValidatorHolder(Class<?> group) {
        AssertionUtils.notNull(group, "null group not acceptable");
        this.group = group;
        this.validators = new ConcurrentHashMap<>();
    }

    public FieldValidatorHolder(Class<?> group, Map<String, List<Validator<?>>> validators) {
        AssertionUtils.notNull(group, "null group not acceptable");
        AssertionUtils.notEmpty(validators, "null validators not acceptable");
        this.group = group;
        this.validators = validators;
    }

    @Override
    public Class<?> getGroup() {
        return group;
    }

    @Override
    public Map<String, List<Validator<?>>> getValidators() {
        return validators;
    }

    public void addValidators(String fieldName, List<Validator<?>> validators) {
        this.validators.put(fieldName, validators);
    }
}
