package com.company.oop.furniture.commands;

import com.company.oop.furniture.TestUtilities;
import com.company.oop.furniture.core.FurnitureRepositoryImpl;
import com.company.oop.furniture.core.contracts.FurnitureRepository;
import com.company.oop.furniture.exceptions.ElementNotFoundException;
import com.company.oop.furniture.exceptions.InvalidUserInputException;
import com.company.oop.furniture.models.contracts.Chair;
import com.company.oop.furniture.models.contracts.Company;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

/**
 * AddFurnitureToCompanyCommand arguments: {{Company Name}} {{Furniture Model Code}}
 */
public class AddFurnitureToCompanyCommand_Tests {

    private Command command;
    private FurnitureRepository furnitureRepository;

    @BeforeEach
    public void before() {
        furnitureRepository = new FurnitureRepositoryImpl();
        command = new AddFurnitureToCompanyCommand(furnitureRepository);
    }

    @ParameterizedTest(name = "with arguments count: {0}")
    @ValueSource(ints = {AddFurnitureToCompanyCommand.EXPECTED_NUMBER_OF_ARGUMENTS - 1, AddFurnitureToCompanyCommand.EXPECTED_NUMBER_OF_ARGUMENTS + 1})
    public void execute_should_throwException_when_argumentsCountDifferentThanExpected(int argumentsCount) {
        // Arrange
        List<String> arguments = TestUtilities.initializeListWithSize(argumentsCount);

        // Act, Assert
        Assertions.assertThrows(InvalidUserInputException.class, () -> command.execute(arguments));
    }

    @Test
    public void execute_should_throwException_when_companyDoesNotExist() {
        // Arrange
        Chair chair = TestUtilities.createChair();
        furnitureRepository.addChair(chair);
        List<String> testList = List.of("invalid-company", chair.getModelCode());

        // Act & Assert
        Assertions.assertThrows(ElementNotFoundException.class, () -> command.execute(testList));
    }

    @Test
    public void execute_should_throwException_when_furnitureDoesNotExist() {
        // Arrange
        Company company = TestUtilities.createCompany();
        furnitureRepository.addCompany(company);
        List<String> testList = List.of(company.getName(), "invalid-model-code");

        // Act & Assert
        Assertions.assertThrows(ElementNotFoundException.class, () -> command.execute(testList));
    }

    @Test
    public void execute_should_addFurnitureToCompany_whenInputCorrect() {
        // Arrange
        Chair chair = TestUtilities.createChair();
        Company company = TestUtilities.createCompany();
        furnitureRepository.addChair(chair);
        furnitureRepository.addCompany(company);

        List<String> testList = List.of(company.getName(), chair.getModelCode());

        // Act
        command.execute(testList);

        // Assert
        Assertions.assertEquals(1, company.getFurniture().size());

    }

}
