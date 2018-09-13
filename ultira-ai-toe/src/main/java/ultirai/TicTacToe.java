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
    
    private final Mark[][] board;

    public TicTacToe() {
        this.board = new Mark[SIZE][SIZE];
        clearBoard();
    }
    
    public void set(int x, int y, Mark mark) { board[y][x] = mark; }
    
    public Mark get(int x, int y) { return board[y][x]; }
    
    public Mark evaluate() {
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
