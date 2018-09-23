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
    
    private int activeBoardIndex;
    private final TicTacToe[] board;
    
    public UltimateTicTacToe() {
        super();
        this.activeBoardIndex = 0;
        this.board = new TicTacToe[getSize()*getSize()];
        for (int i = 0; i < board.length; i++) {
            board[i] = new TicTacToe();
        }
    }
    
    public int getActiveBoardIndex() {
        return activeBoardIndex;
    }
    
    public boolean isValidMove(int index) {
        if (activeBoardIndex > 0) {
            return getActiveBoard().get(index) == Mark.NONE;
        } else {
            return super.get(index) == Mark.NONE;
        }
    }
    
    @Override
    public char[][] toCharArray(char cross, char nought, char none) {
        int size = getSize();
        int sizeSquare = size * size;
        char[][] array = new char[sizeSquare][sizeSquare];
        int offsetX = 0;
        int offsetY = 0;
        for (TicTacToe ttt : board) {
            int y = 0;
            for (char[] subRow : ttt.toCharArray()) {
                System.arraycopy(subRow, 0, array[size*offsetY+y], size*offsetX, size);
                y++;
            }
            offsetX = (offsetX + 1) % size;
            if (offsetX == 0) { offsetY++; }
        }
        return array;
    }
    
    @Override
    public boolean set(int index, Mark mark) {
        boolean success = false;
        if (activeBoardIndex > 0) {
            success = getActiveBoard().set(index, mark);
            if (!success) { return false; }
            if (getActiveBoard().evaluate() == mark) { super.set(activeBoardIndex, mark); }
        }
        activeBoardIndex = (super.get(index) == Mark.NONE) ? index : 0;
        return success;
    }
    
    private TicTacToe getActiveBoard() {
        return board[activeBoardIndex-1];
    }
    
}
