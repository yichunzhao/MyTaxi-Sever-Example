/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytaxi.controller.driverfilter;

import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.domainvalue.EngineType;
import com.mytaxi.domainvalue.SeatCount;
import java.util.List;
import static java.util.stream.Collectors.toList;
import java.util.stream.Stream;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author YNZ
 */
public class DriversFilterTest {

    private List<DriverDTO> drivers;

    private CarDTO car1 = CarDTO.NewBuilder()
            .withId(1L)
            .withEngineType(EngineType.ELECTRIC)
            .withSeatCount(SeatCount.FIVE.count())
            .build();

    private CarDTO car2 = CarDTO.NewBuilder()
            .withId(2L)
            .withEngineType(EngineType.GAS)
            .withSeatCount(SeatCount.FIVE.count())
            .build();

    private CarDTO car3 = CarDTO.NewBuilder()
            .withId(3L)
            .withEngineType(EngineType.GAS)
            .withSeatCount(SeatCount.FIVE.count())
            .build();

    private DriverDTO driver1 = DriverDTO.newBuilder()
            .setId(1L)
            .setPassword("driverpw1")
            .setUsername("driver1")
            .setCar(car1)
            .createDriverDTO();

    private DriverDTO driver2 = DriverDTO.newBuilder()
            .setId(2L)
            .setPassword("driverpw2")
            .setUsername("driver2")
            .setCar(car2)
            .createDriverDTO();

    private DriverDTO driver3 = DriverDTO.newBuilder()
            .setId(3L)
            .setPassword("driverpw3")
            .setUsername("driver3")
            .setCar(car3)
            .createDriverDTO();

    private DriverDTO driver4 = DriverDTO.newBuilder()
            .setId(4L)
            .setPassword("driverpw4")
            .setUsername("driver4")
            .createDriverDTO();

    public DriversFilterTest() {
        drivers = Stream.of(driver1, driver2, driver3, driver4).collect(toList());
    }

    @Test
    public void testDriversWithGasCar() {

        DriversHavingCar driversHavingCar = new DriversHavingCar();
        List<DriverDTO> driversWithCar = driversHavingCar.meetCriteria(drivers);

        GasCar gasCar = new GasCar();

        List<DriverDTO> driversGasCar = gasCar.meetCriteria(driversWithCar);
        assertEquals(2, driversGasCar.size());

    }

    @Test
    public void testDriversWithGasCarUsingAndCriteria() {
        assertEquals(4, drivers.size());
        AndCriteria andCriteria = new AndCriteria(new DriversHavingCar(), new GasCar());
        List<DriverDTO> driversWithGasCar = andCriteria.meetCriteria(drivers);
        assertEquals(2, driversWithGasCar.size());
    }
    
    @Test
    public void testDriversWithFiveSeatCar(){
        assertEquals(4, drivers.size());
        AndCriteria andCriteria = new AndCriteria(new DriversHavingCar(), new FiveSeatCar());
        List<DriverDTO> driversWithFiveSeatCar = andCriteria.meetCriteria(drivers);
        assertEquals(3, driversWithFiveSeatCar.size());
    }

}
