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
public class AndCriteria implements Criteria{
    
    private Criteria criteria;
    private Criteria anotherCriteria;

    public AndCriteria(Criteria criteria, Criteria anotherCriteria) {
        this.criteria = criteria;
        this.anotherCriteria = anotherCriteria;
    }

    @Override
    public List<DriverDTO> meetCriteria(List<DriverDTO> drivers) {
        List<DriverDTO> criteriaDrivers = this.criteria.meetCriteria(drivers);
        return this.anotherCriteria.meetCriteria(criteriaDrivers);
    }
    
}
