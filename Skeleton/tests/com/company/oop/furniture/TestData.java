package com.company.oop.furniture;

import com.company.oop.furniture.models.CompanyImpl;
import com.company.oop.furniture.models.FurnitureBase;
import com.company.oop.furniture.models.enums.MaterialType;

public class TestData {

    public static class Company {

        public static String VALID_NAME = TestUtilities.initializeStringWithSize(CompanyImpl.NAME_MIN_LENGTH + 1);
        public static String VALID_REGISTRATION = "1".repeat(CompanyImpl.REGISTRATION_LENGTH);

    }

    public static class Chair {

        public static String VALID_HEIGHT = "3.2";
        public static String VALID_PRICE = "1.2";
        public static String VALID_LEGS_COUNT = "4";
        public static String VALID_MATERIAL = MaterialType.WOODEN.toString();
        public static String VALID_MODEL_CODE = TestUtilities.initializeStringWithSize(FurnitureBase.MODEL_CODE_MIN_LENGTH + 1);

    }

    public static class Table {

        public static String VALID_HEIGHT = "3.1";
        public static String VALID_LENGTH = "3.2";
        public static String VALID_WIDTH = "3.1";
        public static String VALID_PRICE = "1.2";
        public static String VALID_MATERIAL = MaterialType.WOODEN.toString();
        public static String VALID_MODEL_CODE = TestUtilities.initializeStringWithSize(FurnitureBase.MODEL_CODE_MIN_LENGTH + 1);

    }

}
