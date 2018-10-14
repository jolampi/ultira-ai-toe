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
            List<GameState> playout = play(startState, ai, ai, false);
            ai.learn(playout.get(playout.getSize()-1).resolve(), playout);
            simulations++;
        } while ((totalTime = System.currentTimeMillis() - startTime) < timeLimit);
        System.out.println("\rRan " + simulations + " simulations from current state (" + totalTime / 1000d + " seconds)");
    }
    
    public static void play(int size, Player cross, Player nought) {
        play(new GameState(size), cross, nought, true);
    }
    
    private static List<GameState> play(GameState gameState, Player cross, Player nought, boolean render) {
        List<GameState> moveHistory = new List<>();
        Mark winner = Mark.NONE;
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
        return moveHistory;
    }
    
}
