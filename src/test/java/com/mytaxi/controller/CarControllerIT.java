/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytaxi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.datatransferobject.ManufacturerDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainvalue.EngineType;
import com.mytaxi.service.car.CarService;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author YNZ
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class CarControllerIT {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private CarService carService;

    private CarDTO carDTO;
    private ManufacturerDTO mdto;

    public CarControllerIT() {
        mdto = ManufacturerDTO.NewBuilder()
                .withName("TOYOTA")
                .build();

        carDTO = CarDTO.NewBuilder()
                .withConvertible(true)
                .withEngineType(EngineType.ELECTRIC)
                .withLicensePlate("XXXLLOO")
                .withRating(5)
                .withSeatCount(7)
                .withManufacturer(mdto)
                .build();
    }

    @Test
    public void testCreateCar() throws Exception {
        this.mvc.perform(post("/v1/cars")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(carDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testFindCar() throws Exception {
        Long id = 10L;
        this.mvc.perform(get("/v1/cars/" + id.toString()))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateCar() throws Exception {

        CarDO faked = new CarDO(
                CarDTO.NewBuilder()
                        .withConvertible(true)
                        .withEngineType(EngineType.GAS)
                        .withLicensePlate("YYYLLOO")
                        .withRating(5)
                        .withSeatCount(7)
                        .withManufacturer(mdto)
                        .build()
        );

        CarDO created = this.carService.create(faked);

        CarDO modified = new CarDO(
                CarDTO.NewBuilder()
                        .withConvertible(true)
                        .withEngineType(EngineType.ELECTRIC)
                        .withLicensePlate("XXXLLOO")
                        .withRating(5)
                        .withSeatCount(7)
                        .withManufacturer(mdto)
                        .build()
        );

        this.mvc.perform(put("/v1/cars/" + created.getId().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(modified)))
                .andExpect(status().isOk());

    }

    @Test
    public void testDeleteCar() throws Exception {
        Long id = 10L;
        this.mvc.perform(delete("/v1/cars/" + id.toString()))
                .andExpect(status().isNotFound());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
