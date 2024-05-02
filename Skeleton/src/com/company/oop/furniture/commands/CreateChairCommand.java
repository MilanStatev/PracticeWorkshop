package com.company.oop.furniture.commands;

import com.company.oop.furniture.exceptions.InvalidUserInputException;
import com.company.oop.furniture.core.contracts.FurnitureRepository;
import com.company.oop.furniture.models.AdjustableChairImpl;
import com.company.oop.furniture.models.ChairImpl;
import com.company.oop.furniture.models.ConvertibleChairImpl;
import com.company.oop.furniture.models.enums.ChairType;
import com.company.oop.furniture.models.enums.MaterialType;
import com.company.oop.furniture.utils.ParsingHelpers;
import com.company.oop.furniture.utils.ValidationHelper;

import java.util.List;

import static java.lang.String.format;

public class CreateChairCommand implements Command {

    private static final String FURNITURE_EXISTS_ERROR_MESSAGE = "Furniture with model code %s already exists.";
    private static final String CHAIR_CREATED_SUCCESS_MESSAGE = "Chair with model code %s created.";

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 6;

    private final FurnitureRepository furnitureRepository;

    public CreateChairCommand(FurnitureRepository furnitureRepository) {
        this.furnitureRepository = furnitureRepository;
    }

    @Override
    public String execute(List<String> arguments) {
        ValidationHelper.validateArgumentsCount(arguments, EXPECTED_NUMBER_OF_ARGUMENTS);

        String modelCode = arguments.get(0);
        MaterialType material = ParsingHelpers.tryParseEnum(arguments.get(1), MaterialType.class, "");
        double price = ParsingHelpers.tryParseDouble(arguments.get(2), "price");
        double height = ParsingHelpers.tryParseDouble(arguments.get(3), "height");
        int legs = ParsingHelpers.tryParseInteger(arguments.get(4), "number of legs");
        ChairType chairType = ParsingHelpers.tryParseEnum(arguments.get(5), ChairType.class, "");
        return createChair(modelCode, material, price, height, legs, chairType);
    }

    private String createChair(String model, MaterialType material, double price, double height, int legs, ChairType chairType) {
        if (furnitureRepository.furnitureExists(model)) {
            throw new InvalidUserInputException(format(FURNITURE_EXISTS_ERROR_MESSAGE, model));
        }

        if (chairType.equals(ChairType.REGULAR)) {
            furnitureRepository.addChair(new ChairImpl(model, material, price, height, legs));
        } else if (chairType.equals(ChairType.CONVERTIBLE)) {
            furnitureRepository.addConvertibleChair(new ConvertibleChairImpl(model, material, price, height, legs));
        } else {
            furnitureRepository.addAdjustableChair(new AdjustableChairImpl(model, material, price, height, legs));
        }

        return format(CHAIR_CREATED_SUCCESS_MESSAGE, model);
    }

}
