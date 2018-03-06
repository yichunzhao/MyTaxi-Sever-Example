/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytaxi.domainvalue;

import com.mytaxi.domainobject.DriverDO;
import java.util.Collection;
import static java.util.stream.Collectors.toList;
import java.util.stream.Stream;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author YNZ
 */
public class DriverUserDetails extends DriverDO implements UserDetails {

    public DriverUserDetails(DriverDO driverDO) {
        super(driverDO.getUsername(), driverDO.getPassword(), driverDO.getCar());
    }
    
    //I make it here simplified; DriverDO may has a set of roles; has a one-to-many
    // relationship with a Role entity. 
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Stream.of(new SimpleGrantedAuthority("user")).collect(toList());
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
