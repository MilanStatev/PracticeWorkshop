package com.company.oop.furniture.core.contracts;

import com.company.oop.furniture.models.contracts.*;

import java.util.List;

public interface FurnitureRepository {

    List<Company> getCompanies();

    List<Furniture> getFurniture();

    boolean companyExists(String companyName);

    boolean furnitureExists(String furnitureName);

    Company findCompanyByName(String companyName);

    Furniture findFurnitureByModelCode(String modelCode);

    ConvertibleChair findConvertibleChairByModelCode(String modelCode);

    AdjustableChair findAdjustableChairByModelCode(String modelCode);

    Chair findChairByModelCode(String modelCode);

    void addCompany(Company companyToAdd);

    void addConvertibleChair(ConvertibleChair chair);

    void addAdjustableChair(AdjustableChair chair);

    void addChair(Chair chair);

    void addTable(Table table);

}
