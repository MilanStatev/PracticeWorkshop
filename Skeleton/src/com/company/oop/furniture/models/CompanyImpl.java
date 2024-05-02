package com.company.oop.furniture.models;

import com.company.oop.furniture.exceptions.ElementAlreadyExistException;
import com.company.oop.furniture.exceptions.ElementNotFoundException;
import com.company.oop.furniture.models.contracts.Company;
import com.company.oop.furniture.models.contracts.Furniture;
import com.company.oop.furniture.utils.ValidationHelper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CompanyImpl implements Company {
    public static final int NAME_MIN_LENGTH = 5;
    public static final int REGISTRATION_LENGTH = 10;
    public static final String REGISTRATION_NUMBER_LENGTH_ERROR_MESSAGE =
            String.format("Invalid registration number. Must be %s symbols long", REGISTRATION_LENGTH);
    public static final String REGISTRATION_NUMBER_DIGITS_ERROR_MESSAGE = "Registration number must contain only digits";
    public static final String FURNITURE_ALREADY_EXIST = "Furniture with model code %s does not exist.";
    public static final String FURNITURE_REMOVED_MESSAGE = "%s removed from %s%n";
    public static final String FURNITURE_NOT_FOUND_MESSAGE = "Furniture with model code %s does not exist.";
    // Finish the class

    private String name;
    private String registrationNumber;
    private List<Furniture> furnitures;

    public CompanyImpl(String name, String registrationNumber) {
        setName(name);
        setRegistrationNumber(registrationNumber);
        furnitures = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    @Override
    public List<Furniture> getFurniture() {
        return new ArrayList<>(furnitures);
    }

    @Override
    public void addFurniture(Furniture furniture) {
        if (furnitures.contains(furniture)) {
            throw new ElementAlreadyExistException(String.format(FURNITURE_ALREADY_EXIST, furniture.getModelCode()));
        }
        furnitures.add(furniture);
    }

    @Override
    public void removeFurniture(Furniture furniture) {
        furnitures.remove(furniture);
    }

    @Override
    public Furniture findFurnitureByModelCode(String modelCode) {
        for (Furniture furniture : furnitures) {
            if (furniture.getModelCode().equals(modelCode)) {
                return furniture;
            }
        }
        throw new ElementNotFoundException(String.format(FURNITURE_NOT_FOUND_MESSAGE, modelCode));
    }

    @Override
    public String getCatalog() {
        StringBuilder sb = new StringBuilder();

        sb.append("Catalog of ")
                .append(name)
                .append(String.format(" (%s):", registrationNumber));

        if (furnitures.size() == 0) {
            sb.append(" No furniture.");
        } else {
            furnitures.stream()
                    .sorted(Comparator.comparing(Furniture::getPrice).thenComparing(Furniture::getModelCode))
                    .forEach(furniture -> {
                        sb.append(System.lineSeparator());
                        sb.append("  - ").append(furniture.toString());
                    });
        }
        return sb.toString();
    }

    private void setName(String name) {
        ValidationHelper.validateStringMinLength(name, NAME_MIN_LENGTH);
        this.name = name;
    }

    private void setRegistrationNumber(String registrationNumber) {
        ValidationHelper.validateStringExactLength(registrationNumber,
                REGISTRATION_LENGTH,
                REGISTRATION_NUMBER_LENGTH_ERROR_MESSAGE
        );

        ValidationHelper.validateOnlyDigits(registrationNumber, REGISTRATION_NUMBER_DIGITS_ERROR_MESSAGE);
        this.registrationNumber = registrationNumber;
    }
}
