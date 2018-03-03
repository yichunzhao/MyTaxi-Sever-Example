/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytaxi.dataaccessobject;

import com.mytaxi.domainobject.CarDO;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author YNZ
 */
public interface CarDORepository extends CrudRepository<CarDO, Long> {

}
