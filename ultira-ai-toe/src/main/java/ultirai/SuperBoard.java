/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultirai;

import java.util.Arrays;
import java.util.Objects;

/**
 *
 * @author Jori Lampi
 */
final public class SuperBoard {
    
    private final Board board;
    private final Board[][] subBoards;
    
    public SuperBoard(int size) {
        this.board = new Board(size);
        this.subBoards = new Board[size][];
        Board[] row = new Board[size];
        Arrays.fill(row, this.board);
        Arrays.fill(this.subBoards, row);
    }
    
    private SuperBoard(Board board, Board[][] subBoards) {
        this.board = board;
        this.subBoards = subBoards;
    }
    
    public Board getBoard() {
        return board;
    }
    
    public Board getSubBoardAt(int x, int y) {
        return subBoards[y][x];
    }
    
    public int getSize() {
        return board.getSize();
    }
    
    public Mark resolve() {
        return board.resolve();
    }
    
    public SuperBoard next(Mark mark, int boardX, int boardY, int x, int y) {
        Board changed = subBoards[boardY][boardX].next(mark, x, y);
        Board[][] nextBoards = subBoards.clone();
        nextBoards[boardY] = subBoards[boardY].clone();
        nextBoards[boardY][boardX] = changed;
        Mark result = changed.resolve();
        Board nextBoard = (result == Mark.NONE) ? board : board.next(result, boardX, boardY);
        return new SuperBoard(nextBoard, nextBoards);
    }
    
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
                    case CROSS:
                        subArray = cross(size, cross, none);
                        break;
                    case NOUGHT:
                        subArray = nought(size, nought, none);
                        break;
                    default:
                        subArray = subBoards[y][x].toCharArray(cross, nought, none);
                        
                }
                for (int i = 0; i < subArray.length; i++) {
                    System.arraycopy(subArray[i], 0, array[offsetY+i], offsetX, size);
                }
            }
        }
        return array;
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
