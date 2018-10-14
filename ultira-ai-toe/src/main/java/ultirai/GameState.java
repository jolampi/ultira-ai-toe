/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultirai;

import java.util.Objects;
import structure.List;

/**
 *
 * @author Jori Lampi
 */
final public class GameState {
    
    private final SuperBoard board;
    private final int activeX;
    private final int activeY;
    private final Mark turn;
    
    public GameState(int size) {
        this(new SuperBoard(size), Mark.CROSS, -1, -1);
    }
    
    private GameState(SuperBoard board, Mark turn, int activeX, int activeY) {
        this.board = board;
        this.activeX = activeX;
        this.activeY = activeY;
        this.turn = turn;
    }
    
    public Mark getTurn() {
        return turn;
    }
    
    public boolean isBoardActive() {
        return activeX >= 0;
    }
    
    public boolean isValidMove(int move) {
        int size = board.getSize();
        return isValidMove(move%size, move/size);
    }
    
    private boolean isValidMove(int x, int y) {
        return getActiveBoard().getMarkAt(x, y) == Mark.NONE;
    }
    
    public boolean hasValidMoves() {
        int size = board.getSize();
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                if (isValidMove(x, y)) { return true; }
            }
        }
        return false;
    }
    
    public Board getActiveBoard() {
        return (isBoardActive()) ? board.getSubBoardAt(activeX, activeY) : board.getBoard();
    }
    
    public List<Integer> getValidMoves() {
        int size = board.getSize();
        List<Integer> moves = new List<>();
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                if (isValidMove(x, y)) { moves.add(y*size+x); }
            }
        }
        return moves;
    }
    
    public Mark resolve() {
        return board.resolve();
    }
    
    public GameState next(int move) {
        int size = board.getSize();
        if (move < 0 || move >= size * size) { throw new IllegalArgumentException("Move out of bounds."); }
        int x = move % board.getSize();
        int y = move / board.getSize();
        if (!isValidMove(x, y)) { throw new IllegalStateException("Illegal move."); }
        if (!isBoardActive()) {
            return new GameState(board, turn, x, y);
        }
        SuperBoard nextBoard = board.next(turn, activeX, activeY, x, y);
        Mark nextTurn = (turn == Mark.NOUGHT) ? Mark.CROSS : Mark.NOUGHT;
        if (nextBoard.getBoard().getMarkAt(x, y) == Mark.NONE) {
            return new GameState(nextBoard, nextTurn, x, y);
        }
        return new GameState(nextBoard, nextTurn, -1, -1);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + board.hashCode();
        hash = 29 * hash + this.activeX;
        hash = 29 * hash + this.activeY;
        hash = 29 * hash + turn.hashCode();
        return hash;
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
        if (this.activeX != other.activeX) {
            return false;
        }
        if (this.activeY != other.activeY) {
            return false;
        }
        if (!Objects.equals(this.board, other.board)) {
            return false;
        }
        return this.turn == other.turn;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        char[][] chars = board.toCharArray('X', 'O', '.');
        int breakpoint = board.getSize();
        for (int y = 0; y < chars.length;) {
            for (int x = 0; x < chars[y].length;) {
                sb.append(chars[y][x]);
                if ((++x % breakpoint) == 0) { sb.append("  "); }
            }
            if (++y < chars.length) {
                sb.append('\n');
                if (y % breakpoint == 0) { sb.append('\n'); }
            }
        }
        return sb.toString();
    }
    
}
