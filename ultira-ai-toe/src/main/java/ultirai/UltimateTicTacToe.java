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
public class UltimateTicTacToe extends TicTacToe {
    
    private final static int SIZE = 3;
    
    private int activeX;
    private int activeY;
    private final TicTacToe[][] board;
    
    public UltimateTicTacToe() {
        super();
        this.activeX = -1;
        this.activeY = -1;
        this.board = new TicTacToe[SIZE][SIZE];
        for (TicTacToe[] row : this.board) {
            for (int i = 0; i < row.length; i++) { row[i] = new TicTacToe(); }
        }
    }
    
    public boolean isBoardChosen() { return activeX >= 0 && activeY >= 0; }
    
    @Override
    public String[] rows() {
        String[] rows = new String[SIZE];
        for (int y = 0; y < SIZE; y++) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < SIZE; i++) {
                for (int x = 0; x < SIZE; x++) {
                    sb.append(board[y][x].rows()[i]).append(' ');
                }
                sb.append('\n');
            }
            rows[y] = sb.toString();
        }
        return rows;
    }
    
    public void set(int n, Mark mark) {
        n--;
        set(n%SIZE, n/SIZE, mark);
    }
    
    @Override
    public void set(int x, int y, Mark mark) {
        if (isBoardChosen()) {
            board[activeY][activeX].set(x, y, mark);
            if (board[activeY][activeX].evaluate() == mark) { super.set(activeX, activeY, mark); }
        }
        if (super.get(x, y) == Mark.NONE) {
            activeX = x;
            activeY = y;
        } else {
            activeX = -1;
            activeY = -1;
        }
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String s : rows()) { sb.append(s).append('\n'); }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }
    
}
