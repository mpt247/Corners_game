/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vhscs3.corners;

/**
 *
 * @author mattanpaluy
 */
public class Engine {

    boolean side; //true = white, false = black 
    //weights
    private final double distanceWeight = 1.5;
    private final double jumpWeight = 1.25;
    private final double winWeight = 2.5;
    private int depth = 0;

    int whiteJumps = 0;
    int blackJumps = 0;

    public Engine(int depth, boolean side) {
        this.depth = depth;
        this.side = side;
    }

    public double evaluate(Square[][] board) {
        //evaluation = W1 * (piece_distance_diff) + W2 * (jump_opportunities_diff) + W3 * (pieces_on_destination_side_diff)
        double evaluation;
        //distance between sides 
        int whiteDistance = 0;
        int blackDistance = 0;

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board.length; col++) {
                if (board[row][col].getPiece() != null) {
                    //if white
                    if (board[row][col].getPiece().getSide()) {
                        whiteDistance += calcDistance(row, col, true);
                    } //if black
                    else {
                        blackDistance += calcDistance(row, col, false);
                    }
                }
            }
        }

        int distanceDiff = blackDistance - whiteDistance;

        //jump opportunities diffrence
        blackJumps = 0;
        whiteJumps = 0;

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board.length; col++) {
                if (board[row][col].getPiece() != null) {
                    boolean[][] visited = new boolean[8][8];
                    for (int r = 0; r < board.length; r++) {
                        for (int c = 0; c < board.length; c++) {
                            visited[r][c] = false;
                        }
                    }
                    calculateJumpMoves(row, col, visited, board, board[row][col].getPiece().getSide());
                }
            }
        }

        int jumpDiff = whiteJumps - blackJumps;

        // diffrence between pieces on destination
        int whiteOnBase = 0;
        int blackOnBase = 0;

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board.length; col++) {
                if (col > 4) {
                    if (row < 3) {
                        if (board[row][col].getPiece() != null) {
                            if (board[row][col].getPiece().getSide() == false) {
                                blackOnBase++;
                            }
                        }
                    }
                } else if (col < 3) {
                    if (row > 4) {
                        if (board[row][col].getPiece() != null) {
                            if (board[row][col].getPiece().getSide()) {
                                whiteOnBase++;
                            }
                        }
                    }
                }
            }
        }

        int onBaseDiff = whiteOnBase - blackOnBase;

        //evaluation = W1 * (piece_distance_diff) + W2 * (jump_opportunities_diff) + W3 * (pieces_on_destination_side_diff)
        evaluation = distanceWeight * distanceDiff + jumpWeight * jumpDiff + winWeight * onBaseDiff;

        System.out.println("Distance Diff:: " + distanceDiff);
        System.out.println("Jump Diff:: " + jumpDiff);
        System.out.println("On Base Diff:: " + onBaseDiff);

        return evaluation;
    }

    private int calcDistance(int x, int y, boolean side) {

        int distance = 0;

        //if piece is white
        if (side) {
            if (x > 4 && y < 3) {
                return 0;
            } else {
                while (x < 5) {
                    x++;
                    distance++;
                }
                while (y > 2) {
                    y--;
                    distance++;
                }
            }
        } else {
            if (x < 3 && y > 4) {
                return 0;
            } else {
                while (x > 2) {
                    x--;
                    distance++;
                }
                while (y < 5) {
                    y++;
                    distance++;
                }
            }
        }
        return distance;
    }

    private void calculateJumpMoves(int row, int col, boolean[][] visited, Square[][] board, boolean side) {
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

                if (side) {
                    if (jumpRow < 3 || jumpCol > 4) {
                        whiteJumps++;
                    }
                } else {
                    if (jumpRow > 4 || jumpCol < 3) {
                        blackJumps++;
                    }
                }

                // Recursively check for further jumps from the new position
                calculateJumpMoves(jumpRow, jumpCol, visited, board, this.side);
            }
        }
        visited[row][col] = false;
    }

    private boolean isInBounds(int row, int col) {
        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }
}
