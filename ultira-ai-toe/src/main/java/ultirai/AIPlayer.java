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
    private final List<GameState> moveHistory;

    public AIPlayer(Random random) {
        this.random = random;
        this.data = new Dictionary();
        this.moveHistory = new List<>();
    }

    @Override
    public int move(GameState gameState) {
        if (!data.hasKey(gameState)) {
            data.set(gameState, new Entry(gameState));
        }
        moveHistory.add(gameState);
        return data.get(gameState).randomMove();
    }

    @Override
    public void win() {
    }

    @Override
    public void lose() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private class Entry {
        
        private final List<Integer> validMoves;
        private final int[] wins;
        private final int[] tries;
        
        public Entry(GameState gameState) {
            this.validMoves = gameState.getValidMoves();
            this.wins = new int[validMoves.size()];
            this.tries = new int[validMoves.size()];
        }
        
        public int randomMove() {
            int move = validMoves.get(random.nextInt(validMoves.size()));
            debugPrint(move);
            return move;
        }
        
        private void debugPrint(int move) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < validMoves.size(); i++) {
                if (validMoves.get(i) == move) {
                    sb.append('[').append(wins[i]).append('/').append(tries[i]).append(']');
                } else {
                    sb.append(wins[i]).append('/').append(tries[i]);
                }
                if (i + 1 < validMoves.size()) { sb.append(' '); }
            }
            System.out.println(sb.toString());
        }
    }
    
}
