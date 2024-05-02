package com.company.oop.furniture.commands;

import com.company.oop.furniture.models.contracts.ConvertibleChair;
import com.company.oop.furniture.core.contracts.FurnitureRepository;
import com.company.oop.furniture.utils.ValidationHelper;

import java.util.List;

public class ConvertChairCommand implements Command {
    private static final String CHAIR_STATE_CONVERTED_SUCCESS_MESSAGE = "Chair %s's state switched from %s to %s.";

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private final FurnitureRepository furnitureRepository;

    public ConvertChairCommand(FurnitureRepository furnitureRepository) {
        this.furnitureRepository = furnitureRepository;
    }

    @Override
    public String execute(List<String> arguments) {
        ValidationHelper.validateArgumentsCount(arguments, EXPECTED_NUMBER_OF_ARGUMENTS);
        String chairModelCode = arguments.get(0);
        return convertChair(chairModelCode);
    }

    private String convertChair(String model) {
        ConvertibleChair convertibleChair = furnitureRepository.findConvertibleChairByModelCode(model);
        String oldState = convertibleChair.getIsConverted() ? "converted" : "normal";
        convertibleChair.convert();
        String newState = convertibleChair.getIsConverted() ? "converted" : "normal";
        return String.format(CHAIR_STATE_CONVERTED_SUCCESS_MESSAGE, model, oldState, newState);
    }

}
