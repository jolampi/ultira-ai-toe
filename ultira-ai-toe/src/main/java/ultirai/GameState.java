/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultirai;

import java.util.Arrays;

/**
 *
 * @author Jori Lampi
 */
public class GameState {
    
    private final char[][] board;
    private final int activeBoardIndex;
    private final int size;
    private final int hashCode;
    
    public GameState(UltimateTicTacToe ultimateTicTacToe) {
        this.board = ultimateTicTacToe.toCharArray('X', 'O', '.');
        this.activeBoardIndex = ultimateTicTacToe.getActiveBoardIndex();
        this.size = ultimateTicTacToe.getSize();
        this.hashCode = countHash();
    }

    private int countHash() {
        int hash = 3;
        hash = 59 * hash + Arrays.deepHashCode(this.board);
        hash = 59 * hash + this.activeBoardIndex;
        return hash;
    }
    
    @Override
    public int hashCode() { return hashCode; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GameState other = (GameState) obj;
        if (this.activeBoardIndex != other.activeBoardIndex) {
            return false;
        }
        if (!Arrays.deepEquals(this.board, other.board)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                sb.append(board[y][x]);
                if (x < board[y].length - 1 && x % size == 2) { sb.append(' '); }
            }
            if (y < board.length - 1) {
                sb.append('\n');
                if (y % size == 2) { sb.append('\n'); }
            }
        }
        return sb.toString();
    }
    
}
