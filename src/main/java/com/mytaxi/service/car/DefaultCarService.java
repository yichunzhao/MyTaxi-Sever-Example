/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytaxi.service.car;

import com.mytaxi.dataaccessobject.CarDORepository;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.service.driver.DefaultDriverService;
import javax.validation.ConstraintViolationException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

/**
 *
 * @author YNZ
 */

@Service
public class DefaultCarService implements CarService {

    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(DefaultDriverService.class);

    @Autowired
    CarDORepository carDORepository;

    @Override
    public CarDO find(Long carId) throws EntityNotFoundException {
        CarDO car = carDORepository.findOne(carId);

        if (car == null) {
            throw new EntityNotFoundException("Could not find entity with id: " + carId);
        }

        return car;
    }

    @Override
    public CarDO create(CarDO carDO) throws ConstraintsViolationException {
        CarDO car;
        try {
            car = carDORepository.save(carDO);
        } catch (DataIntegrityViolationException | ConstraintViolationException e) {
            LOG.warn("Some constraints are thrown due to car creation", e);
            throw new ConstraintsViolationException(e.getMessage());
        }
        return car;
    }

    @Override
    public void delete(Long carId) throws EntityNotFoundException {
        find(carId);
        carDORepository.delete(carId);
    }

    @Override
    public CarDO update(Long carId, CarDO carDO) throws EntityNotFoundException {
        find(carId);
        return carDORepository.save(carDO);
    }

}
