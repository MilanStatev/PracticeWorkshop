package com.company.oop.furniture.core;

import com.company.oop.furniture.TestUtilities;
import com.company.oop.furniture.core.contracts.FurnitureRepository;
import com.company.oop.furniture.exceptions.ElementNotFoundException;
import com.company.oop.furniture.models.contracts.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FurnitureRepositoryImpl_Tests {

    private FurnitureRepository furnitureRepository;

    @BeforeEach
    void setUp() {
        furnitureRepository = new FurnitureRepositoryImpl();
    }

    @Test
    void companyExists_should_returnTrue_when_exists() {
        Company company = TestUtilities.initializeCompany(furnitureRepository);

        Assertions.assertTrue(furnitureRepository.companyExists(company.getName()));
    }

    @Test
    void companyExists_should_returnFalse_when_doesntExist() {
        Assertions.assertFalse(furnitureRepository.companyExists("company-name"));
    }

    @Test
    void furnitureExists_should_returnTrue_when_exists() {
        Furniture furniture = TestUtilities.initializeFurniture(furnitureRepository);

        Assertions.assertTrue(furnitureRepository.furnitureExists(furniture.getModelCode()));
    }

    @Test
    void furnitureExists_should_returnFalse_when_doesntExist() {
        Assertions.assertFalse(furnitureRepository.furnitureExists("furniture-model-code"));
    }

    @Test
    void findCompanyByName_should_returnCompany_when_exists() {
        Company company = TestUtilities.initializeCompany(furnitureRepository);

        assertAll(
                () -> assertDoesNotThrow(() -> furnitureRepository.findCompanyByName(company.getName())),
                () -> assertEquals(company, furnitureRepository.findCompanyByName(company.getName()))
        );
    }

    @Test
    void findCompanyByName_should_throwException_when_doesntExist() {
        assertThrows(ElementNotFoundException.class,
                () -> furnitureRepository.findCompanyByName("company-name"));
    }

    @Test
    void findFurnitureByModelCode_should_returnFurniture_when_exists() {
        Furniture furniture = TestUtilities.initializeFurniture(furnitureRepository);

        assertEquals(furniture, furnitureRepository.findFurnitureByModelCode(furniture.getModelCode()));
    }

    @Test
    void findFurnitureByModelCode_should_throwException_when_doesntExist() {
        assertThrows(ElementNotFoundException.class,
                () -> furnitureRepository.findFurnitureByModelCode("furniture-model-code"));
    }

    @Test
    void findConvertibleChairByModelCode_should_returnConvertibleChair_when_exists() {
        ConvertibleChair chair = TestUtilities.initializeConvertibleChair(furnitureRepository);

        assertEquals(chair, furnitureRepository.findConvertibleChairByModelCode(chair.getModelCode()));
    }

    @Test
    void findConvertibleChairByModelCode_should_throwException_when_doesntExist() {
        assertThrows(ElementNotFoundException.class,
                () -> furnitureRepository.findConvertibleChairByModelCode("chair-model-code"));
    }

    @Test
    void findAdjustableChairByModelCode_should_returnAdjustableChair_when_exists() {
        AdjustableChair chair = TestUtilities.initializeAdjustableChair(furnitureRepository);

        assertEquals(chair, furnitureRepository.findAdjustableChairByModelCode(chair.getModelCode()));
    }

    @Test
    void findAdjustableChairByModelCode_should_throwException_when_doesntExist() {
        assertThrows(ElementNotFoundException.class,
                () -> furnitureRepository.findAdjustableChairByModelCode("chair-model-code"));
    }

    @Test
    void findChairByModelCode_should_returnChair_when_exists() {
        Chair chair = TestUtilities.initializeChair(furnitureRepository);

        assertEquals(chair, furnitureRepository.findChairByModelCode(chair.getModelCode()));
    }

    @Test
    void findChairByModelCode_should_throwException_when_doesntExist() {
        assertThrows(ElementNotFoundException.class,
                () -> furnitureRepository.findChairByModelCode("chair-model-code"));
    }

    @Test
    void addChair_should_addChair() {
        Chair chair = TestUtilities.createChair();
        furnitureRepository.addChair(chair);

        assertTrue(furnitureRepository.furnitureExists(chair.getModelCode()));
    }

    @Test
    void addConvertibleChair_should_addConvertibleChair() {
        ConvertibleChair chair = TestUtilities.createConvertibleChair();
        furnitureRepository.addConvertibleChair(chair);

        assertTrue(furnitureRepository.furnitureExists(chair.getModelCode()));
    }

    @Test
    void addAdjustableChair_should_addAdjustableChair() {
        AdjustableChair chair = TestUtilities.createAdjustableChair();
        furnitureRepository.addAdjustableChair(chair);

        assertTrue(furnitureRepository.furnitureExists(chair.getModelCode()));
    }

    @Test
    void addTableChair_should_addTable() {
        Table table = TestUtilities.createTable();
        furnitureRepository.addTable(table);

        assertTrue(furnitureRepository.furnitureExists(table.getModelCode()));
    }

    @Test
    void addCompany_should_addCompany() {
        Company company = TestUtilities.createCompany();
        furnitureRepository.addCompany(company);

        assertTrue(furnitureRepository.companyExists(company.getName()));
    }
}
