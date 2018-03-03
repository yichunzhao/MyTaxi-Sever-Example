/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytaxi.domainobject;

import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author YNZ
 */
@Entity
@Table(name = "MANUFACTURER")
@Data
public class ManufacturerDO implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "MANUFACTURE_PK")
    protected Long id;

    @Column(name = "NAME")
    @NotNull
    protected String name;

    @ManyToOne
    @JoinColumn(name = "CAR_PK_FK", referencedColumnName = "CAR_PK")
    protected CarDO car;

    @Column
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateCreation = ZonedDateTime.now();

    public ManufacturerDO() {
    }

    public ManufacturerDO(String name) {
        this.name = name;
    }

    public ManufacturerDO(String name, CarDO car) {
        this.name = name;
        this.car = car;
    }

}
