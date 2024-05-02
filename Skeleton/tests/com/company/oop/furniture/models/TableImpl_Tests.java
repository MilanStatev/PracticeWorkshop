package com.company.oop.furniture.models;

import com.company.oop.furniture.TestData;
import com.company.oop.furniture.exceptions.InvalidUserInputException;
import com.company.oop.furniture.models.enums.MaterialType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class TableImpl_Tests {

    @ParameterizedTest(name = "with length: {0}")
    @ValueSource(ints = {0, -1})
    public void constructor_should_throwException_when_lengthInvalid(int length) {
        // Arrange, Act, Assert
        Assertions.assertThrows(InvalidUserInputException.class,
                () -> new TableImpl(TestData.Table.VALID_MODEL_CODE,
                        MaterialType.LEATHER,
                        Double.parseDouble(TestData.Table.VALID_PRICE),
                        Double.parseDouble(TestData.Table.VALID_HEIGHT),
                        length,
                        Double.parseDouble(TestData.Table.VALID_WIDTH)));
    }

    @ParameterizedTest(name = "with width: {0}")
    @ValueSource(ints = {0, -1})
    public void constructor_should_throwException_when_widthInvalid(int width) {
        // Arrange, Act, Assert
        Assertions.assertThrows(InvalidUserInputException.class,
                () -> new TableImpl(TestData.Table.VALID_MODEL_CODE,
                        MaterialType.LEATHER,
                        Double.parseDouble(TestData.Table.VALID_PRICE),
                        Double.parseDouble(TestData.Table.VALID_HEIGHT),
                        Double.parseDouble(TestData.Table.VALID_LENGTH),
                        width));
    }

}
