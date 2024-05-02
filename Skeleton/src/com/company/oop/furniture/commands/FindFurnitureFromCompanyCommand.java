package com.company.oop.furniture.commands;

import com.company.oop.furniture.core.contracts.FurnitureRepository;
import com.company.oop.furniture.models.contracts.Company;
import com.company.oop.furniture.models.contracts.Furniture;
import com.company.oop.furniture.utils.ValidationHelper;

import java.util.List;

public class FindFurnitureFromCompanyCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    private final FurnitureRepository furnitureRepository;

    public FindFurnitureFromCompanyCommand(FurnitureRepository furnitureRepository) {
        this.furnitureRepository = furnitureRepository;
    }

    @Override
    public String execute(List<String> arguments) {
        ValidationHelper.validateArgumentsCount(arguments, EXPECTED_NUMBER_OF_ARGUMENTS);
        String companyToFindFrom = arguments.get(0);
        String furnitureToBeFound = arguments.get(1);
        return findFurnitureFromCompany(companyToFindFrom, furnitureToBeFound);
    }

    private String findFurnitureFromCompany(String companyName, String furnitureName) {
        Company company = furnitureRepository.findCompanyByName(companyName);
        Furniture furniture = company.findFurnitureByModelCode(furnitureName);
        return furniture.toString();
    }

}
