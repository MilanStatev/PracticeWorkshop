package com.company.oop.furniture.core;

import com.company.oop.furniture.core.contracts.FurnitureRepository;
import com.company.oop.furniture.exceptions.ElementNotFoundException;
import com.company.oop.furniture.models.contracts.*;

import java.util.ArrayList;
import java.util.List;

public class FurnitureRepositoryImpl implements FurnitureRepository {

    private final List<Company> companies;
    private final List<Table> tables;
    private final List<Chair> regularChairs;
    private final List<AdjustableChair> adjustableChairs;
    private final List<ConvertibleChair> convertibleChairs;

    public FurnitureRepositoryImpl() {
        this.regularChairs = new ArrayList<>();
        this.tables = new ArrayList<>();
        this.companies = new ArrayList<>();
        this.convertibleChairs = new ArrayList<>();
        this.adjustableChairs = new ArrayList<>();
    }

    @Override
    public List<Company> getCompanies() {
        return new ArrayList<>(companies);
    }

    @Override
    public List<Furniture> getFurniture() {
        List<Furniture> furniture = new ArrayList<>(regularChairs);
        furniture.addAll(tables);
        furniture.addAll(convertibleChairs);
        furniture.addAll(adjustableChairs);

        return furniture;
    }

    @Override
    public boolean companyExists(String companyName) {
        boolean exists = false;

        for (Company company : getCompanies()) {
            if (company.getName().equalsIgnoreCase(companyName)) {
                exists = true;
                break;
            }
        }

        return exists;
    }

    @Override
    public boolean furnitureExists(String modelCode) {
        boolean exists = false;

        for (Furniture furniture : getFurniture()) {
            if (furniture.getModelCode().equalsIgnoreCase(modelCode)) {
                exists = true;
                break;
            }
        }

        return exists;
    }

    @Override
    public Company findCompanyByName(String companyName) {
        for (Company company : getCompanies()) {
            if (company.getName().equalsIgnoreCase(companyName)) return company;
        }

        throw new ElementNotFoundException(String.format("Company with name %s doesn't exist.", companyName));
    }

    @Override
    public Furniture findFurnitureByModelCode(String modelCode) {
        return findElementByModelCode(modelCode, getFurniture());
    }

    @Override
    public ConvertibleChair findConvertibleChairByModelCode(String modelCode) {
        return findElementByModelCode(modelCode, convertibleChairs);
    }

    @Override
    public AdjustableChair findAdjustableChairByModelCode(String modelCode) {
        return findElementByModelCode(modelCode, adjustableChairs);
    }

    @Override
    public Chair findChairByModelCode(String modelCode) {
        return findElementByModelCode(modelCode, regularChairs);
    }

    @Override
    public void addConvertibleChair(ConvertibleChair chair) {
        this.convertibleChairs.add(chair);
    }

    @Override
    public void addAdjustableChair(AdjustableChair chair) {
        this.adjustableChairs.add(chair);
    }

    @Override
    public void addChair(Chair chair) {
        this.regularChairs.add(chair);
    }

    @Override
    public void addTable(Table table) {
        this.tables.add(table);
    }

    @Override
    public void addCompany(Company company) {
        this.companies.add(company);
    }

    private <T extends Furniture> T findElementByModelCode(String wantedModelCode, List<T> elements) {
        for (T element : elements) {
            if (element.getModelCode().equalsIgnoreCase(wantedModelCode)) {
                return element;
            }
        }

        throw new ElementNotFoundException(String.format("Furniture with model code %s does not exist.", wantedModelCode));
    }

}
