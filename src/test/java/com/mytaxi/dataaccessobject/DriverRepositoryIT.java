/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytaxi.dataaccessobject;

import com.mytaxi.domainobject.DriverDO;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author YNZ
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DriverRepositoryIT {

    @Autowired
    private DriverRepository driverRepository;

    public DriverRepositoryIT() {
    }

    @Test
    public void testFindByUsername() {
        assertNotNull(this.driverRepository);
        
        DriverDO driver = this.driverRepository.findByUsername("driver08");
        assertNotNull(driver);
        
        assertEquals("driver08pw", driver.getPassword());
    }
    
    @Test
    public void testFindByUsername_NotExsited() {
        assertNotNull(this.driverRepository);
        
        DriverDO driver = this.driverRepository.findByUsername("xxxx08");
        assertNull(driver);
    }

}
