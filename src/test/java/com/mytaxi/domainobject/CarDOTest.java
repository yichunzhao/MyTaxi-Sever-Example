/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytaxi.domainobject;

import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.datatransferobject.ManufacturerDTO;
import com.mytaxi.domainvalue.EngineType;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author YNZ
 */
public class CarDOTest {
    
    private CarDO carDO;
    private DriverDO driverDO;
    
    public CarDOTest() {
        carDO = new CarDO(CarDTO.NewBuilder()
                .withConvertible(true)
                .withEngineType(EngineType.ELECTRIC)
                .withLicensePlate("XXXLLOO")
                .withRating(5)
                .withSeatCount(7)
                .withManufacturer(ManufacturerDTO.NewBuilder().withName("TOYOTA").build())
                .build());
        
        driverDO = new DriverDO("driver007", "qwerty1234", carDO);
        carDO.setDriver(driverDO);
    }
    
    @Test
    public void testIsOccupied() {
        assertNotNull(driverDO.getCar());
        assertNotNull(carDO.getDriver());
        assertTrue(carDO.isOccupied());
    }
    
}
