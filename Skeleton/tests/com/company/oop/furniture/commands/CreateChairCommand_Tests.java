package com.company.oop.furniture.commands;

import com.company.oop.furniture.TestData;
import com.company.oop.furniture.TestUtilities;
import com.company.oop.furniture.core.FurnitureRepositoryImpl;
import com.company.oop.furniture.core.contracts.FurnitureRepository;
import com.company.oop.furniture.exceptions.InvalidUserInputException;
import com.company.oop.furniture.models.contracts.Chair;
import com.company.oop.furniture.models.enums.ChairType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

/**
 * CreateChairCommand arguments:  {{Model Code}} {{Material}} {{Price}} {{Height}} {{Number of legs}} {{Type}}
 */
public class CreateChairCommand_Tests {

    private Command command;
    private FurnitureRepository furnitureRepository;

    @BeforeEach
    public void before() {
        furnitureRepository = new FurnitureRepositoryImpl();
        command = new CreateChairCommand(furnitureRepository);
    }

    @ParameterizedTest(name = "with arguments count: {0}")
    @ValueSource(ints = {CreateChairCommand.EXPECTED_NUMBER_OF_ARGUMENTS - 1, CreateChairCommand.EXPECTED_NUMBER_OF_ARGUMENTS + 1})
    public void execute_should_throwException_when_argumentsCountDifferentThanExpected(int argumentsCount) {
        // Arrange
        List<String> arguments = TestUtilities.initializeListWithSize(argumentsCount);

        // Act, Assert
        Assertions.assertThrows(InvalidUserInputException.class, () -> command.execute(arguments));
    }

    @Test
    public void execute_should_throwException_when_furnitureWithSameModelCodeExists() {
        // Arrange, Act
        Chair existingChair = TestUtilities.createChair();
        furnitureRepository.addChair(existingChair);

        List<String> arguments = List.of(existingChair.getModelCode(),
                TestData.Chair.VALID_MATERIAL,
                TestData.Chair.VALID_PRICE,
                TestData.Chair.VALID_HEIGHT,
                TestData.Chair.VALID_LEGS_COUNT,
                ChairType.REGULAR.toString());

        // Assert
        Assertions.assertThrows(InvalidUserInputException.class, () -> command.execute(arguments));
    }

    @Test
    public void execute_should_throwException_when_materialTypeInvalid() {
        // Arrange, Act
        List<String> arguments = List.of(TestData.Chair.VALID_MODEL_CODE,
                "invalid-material",
                TestData.Chair.VALID_PRICE,
                TestData.Chair.VALID_HEIGHT,
                TestData.Chair.VALID_LEGS_COUNT,
                ChairType.REGULAR.toString());

        // Assert
        Assertions.assertThrows(InvalidUserInputException.class, () -> command.execute(arguments));
    }

    @Test

    public void execute_should_throwException_when_chairTypeInvalid() {
        // Arrange, Act
        List<String> arguments = List.of(TestData.Chair.VALID_MODEL_CODE,
                TestData.Chair.VALID_MATERIAL,
                TestData.Chair.VALID_PRICE,
                TestData.Chair.VALID_HEIGHT,
                TestData.Chair.VALID_LEGS_COUNT,
                "invalid-chair-type");

        // Assert
        Assertions.assertThrows(InvalidUserInputException.class, () -> command.execute(arguments));
    }

    @Test
    public void execute_should_createRegularChair_when_inputIsValid() {
        // Arrange, Act
        String chairModelCode = TestData.Chair.VALID_MODEL_CODE;
        command.execute(List.of(chairModelCode,
                TestData.Chair.VALID_MATERIAL,
                TestData.Chair.VALID_PRICE,
                TestData.Chair.VALID_HEIGHT,
                TestData.Chair.VALID_LEGS_COUNT,
                ChairType.REGULAR.toString()));

        // Assert
        Assertions.assertAll(
                () -> Assertions.assertEquals(1, furnitureRepository.getFurniture().size()),
                () -> Assertions.assertNotNull(furnitureRepository.findChairByModelCode(chairModelCode))
        );
    }

    @Test
    public void execute_should_createConvertibleChair_when_inputIsValid() {
        // Arrange, Act
        String chairModelCode = TestData.Chair.VALID_MODEL_CODE;
        command.execute(List.of(chairModelCode,
                TestData.Chair.VALID_MATERIAL,
                TestData.Chair.VALID_PRICE,
                TestData.Chair.VALID_HEIGHT,
                TestData.Chair.VALID_LEGS_COUNT,
                ChairType.CONVERTIBLE.toString()));

        // Assert
        Assertions.assertAll(
                () -> Assertions.assertEquals(1, furnitureRepository.getFurniture().size()),
                () -> Assertions.assertNotNull(furnitureRepository.findConvertibleChairByModelCode(chairModelCode))
        );
    }

    @Test
    public void execute_should_createAdjustableChair_when_inputIsValid() {
        // Arrange, Act
        String chairModelCode = TestData.Chair.VALID_MODEL_CODE;
        command.execute(List.of(chairModelCode,
                TestData.Chair.VALID_MATERIAL,
                TestData.Chair.VALID_PRICE,
                TestData.Chair.VALID_HEIGHT,
                TestData.Chair.VALID_LEGS_COUNT,
                ChairType.ADJUSTABLE.toString()));

        // Assert
        Assertions.assertAll(
                () -> Assertions.assertEquals(1, furnitureRepository.getFurniture().size()),
                () -> Assertions.assertNotNull(furnitureRepository.findAdjustableChairByModelCode(chairModelCode))
        );
    }

}
