package org.bardframework.validator.field.collection;

import org.bardframework.validator.FieldValueHolder;
import org.bardframework.validator.field.SingleFieldValidatorAbstract;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by v.zafari on 30/01/2016.
 */
public class CollectionMaxSizeValidator extends SingleFieldValidatorAbstract<Collection<?>> {

    protected final int max;

    public CollectionMaxSizeValidator(int max) {
        super("collection_max_size.error");
        this.max = max;
    }

    public CollectionMaxSizeValidator(String errorCode, int max) {
        super(errorCode);
        this.max = max;
    }

    @Override
    protected boolean isValid(Collection<?> value) {
        return value.size() <= max;
    }

    @Override
    protected List<Object> getArgs(FieldValueHolder<Collection<?>> fieldValue) {
        return Arrays.asList(fieldValue.translateFieldName(messageSource, this.getLocale()), max);
    }
}
