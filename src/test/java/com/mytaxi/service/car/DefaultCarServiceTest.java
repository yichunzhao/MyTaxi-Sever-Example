/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytaxi.service.car;

import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.datatransferobject.ManufacturerDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainvalue.EngineType;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;
import javax.transaction.Transactional;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author YNZ
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class DefaultCarServiceTest {

    @Autowired
    private CarService carService;

    private CarDO carDO;

    public DefaultCarServiceTest() {

        carDO = new CarDO(CarDTO.NewBuilder()
                .withConvertible(true)
                .withEngineType(EngineType.ELECTRIC)
                .withLicensePlate("XXXLLOO")
                .withRating(5)
                .withSeatCount(7)
                .withManufacturer(ManufacturerDTO.NewBuilder().withName("TOYOTA").build())
                .build());
    }

    @Test(expected = EntityNotFoundException.class)
    public void testFindFailure() throws Exception {
        carService.find(10L);
    }

    @Test(expected = ConstraintsViolationException.class)
    public void testCreateFailure() throws Exception {

        CarDO badCarDO = new CarDO(CarDTO.NewBuilder()
                .withConvertible(true)
                .withEngineType(EngineType.ELECTRIC)
                .withLicensePlate("YYYXXXLLOO")
                .withRating(5)
                .withSeatCount(7)
                .withManufacturer(ManufacturerDTO.NewBuilder().withName("TOYOTA").build())
                .build());

        carService.create(badCarDO);
    }

    @Test
    public void testCreateCar() throws Exception {
        CarDO created = carService.create(carDO);
        assertNotNull(created);
        assertNotNull(created.getManufacturer());
        assertNotNull(created.getDateCreation());
        assertEquals(created.getManufacturer().getName(), "TOYOTA");
    }

    @Test(expected = EntityNotFoundException.class)
    public void testDeleteSuccess() throws Exception {
        CarDO created = carService.create(carDO);
        carService.delete(created.getId());
        carService.find(created.getId());
    }

    @Test(expected = EntityNotFoundException.class)
    public void testDeleteFailure() throws Exception {
        carService.delete(10L);
    }

    @Test(expected = EntityNotFoundException.class)
    public void testUpdateFailure() throws Exception {
        carService.update(10L, carDO);
    }

    @Test
    public void testUpdateSucess() throws Exception {
        CarDO created = carService.create(carDO);
        CarDO carDO_ = new CarDO(CarDTO.NewBuilder()
                .withConvertible(true)
                .withEngineType(EngineType.ELECTRIC)
                .withLicensePlate("YYYLLOO")
                .withRating(5)
                .withSeatCount(7)
                .withManufacturer(ManufacturerDTO.NewBuilder().withName("TOYOTA").build())
                .build());
        CarDO updated = carService.update(created.getId(), carDO_);
        assertEquals(updated.getLicensePlate(), "YYYLLOO");
    }

}
