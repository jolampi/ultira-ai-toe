/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultirai;

/**
 *
 * @author Jori Lampi
 */
public class TicTacToe {
    
    private final static int SIZE = 3;
    private final static int WIN_REQUIREMENT = 3;
    
    private final Mark[][] board;

    public TicTacToe() {
        this.board = new Mark[SIZE][SIZE];
        clearBoard();
    }
    
    public int getSize() { return SIZE; }
    
    public void set(int x, int y, Mark mark) { board[y][x] = mark; }
    
    public Mark get(int x, int y) { return board[y][x]; }
    
    public Mark evaluate() {
        Mark mark;
        for (int i = 0; i < SIZE; i++) {
            if ((mark = evaluateRow(i, 0, 1, 0)) != Mark.NONE) { return mark; }
            if ((mark = evaluateRow(0, i, 0, 1)) != Mark.NONE) { return mark; }
        }
        // diagonal rows
        if ((mark = evaluateRow(0, 0, 1, 1)) != Mark.NONE) { return mark; }
        if ((mark = evaluateRow(SIZE-1, 0, -1, 1)) != Mark.NONE) { return mark; }
        /* required block if the WIN_REQUIREMENT is less than a board's dimension. Untested ATM
        for (int i = 1; i <= SIZE - WIN_REQUIREMENT; i++) {
            if ((mark = evaluateRow(i, 0, 1, 1)) != Mark.NONE) { return mark; }
            if ((mark = evaluateRow(0, i, 1, 1)) != Mark.NONE) { return mark; }
            if ((mark = evaluateRow(SIZE-1, i, -1, 1)) != Mark.NONE) { return mark; }
            if ((mark = evaluateRow(SIZE-1-i, 0, -1, 1)) != Mark.NONE) { return mark; }
        }*/
        return Mark.NONE;
    }
    
    private Mark evaluateRow(int x, int y, int dx, int dy) {
        Mark value = Mark.NONE;
        int count = 0;
        while (0 <= y && y < board.length && 0 <= x && x < board[y].length) {
            if (value == board[y][x]) {
                if (++count == WIN_REQUIREMENT) { return value; }
            } else {
                value = board[y][x];
                count = 1;
            }
            x += dx;
            y += dy;
        }
        return Mark.NONE;
    }
    
    public void clear() { clearBoard(); }
    
    public String[] rows() {
        String[] rows = new String[SIZE];
        for (int i = 0; i < SIZE; i++) {
            StringBuilder sb = new StringBuilder(SIZE);
            for (Mark m : board[i]) {
                switch (m) {
                    case CROSS: sb.append('X'); break;
                    case NOUGHT: sb.append('O'); break;
                    default: sb.append('.');
                }
            }
            rows[i] = sb.toString();
        }
        return rows;
    }
    
    private void clearBoard() {
        for (Mark[] row : board) {
            for (int x = 0; x < row.length; x++) {
                row[x] = Mark.NONE;
            }
        }
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(SIZE * (SIZE + 1));
        for (String s : rows()) { sb.append(s).append('\n'); }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }
    
}