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
    private final TicTacToe[] boards;
    
    public UltimateTicTacToe() {
        super();
        this.activeBoardIndex = 0;
        this.boards = new TicTacToe[getSize()*getSize()];
        for (int i = 0; i < boards.length; i++) {
            boards[i] = new TicTacToe();
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
        for (int i = 0; i < boards.length; i++) {
            int offsetX = (i % size) * size;
            int offsetY = (i / size) * size;
            char[][] subArray = boards[i].toCharArray();
            for (int y = 0; y < subArray.length; y++) {
                System.arraycopy(subArray[y], 0, array[offsetY+y], offsetX, size);
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
        return boards[activeBoardIndex-1];
    }
    
}
