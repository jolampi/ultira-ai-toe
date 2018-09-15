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
        char[][] array = toCharArray();
        for (int y = 0; y < array.length; y++) {
            for (int x = 0; x < array[y].length; x++) {
                sb.append(array[y][x]);
                if (x < array[y].length - 1 && x % getSize() == 2) { sb.append(' '); }
            }
            if (y < array.length - 1) {
                sb.append('\n');
                if (y % getSize() == 2) { sb.append('\n'); }
            }
        }
        return sb.toString();
    }
    
}
