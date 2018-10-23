/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultirai;

import structure.List;

/**
 *
 * @author Jori Lampi
 */
public class Game {
    
    public static void trainAI(AIPlayer ai, GameState startState, long timeLimit) {
        long startTime = System.currentTimeMillis(), totalTime;
        int simulations = 0;
        System.out.print("Simulating...");
        do {
            GameResult gr = play(startState, ai, ai, false);
            ai.learn(gr.winner, gr.history);
            simulations++;
        } while ((totalTime = System.currentTimeMillis() - startTime) < timeLimit);
        System.out.println("\rRan " + simulations + " simulations from current state (" + totalTime + "ms)");
    }
    
    public static void play(int size, Player cross, Player nought) {
        play(new GameState(size), cross, nought, true);
    }
    
    private static GameResult play(GameState gameState, Player cross, Player nought, boolean render) {
        List<GameState> moveHistory = new List<>();
        Mark winner = Mark.NONE;
        if (render) { System.out.println(gameState); }
        while (winner == Mark.NONE && gameState.hasValidMoves()) {
            int move = (gameState.getTurn() == Mark.CROSS) ? cross.move(gameState) : nought.move(gameState);
            if (gameState.isValidMove(move)) {
                if (render) { System.out.println(gameState.getTurn() + ": " + (move+1)); }
                moveHistory.add(gameState);
                gameState = gameState.next(move);
                if (render) { System.out.println(gameState); }
                winner = gameState.resolve();
            }
        }
        if (render) {
            System.out.println(winner + " won.");
        }
        GameResult gr = new GameResult(moveHistory, winner);
        return gr;
    }
    
    final private static class GameResult {
        private final List<GameState> history;
        private final Mark winner;

        public GameResult(List<GameState> history, Mark winner) {
            this.history = history;
            this.winner = winner;
        }
        
    }
    
}
