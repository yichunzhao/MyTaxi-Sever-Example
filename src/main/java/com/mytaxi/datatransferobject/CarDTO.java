/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytaxi.datatransferobject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mytaxi.domainvalue.EngineType;
import lombok.Getter;

/**
 *
 * @author YNZ
 */
@Getter
public class CarDTO {

    protected Long id;

    protected String licensePlate;

    protected int seatCount;

    protected boolean convertible;

    protected int rating;

    protected EngineType engineType;

    protected boolean occupied;

    protected ManufacturerDTO manufacturer;

    public CarDTO() {
    }

    public CarDTO(Builder builder) {
        this.id = builder.id;
        this.convertible = builder.convertible;
        this.engineType = builder.engineType;
        this.licensePlate = builder.licensePlate;
        this.manufacturer = builder.manufacturer;
        this.rating = builder.rating;
        this.seatCount = builder.seatCount;
        this.occupied = builder.occupied;
    }

    public static Builder NewBuilder() {
        return new Builder();
    }

    public static class Builder {

        private Long id;

        private String licensePlate;

        private int seatCount;

        private boolean convertible;

        private int rating;

        private EngineType engineType;

        private boolean occupied;

        private ManufacturerDTO manufacturer;

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withLicensePlate(String licensePlate) {
            this.licensePlate = licensePlate;
            return this;
        }

        public Builder withSeatCount(int seatCount) {
            this.seatCount = seatCount;
            return this;
        }

        public Builder withConvertible(boolean convertible) {
            this.convertible = convertible;
            return this;
        }

        public Builder withRating(int rating) {
            this.rating = rating;
            return this;
        }

        public Builder withEngineType(EngineType engineType) {
            this.engineType = engineType;
            return this;
        }

        public Builder isOccupied(boolean occupied) {
            this.occupied = occupied;
            return this;
        }

        public Builder withManufacturer(ManufacturerDTO manufacturer) {
            this.manufacturer = manufacturer;
            return this;
        }

        public CarDTO build() {
            return new CarDTO(this);
        }

    }

}
