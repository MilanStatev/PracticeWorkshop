package com.company.oop.furniture.models;

import com.company.oop.furniture.models.contracts.Furniture;
import com.company.oop.furniture.models.enums.MaterialType;
import com.company.oop.furniture.utils.ValidationHelper;

import java.util.Objects;

public class FurnitureBase implements Furniture {

    public static final int MODEL_CODE_MIN_LENGTH = 3;
    private String modelCode;
    private final MaterialType materialType;
    private double price;
    private double height;

    protected FurnitureBase(String modelCode, MaterialType materialType, double price, double height) {
        setModelCode(modelCode);
        this.materialType = materialType;
        setPrice(price);
        setHeight(height);
    }

    @Override
    public String getModelCode() {
        return modelCode;
    }

    @Override
    public MaterialType getMaterialType() {
        return materialType;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return String.format("Model Code: %s, Material: %s, Price: %.2f, Height: %.2f",
                getModelCode(), getMaterialType(), getPrice(), getHeight());
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FurnitureBase that = (FurnitureBase) o;
        return Objects.equals(modelCode, that.modelCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(modelCode);
    }

    protected void setHeight(double height) {
        ValidationHelper.validateGreaterThanZero(height);
        this.height = height;
    }

    private void setModelCode(String modelCode) {
        ValidationHelper.validateStringMinLength(modelCode, MODEL_CODE_MIN_LENGTH);
        this.modelCode = modelCode;
    }

    private void setPrice(double price) {
        ValidationHelper.validateGreaterThanZero(price);
        this.price = price;
    }
}
