package com.company.oop.furniture.models.contracts;

import com.company.oop.furniture.models.enums.MaterialType;

public interface Furniture {

    String getModelCode();

    MaterialType getMaterialType();

    double getPrice();

    double getHeight();

}
