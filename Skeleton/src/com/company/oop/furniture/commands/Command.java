package com.company.oop.furniture.commands;

import java.util.List;

public interface Command {

    String execute(List<String> arguments);

}
