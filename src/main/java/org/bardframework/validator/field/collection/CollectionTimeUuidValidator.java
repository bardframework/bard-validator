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
public class CollectionTimeUuidValidator extends SingleFieldValidatorAbstract<Collection<String>> {

    public CollectionTimeUuidValidator() {
        this("collection_element_must_time_uuid");
    }

    public CollectionTimeUuidValidator(String errorCode) {
        super(errorCode);
    }

    @Override
    protected boolean isValid(Collection<String> collection) {
        try {
            for (String uuidString : collection) {
                UUID uuid = UUID.fromString(uuidString);
                if (uuid.version() != 1) {
                    LOGGER.debug("invalid uuid in list, [{}] is not time uuid", uuidString);
                    return false;
                }

            }
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
