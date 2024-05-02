package com.company.oop.furniture.commands;

import com.company.oop.furniture.core.contracts.FurnitureRepository;
import com.company.oop.furniture.models.contracts.Company;
import com.company.oop.furniture.models.contracts.Furniture;
import com.company.oop.furniture.utils.ValidationHelper;

import java.util.List;

public class AddFurnitureToCompanyCommand implements Command {
    private static final String FURNITURE_ADDED_SUCCESS_MESSAGE = "%s added to %s.";

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    private final FurnitureRepository furnitureRepository;

    public AddFurnitureToCompanyCommand(FurnitureRepository furnitureRepository) {
        this.furnitureRepository = furnitureRepository;
    }

    @Override
    public String execute(List<String> arguments) {
        ValidationHelper.validateArgumentsCount(arguments, EXPECTED_NUMBER_OF_ARGUMENTS);

        String companyName = arguments.get(0);
        String furnitureModelCode = arguments.get(1);

        Company company = furnitureRepository.findCompanyByName(companyName);
        Furniture furniture = furnitureRepository.findFurnitureByModelCode(furnitureModelCode);
        company.addFurniture(furniture);

        return String.format(FURNITURE_ADDED_SUCCESS_MESSAGE, furnitureModelCode, companyName);
    }

}
