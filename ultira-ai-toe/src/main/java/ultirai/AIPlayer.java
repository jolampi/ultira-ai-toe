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

    public AIPlayer(Random random) {
        this.random = random;
        this.data = new Dictionary();
    }

    @Override
    public int move(GameState gameState) {
        if (!data.hasKey(gameState)) {
            data.set(gameState, new Entry(gameState));
        }
        return data.get(gameState).randomMove();
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
            return validMoves.get(random.nextInt(validMoves.size()));
        }
    }
    
}
