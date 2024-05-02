package com.company.oop.furniture.commands;

import com.company.oop.furniture.core.contracts.FurnitureRepository;
import com.company.oop.furniture.exceptions.ElementAlreadyExistException;
import com.company.oop.furniture.exceptions.InvalidUserInputException;
import com.company.oop.furniture.models.TableImpl;
import com.company.oop.furniture.models.enums.ChairType;
import com.company.oop.furniture.models.enums.MaterialType;
import com.company.oop.furniture.utils.ParsingHelpers;
import com.company.oop.furniture.utils.ValidationHelper;

import java.util.List;

public class CreateTableCommand implements Command {
    private static final String FURNITURE_EXISTS_ERROR_MESSAGE = "Furniture with model code %s already exists.";
    private static final String TABLE_CREATED_SUCCESS_MESSAGE = "Table with model code %s created.";

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 6;

    private final FurnitureRepository furnitureRepository;

    public CreateTableCommand(FurnitureRepository furnitureRepository) {
        this.furnitureRepository = furnitureRepository;
    }

    @Override
    public String execute(List<String> arguments) {
        ValidationHelper.validateArgumentsCount(arguments, EXPECTED_NUMBER_OF_ARGUMENTS);

        String modelCode = arguments.get(0);
        MaterialType material = ParsingHelpers.tryParseEnum(arguments.get(1), MaterialType.class, "");
        double price = ParsingHelpers.tryParseDouble(arguments.get(2), "price");
        double height = ParsingHelpers.tryParseDouble(arguments.get(3), "height");
        double length = ParsingHelpers.tryParseDouble(arguments.get(4), "length");
        double width = ParsingHelpers.tryParseDouble(arguments.get(5), "width");

        return createTable(modelCode, material, price, height, length, width);
    }

    private String createTable(String modelCode, MaterialType material, double price, double height, double length, double width) {
        if (furnitureRepository.furnitureExists(modelCode)) {
            throw new InvalidUserInputException(String.format(FURNITURE_EXISTS_ERROR_MESSAGE, modelCode));
        }
        furnitureRepository.addTable(new TableImpl(modelCode, material, price, height, length, width));
        return String.format(TABLE_CREATED_SUCCESS_MESSAGE, modelCode);
    }


    // TODO
}
