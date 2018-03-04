/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytaxi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author YNZ
 */

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Selected car is alaredy in use ...")
public class CarAlreadyInUseException extends Exception {

    public CarAlreadyInUseException(String message) {
        super(message);
    }

}
