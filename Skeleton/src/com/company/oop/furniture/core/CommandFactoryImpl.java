package com.company.oop.furniture.core;

import com.company.oop.furniture.commands.*;
import com.company.oop.furniture.core.contracts.CommandFactory;
import com.company.oop.furniture.core.contracts.FurnitureRepository;
import com.company.oop.furniture.utils.ParsingHelpers;

public class CommandFactoryImpl implements CommandFactory {

    private static final String INVALID_COMMAND = "Invalid command name: %s!";

    @Override
    public Command createCommandFromCommandName(String commandName, FurnitureRepository furnitureRepository) {
        CommandType commandType = ParsingHelpers.tryParseEnum(commandName, CommandType.class, String.format(INVALID_COMMAND, commandName));

        switch (commandType) {
            case CREATECHAIR:
                return new CreateChairCommand(furnitureRepository);
            case CREATETABLE:
                return new CreateTableCommand(furnitureRepository);
            case CONVERTCHAIR:
                return new ConvertChairCommand(furnitureRepository);
            case CREATECOMPANY:
                return new CreateCompanyCommand(furnitureRepository);
            case SETCHAIRHEIGHT:
                return new SetChairHeightCommand(furnitureRepository);
            case SHOWCOMPANYCATALOG:
                return new ShowCompanyCatalogCommand(furnitureRepository);
            case ADDFURNITURETOCOMPANY:
                return new AddFurnitureToCompanyCommand(furnitureRepository);
            case FINDFURNITUREFROMCOMPANY:
                return new FindFurnitureFromCompanyCommand(furnitureRepository);
            case REMOVEFURNITUREFROMCOMPANY:
                return new RemoveFurnitureFromCompanyCommand(furnitureRepository);
        }
        throw new IllegalArgumentException(String.format(INVALID_COMMAND, commandName));
    }

}
