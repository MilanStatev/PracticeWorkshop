package com.company.oop.furniture.utils;

import com.company.oop.furniture.exceptions.InvalidUserInputException;

import java.util.List;

public class ValidationHelper {

    public static final String INVALID_NUMBER_OF_ARGUMENTS = "Invalid number of arguments. Expected: %d, Received: %d";

    public static void validateOnlyDigits(String stringToValidate, String errorMessage) {
        for (char character : stringToValidate.toCharArray()) {
            if (!Character.isDigit(character)) {
                throw new InvalidUserInputException(errorMessage);
            }
        }
    }

    public static void validateArgumentsCount(List<String> list, int expectedArgumentsCount) {
        if (list.size() < expectedArgumentsCount || list.size() > expectedArgumentsCount) {
            throw new InvalidUserInputException(String.format(INVALID_NUMBER_OF_ARGUMENTS, expectedArgumentsCount, list.size()));
        }
    }

    public static void validateGreaterThanZero(double value) {
        if (value <= 0) {
            throw new InvalidUserInputException();
        }
    }

    public static void validateStringMinLength(String value, int minLength) {
        if (value == null || value.length() < minLength) {
            throw new InvalidUserInputException();
        }
    }

    public static void validateStringExactLength(String value, int length, String errorMessage) {
        if (value == null || value.length() != length) {
            throw new InvalidUserInputException(errorMessage);
        }
    }

}