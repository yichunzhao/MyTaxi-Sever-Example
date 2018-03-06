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
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.service.car.CarService;
import javax.transaction.Transactional;
import lombok.experimental.Wither;
import org.junit.Before;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
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
@WithMockUser(roles = "user", password = "driver08pw", username = "driver08")
public class CarControllerIT {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private CarService carService;

    private CarDO faked;
    private CarDO created;

    public CarControllerIT() {
    }

    @Before
    public void putFakeCar() throws ConstraintsViolationException {
        faked = new CarDO(
                CarDTO.NewBuilder()
                        .withConvertible(true)
                        .withEngineType(EngineType.GAS)
                        .withLicensePlate("YYYLLOO")
                        .withRating(5)
                        .withSeatCount(7)
                        .withManufacturer(ManufacturerDTO.NewBuilder().withName("Ford").build())
                        .build()
        );

        created = this.carService.create(faked);
    }

    @Test
    public void testCreateCar() throws Exception {

        CarDTO carDTO = CarDTO.NewBuilder()
                .withConvertible(true)
                .withEngineType(EngineType.ELECTRIC)
                .withLicensePlate("XXXLLOO")
                .withRating(5)
                .withSeatCount(7)
                .withManufacturer(ManufacturerDTO.NewBuilder().withName("TOYOTA").build())
                .build();

        this.mvc.perform(post("/v1/cars")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(carDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testFindCarFail() throws Exception {
        Long id = 10L;
        this.mvc.perform(get("/v1/cars/" + id.toString()))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testFindCarSuccess() throws Exception {
        Long id = created.getId();
        this.mvc.perform(get("/v1/cars/" + id))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateCar() throws Exception {

        CarDO modified = new CarDO(
                CarDTO.NewBuilder()
                        .withConvertible(true)
                        .withEngineType(EngineType.ELECTRIC)
                        .withLicensePlate("XXXLLOO")
                        .withRating(5)
                        .withSeatCount(7)
                        .withManufacturer(ManufacturerDTO.NewBuilder().withName("Ford").build())
                        .build()
        );

        this.mvc.perform(put("/v1/cars/" + created.getId().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(modified)))
                .andExpect(status().isOk());

    }

    @Test
    public void testUpdateCarFail() throws Exception {
        CarDO modified = new CarDO(
                CarDTO.NewBuilder()
                        .withConvertible(true)
                        .withEngineType(EngineType.ELECTRIC)
                        .withLicensePlate("XXXLLOO")
                        .withRating(5)
                        .withSeatCount(7)
                        .withManufacturer(ManufacturerDTO.NewBuilder().withName("Ford").build())
                        .build()
        );

        Long id = 10L;
        this.mvc.perform(put("/v1/cars/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(modified)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteCarFail() throws Exception {
        Long id = 10L;
        this.mvc.perform(delete("/v1/cars/" + id.toString()))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteCar() throws Exception {
        Long id = created.getId();
        this.mvc.perform(delete("/v1/cars/" + id))
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
