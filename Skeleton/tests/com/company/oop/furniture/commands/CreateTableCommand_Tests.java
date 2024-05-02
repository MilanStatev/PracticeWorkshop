package com.company.oop.furniture.commands;

import com.company.oop.furniture.TestData;
import com.company.oop.furniture.TestUtilities;
import com.company.oop.furniture.core.FurnitureRepositoryImpl;
import com.company.oop.furniture.core.contracts.FurnitureRepository;
import com.company.oop.furniture.exceptions.InvalidUserInputException;
import com.company.oop.furniture.models.contracts.Furniture;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static com.company.oop.furniture.commands.CreateTableCommand.EXPECTED_NUMBER_OF_ARGUMENTS;

public class CreateTableCommand_Tests {

    private Command command;
    private FurnitureRepository furnitureRepository;

    @BeforeEach
    public void before() {
        furnitureRepository = new FurnitureRepositoryImpl();
        command = new CreateTableCommand(furnitureRepository);
    }

    @ParameterizedTest(name = "with arguments count: {0}")
    @ValueSource(ints = {EXPECTED_NUMBER_OF_ARGUMENTS - 1, EXPECTED_NUMBER_OF_ARGUMENTS + 1})
    public void execute_should_throwException_when_argumentsCountDifferentThanExpected(int argumentsCount) {
        // Arrange
        List<String> arguments = TestUtilities.initializeListWithSize(argumentsCount);

        // Act, Assert
        Assertions.assertThrows(InvalidUserInputException.class, () -> command.execute(arguments));
    }

    @Test
    public void execute_should_throwException_when_materialUnparsable() {
        // Arrange, Act
        List<String> arguments = List.of(
                TestData.Table.VALID_MODEL_CODE,
                "invalid-material",
                TestData.Table.VALID_PRICE,
                TestData.Table.VALID_HEIGHT,
                TestData.Table.VALID_LENGTH,
                TestData.Table.VALID_WIDTH);

        // Arrange
        Assertions.assertThrows(InvalidUserInputException.class,
                () -> command.execute(arguments));

    }

    @Test
    public void execute_should_throwException_when_priceUnparsable() {
        // Arrange, Act
        List<String> arguments = List.of(
                TestData.Table.VALID_MODEL_CODE,
                TestData.Table.VALID_MATERIAL,
                "invalid-price",
                TestData.Table.VALID_HEIGHT,
                TestData.Table.VALID_LENGTH,
                TestData.Table.VALID_WIDTH);

        // Arrange
        Assertions.assertThrows(InvalidUserInputException.class,
                () -> command.execute(arguments));

    }

    @Test
    public void execute_should_throwException_when_heightUnparsable() {
        // Arrange, Act
        List<String> arguments = List.of(
                TestData.Table.VALID_MODEL_CODE,
                TestData.Table.VALID_MATERIAL,
                TestData.Table.VALID_PRICE,
                "invalid-height",
                TestData.Table.VALID_LENGTH,
                TestData.Table.VALID_WIDTH);

        // Arrange
        Assertions.assertThrows(InvalidUserInputException.class,
                () -> command.execute(arguments));

    }

    @Test
    public void execute_should_throwException_when_lengthUnparsable() {
        // Arrange, Act
        List<String> arguments = List.of(
                TestData.Table.VALID_MODEL_CODE,
                TestData.Table.VALID_MATERIAL,
                TestData.Table.VALID_PRICE,
                TestData.Table.VALID_HEIGHT,
                "invalid-length",
                TestData.Table.VALID_WIDTH);

        // Arrange
        Assertions.assertThrows(InvalidUserInputException.class,
                () -> command.execute(arguments));

    }

    @Test
    public void execute_should_throwException_when_widthUnparsable() {
        // Arrange, Act
        List<String> arguments = List.of(
                TestData.Table.VALID_MODEL_CODE,
                TestData.Table.VALID_MATERIAL,
                TestData.Table.VALID_PRICE,
                TestData.Table.VALID_HEIGHT,
                TestData.Table.VALID_LENGTH,
                "invalid-width");

        // Arrange
        Assertions.assertThrows(InvalidUserInputException.class,
                () -> command.execute(arguments));

    }

    @Test
    public void execute_should_throwException_when_modelCodeExists() {
        // Arrange, Act
        Furniture furniture = TestUtilities.initializeFurniture(furnitureRepository);

        List<String> arguments = List.of(
                furniture.getModelCode(),
                TestData.Table.VALID_MATERIAL,
                TestData.Table.VALID_PRICE,
                TestData.Table.VALID_HEIGHT,
                TestData.Table.VALID_LENGTH,
                TestData.Table.VALID_WIDTH);

        // Arrange
        Assertions.assertThrows(InvalidUserInputException.class,
                () -> command.execute(arguments));

    }

    @Test
    public void execute_should_throwException_when_inputIsValid() {
        // Arrange, Act
        List<String> arguments = List.of(
                TestData.Table.VALID_MODEL_CODE,
                TestData.Table.VALID_MATERIAL,
                TestData.Table.VALID_PRICE,
                TestData.Table.VALID_HEIGHT,
                TestData.Table.VALID_LENGTH,
                TestData.Table.VALID_WIDTH);

        // Arrange
        Assertions.assertAll(
                () -> Assertions.assertDoesNotThrow(() -> command.execute(arguments)),
                () -> Assertions.assertEquals(1, furnitureRepository.getFurniture().size())
        );

    }

}
