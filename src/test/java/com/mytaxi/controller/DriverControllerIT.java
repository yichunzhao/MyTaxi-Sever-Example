/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytaxi.controller;

import javax.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author YNZ
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class DriverControllerIT {

    @Autowired
    private MockMvc mvc;

    public DriverControllerIT() {
    }

    @Test //OFF-LINE driver cannot select a car
    public void testOfflineDriverSelectCarFail1() throws Exception {
        Long driverId = 1L; //OFFLINE driver
        Long carId = 2L;    //Non-selected car 
        this.mvc.perform(put("/v1/drivers/" + driverId + "/select")
                .param("carId", carId.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test //ON-LINE driver having a car, cannot select a car
    public void testOnlineDriverSelectCarFail() throws Exception {
        Long driverId = 8L; //ONLINE driver having a car 
        Long carId = 2L;    //Non-selected car 
        this.mvc.perform(put("/v1/drivers/" + driverId + "/select")
                .param("carId", carId.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test //ON-LINE driver having a car, cannot select another car
    public void testOnlineDriverDrivingSelectCarFail() throws Exception {
        Long driverId = 8L; //ONLINE driver having a car 
        Long carId = 2L;    //Non-selected car 
        this.mvc.perform(put("/v1/drivers/" + driverId + "/select")
                .param("carId", carId.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test //ON-LINE driver without a car to select, is able to select
    public void testOnlineDriverNotDrivingSelectCarSuccess() throws Exception {
        Long driverId = 9L; //ONLINE driver without a car 
        Long carId = 2L;    //Not-selected car 
        this.mvc.perform(put("/v1/drivers/" + driverId + "/select")
                .param("carId", carId.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test //ON-LINE driver without a car to select, is able to select
    public void testOnlineDriverDeselectCarSuccess() throws Exception {
        Long driverId = 8L; //ONLINE driver with a car 

        this.mvc.perform(put("/v1/drivers/" + driverId + "/deselect")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test //driver without a car to deselect, is not able to deselect
    public void testDriverDeselectCarFail() throws Exception {
        Long driverId = 9L; //driver without a car 

        this.mvc.perform(put("/v1/drivers/" + driverId + "/deselect")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

}
