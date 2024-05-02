package com.company.oop.furniture.models;

import com.company.oop.furniture.TestData;
import com.company.oop.furniture.exceptions.InvalidUserInputException;
import com.company.oop.furniture.models.enums.MaterialType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ChairImpl_Tests {

    @Test
    public void constructor_should_throwError_when_numberOfLegsIsLessThanZero() {
        //Arrange, Act, Assert
        Assertions.assertThrows(InvalidUserInputException.class,
                () -> new ChairImpl(TestData.Chair.VALID_MODEL_CODE,
                        MaterialType.LEATHER,
                        Double.parseDouble(TestData.Chair.VALID_PRICE),
                        Double.parseDouble(TestData.Chair.VALID_HEIGHT),
                        -1));
    }

}
