package org.bardframework.validator.holder;

import org.bardframework.validator.Validator;

import java.util.List;
import java.util.Map;

/**
 * Created by Vahid Zafari on 5/12/2016.
 */
public interface ValidatorHolder {

    Class<?> getGroup();

    Map<String, List<Validator<?>>> getValidators();
}
