/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytaxi.controller.mapper;

import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.domainobject.CarDO;
import java.util.List;
import static java.util.stream.Collectors.toList;

/**
 *
 * @author YNZ
 */
public class CarMapper {

    public static CarDO makeCarDO(CarDTO carDTO) {
        return new CarDO(carDTO);
    }

    public static CarDTO makeCarDTO(CarDO carDO) {
        return CarDTO.NewBuilder().withId(carDO.getId())
                .withConvertible(carDO.isConvertible())
                .withDateCreation(carDO.getDateCreation())
                .withEngineType(carDO.getEngineType())
                .withLicensePlate(carDO.getLicensePlate())
                .withRating(carDO.getRating())
                .withSeatCount(carDO.getSeatCount())
                .withManufacturer(ManufacturerMapper.MakeManufacturerDTO(carDO.getManufacturer()))
                .build();

    }

    public static List<CarDTO> makeCarDTOList(List<CarDO> carDOs) {
        return carDOs.stream().map(CarMapper::makeCarDTO).collect(toList());
    }

}
