package org.bardframework.validator.field.collection;

import org.bardframework.validator.FieldValueHolder;
import org.bardframework.validator.field.SingleFieldValidatorAbstract;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Created by v.zafari on 30/01/2016.
 */
public class CollectionUuidValidator extends SingleFieldValidatorAbstract<Collection<String>> {

    public CollectionUuidValidator() {
        this("collection_element_must_uuid");
    }

    public CollectionUuidValidator(String errorCode) {
        super(errorCode);
    }

    @Override
    protected boolean isValid(Collection<String> collection) {
        try {
            collection.forEach(UUID::fromString);
            return true;
        } catch (Exception e) {
            LOGGER.debug("invalid uuid in list [{}]", collection);
            return false;
        }
    }

    @Override
    protected List<Object> getArgs(FieldValueHolder<Collection<String>> fieldValue) {
        return Collections.singletonList(fieldValue.translateFieldName(messageSource, this.getLocale()));
    }
}
