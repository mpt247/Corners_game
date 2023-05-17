/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vhscs3.corners;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.*;
import javax.swing.JPanel;

/**
 *
 * @author mattanpaluy
 */
public class BoardGraphicPanel extends JPanel {
    //this class handles all the game graphics

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
        if (state) {
            //if a game is active draw game
            drawBoard(g);
            drawBase(g);
            drawPieces(g, game);
            if (game.getSelectedX() != -1 || game.getSelectedY() != -1) {
                drawSelected(g);
            }

        }
    }

    private void drawBoard(Graphics g) {
        // this method draws the board

        g.setColor(Color.CYAN);
        g.fillRect(0, 0, getWidth(), getWidth());
        g.setColor(Color.BLUE);
        g.fillRect(55, 55, 650, 650);
        int squareSize = 640 / 8;
        int squareX = 60;

        boolean squareColor = true;
        for (int row = 0; row < 8; row++) {
            int squareY = 60;
            if (squareColor) {
                squareColor = false;
            } else {
                squareColor = true;
            }

            for (int collum = 0; collum < 8; collum++) {
                if (squareColor) {
                    g.setColor(Color.CYAN);
                    squareColor = false;
                } else {
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
        // this method draws the pieces on the board
        int locationX = 60;
        int squareSize = 640 / 8;

        Graphics2D g2 = (Graphics2D) g;
        for (int row = 0; row < 8; row++) {
            int locationY = 60;
            for (int collum = 0; collum < 8; collum++) {
                Square current = game.getSquare(row, collum);
                if (current.getPiece() != null) {
                    if (current.getPiece().getSide()) {
                        g.setColor(Color.WHITE);
                    } else {
                        g.setColor(Color.BLACK);
                    }

                    //draw rings around player turn
                    g.fillOval(locationX, locationY, squareSize, squareSize);
                    g.setColor(Color.MAGENTA);
                    g2.setStroke(new BasicStroke(3));
                    if (game.getTurn() == type.white) {
                        if (current.getPiece().getSide()) {
                            g2.drawOval(locationX, locationY, squareSize, squareSize);
                        }
                    } else if (current.getPiece().getSide() == false) {
                        g2.drawOval(locationX, locationY, squareSize, squareSize);
                    }

                }
                locationY += squareSize;
            }
            locationX += squareSize;
        }
    }

    private void drawSelected(Graphics g) {
        // this method draws the selected piece and its legal moves

        //draw selected
        Color selectedColor = new Color(255, 0, 255, 125);
        int locationX = 60 + (80 * game.getSelectedX());
        int locationY = 60 + (80 * game.getSelectedY());
        g.setColor(selectedColor);
        g.fillRect(locationX, locationY, 80, 80);

        //draw leagl moves
        int[][] moves = game.legalMove();

        for (int row = 0; row < 8; row++) {
            for (int collum = 0; collum < 8; collum++) {
                if (moves[row][collum] == 1) {
                    locationX = 60 + (80 * row);
                    locationY = 60 + (80 * collum);
                    g.fillRect(locationX, locationY, 80, 80);
                }
            }
        }

    }

    private void drawBase(Graphics g) {
        // this method draws the the bases of the sides

        int locationX = 60;
        int locationY = 60;
        int squareSize = 640 / 8;

        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));

        g2.setColor(Color.WHITE);
        g2.drawRect(locationX, locationY + (squareSize * 5), squareSize * 3, squareSize * 3);

        g2.setColor(Color.BLACK);
        g2.drawRect(locationX + (squareSize * 5), locationY, squareSize * 3, squareSize * 3);
    }
}
