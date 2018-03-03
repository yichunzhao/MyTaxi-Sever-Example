/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytaxi.datatransferobject;

import com.mytaxi.domainvalue.EngineType;
import java.time.ZonedDateTime;
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

    protected ManufacturerDTO manufacturer;

    private ZonedDateTime dateCreation;

    public CarDTO() {
    }

    public CarDTO(Builder builder) {
        this.id = builder.id;
        this.convertible = builder.convertible;
        this.dateCreation = builder.dateCreation;
        this.engineType = builder.engineType;
        this.licensePlate = builder.licensePlate;
        this.manufacturer = builder.manufacturer;
        this.rating = builder.rating;
        this.seatCount = builder.seatCount;
    }

    public static Builder NewBuilder() {
        return new Builder();
    }

    public static class Builder {

        protected Long id;

        protected String licensePlate;

        protected int seatCount;

        protected boolean convertible;

        protected int rating;

        protected EngineType engineType;

        protected ManufacturerDTO manufacturer;

        private ZonedDateTime dateCreation;

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

        public Builder withManufacturer(ManufacturerDTO manufacturer) {
            this.manufacturer = manufacturer;
            return this;
        }

        public Builder withDateCreation(ZonedDateTime dateCreation) {
            this.dateCreation = dateCreation;
            return this;
        }

        public CarDTO build() {
            return new CarDTO(this);
        }

    }

}
