package com.company.oop.furniture;

import com.company.oop.furniture.core.contracts.FurnitureRepository;
import com.company.oop.furniture.models.*;
import com.company.oop.furniture.models.contracts.*;
import com.company.oop.furniture.models.enums.MaterialType;

import java.util.List;

import static com.company.oop.furniture.TestData.Company.VALID_NAME;
import static com.company.oop.furniture.TestData.Company.VALID_REGISTRATION;
import static java.util.Arrays.asList;

public class TestUtilities {

    public static Company initializeCompany(FurnitureRepository repository) {
        Company company = createCompany();
        repository.addCompany(company);
        return company;
    }

    public static Chair initializeChair(FurnitureRepository repository) {
        Chair chair = createChair();
        repository.addChair(chair);
        return chair;
    }

    public static AdjustableChair initializeAdjustableChair(FurnitureRepository repository) {
        AdjustableChair chair = createAdjustableChair();
        repository.addAdjustableChair(chair);
        return chair;
    }

    public static ConvertibleChair initializeConvertibleChair(FurnitureRepository repository) {
        ConvertibleChair chair = createConvertibleChair();
        repository.addConvertibleChair(chair);
        return chair;
    }

    public static Furniture initializeFurniture(FurnitureRepository repository) {
        return initializeChair(repository);
    }

    public static AdjustableChair createAdjustableChair() {
        return new AdjustableChairImpl(TestData.Chair.VALID_MODEL_CODE,
                MaterialType.LEATHER,
                Double.parseDouble(TestData.Chair.VALID_PRICE),
                Double.parseDouble(TestData.Chair.VALID_HEIGHT),
                Integer.parseInt(TestData.Chair.VALID_LEGS_COUNT));
    }

    public static Chair createChair() {
        return new ChairImpl(TestData.Chair.VALID_MODEL_CODE,
                MaterialType.LEATHER,
                Double.parseDouble(TestData.Chair.VALID_PRICE),
                Double.parseDouble(TestData.Chair.VALID_HEIGHT),
                Integer.parseInt(TestData.Chair.VALID_LEGS_COUNT));
    }

    public static ConvertibleChair createConvertibleChair() {
        return new ConvertibleChairImpl(TestData.Chair.VALID_MODEL_CODE,
                MaterialType.LEATHER,
                Double.parseDouble(TestData.Chair.VALID_PRICE),
                Double.parseDouble(TestData.Chair.VALID_HEIGHT),
                Integer.parseInt(TestData.Chair.VALID_LEGS_COUNT));
    }

    public static Company createCompany() {
        return new CompanyImpl(VALID_NAME, VALID_REGISTRATION);
    }

    public static Table createTable() {
        return new TableImpl(TestData.Table.VALID_MODEL_CODE,
                MaterialType.LEATHER,
                Double.parseDouble(TestData.Table.VALID_PRICE),
                Double.parseDouble(TestData.Table.VALID_HEIGHT),
                Double.parseDouble(TestData.Table.VALID_LENGTH),
                Double.parseDouble(TestData.Table.VALID_WIDTH));
    }

    /**
     * Returns a new String with size equal to wantedSize.
     * Useful when you do not care what the value of the String is,
     * for example when testing if a String is of a certain size.
     *
     * @param wantedSize the size of the String to be returned.
     * @return a new String with size equal to wantedSize
     */
    public static String initializeStringWithSize(int wantedSize) {
        return "x".repeat(wantedSize);
    }

    /**
     * Returns a new List with size equal to wantedSize.
     * Useful when you do not care what the contents of the List are,
     * for example when testing if a list of a command throws exception
     * when it's parameters list's size is less/more than expected.
     *
     * @param wantedSize the size of the List to be returned.
     * @return a new List with size equal to wantedSize
     */
    public static List<String> initializeListWithSize(int wantedSize) {
        return asList(new String[wantedSize]);
    }

}
