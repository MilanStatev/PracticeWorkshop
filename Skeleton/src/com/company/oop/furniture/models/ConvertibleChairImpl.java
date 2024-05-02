package com.company.oop.furniture.models;

import com.company.oop.furniture.models.contracts.ConvertibleChair;
import com.company.oop.furniture.models.enums.ChairType;
import com.company.oop.furniture.models.enums.MaterialType;

public class ConvertibleChairImpl extends ChairImpl implements ConvertibleChair {
    public static final double CONVERTED_HEIGHT = 0.1;
    private final double originalHeight;
    private boolean isConverted;

    public ConvertibleChairImpl(String modelCode, MaterialType materialType, double price, double height, int numberOfLegs) {
        super(modelCode, materialType, price, height, numberOfLegs);
        setChairType(ChairType.CONVERTIBLE);
        this.originalHeight = height;
    }

    @Override
    public boolean getIsConverted() {
        return isConverted;
    }

    @Override
    public void convert() {
        if (isConverted) {
            isConverted = false;
            setHeight(originalHeight);
        } else {
            isConverted = true;
            setHeight(CONVERTED_HEIGHT);
        }
    }

    @Override
    public String toString() {
        return String.format("%s, State: %s", super.toString(), isConverted ? "Converted" : "Normal");
    }
}
