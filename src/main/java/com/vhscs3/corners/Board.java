/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vhscs3.corners;

/**
 *
 * @author mattanpaluy
 */
enum type{
    white,
    neutral,
    black
}
public class Board {
    
    private Square[][] board;
    
    public Board() {
        board = new Square[8][8];
        for(int row = 0; row < 8; row++){
            for(int collum = 0; collum < 8; collum++){
                if(collum > 4){
                    if(row < 3)
                         board[row][collum] = new Square(new Piece(true), type.white);
                    else
                    board[row][collum] = new Square(null, type.neutral);
                } else if(collum < 3){
                    if(row > 4)
                        board[row][collum] = new Square(new Piece(false), type.black);
                    else
                    board[row][collum] = new Square(null, type.neutral);
                } else
                    board[row][collum] = new Square(null, type.neutral);
            }
        }
    }

    public Square getSquare(int row, int collum) {
        return board[row][collum];
    }
    
    public void setSquare(Square square, int row, int collum){
        board[row][collum] = square;
    }    
}
