/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vhscs3.corners;

/**
 *
 * @author mattanpaluy
 */
public class Piece {
    //this class represents a piece that will be held by a Square object, and contain a boolean for side

    //true = white, false = black
    private boolean side;

    public Piece(boolean side) {
        this.side = side;
    }

    public boolean getSide() {
        return side;
    }

}
