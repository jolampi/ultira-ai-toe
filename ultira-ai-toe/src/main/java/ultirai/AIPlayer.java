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
    
    private final static int MAX_LEARN_DEPTH = 7;
    
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
        int n = validMoves.getSize();
        GameStateData[] gsds = new GameStateData[n];
        int playsTotal = 0;
        int dataFound = 0;
        for (int i = 0; i < n; i++) {
            GameState next = gameState.next(validMoves.get(i));
            if (data.hasKey(next)) {
                GameStateData gsd = data.get(next);
                gsds[i] = gsd;
                playsTotal += gsd.games;
                dataFound++;
            }
        }
        if (dataFound != n) { return validMoves.get(random.nextInt(n)); }
        double twoLog = 2 * Math.log(playsTotal);
        int bestIndex = 0;
        boolean maximize = (gameState.getTurn() == gameState.next(validMoves.get(0)).getTurn());
        double bestScore = 0;
        for (int i = 0; i < n; i++) {
            GameStateData gsd = gsds[i];
            int wins = (maximize) ? gsd.wins : gsd.games - gsd.wins;
            double score = wins * 1.0 / gsd.games + Math.sqrt(twoLog / gsd.games);
            if (score > bestScore) {
                bestIndex = i;
                bestScore = score;
            }
        }
        //if (!training) { System.out.print("w" + gsds[bestIndex].wins + " g" + gsds[bestIndex].games + " s" + bestScore + " "); }
        return validMoves.get(bestIndex);
        
        /*
        float[] scores = new float[validMoves.getSize()];
        float scoreSum = 0.0f;
        for (int i = 0; i < scores.length; i++) {
            GameState next = gameState.next(validMoves.get(i));
            float score = 0.1f;
            if (data.hasKey(next)) {
                GameStateData gsd = data.get(next);
                score+= gsd.wins / gsd.games;
            }
            scores[i] = score;
            scoreSum += score;
        }
        float randomValue = random.nextFloat() * scoreSum;
        for (int i = 0; i < scores.length; i++) {
            float score = scores[i];
            if (randomValue < score) {
                return validMoves.get(i);
            } else {
                randomValue -= score;
            }
        }
        // Never gets here
        return validMoves.get(0);
*/
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
        
        public GameStateData() {
            this.wins = 0;
            this.games = 0;
        }
        
        public void increment(boolean win) {
            if (win) { wins++; }
            games++;
        }
        
    }
    
}
