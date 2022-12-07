package org.bardframework.validator.field.collection;

import org.bardframework.validator.FieldValueHolder;
import org.bardframework.validator.field.SingleFieldValidatorAbstract;

import java.util.Collection;
import java.util.List;

/**
 * Created by v.zafari on 30/01/2016.
 */
public class CollectionMinSizeValidator extends SingleFieldValidatorAbstract<Collection<?>> {
    protected final int min;

    public CollectionMinSizeValidator(int min) {
        this(min, "collection_min_size.error");
    }

    public CollectionMinSizeValidator(int min, String errorCode) {
        super(errorCode);
        this.min = min;
    }

    @Override
    protected boolean isValid(Collection<?> value) {
        return value.size() >= min;
    }

    @Override
    protected List<Object> getArgs(FieldValueHolder<Collection<?>> fieldValue) {
        return List.of(fieldValue.translateFieldName(messageSource, this.getLocale()), min);
    }
}
