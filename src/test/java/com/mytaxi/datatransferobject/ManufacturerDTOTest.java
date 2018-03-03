/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytaxi.datatransferobject;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author YNZ
 */
public class ManufacturerDTOTest {
    
    private ManufacturerDTO manufacturerDTO;
    private Long expectedId = 1L;
    private String expectedName = "TOYOTA";
    
    public ManufacturerDTOTest() {
        manufacturerDTO = ManufacturerDTO.NewBuilder()
                .withId(this.expectedId)
                .withName(expectedName)
                .build();
    }
    
    @Test
    public void testNewBuilder() {
        assertNotNull(ManufacturerDTO.NewBuilder());
    }
    
    @Test
    public void testGetId() {
        assertEquals(expectedId, manufacturerDTO.getId());
    }
    
    @Test
    public void testGetName() {
        assertEquals(expectedName,manufacturerDTO.getName());
    }
    
}
