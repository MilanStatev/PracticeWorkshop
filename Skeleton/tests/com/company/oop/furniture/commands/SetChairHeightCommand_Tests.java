package com.company.oop.furniture.commands;

import com.company.oop.furniture.TestData;
import com.company.oop.furniture.TestUtilities;
import com.company.oop.furniture.core.FurnitureRepositoryImpl;
import com.company.oop.furniture.core.contracts.FurnitureRepository;
import com.company.oop.furniture.exceptions.ElementNotFoundException;
import com.company.oop.furniture.exceptions.InvalidUserInputException;
import com.company.oop.furniture.models.contracts.AdjustableChair;
import com.company.oop.furniture.models.contracts.Chair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

public class SetChairHeightCommand_Tests {

    private Command command;
    private FurnitureRepository furnitureRepository;

    @BeforeEach
    public void before() {
        furnitureRepository = new FurnitureRepositoryImpl();
        command = new SetChairHeightCommand(furnitureRepository);
    }

    @ParameterizedTest(name = "with arguments count: {0}")
    @ValueSource(ints = {SetChairHeightCommand.EXPECTED_NUMBER_OF_ARGUMENTS - 1, SetChairHeightCommand.EXPECTED_NUMBER_OF_ARGUMENTS + 1})
    public void execute_should_throwException_when_argumentsCountDifferentThanExpected(int argumentsCount) {
        // Arrange
        List<String> arguments = TestUtilities.initializeListWithSize(argumentsCount);

        // Act, Assert
        Assertions.assertThrows(InvalidUserInputException.class, () -> command.execute(arguments));
    }

    @Test
    public void execute_should_throwException_when_heightUnparsable() {
        // Arrange, Act
        List<String> arguments = List.of(TestData.Table.VALID_MODEL_CODE, "invalid-height");

        // Arrange
        Assertions.assertThrows(InvalidUserInputException.class, () -> command.execute(arguments));

    }

    @Test
    public void execute_should_throwException_when_providedModelCodeNotOfAdjustableChair() {
        // Arrange
        Chair chair = TestUtilities.initializeChair(furnitureRepository);
        List<String> arguments = List.of(chair.getModelCode(), "1.11");

        // Act, Arrange
        Assertions.assertThrows(ElementNotFoundException.class, () -> command.execute(arguments));
    }


    @Test
    public void execute_should_setHeight_when_inputValid() {
        // Arrange
        AdjustableChair chair = TestUtilities.initializeAdjustableChair(furnitureRepository);
        List<String> arguments = List.of(chair.getModelCode(), "1.11");

        // Act, Arrange
        Assertions.assertAll(
                () -> Assertions.assertDoesNotThrow(() -> command.execute(arguments)),
                () -> Assertions.assertEquals(1.11, chair.getHeight())
        );
    }

}
