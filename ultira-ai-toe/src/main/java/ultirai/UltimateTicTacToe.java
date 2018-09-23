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
    private final TicTacToe[][] board;
    
    public UltimateTicTacToe() {
        super();
        this.activeBoardIndex = 0;
        this.board = new TicTacToe[getSize()][getSize()];
        for (TicTacToe[] row : this.board) {
            for (int i = 0; i < row.length; i++) { row[i] = new TicTacToe(); }
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
        char[][] array = new char[size*size][size*size];
        for (int y = 0; y < size; y++) {
            int offsetY = y * size;
            for (int x = 0; x < size; x++) {
                int offsetX = x * size;
                char[][] subArray = board[y][x].toCharArray(cross, nought, none);
                for (int z = 0; z < subArray.length; z++) {
                    System.arraycopy(subArray[z], 0, array[offsetY+z], offsetX, size);
                }
            }
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
        int y = (activeBoardIndex - 1) / getSize();
        int x = (activeBoardIndex - 1) % getSize();
        return board[y][x];
    }
    
}
