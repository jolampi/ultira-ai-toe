/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultirai;

import java.util.Random;
import structure.Dictionary;
import structure.List;

/**
 *
 * @author Jori Lampi
 */
public class AIPlayer implements Player {
    
    private final Random random;
    private final Dictionary<GameState, Entry> data;
    private final List<Move> moveHistory;

    public AIPlayer(Random random) {
        this.random = random;
        this.data = new Dictionary<>();
        this.moveHistory = new List<>();
    }

    @Override
    public int move(GameState gameState) {
        if (!data.hasKey(gameState)) {
            data.set(gameState, new Entry(gameState));
        }
        int move = data.get(gameState).randomMove();
        moveHistory.add(new Move(gameState, move));
        return move;
    }

    @Override
    public void end(Mark winner) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private class Move {
        private final GameState gameState;
        private final int move;

        public Move(GameState gameState, int move) {
            this.gameState = gameState;
            this.move = move;
        }
    }
    
    private class Entry {
        
        private final Integer[] validMoves;
        private final int[] wins;
        private final int[] tries;
        
        public Entry(GameState gameState) {
            this.validMoves = gameState.getValidMoves();
            this.wins = new int[validMoves.length];
            this.tries = new int[validMoves.length];
        }
        
        public int randomMove() {
            int move = validMoves[random.nextInt(validMoves.length)];
            debugPrint(move);
            return move;
        }
        
        private void debugPrint(int move) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < validMoves.length; i++) {
                if (validMoves[i] == move) {
                    sb.append('[').append(validMoves[i]).append(':').append(wins[i]).append('/').append(tries[i]).append(']');
                } else {
                    sb.append(validMoves[i]).append(':').append(wins[i]).append('/').append(tries[i]);
                }
                if (i + 1 < validMoves[i]) { sb.append(", "); }
            }
            System.out.println(sb.toString());
        }
    }
    
}
