package com.company.oop.furniture.commands;

import com.company.oop.furniture.exceptions.InvalidUserInputException;
import com.company.oop.furniture.core.contracts.FurnitureRepository;
import com.company.oop.furniture.models.CompanyImpl;
import com.company.oop.furniture.utils.ValidationHelper;

import java.util.List;

public class CreateCompanyCommand implements Command {

    private static final String COMPANY_EXISTS_ERROR_MESSAGE = "Company %s already exists.";
    private static final String COMPANY_CREATED_SUCCESS_MESSAGE = "Company %s created.";

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    private final FurnitureRepository furnitureRepository;

    public CreateCompanyCommand(FurnitureRepository furnitureRepository) {
        this.furnitureRepository = furnitureRepository;
    }

    @Override
    public String execute(List<String> arguments) {
        ValidationHelper.validateArgumentsCount(arguments, EXPECTED_NUMBER_OF_ARGUMENTS);
        String companyName = arguments.get(0);
        String companyRegistrationNumber = arguments.get(1);
        return createCompany(companyName, companyRegistrationNumber);
    }

    private String createCompany(String name, String registrationNumber) {
        if (furnitureRepository.companyExists(name)) {
            throw new InvalidUserInputException(String.format(COMPANY_EXISTS_ERROR_MESSAGE, name));
        }

        furnitureRepository.addCompany(new CompanyImpl(name, registrationNumber));

        return String.format(COMPANY_CREATED_SUCCESS_MESSAGE, name);
    }

}
