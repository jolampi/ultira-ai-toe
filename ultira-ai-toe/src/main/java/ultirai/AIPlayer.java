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
    
    private final static int MAX_LEARN_DEPTH = 6;
    
    private final Random random;
    private final Dictionary<GameState, GameStateData> data;
    private boolean training;

    public AIPlayer(Random random) {
        this.random = random;
        this.data = new Dictionary<>();
        this.training = false;
    }

    @Override
    public int move(GameState gameState) {
        if (!training) {
            training = true;
            Game.trainAI(this, gameState, 1000);
            training = false;
        }
        List<Integer> validMoves = gameState.getValidMoves();
        float[] scores = new float[validMoves.getSize()];
        float scoreSum = 0.0f;
        for (int i = 0; i < scores.length; i++) {
            GameState next = gameState.next(validMoves.get(i));
            float score = data.hasKey(next) ? data.get(next).getScore() : GameStateData.DEFAULT_SCORE;
            scores[i] = score;
            scoreSum += score;
        }
        float randomValue = random.nextFloat();
        for (int i = 0; i < scores.length; i++) {
            float weightedScore = scores[i] / scoreSum;
            if (randomValue < weightedScore) {
                return validMoves.get(i);
            } else {
                randomValue -= weightedScore;
            }
        }
        // Never gets here
        return validMoves.get(0);
    }

    public void learn(Mark winner, List<GameState> moves) {
        for (int i = 0; i < Math.min(moves.getSize(), MAX_LEARN_DEPTH); i++) {
            GameState gs = moves.get(i);
            if (!data.hasKey(gs)) {
                data.set(gs, new GameStateData());
            }
            data.get(gs).increment(winner == gs.getTurn());
        }
    }
        
        
        
    private class GameStateData {
        
        public static final float DEFAULT_SCORE = 0.5f;
        
        private int wins;
        private int games;
;        
        public GameStateData() {
            this.wins = 0;
            this.games = 0;
        }
        
        public void increment(boolean win) {
            if (win) { wins++; }
            games++;
        }
        
        public float getScore() {
            return (3f + wins) / (3f + games);
        }
        
    }
    
}
