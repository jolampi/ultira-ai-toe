/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultirai;

import java.util.Arrays;

/**
 *
 * @author Jori Lampi
 */
final public class Board {
    
    private final Mark[][] board;
    
    public Board(int size) {
        if (size <= 0) { throw new IllegalArgumentException("Board size must be positive."); }
        this.board = new Mark[size][];
        Mark[] row = new Mark[size];
        Arrays.fill(row, Mark.NONE);
        Arrays.fill(board, row);
    }
    
    private Board(Board previous, Mark mark, int x, int y) {
        this.board = previous.board.clone();
        this.board[y] = previous.board[y].clone();
        this.board[y][x] = mark;
    }
        
    public int getSize() {
        return board.length;
    }
    
    public Mark getMarkAt(int x, int y) {
        return board[y][x];
    }
    
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
    
    public Board next(Mark mark, int x, int y) {
        return new Board(this, mark, x, y);
    }
    
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
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Arrays.deepHashCode(this.board);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Board other = (Board) obj;
        if (!Arrays.deepEquals(this.board, other.board)) {
            return false;
        }
        return true;
    }
    
}
