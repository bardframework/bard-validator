package org.bardframework.validator.holder;

import org.bardframework.commons.utils.AssertionUtils;
import org.bardframework.validator.Validator;

import java.util.List;
import java.util.Map;

/**
 * Created by Vahid Zafari on 5/12/2016.
 */
public class FieldValidatorHolder implements ValidatorHolder {

    private final Class<?> group;
    private Map<String, List<Validator<?>>> validators;

    public FieldValidatorHolder(Class<?> group) {
        this.group = group;
    }

    public FieldValidatorHolder(Class<?> group, Map<String, List<Validator<?>>> validators) {
        AssertionUtils.notNull(group, "null group not acceptable");
        AssertionUtils.notEmpty(validators, "null validators not acceptable");
        this.group = group;
        this.validators = validators;
    }

    public Class<?> getGroup() {
        return group;
    }

    public Map<String, List<Validator<?>>> getValidators() {
        return validators;
    }

    public void setValidators(Map<String, List<Validator<?>>> validators) {
        this.validators = validators;
    }
}
