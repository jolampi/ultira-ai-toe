/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultirai;

import java.util.Arrays;

/**
 * An immutable class that represents a square-shaped tic-tac-toe board.
 * 
 * @author Jori Lampi
 */
final public class Board {
    
    // Internally the board is a 2D array
    private final Mark[][] board;
    
    /**
     * Creates a new Board that is clear at start. The size argument must be
     * positive.
     * 
     * @param size the size of the board
     */
    public Board(int size) {
        if (size <= 0) { throw new IllegalArgumentException("Board size must be positive."); }
        this.board = createBoard(size);
    }
    
    /**
     * Initializes a new empty board.
     * <p>
     * Since the class is immutable and no direct access to the fields is
     * provided, only one row of board is actually required. The rest of the
     * rows are simply references to that one. This way a time and memory
     * requirement of O(size) is achieved on a size^2 board at start.
     * 
     * @param size the size of the both dimensions of the board.
     * @return initialized 2D Mark array
     */
    private static Mark[][] createBoard(int size) {
        Mark[][] board = new Mark[size][];
        Mark[] row = new Mark[size];
        Arrays.fill(row, Mark.NONE);
        Arrays.fill(board, row);
        return board;
    }
    
    /**
     * This constructor is private access only. Creates a new board from the
     * given array. The constructor doesn't do any verifications on the board
     * itself so the responsibility is on the caller.
     *
     * @param board the internal Mark array of the new Board.
     */
    private Board(Mark[][] board) {
        this.board = board;
    }
    
    /**
     * Creates and returns the next state of the board, where the value
     * indicated with x and y is replaced with mark. Both x and y must be
     * between 0 (inclusive) and the size of the board (exclusive).
     * <p>
     * Only required references will be changed for the new object. In other
     * words the board returned will retain all same references to unchanged
     * rows and columns. This makes the function to perform in O(n) time.
     * 
     * @param mark the new value of Mark indicated by x and y in returned board
     * @param x    position index in the horizontal dimension for mark
     * @param y    position index in the vertical dimension for mark
     * @return     Board object where the value in position indicated by x and y
     *             is mark
     */
    public Board next(Mark mark, int x, int y) {
        Mark[][] nextBoard = board.clone();
        nextBoard[y] = board[y].clone();
        nextBoard[y][x] = mark;
        return new Board(nextBoard);
    }
        
    /**
     * Returns the size of the board, that is the length of its either
     * dimension.
     * 
     * @return the size of the board
     */
    public int getSize() {
        return board.length;
    }
    
    /**
     * Returns the Mark value in the position (x,y). Both arguments need to be
     * between 0 (inclusive) and the size of the board (exclusive).
     * 
     * @param x index of the horizontal dimension
     * @param y index of the vertical dimension
     * @return Mark in the given position
     */
    public Mark getMarkAt(int x, int y) {
        return board[y][x];
    }
    
    /**
     * 
     * 
     * @return true if the grid is filled with CROSS and NOUGHT
     */
    public boolean isFilled() {
        for (Mark[] row : board) {
            for (Mark mark : row) {
                if (mark == Mark.NONE || mark == Mark.TIE) {
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * Creates and returns the char array representation of the board. The board
     * returned will not be maintained by this object so it can be manipulated
     * as required.
     * 
     * @param cross  representation of cross in returned array
     * @param nought representation of nought in returned array
     * @param none   representation of an empty position
     * @return 2D char array with the same dimensions as the board
     */
    public char[][] toCharArray(char cross, char nought, char none) {
        int size = board.length;
        char[][] array = new char[size][size];
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                switch (board[y][x]) {
                    case CROSS: array[y][x] = cross; break;
                    case NOUGHT: array[y][x] = nought; break;
                    default: array[y][x] = none;
                }
            }
        }
        return array;
    }
    
    /**
     * Attempts to resolve the value (or winner) of the board by checking all
     * suitable rows for streaks of the same Mark. 
     * 
     * @return the value of the board
     */
    public Mark resolve() {
        int size = getSize();
        Mark mark;
        for (int i = 0; i < size; i++) {
            if ((mark = resolveRow(0, i, 1, 0)) != Mark.NONE) { return mark; }
            if ((mark = resolveRow(i, 0, 0, 1)) != Mark.NONE) { return mark; }
        }
        // diagonal rows
        if ((mark = resolveRow(0, 0, 1, 1)) != Mark.NONE) { return mark; }
        if ((mark = resolveRow(size-1, 0, -1, 1)) != Mark.NONE) { return mark; }
        return Mark.NONE;
    }
    
    /**
     * A help method for checking individual rows. Simply start from (x,y) and
     * add (dx,dy) after every step until a long enough streak is found or
     * hit the border.
     * 
     * @param x  start index of horizontal dimension
     * @param y  start index of vertical dimension
     * @param dx delta step for x-coordinate
     * @param dy delta step for y-coordinate
     * @return value of the row
     */
    private Mark resolveRow(int x, int y, int dx, int dy) {
        int size = getSize();
        Mark mark = Mark.NONE;
        int count = 0;
        while (0 <= y && y < size && 0 <= x && x < size) {
            if (mark == board[y][x]) {
                if (++count == size) { return mark; }
            } else {
                mark = board[y][x];
                count = 1;
            }
            x += dx;
            y += dy;
        }
        return Mark.NONE;
    }
    
    @Override
    public int hashCode() {
        return 7 + Arrays.deepHashCode(this.board);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Board other = (Board) obj;
        return Arrays.deepEquals(this.board, other.board);
    }
    
}
