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
public class GraphicPanel extends JPanel{
    
    private boolean game;
    
    public void processData(boolean state) {
        game = state;
        
        // Calls the paint, update, and paintComponent methods
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        
        // Clear all of the panel content before drawing
        super.paintComponent(g);
        
        // Code to draw graphics
        if(game){
            //draw board if game is true
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
    }
}