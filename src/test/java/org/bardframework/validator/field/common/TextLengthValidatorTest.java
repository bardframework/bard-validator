package org.bardframework.validator.field.common;

import org.assertj.core.api.Assertions;
import org.bardframework.commons.utils.RandomStringUtils;
import org.junit.jupiter.api.Test;

class TextLengthValidatorTest {

    private final TextLengthValidator lengthValidator = new TextLengthValidator(10, 2);

    @Test
    void isValid() {
        Assertions.assertThat(lengthValidator.isValid(RandomStringUtils.randomAlphanumeric(2, 10))).isTrue();
    }

    @Test
    void isInvalid() {
        Assertions.assertThat(lengthValidator.isValid(RandomStringUtils.randomAlphanumeric(1))).isFalse();
        Assertions.assertThat(lengthValidator.isValid(RandomStringUtils.randomAlphanumeric(11))).isFalse();
    }
}
