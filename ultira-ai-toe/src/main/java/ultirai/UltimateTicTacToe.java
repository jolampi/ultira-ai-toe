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
    
    private TicTacToe activeSubBoard;
    private final TicTacToe[][] board;
    
    public UltimateTicTacToe() {
        super();
        this.activeSubBoard = null;
        this.board = new TicTacToe[SIZE][SIZE];
        for (TicTacToe[] row : this.board) {
            for (int i = 0; i < row.length; i++) { row[i] = new TicTacToe(); }
        }
    }
    
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
        if (activeSubBoard == null) {
            activeSubBoard = board[y][x];
        } else {
            activeSubBoard.set(x, y, mark);
//            if (activeSubBoard.evaluate() == mark) { super.set(x, y, mark); }
            activeSubBoard = (super.get(x, y) == Mark.NONE) ? board[y][x] : null;
        }
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String s : rows()) { sb.append(s).append('\n'); }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }
    
}
