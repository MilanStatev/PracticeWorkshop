package com.company.oop.furniture.models.enums;

public enum MaterialType {
    WOODEN,
    LEATHER,
    PLASTIC;

    @Override
    public String toString() {
        return this.name().substring(0, 1).toUpperCase() + this.name().substring(1).toLowerCase();
    }
}
