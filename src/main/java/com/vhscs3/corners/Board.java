/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vhscs3.corners;

/**
 *
 * @author mattanpaluy
 */
enum type {
    white,
    neutral,
    black
}

public class Board {
    //this class will be used as the base for the game. 
    //Additionally, it will be used to calculate legal moves for a selected piece 
    //The class will hold a matrix of squares

    private type turn;
    private Square[][] board;
    private int selectedX = -1;
    private int selectedY = -1;
    int[][] currLegalMoves = new int[8][8];

    private static final int EMPTY = 0;
    private static final int ILLEGAL_MOVE = 0;
    private static final int LEGAL_MOVE = 1;

    public Board() {
        turn = type.white;
        board = new Square[8][8];
        for (int row = 0; row < 8; row++) {
            for (int collum = 0; collum < 8; collum++) {
                if (collum > 4) {
                    if (row < 3) {
                        board[row][collum] = new Square(new Piece(true), type.white);
                    } else {
                        board[row][collum] = new Square(null, type.neutral);
                    }
                } else if (collum < 3) {
                    if (row > 4) {
                        board[row][collum] = new Square(new Piece(false), type.black);
                    } else {
                        board[row][collum] = new Square(null, type.neutral);
                    }
                } else {
                    board[row][collum] = new Square(null, type.neutral);
                }
            }
        }
    }

    public Square getSquare(int row, int collum) {
        return board[row][collum];
    }

    public void setSquare(Square square, int row, int collum) {
        board[row][collum] = square;
    }

    public type getTurn() {
        return turn;
    }

    public void setTurn(type turn) {
        this.turn = turn;
    }

    public int getSelectedX() {
        return selectedX;
    }

    public void setSelectedX(int selectedX) {
        this.selectedX = selectedX;
    }

    public int getSelectedY() {
        return selectedY;
    }

    public void setSelectedY(int selectedY) {
        this.selectedY = selectedY;
    }

    public Square[][] getBoard() {
        return board;
    }

    public int[][] getCurrLegalMoves() {
        return currLegalMoves;
    }

    public int[][] legalMove() {
        //this algorithm gets the x and y values for a current square and returns a 
        //matrix of leagal moves to be used for display and later for move validation.
        //the algorithm is divided into 2 steps.
        //step 1 checks the squares imidate to the current square and call on step 2.

        boolean[][] visited = new boolean[8][8];
        for (int r = 0; r < currLegalMoves.length; r++) {
            for (int c = 0; c < currLegalMoves.length; c++) {
                currLegalMoves[r][c] = 0;
                visited[r][c] = false;
            }
        }

        int[] rowDirections = {0, 0, -1, 1};
        int[] colDirections = {-1, 1, 0, 0};

        for (int k = 0; k < 4; k++) {
            int newRow = selectedX + rowDirections[k];
            int newCol = selectedY + colDirections[k];

            if (isInBounds(newRow, newCol) && board[newRow][newCol].getPiece() == null) {
                currLegalMoves[newRow][newCol] = LEGAL_MOVE;
            } else {
                calculateJumpMoves(selectedX, selectedY, visited);
            }
        }
        return currLegalMoves;
    }

    private void calculateJumpMoves(int row, int col, boolean[][] visited) {
        // calculates part 2 of the legal moves (the jumps)
        int[] rowDirections = {0, 0, -1, 1};
        int[] colDirections = {-1, 1, 0, 0};

        visited[row][col] = true;
        for (int k = 0; k < 4; k++) {
            int newRow = row + rowDirections[k];
            int newCol = col + colDirections[k];
            int jumpRow = newRow + rowDirections[k];
            int jumpCol = newCol + colDirections[k];

            //if these 4 conditions are met its a legal move
            /*
               1. if the 2nd square is not occupied
               2. if the 2nd square is on the board
               3. if the 1st square is occupied
               4. if the 2nd square is a square that hasn't been visited before
             */
            if (isInBounds(jumpRow, jumpCol) && isInBounds(newRow, newCol) && board[jumpRow][jumpCol].getPiece() == null && board[newRow][newCol].getPiece() != null && visited[jumpRow][jumpCol] == false) {
                currLegalMoves[jumpRow][jumpCol] = LEGAL_MOVE;

                // Recursively check for further jumps from the new position
                calculateJumpMoves(jumpRow, jumpCol, visited);
            }
        }
        visited[row][col] = false;
    }

    private boolean isInBounds(int row, int col) {
        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }
}
