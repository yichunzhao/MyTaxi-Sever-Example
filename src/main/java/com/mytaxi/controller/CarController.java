/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytaxi.controller;

import com.mytaxi.controller.mapper.CarMapper;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.service.car.CarService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.exception.ConstraintsViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author YNZ
 */
@RestController
@RequestMapping("v1/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CarDTO createCar(@Valid @RequestBody CarDTO carDTO) throws ConstraintsViolationException {
        CarDO carDO = CarMapper.makeCarDO(carDTO);
        CarDO created = carService.create(carDO);
        return CarMapper.makeCarDTO(created);
    }

    @GetMapping(value = "/{id}")
    public CarDTO findCar(@PathVariable long id) throws EntityNotFoundException {
        CarDO carDO = carService.find(id);
        return CarMapper.makeCarDTO(carDO);
    }

    @PutMapping(value = "/{id}")
    public CarDTO updateCar(@PathVariable long id, @Valid @RequestBody CarDTO carDTO) throws EntityNotFoundException {
        CarDO carDO = CarMapper.makeCarDO(carDTO);
        return CarMapper.makeCarDTO(carService.update(id, carDO));
    }

    @DeleteMapping(value = "/{id}")
    public void deleteCar(@PathVariable long id) throws EntityNotFoundException {
        carService.delete(id);
    }

}
