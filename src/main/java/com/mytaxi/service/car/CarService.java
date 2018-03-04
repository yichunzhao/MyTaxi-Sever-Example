/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytaxi.service.car;

import com.mytaxi.domainobject.CarDO;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;
import java.util.List;

/**
 *
 * @author YNZ
 */
public interface CarService {
    
    CarDO find(Long carId) throws EntityNotFoundException;
    
    List<CarDO> findAll() throws EntityNotFoundException;

    CarDO create(CarDO carDO) throws ConstraintsViolationException;

    void delete(Long carId) throws EntityNotFoundException;

    CarDO update(Long carId, CarDO carDo) throws EntityNotFoundException;
    
}
