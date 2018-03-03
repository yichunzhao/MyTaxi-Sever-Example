/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytaxi.controller.mapper;

import com.mytaxi.datatransferobject.ManufacturerDTO;
import com.mytaxi.domainobject.ManufacturerDO;

/**
 *
 * @author YNZ
 */
public class ManufacturerMapper {
    
    public static ManufacturerDO MakeManufacturerDO(ManufacturerDTO manufacturerDTO){
        return new ManufacturerDO(manufacturerDTO.getName(), manufacturerDTO.getCar());
    } 
    
    public static ManufacturerDTO MakeManufacturerDTO(ManufacturerDO manufacturerDO){
        return ManufacturerDTO.NewBuilder()
                .withId(manufacturerDO.getId())
                .withName(manufacturerDO.getName()).build();
    }
    
}
