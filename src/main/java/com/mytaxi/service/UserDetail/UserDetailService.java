/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytaxi.service.UserDetail;

import com.mytaxi.dataaccessobject.DriverRepository;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.DriverUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author YNZ
 */
@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private DriverRepository driverRepository;

    public UserDetailService() {
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        DriverDO driverDO = driverRepository.findByUsername(username);

        if (driverDO == null) {
            throw new UsernameNotFoundException(username + " is not found!");
        }

        return new DriverUserDetails(driverDO);

    }

}
