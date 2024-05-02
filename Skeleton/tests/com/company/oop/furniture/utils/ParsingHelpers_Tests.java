package com.company.oop.furniture.utils;

import com.company.oop.furniture.exceptions.InvalidUserInputException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.company.oop.furniture.utils.ParsingHelpers.*;

public class ParsingHelpers_Tests {

    enum TestEnum {
        TEST_VALUE,
        ANOTHER_TEST_VALUE
    }

    @Test
    void tryParseEnum_should_parseCorrectly_when_valueToParseHasWhitespace() {
        Assertions.assertEquals(TestEnum.TEST_VALUE,
                tryParseEnum("test value", TestEnum.class, ""));
    }

    @Test
    void tryParseEnum_should_parseCorrectly_when_valueToParseHasMultipleWhitespaces() {
        Assertions.assertEquals(TestEnum.ANOTHER_TEST_VALUE,
                tryParseEnum("another test value", TestEnum.class, ""));
    }

    @Test
    void tryParseDouble_should_throwException_when_valueUnparsable() {
        Assertions.assertThrows(InvalidUserInputException.class,
                () -> tryParseDouble("invalid-value", ""));
    }

    @Test
    void tryParseDouble_should_return_when_valueParsable() {
        Assertions.assertAll(
                () -> Assertions.assertDoesNotThrow(() -> tryParseDouble("1.1", "")),
                () -> Assertions.assertEquals(1.1, tryParseDouble("1.1", ""))
        );
    }

    @Test
    void tryParseInteger_should_throwException_when_valueUnparsable() {
        Assertions.assertThrows(InvalidUserInputException.class,
                () -> tryParseInteger("invalid-value", ""));
    }

    @Test
    void tryParseInteger_should_return_when_valueParsable() {
        Assertions.assertAll(
                () -> Assertions.assertDoesNotThrow(() -> tryParseInteger("1", "")),
                () -> Assertions.assertEquals(1, tryParseInteger("1", ""))
        );
    }
}
