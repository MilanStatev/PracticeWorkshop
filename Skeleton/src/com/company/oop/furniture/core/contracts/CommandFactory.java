package com.company.oop.furniture.core.contracts;

import com.company.oop.furniture.commands.Command;

public interface CommandFactory {

    Command createCommandFromCommandName(String commandTypeAsString, FurnitureRepository furnitureRepository);

}
