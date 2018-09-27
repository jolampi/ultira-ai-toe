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
        this.boards = new TicTacToe[getSizeSquare()];
        for (int i = 0; i < boards.length; i++) {
            boards[i] = new TicTacToe();
        }
    }
    
    public int getActiveBoardIndex() {
        return activeBoardIndex;
    }
    
    public void setActiveBoard(int index) {
        if (index < 0 || index > getSizeSquare()) { throw new IllegalArgumentException("Active board index out of bounds."); }
        activeBoardIndex = index;
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
        int sizeSquare = getSizeSquare();
        char[][] array = new char[sizeSquare][sizeSquare];
        for (int i = 0; i < boards.length; i++) {
            int offsetX = (i % size) * size;
            int offsetY = (i / size) * size;
            switch (get(i+1)) {
                case CROSS: renderCross(cross, none, array, offsetX, offsetY); break;
                case NOUGHT: renderNought(nought, none, array, offsetX, offsetY); break;
                default:
                    char[][] subArray = boards[i].toCharArray();
                    for (int y = 0; y < subArray.length; y++) {
                        System.arraycopy(subArray[y], 0, array[offsetY+y], offsetX, size);
                    }
            }
        }
//        renderCross(cross, none, array, 3, 6);
        return array;
    }

    private void renderCross(char cross, char none, char[][] array, int startX, int startY) {
        int size = getSize();
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                array[startY+y][startX+x] = (y == x || y+x+1 == getSize()) ? cross : none;
            }
        }
    }
    
    private void renderNought(char nought, char none, char[][] array, int startX, int startY) {
        int size = getSize();
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                array[startY+y][startX+x] = (x == 0 || x == size-1 || y == 0 || y == size-1) ? nought : none;
            }
        }
    }
    
    @Override
    public boolean set(int index, Mark mark) {
        boolean success = false;
        if (activeBoardIndex > 0) {
            success = getActiveBoard().set(index, mark);
            if (success && getActiveBoard().evaluate() == mark) { super.set(activeBoardIndex, mark); }
        }
        return success;
        
    }
    
    @Override
    public void clearBoard() {
        super.clearBoard();
        for (TicTacToe board : boards) {
            board.clearBoard();
        }
    }
    
    private int getSizeSquare() {
        return getSize() * getSize();
    }
    
    private TicTacToe getActiveBoard() {
        return boards[activeBoardIndex-1];
    }
    
}
