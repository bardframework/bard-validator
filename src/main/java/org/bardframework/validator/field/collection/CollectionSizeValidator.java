package org.bardframework.validator.field.collection;

import org.bardframework.validator.FieldValueHolder;
import org.bardframework.validator.field.SingleFieldValidatorAbstract;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by v.zafari on 30/01/2016.
 */
public class CollectionSizeValidator extends SingleFieldValidatorAbstract<Collection<?>> {
    protected final int min;
    protected final int max;

    public CollectionSizeValidator(int max, int min) {
        this(min, max, "collection_min_max_size.error");
    }

    public CollectionSizeValidator(int min, int max, String errorCode) {
        super(errorCode);
        this.min = min;
        this.max = max;
    }

    @Override
    protected boolean isValid(Collection<?> value) {
        return value.size() >= min && value.size() <= max;
    }

    @Override
    protected List<Object> getArgs(FieldValueHolder<Collection<?>> fieldValue) {
        return Arrays.asList(fieldValue.translateFieldName(messageSource, this.getLocale()), min, max);
    }
}
