package com.company.oop.furniture.models;

import com.company.oop.furniture.TestData;
import com.company.oop.furniture.TestUtilities;
import com.company.oop.furniture.exceptions.InvalidUserInputException;
import com.company.oop.furniture.models.enums.MaterialType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FurnitureBase_Tests {
    @Test
    public void constructor_should_throwException_when_modelCodeLengthLessThanMinimum() {
        //Arrange, Act, Assert
        Assertions.assertThrows(InvalidUserInputException.class,
                () -> new ChairImpl(TestUtilities.initializeStringWithSize(FurnitureBase.MODEL_CODE_MIN_LENGTH - 1),
                        MaterialType.LEATHER,
                        Double.parseDouble(TestData.Chair.VALID_PRICE),
                        Double.parseDouble(TestData.Chair.VALID_HEIGHT),
                        Integer.parseInt(TestData.Chair.VALID_LEGS_COUNT)));
    }

    @Test
    public void constructor_should_throwException_when_heightIsLessThanZero() {
        //Arrange, Act, Assert
        Assertions.assertThrows(InvalidUserInputException.class,
                () -> new ChairImpl(TestData.Chair.VALID_MODEL_CODE,
                        MaterialType.LEATHER,
                        Double.parseDouble(TestData.Chair.VALID_PRICE),
                        -1,
                        Integer.parseInt(TestData.Chair.VALID_LEGS_COUNT)));
    }

    @Test
    public void constructor_should_throwException_when_priceIsLessThanZero() {
        //Arrange, Act, Assert
        Assertions.assertThrows(InvalidUserInputException.class,
                () -> new ChairImpl(TestUtilities.initializeStringWithSize(FurnitureBase.MODEL_CODE_MIN_LENGTH - 1),
                        MaterialType.LEATHER,
                        -1,
                        Double.parseDouble(TestData.Chair.VALID_HEIGHT),
                        Integer.parseInt(TestData.Chair.VALID_LEGS_COUNT)));
    }

}
