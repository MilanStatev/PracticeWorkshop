package com.company.oop.furniture.commands;

import com.company.oop.furniture.TestUtilities;
import com.company.oop.furniture.core.FurnitureRepositoryImpl;
import com.company.oop.furniture.core.contracts.FurnitureRepository;
import com.company.oop.furniture.exceptions.ElementNotFoundException;
import com.company.oop.furniture.exceptions.InvalidUserInputException;
import com.company.oop.furniture.models.contracts.Chair;
import com.company.oop.furniture.models.contracts.ConvertibleChair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

/**
 * ConvertChairCommand arguments: {{Furniture Model Code}}
 */
public class ConvertChairCommand_Tests {

    private Command command;
    private FurnitureRepository furnitureRepository;

    @BeforeEach
    public void before() {
        furnitureRepository = new FurnitureRepositoryImpl();
        command = new ConvertChairCommand(furnitureRepository);
    }

    @ParameterizedTest(name = "with arguments count: {0}")
    @ValueSource(ints = {ConvertChairCommand.EXPECTED_NUMBER_OF_ARGUMENTS - 1, ConvertChairCommand.EXPECTED_NUMBER_OF_ARGUMENTS + 1})
    public void execute_should_throwException_when_argumentsCountDifferentThanExpected(int argumentsCount) {
        // Arrange
        List<String> arguments = TestUtilities.initializeListWithSize(argumentsCount);

        // Act, Assert
        Assertions.assertThrows(InvalidUserInputException.class, () -> command.execute(arguments));
    }

    @Test
    public void execute_should_throwException_when_chairDoesNotExist() {
        // Arrange
        List<String> testList = List.of("invalid-model-code");

        // Act & Assert
        Assertions.assertThrows(ElementNotFoundException.class, () -> command.execute(testList));
    }

    @Test
    public void execute_should_throwException_when_chairExistButIsNotConvertible() {
        // Arrange
        Chair chair = TestUtilities.createChair();
        furnitureRepository.addChair(chair);
        List<String> testList = List.of(chair.getModelCode());

        // Act & Assert
        Assertions.assertThrows(ElementNotFoundException.class, () -> command.execute(testList));
    }

    @Test
    public void execute_should_convertChair_when_inputIsValid() {
        // Arrange
        ConvertibleChair chair = TestUtilities.createConvertibleChair();
        furnitureRepository.addConvertibleChair(chair);

        List<String> testList = List.of(chair.getModelCode());

        // Act
        command.execute(testList);

        // Assert
        Assertions.assertTrue(chair.getIsConverted());
    }

}
