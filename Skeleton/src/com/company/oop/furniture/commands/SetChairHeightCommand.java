package com.company.oop.furniture.commands;

import com.company.oop.furniture.core.contracts.FurnitureRepository;
import com.company.oop.furniture.models.contracts.AdjustableChair;
import com.company.oop.furniture.utils.ParsingHelpers;
import com.company.oop.furniture.utils.ValidationHelper;

import java.util.List;

public class SetChairHeightCommand implements Command {

    private static final String CHAIR_HEIGHT_ADJUSTED_SUCCESS_MESSAGE = "Chair %s adjusted to height %.2f";

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    private final FurnitureRepository furnitureRepository;

    public SetChairHeightCommand(FurnitureRepository furnitureRepository) {
        this.furnitureRepository = furnitureRepository;
    }

    @Override
    public String execute(List<String> arguments) {
        ValidationHelper.validateArgumentsCount(arguments, EXPECTED_NUMBER_OF_ARGUMENTS);
        String modelCode = arguments.get(0);
        double height = ParsingHelpers.tryParseDouble(arguments.get(1), "height");
        return adjustChairHeight(modelCode, height);
    }

    private String adjustChairHeight(String model, double height) {
        AdjustableChair adjChair = furnitureRepository.findAdjustableChairByModelCode(model);
        adjChair.setHeight(height);

        return String.format(CHAIR_HEIGHT_ADJUSTED_SUCCESS_MESSAGE, model, height);
    }

}
