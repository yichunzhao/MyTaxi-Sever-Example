/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytaxi.datatransferobject;

import com.mytaxi.domainvalue.EngineType;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author YNZ
 */
public class CarDTOTest {

    private CarDTO carDTO;
    private ManufacturerDTO manufacturerDTO;
    private Long expectedId = 1L;
    private int expectedRating = 10;
    private int expectedSeatCount = 7;
    private EngineType expectedEngineType = EngineType.GAS;
    private boolean expectedConvertible = true;
    private String expectedLicensePlate = "XXXUWOW";
    private String expectedManufacturerName = "TOYOTA";
    

    public CarDTOTest() {
        
        
        carDTO = CarDTO.NewBuilder()
                .withId(expectedId)
                .withRating(expectedRating)
                .withSeatCount(expectedSeatCount)
                .withEngineType(expectedEngineType)
                .withConvertible(expectedConvertible)
                .withLicensePlate(expectedLicensePlate)
                .build();
        
        manufacturerDTO = ManufacturerDTO.NewBuilder()
                .withName(expectedManufacturerName)
                .build();
    }

    @Test
    public void testNewBuilder() {
        assertNotNull(CarDTO.NewBuilder());
    }

    @Test
    public void testGetId() {
        assertEquals(this.expectedId, carDTO.getId());
    }

    @Test
    public void testGetLicensePlate() {
        assertEquals(this.expectedLicensePlate, carDTO.getLicensePlate());
    }

    @Test
    public void testGetSeatCount() {
        assertEquals(this.expectedSeatCount, carDTO.getSeatCount());
    }

    @Test
    public void testIsConvertible() {
        assertEquals(this.expectedConvertible, carDTO.isConvertible());
    }

    @Test
    public void testGetRating() {
        assertEquals(this.expectedRating, carDTO.getRating());
    }

    @Test
    public void testGetEngineType() {
        assertEquals(this.expectedEngineType, carDTO.getEngineType());
    }

    @Test
    public void testGetManufacturer() {
        assertEquals(this.expectedManufacturerName, carDTO.getManufacturer().getName());
    }

    @Test
    public void testGetDateCreation() {
        assertNotNull(carDTO.getDateCreation());
    }

}
