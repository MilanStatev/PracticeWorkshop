package com.company.oop.furniture.commands;

import com.company.oop.furniture.models.contracts.Company;
import com.company.oop.furniture.models.contracts.Furniture;
import com.company.oop.furniture.core.contracts.FurnitureRepository;
import com.company.oop.furniture.utils.ValidationHelper;

import java.util.List;

public class RemoveFurnitureFromCompanyCommand implements Command {

    private static final String FURNITURE_REMOVED_SUCCESS_MESSAGE = "%s removed from %s.";

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    private final FurnitureRepository furnitureRepository;

    public RemoveFurnitureFromCompanyCommand(FurnitureRepository furnitureRepository) {
        this.furnitureRepository = furnitureRepository;
    }

    @Override
    public String execute(List<String> arguments) {
        ValidationHelper.validateArgumentsCount(arguments, EXPECTED_NUMBER_OF_ARGUMENTS);
        String companyToRemoveFrom = arguments.get(0);
        String furnitureToBeRemoved = arguments.get(1);
        return removeFurnitureFromCompany(companyToRemoveFrom, furnitureToBeRemoved);
    }

    private String removeFurnitureFromCompany(String companyName, String furnitureName) {
        Company company = furnitureRepository.findCompanyByName(companyName);
        Furniture furniture = furnitureRepository.findFurnitureByModelCode(furnitureName);
        company.removeFurniture(furniture);

        return String.format(FURNITURE_REMOVED_SUCCESS_MESSAGE, furnitureName, companyName);
    }

}
