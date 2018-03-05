/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytaxi.controller.driverfilter;

import com.mytaxi.datatransferobject.DriverDTO;
import java.util.List;
import static java.util.stream.Collectors.toList;

/**
 *
 * @author YNZ
 */
public class DriversHavingCar implements Criteria {

    @Override
    public List<DriverDTO> meetCriteria(List<DriverDTO> drivers) {
        return drivers.stream().filter(x -> x.getCar() != null).collect(toList());
    }

}
