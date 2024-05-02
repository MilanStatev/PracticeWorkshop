package com.company.oop.furniture.models;

import com.company.oop.furniture.TestUtilities;
import com.company.oop.furniture.exceptions.InvalidUserInputException;
import com.company.oop.furniture.models.contracts.AdjustableChair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AdjustableChair_Tests {

    @Test
    public void setHeight_should_adjustChairHeight() {
        // Arrange
        AdjustableChair adjChair = TestUtilities.createAdjustableChair();

        // Act
        adjChair.setHeight(7);

        // Assert
        Assertions.assertEquals(7, adjChair.getHeight());
    }

    @Test
    public void setHeight_should_throwException_when_heightLessThanZero() {
        // Arrange
        AdjustableChair adjChair = TestUtilities.createAdjustableChair();

        //Act
        Assertions.assertThrows(InvalidUserInputException.class, () -> adjChair.setHeight(-1));

    }

}
