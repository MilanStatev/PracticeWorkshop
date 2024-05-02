package com.company.oop.furniture.models;

import com.company.oop.furniture.models.contracts.AdjustableChair;
import com.company.oop.furniture.models.enums.ChairType;
import com.company.oop.furniture.models.enums.MaterialType;

public class AdjustableChairImpl extends ChairImpl implements AdjustableChair {

    public AdjustableChairImpl(String modelCode, MaterialType materialType, double price, double height, int numberOfLegs) {
        super(modelCode, materialType, price, height, numberOfLegs);
        super.setChairType(ChairType.ADJUSTABLE);
    }

    @Override
    public void setHeight(double height) {
        super.setHeight(height);
    }

}
