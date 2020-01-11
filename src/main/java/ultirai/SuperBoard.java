/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultirai;

import java.util.Arrays;
import java.util.Objects;

/**
 * An immutable class that represents a square-shaped board of tic
 * 
 * @author Jori Lampi
 */
final public class SuperBoard {
    
    // Uses a Board as a lookup for solved boards
    private final Board board;
    
    // Internally a 2D array
    private final Board[][] subBoards;
    
    /**
     * Initializes new empty SuperBoard.
     * <p>
     * Like with Board, a reduced amount of memory is required. However, as a
     * SuperBoard consists of a square amount of normal boards, the time and
     * space requirements are squared as well.
     * 
     * @param size the root of the SuperBoard's squared size
     */
    public SuperBoard(int size) {
        this.board = new Board(size);
        this.subBoards = new Board[size][];
        Board[] row = new Board[size];
        Arrays.fill(row, this.board);
        Arrays.fill(this.subBoards, row);
    }
    
    /**
     * Private access only. Creates a new SuperBoard directly from the given
     * fields. No validations are made so the responsibility for the object's
     * validity is on the caller.
     * 
     * @param board
     * @param subBoards 
     */
    private SuperBoard(Board board, Board[][] subBoards) {
        this.board = board;
        this.subBoards = subBoards;
    }
    
    /**
     * Returns the next SuperBoard after the given mark is set to given position.
     * 
     * @param mark   Mark being put to board
     * @param boardX index of the board's x-position
     * @param boardY index of the board's y-position
     * @param x      index of the mark's x-position on board
     * @param y      index of the mark's y-position on board
     * @return next SuperBoard
     */
    public SuperBoard next(Mark mark, int boardX, int boardY, int x, int y) {
        Board changed = subBoards[boardY][boardX].next(mark, x, y);
        Board[][] nextBoards = subBoards.clone();
        nextBoards[boardY] = subBoards[boardY].clone();
        nextBoards[boardY][boardX] = changed;
        Mark result = changed.resolve();
        Board nextBoard = (result == Mark.NONE) ? board : board.next(result, boardX, boardY);
        return new SuperBoard(nextBoard, nextBoards);
    }
    
    /**
     * Returns the master board
     * 
     * @return Board representing the overall state of the boards
     */
    public Board getBoard() {
        return board;
    }
    
    /**
     * Gets an individual board in the given position.
     * 
     * @param x index of the horizontal dimension
     * @param y index of the vertical dimension
     * @return board in the specified position
     */
    public Board getSubBoardAt(int x, int y) {
        return subBoards[y][x];
    }
    
    /**
     * Gets the size of the board.
     * 
     * @return size of the board
     */
    public int getSize() {
        return board.getSize();
    }
    
    /**
     * Creates and returns the char array representation of the SuperBoard. The
     * board returned will not be maintained by this object so it can be
     * manipulated as required.
     * <p>
     * Boards that are won are written as crosses and noughts formed of their
     * respective characters.
     * 
     * @param cross  representation of cross in returned array
     * @param nought representation of nought in returned array
     * @param none   representation of an empty position
     * @return 2D char array with squared dimensions of the board
     */
    public char[][] toCharArray(char cross, char nought, char none) {
        int size = getSize();
        int sizeSquare = size * size;
        char[][] array = new char[sizeSquare][sizeSquare];
        for (int y = 0; y < size; y++) {
            int offsetY = y * size;
            for (int x = 0; x < size; x++) {
                int offsetX = x * size;
                char[][] subArray;
                switch (board.getMarkAt(x, y)) {
                    case CROSS: subArray = cross(size, cross, none); break;
                    case NOUGHT: subArray = nought(size, nought, none); break;
                    default: subArray = subBoards[y][x].toCharArray(cross, nought, none);
                }
                for (int i = 0; i < subArray.length; i++) {
                    System.arraycopy(subArray[i], 0, array[offsetY+i], offsetX, size);
                }
            }
        }
        return array;
    }
    
    /**
     * Attempts to resolve the value (or winner) of the SuperBoard by checking
     * all suitable rows for streaks of winned boards.
     * 
     * @return mark representing the result of the board.
     */
    public Mark resolve() {
        return board.resolve();
    }
    
    private char[][] cross(int size, char cross, char none) {
        char[][] array = new char[size][size];
        int end = size - 1;
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                array[y][x] = (x == y || x + y == end) ? cross : none;
            }
        }
        return array;
    }
    
    private char[][] nought(int size, char nought, char none) {
        char[][] array = new char[size][size];
        int end = size - 1;
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                array[y][x] = (x == 0 || x == end || y == 0 || y == end) ? nought : none;
            }
        }
        return array;
    }

    @Override
    public int hashCode() {
        int size = getSize();
        int hash = 7;
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                Mark mark = board.getMarkAt(x, y);
                hash = 97 * hash + ((mark != Mark.NONE) ? Objects.hashCode(mark) : subBoards[y][x].hashCode());
            }
        }
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final SuperBoard other = (SuperBoard) obj;
        int size = getSize();
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                Mark mark = this.board.getMarkAt(x, y);
                if (mark != Mark.NONE) {
                    if (mark != other.board.getMarkAt(x, y)) {
                        return false;
                    }
                } else {
                    if (!this.subBoards[y][x].equals(other.subBoards[y][x])) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
}
