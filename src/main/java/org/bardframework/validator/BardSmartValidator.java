package org.bardframework.validator;

import org.bardframework.commons.reflection.ReflectionUtils;
import org.bardframework.commons.utils.HtmlUtils;
import org.bardframework.validator.exception.BardValidationException;
import org.bardframework.validator.holder.ValidatorHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.SmartValidator;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Created by zafari on 10/4/2015.
 */
public class BardSmartValidator implements SmartValidator {

    protected static final Logger LOGGER = LoggerFactory.getLogger(BardSmartValidator.class);

    private final Map<Class<?>, ValidatorHolder> validatorsMap;
    private final Map<Class<?>, List<Class<?>>> superClasses = new ConcurrentHashMap<>();

    public BardSmartValidator(@Autowired List<ValidatorHolder> validatorHolders) {
        this.validatorsMap = new ConcurrentHashMap<>();
        for (ValidatorHolder validatorHolder : validatorHolders) {
            if (this.validatorsMap.containsKey(validatorHolder.getGroup())) {
                LOGGER.error("validator with group [{}] define multiple, ignoring previous validators (use last of them)", validatorHolder.getGroup());
            }
            this.validatorsMap.put(validatorHolder.getGroup(), validatorHolder);
        }
    }

    @Override
    public final void validate(Object target, Errors errors) {
        this.validate(target, errors, target.getClass());
    }

    @Override
    public void validate(Object target, Errors errors, Object... validationHints) {
        if (1 > validationHints.length || null == validationHints[0]) {
            LOGGER.warn("no validation specified for '{}', maybe no validation group specified in @Validated annotation.", target);
            return;
        }
        Set<Class<?>> targetValidatorClasses = new HashSet<>();
        /*
          converting validationHints to Class<? extends ValidatorGroup>
         */
        for (Object validationHint : validationHints) {
            if (validationHint instanceof Class<?>) {
                targetValidatorClasses.add((Class<?>) validationHint);
            } else {
                throw new IllegalArgumentException("validation groups must be a Class");
            }
        }
        /*
            add all super class & interface of Validators as validator
         */
        targetValidatorClasses.addAll(targetValidatorClasses.stream().map(this::getSuperClasses).flatMap(Collection::stream).collect(Collectors.toSet()));
        /*
          select affected validators
         */
        for (Class<?> targetValidatorClass : targetValidatorClasses) {
            if (validatorsMap.containsKey(targetValidatorClass)) {
                this.validate(errors, target, validatorsMap.get(targetValidatorClass));
            } else {
                LOGGER.error("validator[{}] not exist, maybe not defined as spring validators", targetValidatorClasses);
            }
        }
        if (validatorsMap.containsKey(target.getClass())) {
            this.validate(errors, target, validatorsMap.get(target.getClass()));
        }
        if (errors.hasErrors()) {
            throw new BardValidationException(errors);
        }
    }

    private void validate(Errors errors, Object target, ValidatorHolder holder) {
        LOGGER.debug("start validating [{}], groups: [{}]", target.getClass(), holder.getGroup());
        Object value;
        for (String property : holder.getValidators().keySet()) {
            if (errors.hasFieldErrors(property)) {
                /*
                  if field already has error in other validator, may be it's value not valid for next validators.
                 */
                continue;
            }
            List<Validator<?>> propertyValidators = holder.getValidators().get(property);
            if (null != propertyValidators && !propertyValidators.isEmpty()) {
                value = this.getValueOfProperty(property, target, errors);
                this.validateProperties(propertyValidators, errors, property, target, value);
                if (value instanceof Iterable) {
                    ((Iterable<?>) value).forEach(element -> {
                        if (null != element && validatorsMap.containsKey(element.getClass())) {
                            this.validate(errors, element, validatorsMap.get(element.getClass()));
                        }
                    });
                } else if (null != value && validatorsMap.containsKey(value.getClass())) {
                    this.validate(errors, value, validatorsMap.get(value.getClass()));
                }
            }
        }
        LOGGER.debug("validating [{}] by group [{}] finished", target.getClass(), holder.getGroup());
    }

    @Override
    public final boolean supports(Class<?> clazz) {
        return true;
    }

    private Object getValueOfProperty(String property, Object target, Errors errors) {
        try {
            return ReflectionUtils.getPropertyValue(target, property);
        } catch (Exception e) {
            LOGGER.error("validation problem : get value of '{}' from '{}' failed.", property, target, e);
            errors.reject("validation_exception");
            return null;
        }
    }

    private void validateProperties(List<Validator<?>> propertyValidators, Errors errors, String property, Object target, Object value) {
        for (Validator<?> validator : propertyValidators) {
            if (errors.hasFieldErrors(property)) {
                    /*
                      if field already has error in other validator, may be it's value not valid for next validators.
                     */
                continue;
            }
            List<FieldValueHolder<?>> args = new ArrayList<>();
            for (Object argName : validator.getArgsNames()) {
                try {
                    args.add(new FieldValueHolder<>(target.getClass(), argName.toString(), ReflectionUtils.getPropertyValue(target, argName.toString())));
                } catch (Exception e) {
                    errors.reject("validation_exception");
                    throw new IllegalStateException(String.format("validation problem : get value of '%s' from '%s' failed.", argName.toString(), target.toString()), e);
                }
            }
            if (value instanceof String) {
                value = HtmlUtils.htmlUnescape((String) value);
            }
            validator.validate(new FieldValueHolder(target.getClass(), property, value), args, errors);
        }
    }

    private List<Class<?>> getSuperClasses(Class<?> clazz) {
        if (!this.superClasses.containsKey(clazz)) {
            this.superClasses.put(clazz, ReflectionUtils.getSupersOf(clazz, true, true, false));
        }
        return this.superClasses.get(clazz);
    }
}
