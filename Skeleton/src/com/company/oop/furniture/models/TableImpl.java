package com.company.oop.furniture.models;

import com.company.oop.furniture.models.contracts.Table;
import com.company.oop.furniture.models.enums.MaterialType;
import com.company.oop.furniture.utils.ValidationHelper;

public class TableImpl extends FurnitureBase implements Table {

    private double width;
    private double length;

    public TableImpl(String modelCode, MaterialType materialType, double price, double height, double length, double width) {
        super(modelCode, materialType, price, height);
        setWidth(width);
        setLength(length);
    }

    @Override
    public double getLength() {
        return length;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getArea() {
        return width * length;
    }

    private void setWidth(double width) {
        ValidationHelper.validateGreaterThanZero(width);
        this.width = width;
    }

    private void setLength(double length) {
        ValidationHelper.validateGreaterThanZero(length);
        this.length = length;
    }

    @Override
    public String toString() {
        return String.format("Type: Table, %s, Length: %.2f, Width: %.2f, Area: %.2f",
                                    super.toString(), length, width, getArea());
    }
}
