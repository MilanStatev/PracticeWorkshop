package com.company.oop.furniture.commands;

import com.company.oop.furniture.core.contracts.FurnitureRepository;
import com.company.oop.furniture.utils.ValidationHelper;

import java.util.List;

public class ShowCompanyCatalogCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private final FurnitureRepository furnitureRepository;

    public ShowCompanyCatalogCommand(FurnitureRepository furnitureRepository) {
        this.furnitureRepository = furnitureRepository;
    }

    @Override
    public String execute(List<String> arguments) {
        ValidationHelper.validateArgumentsCount(arguments, EXPECTED_NUMBER_OF_ARGUMENTS);
        String companyToShowCatalog = arguments.get(0);
        return showCompanyCatalog(companyToShowCatalog);
    }

    private String showCompanyCatalog(String companyName) {
        return furnitureRepository.findCompanyByName(companyName).getCatalog();
    }

}
