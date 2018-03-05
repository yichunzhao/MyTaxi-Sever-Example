/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytaxi.domainvalue;

/**
 *
 * @author YNZ
 */
public enum SeatCount {
    FIVE(5), SEVEN(7);

    private int count;

    private SeatCount(int count) {
        this.count = count;
    }
    
    public int count(){
        return this.count;
    }
}
