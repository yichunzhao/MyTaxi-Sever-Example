/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytaxi.exception;

/**
 *
 * @author YNZ
 */
public class CarAlreadyInUseException extends Exception {

    public CarAlreadyInUseException(String message) {
        super(message);
    }

}
