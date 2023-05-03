/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vhscs3.corners;

/**
 *
 * @author mattanpaluy
 */
public class Square {

    private Piece piece;
    private type side;

    public Square(Piece piece, type side) {
        this.piece = piece;
        this.side = side;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public type getSide() {
        return side;
    }
}
