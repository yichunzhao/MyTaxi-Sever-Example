/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytaxi.domainobject;

import com.mytaxi.controller.mapper.ManufacturerMapper;
import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.domainvalue.EngineType;
import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author YNZ
 */
@Entity
@Table(name = "CAR")
@Getter
@Setter
public class CarDO implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "CAR_PK")
    protected Long id;

    @Column(name = "LISENCE_PLATE", unique = true)
    @Size(min = 7, max = 7)
    protected String licensePlate;

    @Column(name = "SEAT_COUNT")
    @Min(5)
    @Max(11)
    protected int seatCount;

    @Column(name = "CONVERTIBLE")
    protected boolean convertible;

    @Column(name = "RATING")
    @Min(0)
    @Max(5)
    protected int rating;

    @Column(name = "ENGINE_TYPE")
    @Enumerated(EnumType.STRING)
    protected EngineType engineType;

    @OneToOne(mappedBy = "car", cascade = CascadeType.ALL, targetEntity = ManufacturerDO.class)
    protected ManufacturerDO manufacturer;

    @Column(name = "DATE_CREATION")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateCreation;

    @OneToOne
    @JoinColumn(name = "Driver_PK_FK", referencedColumnName = "id")
    private DriverDO driver;

    public CarDO() {
        dateCreation = ZonedDateTime.now();
    }

    public CarDO(CarDTO carDTO) {
        this();
        this.id = carDTO.getId();
        this.convertible = carDTO.isConvertible();
        this.engineType = carDTO.getEngineType();
        this.licensePlate = carDTO.getLicensePlate();
        this.manufacturer = ManufacturerMapper
                .MakeManufacturerDO(carDTO.getManufacturer());
        this.rating = carDTO.getRating();
        this.seatCount = carDTO.getSeatCount();
    }

    public boolean isOccupied() {
        return this.driver != null; 
    }

}
