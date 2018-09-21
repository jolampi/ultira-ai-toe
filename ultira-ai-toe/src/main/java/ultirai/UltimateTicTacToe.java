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
    
    private int activeX;
    private int activeY;
    private final TicTacToe[][] board;
    
    public UltimateTicTacToe() {
        super();
        this.activeX = -1;
        this.activeY = -1;
        this.board = new TicTacToe[getSize()][getSize()];
        for (TicTacToe[] row : this.board) {
            for (int i = 0; i < row.length; i++) { row[i] = new TicTacToe(); }
        }
    }
    
    public int getActiveBoardIndex() {
        return activeY * getSize() + activeX + 1;
    }
    
    public boolean isBoardChosen() { return activeX >= 0 && activeY >= 0; }
    
    public boolean isValidMove(int index) {
        index--;
        int x = index % getSize();
        int y = index / getSize();
        return ((isBoardChosen()) ? board[activeY][activeX].get(x, y) : super.get(x, y)) == Mark.NONE;
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
    public boolean set(int x, int y, Mark mark) {
        boolean success = false;
        if (isBoardChosen()) {
            success = board[activeY][activeX].set(x, y, mark);
            if (!success) { return false; }
            if (board[activeY][activeX].evaluate() == mark) { super.set(activeX, activeY, mark); }
        }
        if (super.get(x, y) == Mark.NONE) {
            activeX = x;
            activeY = y;
        } else {
            activeX = -1;
            activeY = -1;
        }
        return success;
    }
    
}
