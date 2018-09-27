/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultirai;

import java.util.Arrays;
import java.util.Objects;
import structure.List;

/**
 *
 * @author Jori Lampi
 */
public class GameState {
    
    private final char[][] board;
    private final int activeBoardIndex;
    private final Mark turn;
    private final Integer[] validMoves;
    private final int hashCode;
    
    public GameState(UltimateTicTacToe ultimateTicTacToe, Mark turn) {
        this.board = ultimateTicTacToe.toCharArray();
        this.activeBoardIndex = ultimateTicTacToe.getActiveBoardIndex();
        this.turn = turn;
        this.validMoves = findValidMoves(ultimateTicTacToe);
        this.hashCode = countHash();
    }
    
    private Integer[] findValidMoves(UltimateTicTacToe uttt) {
        List<Integer> moves = new List<>();
        for (int i = 1; i <= board.length; i++) {
            if (uttt.isValidMove(i)) { moves.add(i); }
        }
        return moves.toArray(new Integer[moves.size()]);
    }
    
    private int countHash() {
        int hash = 7;
        hash = 79 * hash + Arrays.deepHashCode(this.board);
        hash = 79 * hash + this.activeBoardIndex;
        hash = 79 * hash + Objects.hashCode(this.turn);
        return hash;
    }
    
    public Mark getTurn() {
        return turn;
    }
    
    public boolean isValidMoves() {
        return validMoves.length > 0;
    }
    
    public Integer[] getValidMoves() {
        return Arrays.copyOf(validMoves, validMoves.length);
    }

    @Override
    public int hashCode() {
        return hashCode;
    }

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
        if (this.turn != other.turn) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        int size = (int) Math.sqrt(board.length);
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
