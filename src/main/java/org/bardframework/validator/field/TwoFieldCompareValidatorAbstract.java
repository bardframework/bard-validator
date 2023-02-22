package org.bardframework.validator.field;

import org.bardframework.validator.FieldValueHolder;
import org.bardframework.validator.ValidatorAbstract;
import org.springframework.validation.Errors;

import java.util.Collections;
import java.util.List;

/**
 * Created by zafari on 10/7/2015.
 */
public abstract class TwoFieldCompareValidatorAbstract extends ValidatorAbstract<Comparable<?>> {

    private String other;

    public TwoFieldCompareValidatorAbstract(String other, String errorCode) {
        super(errorCode);
        this.other = other;
    }

    public TwoFieldCompareValidatorAbstract(String errorCode) {
        super(errorCode);
    }


    @Override
    public List<String> getArgsNames() {
        return Collections.singletonList(other);
    }

    @Override
    public final void validate(FieldValueHolder<Comparable<?>> fieldValue, List<FieldValueHolder<?>> args, Errors errors) {
        if (null == fieldValue.getValue()) {
            return;
        }
        if (null == args || 1 != args.size()) {
            log.error("error when validation '{}' value of '{}'.\n validator required one and only one args.\n this args not acceptable [{}]", fieldValue.getValue(), fieldValue.getField(), args);
            throw new IllegalStateException("error when validation.");
        }
        FieldValueHolder<?> otherValue = args.get(0);
        if (null == otherValue.getValue()) {
            return;
        }
        if (!(otherValue.getValue() instanceof Comparable)) {
            log.error("error when validation '{}' value of '{}'.\nvalidator args must be of type comparator.\n [{}] not valid.", fieldValue.getValue(), fieldValue.getField(), otherValue.getValue());
            throw new IllegalStateException("error when validation.");
        }
        if (null == fieldValue.getValue()) {
            return;
        }
        if (!isValid(fieldValue.getValue(), (Comparable<?>) otherValue.getValue())) {
            this.reject(fieldValue.getField(), errors, getArgs(fieldValue, otherValue).toArray());
        }
    }

    protected abstract boolean isValid(Comparable<?> value, Comparable<?> other);

    protected abstract List<Object> getArgs(FieldValueHolder<?> fieldValue, FieldValueHolder<?> otherValue);
}
