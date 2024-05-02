package com.company.oop.furniture.commands;

import com.company.oop.furniture.TestUtilities;
import com.company.oop.furniture.core.FurnitureRepositoryImpl;
import com.company.oop.furniture.core.contracts.FurnitureRepository;
import com.company.oop.furniture.exceptions.InvalidUserInputException;
import com.company.oop.furniture.models.contracts.Company;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

public class ShowCompanyCatalogCommand_Tests {

    private Command command;
    private FurnitureRepository furnitureRepository;

    @BeforeEach
    public void before() {
        furnitureRepository = new FurnitureRepositoryImpl();
        command = new ShowCompanyCatalogCommand(furnitureRepository);
    }

    @ParameterizedTest(name = "with arguments count: {0}")
    @ValueSource(ints = {ShowCompanyCatalogCommand.EXPECTED_NUMBER_OF_ARGUMENTS - 1, ShowCompanyCatalogCommand.EXPECTED_NUMBER_OF_ARGUMENTS + 1})
    public void execute_should_throwException_when_argumentsCountDifferentThanExpected(int argumentsCount) {
        // Arrange
        List<String> arguments = TestUtilities.initializeListWithSize(argumentsCount);

        // Act, Assert
        Assertions.assertThrows(InvalidUserInputException.class, () -> command.execute(arguments));
    }

    @Test
    public void execute_should_printCatalog_when_inputValid() {
        //Arrange
        Company company = TestUtilities.initializeCompany(furnitureRepository);
        List<String> arguments = List.of(company.getName());

        //Act, Assert
        Assertions.assertAll(
                () -> Assertions.assertDoesNotThrow(() -> command.execute(arguments)),
                () -> Assertions.assertEquals(company.getCatalog(), command.execute(arguments))
        );
    }

}
