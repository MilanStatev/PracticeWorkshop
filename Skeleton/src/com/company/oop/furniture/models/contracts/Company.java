package com.company.oop.furniture.models.contracts;

import java.util.List;

public interface Company {

    String getName();

    String getRegistrationNumber();

    List<Furniture> getFurniture();

    void addFurniture(Furniture furniture);

    void removeFurniture(Furniture furniture);

    Furniture findFurnitureByModelCode(String modelCode);

    String getCatalog();

}
