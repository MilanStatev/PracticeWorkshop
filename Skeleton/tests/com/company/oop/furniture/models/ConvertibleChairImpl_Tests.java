package com.company.oop.furniture.models;

import com.company.oop.furniture.TestUtilities;
import com.company.oop.furniture.models.contracts.ConvertibleChair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.company.oop.furniture.models.ConvertibleChairImpl.CONVERTED_HEIGHT;

public class ConvertibleChairImpl_Tests {

    @Test
    public void convert_should_changeIsConvertedFromTrueToFalse() {
        // Arrange
        ConvertibleChair convertibleChair = TestUtilities.createConvertibleChair();

        // Act
        convertibleChair.convert();

        // Assert
        // A ConvertibleChair is initialized with isConverted equal to false.
        Assertions.assertTrue(convertibleChair.getIsConverted());
    }

    @Test
    public void convert_should_changeIsConvertedFromFalseToTrue() {
        // Arrange
        ConvertibleChair convertibleChair = TestUtilities.createConvertibleChair();
        convertibleChair.convert();

        // Act
        convertibleChair.convert();

        // Assert
        // A ConvertibleChair is initialized with isConverted equal to false.
        Assertions.assertFalse(convertibleChair.getIsConverted());
    }

    @Test
    public void convert_should_convertHeightCorrectly() {
        // Arrange
        ConvertibleChair convertibleChair = TestUtilities.createConvertibleChair();

        // Act
        convertibleChair.convert();

        // Assert
        Assertions.assertEquals(CONVERTED_HEIGHT, convertibleChair.getHeight());
    }

    @Test
    public void convert_should_restoreOriginalHeight() {
        // Arrange
        ConvertibleChair convertibleChair = TestUtilities.createConvertibleChair();
        double initialHeight = convertibleChair.getHeight();
        convertibleChair.convert();

        // Act
        convertibleChair.convert();

        // Assert
        Assertions.assertEquals(initialHeight, convertibleChair.getHeight());
    }

}
