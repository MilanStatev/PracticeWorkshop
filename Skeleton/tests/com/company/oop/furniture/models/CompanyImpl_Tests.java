package com.company.oop.furniture.models;

import com.company.oop.furniture.TestData;
import com.company.oop.furniture.TestUtilities;
import com.company.oop.furniture.exceptions.ElementNotFoundException;
import com.company.oop.furniture.exceptions.InvalidUserInputException;
import com.company.oop.furniture.models.contracts.Chair;
import com.company.oop.furniture.models.contracts.Company;
import com.company.oop.furniture.models.contracts.Furniture;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static com.company.oop.furniture.models.CompanyImpl.NAME_MIN_LENGTH;
import static com.company.oop.furniture.models.CompanyImpl.REGISTRATION_LENGTH;

public class CompanyImpl_Tests {

    @ParameterizedTest(name = "with registration number length: {0}")
    @ValueSource(ints = {REGISTRATION_LENGTH - 1, REGISTRATION_LENGTH + 1})
    public void constructor_should_throwException_when_registrationNumberLengthDifferentThanExpected(int length) {
        // Arrange
        // registration must contain digits only
        String registration = "1".repeat(length);

        // Act, Assert
        Assertions.assertThrows(InvalidUserInputException.class,
                () -> new CompanyImpl(TestData.Company.VALID_NAME, registration));
    }

    @Test
    public void constructor_should_throwException_when_registrationNumberContainsInvalidCharacters() {
        // Arrange
        // registration must contain digits only
        String registration = "x".repeat(REGISTRATION_LENGTH);

        // Act, Assert
        Assertions.assertThrows(InvalidUserInputException.class,
                () -> new CompanyImpl(TestData.Company.VALID_NAME, registration));
    }

    @Test
    public void constructor_should_throwError_when_nameLengthIsLessThanMin() {
        // Arrange, Act, Assert
        Assertions.assertThrows(InvalidUserInputException.class,
                () -> new CompanyImpl(TestUtilities.initializeStringWithSize(NAME_MIN_LENGTH - 1), TestData.Company.VALID_REGISTRATION));
    }

    @Test
    public void constructor_should_createCompany_when_valuesAreValid() {
        // Arrange, Act, Assert
        Assertions.assertDoesNotThrow(() -> new CompanyImpl(TestData.Company.VALID_NAME, TestData.Company.VALID_REGISTRATION));
    }

    @Test
    public void getFurniture_should_returnCopy() {
        // Arrange
        Company company = TestUtilities.createCompany();

        // Act
        List<Furniture> furniture = company.getFurniture();
        furniture.add(TestUtilities.createTable());

        // Assert
        Assertions.assertEquals(0, company.getFurniture().size());
    }

    @Test
    public void findFurnitureByModelCode_should_returnFurniture_when_furnitureExists() {
        // Arrange
        Company company = TestUtilities.createCompany();
        Furniture furniture = TestUtilities.createTable();
        company.addFurniture(furniture);

        // Act
        Furniture found = company.findFurnitureByModelCode(furniture.getModelCode());

        // Assert
        Assertions.assertEquals(found.getModelCode(), found.getModelCode());
    }

    @Test
    public void findFurnitureByModelCode_should_throwException_when_furnitureDoesntExist() {
        // Arrange
        Company company = TestUtilities.createCompany();

        // Act, Assert
        Assertions.assertThrows(ElementNotFoundException.class, () -> company.findFurnitureByModelCode("model123"));
    }

    @Test
    public void removeFurniture_should_removeFurniture_when_furnitureExists() {
        // Arrange
        Company company = TestUtilities.createCompany();
        Chair chair = TestUtilities.createChair();
        company.addFurniture(chair);

        // Act
        company.removeFurniture(chair);

        // Assert
        Assertions.assertEquals(0, company.getFurniture().size());
    }

}
