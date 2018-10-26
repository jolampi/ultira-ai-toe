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
 * Implementation of Player interface that uses a Monte Carlo tree search to
 * play moves.
 * 
 * @author Jori Lampi
 */
public class AIPlayer implements Player {
    
    // The max depth of saved gamestates when simulating
    private final static int MAX_LEARN_DEPTH = 7;
    
    private final Random random;
    private final Dictionary<GameState, GameStateData> data;
    private final int timer;
    private boolean training;
    
    /**
     * Creates a new instance of AIPlayer with a given simulation timer using a
     * new Random class created without a seed.
     * 
     * @param timer the simulation timer
     */
    public AIPlayer(int timer) {
        this(new Random(), timer);
    }
    
    /**
     * Creates a new instance of AIPlayer that uses given random object and
     * the given simulation timer.
     * 
     * @param random random object being used
     * @param timer  the simulation timer
     */
    public AIPlayer(Random random, int timer) {
        this.random = random;
        this.data = new Dictionary<>();
        this.timer = timer;
        this.training = false;
    }

    @Override
    public int move(GameState gameState) {
        // Simulate if called from the actual game
        if (!training) {
            training = true;
            Game.trainAI(this, gameState, timer);
            training = false;
        }
        // Gather known data from the given gamestate
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
        // Ensure that we have played every choice at least once
        // If not, play a random move
        if (dataFound != n) { return validMoves.get(random.nextInt(n)); }
        // Give a score for every move based on success data and play the best one
        double twoLog = 2 * Math.log(playsTotal);
        int bestIndex = 0;
        boolean maximize = (gameState.getTurn() == gameState.next(validMoves.get(0)).getTurn());
        double bestScore = 0;
        for (int i = 0; i < n; i++) {
            GameStateData gsd = gsds[i];
            // If the opponent plays next, play the move that led to most defeats
            int wins = (maximize) ? gsd.wins : gsd.games - gsd.wins;
            // The score formula
            double score = wins * 1.0 / gsd.games + Math.sqrt(twoLog / gsd.games);
            if (score > bestScore) {
                bestIndex = i;
                bestScore = score;
            }
        }
        return validMoves.get(bestIndex);
    }

    /**
     * Saves the data from a past game with given information.
     * 
     * @param winner the winner of the game
     * @param moves  list of moves played in order from first to last
     */
    public void learn(Mark winner, List<GameState> moves) {
        for (int i = 0; i < Math.min(moves.getSize(), MAX_LEARN_DEPTH); i++) {
            GameState gs = moves.get(i);
            if (!data.hasKey(gs)) {
                data.set(gs, new GameStateData());
            }
            data.get(gs).increment(winner == gs.getTurn());
        }
    }
          
    // inner class used to store gamestate specific data
    private class GameStateData {
        
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
