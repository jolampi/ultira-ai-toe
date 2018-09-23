/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultirai;

import java.util.Arrays;
import structure.List;

/**
 *
 * @author Jori Lampi
 */
public class GameState {
    
    private final char[][] board;
    private final int activeBoardIndex;
    private final int size;
    private final int hashCode;
    private final List<Integer> validMoves;
    
    public GameState(UltimateTicTacToe ultimateTicTacToe) {
        this.board = ultimateTicTacToe.toCharArray();
        this.activeBoardIndex = ultimateTicTacToe.getActiveBoardIndex();
        this.size = ultimateTicTacToe.getSize();
        this.hashCode = countHash();
        this.validMoves = findValidMoves(ultimateTicTacToe);
    }
    
    private List<Integer> findValidMoves(UltimateTicTacToe uttt) {
        List<Integer> moves = new List<>();
        for (int i = 1; i <= size*size; i++) {
            if (uttt.isValidMove(i)) { moves.add(i); }
        }
        return moves;
    }

    private int countHash() {
        int hash = 3;
        hash = 59 * hash + Arrays.deepHashCode(this.board);
        hash = 59 * hash + this.activeBoardIndex;
        return hash;
    }
    
    public List<Integer> getValidMoves() {
        return validMoves;
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
                if (x < board[y].length - 1 && x % size == 2) { sb.append('|'); }
            }
            if (y < board.length - 1) {
                sb.append('\n');
                if (y % size == 2) { sb.append("---+---+---\n"); }
            }
        }
        return sb.toString();
    }
    
}
