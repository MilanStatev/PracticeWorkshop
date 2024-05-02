package com.company.oop.furniture.utils;

import com.company.oop.furniture.TestUtilities;
import com.company.oop.furniture.exceptions.InvalidUserInputException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.company.oop.furniture.utils.ValidationHelper.*;

public class ValidationHelpers_Tests {

    @Test
    void validateOnlyDigits_shouldNotThrowException_when_onlyDigits() {
        Assertions.assertDoesNotThrow(() -> validateOnlyDigits("123", ""));
    }

    @Test
    void validateOnlyDigits_shouldThrowException_when_notOnlyDigits() {
        Assertions.assertThrows(InvalidUserInputException.class, () -> validateOnlyDigits("123A", ""));
    }

    @Test
    void validateArgumentsCount_shouldNotThrowException_when_argumentsHaveCorrectCount() {
        Assertions.assertDoesNotThrow(() -> validateArgumentsCount(TestUtilities.initializeListWithSize(2), 2));
    }

    @Test
    void validateArgumentsCount_shouldThrowException_when_argumentsDoesntHaveCorrectCount() {
        Assertions.assertThrows(InvalidUserInputException.class,
                () -> validateArgumentsCount(TestUtilities.initializeListWithSize(2), 6));
    }

    @Test
    void validateGreaterThanZero_shouldNotThrowException_when_graterThanZero() {
        Assertions.assertDoesNotThrow(() -> validateGreaterThanZero(1));
    }

    @Test
    void validateGreaterThanZero_shouldThrowException_when_lessThanZero() {
        Assertions.assertThrows(InvalidUserInputException.class, () -> validateGreaterThanZero(-1));
    }

    @Test
    void validateStringMinLength_shouldNotThrowException_when_stringMatchesExpectedLength() {
        Assertions.assertDoesNotThrow(() -> validateStringMinLength(TestUtilities.initializeStringWithSize(5), 2));
    }

    @Test
    void validateStringMinLength_shouldThrowException_when_stringDoesntMatchExpectedLength() {
        Assertions.assertThrows(InvalidUserInputException.class,
                () -> validateStringMinLength(TestUtilities.initializeStringWithSize(2), 5));
    }

    @Test
    void validateStringExactLength_shouldNotThrowException_when_stringMatchesExpectedLength() {
        Assertions.assertDoesNotThrow(() -> validateStringMinLength(TestUtilities.initializeStringWithSize(5), 5));
    }

    @Test
    void validateStringExactLength_shouldThrowException_when_stringDoesntMatchExpectedLength() {
        Assertions.assertThrows(InvalidUserInputException.class,
                () -> validateStringMinLength(TestUtilities.initializeStringWithSize(2), 5));
    }
}
