/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytaxi.datatransferobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mytaxi.domainobject.CarDO;
import javax.validation.constraints.NotNull;
import lombok.Getter;

/**
 *
 * @author YNZ
 */

@Getter
public class ManufacturerDTO {

    @JsonIgnore
    protected Long id;

    @NotNull
    protected String name;

    @JsonIgnore
    protected CarDO car;

    public ManufacturerDTO() {
    }

    public ManufacturerDTO(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.car = builder.car;
    }

    public static Builder NewBuilder() {
        return new Builder();
    }

    public static class Builder {

        private Long id;
        private String name;
        private CarDO car;

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withCar(CarDO car) {
            this.car = car;
            return this;
        }

        public ManufacturerDTO build() {
            return new ManufacturerDTO(this);
        }

    }

}
