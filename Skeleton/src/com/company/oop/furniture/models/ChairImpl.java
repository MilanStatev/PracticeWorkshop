package com.company.oop.furniture.models;

import com.company.oop.furniture.models.contracts.Chair;
import com.company.oop.furniture.models.enums.ChairType;
import com.company.oop.furniture.models.enums.MaterialType;
import com.company.oop.furniture.utils.ValidationHelper;

public class ChairImpl extends FurnitureBase implements Chair {
    private int numberOfLegs;
    private ChairType chairType;

    public ChairImpl(String modelCode, MaterialType materialType, double price, double height, int numberOfLegs) {
        super(modelCode, materialType, price, height);
        setNumberOfLegs(numberOfLegs);
        setChairType(ChairType.REGULAR);
    }

    @Override
    public int getNumberOfLegs() {
        return numberOfLegs;
    }

    @Override
    public String toString() {
        return String.format("Type:%s Chair, %s, Legs: %d",
                getChairType().equals("Regular") ? "" : " " + getChairType(),
                super.toString(),
                getNumberOfLegs());
    }

    protected void setChairType(ChairType chairType) {
        this.chairType = chairType;
    }

    private String getChairType(){
        return chairType.toString();
    }


    private void setNumberOfLegs(int numberOfLegs) {
        ValidationHelper.validateGreaterThanZero(numberOfLegs);
        this.numberOfLegs = numberOfLegs;
    }
}
