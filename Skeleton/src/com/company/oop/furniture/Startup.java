package com.company.oop.furniture;

import com.company.oop.furniture.core.FurnitureEngineImpl;

public class Startup {

    public static void main(String[] args) {
        FurnitureEngineImpl engine = new FurnitureEngineImpl();
        engine.start();
    }

}
