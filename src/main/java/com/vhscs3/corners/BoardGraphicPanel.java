/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vhscs3.corners;


import java.awt.Color;
import java.awt.Graphics;
import java.util.Scanner;
import javax.swing.JPanel;

/**
 *
 * @author mattanpaluy
 */
public class BoardGraphicPanel extends JPanel{
    
    private Board game;
    private boolean state;
    
    public void processData(Board game, boolean state) {
        this.game = game;
        this.state = state;
        
        // Calls the paint, update, and paintComponent methods
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        
        // Clear all of the panel content before drawing
        super.paintComponent(g);
        
        // Code to draw graphics
        if(state){
            //if a game is active draw game
            drawBoard(g);
            drawPieces(g, game);
            
        }
    }
    
    private void drawBoard(Graphics g) {
        g.setColor(Color.CYAN);
            g.fillRect(0, 0, getWidth(), getWidth());
            g.setColor(Color.BLUE);
            g.fillRect(55, 55, 650, 650);
            int squareSize = 640/8;
            int squareX = 60;
            
            boolean squareColor = true;
            for(int row = 0; row < 8; row++){
                int squareY = 60;
                if(squareColor)
                    squareColor = false;
                else
                    squareColor = true;
                
                for(int collum = 0; collum < 8; collum++){
                    if(squareColor){
                        g.setColor(Color.CYAN);
                        squareColor = false;
                    }else{
                        g.setColor(Color.BLUE);
                        squareColor = true;
                    }
                    g.fillRect(squareX, squareY, squareSize, squareSize);
                    
                    squareY += squareSize;
                }
                squareX += squareSize;
            }
    }
    
    private void drawPieces(Graphics g, Board game) {
        int locationX = 60;
            int squareSize = 640/8;

            for(int row = 0; row < 8; row++){
                int locationY = 60;
                for(int collum = 0; collum < 8; collum++){
                    Square current = game.getSquare(row, collum);
                    if(current.getPiece() != null){
                        if(current.getPiece().getSide())
                            g.setColor(Color.WHITE);
                        else
                            g.setColor(Color.BLACK);

                        g.fillOval(locationX, locationY, squareSize, squareSize);
                    }
                    locationY += squareSize;
                }
                locationX += squareSize;
            }
    }
}