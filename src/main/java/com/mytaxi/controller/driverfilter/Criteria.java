/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytaxi.controller.driverfilter;

import com.mytaxi.datatransferobject.DriverDTO;
import java.util.List;

/**
 *
 * @author YNZ
 */
public interface Criteria {

    List<DriverDTO> meetCriteria(List<DriverDTO> drivers);

}
