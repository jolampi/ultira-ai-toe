/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultirai;

import java.text.DecimalFormat;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import structure.List;

/**
 *
 * @author Jori Lampi
 */
public class Game {
    
    private final static DecimalFormat FORMAT = new DecimalFormat("#.000");
    
    /**
     * Makes the AI play against itself 100 times and reports results. No game
     * data is shared between any of the AI instances so the games don't have
     * effect on each other.
     * <p>
     * The amount of threads should not exceed the amount of available CPU cores
     * as this would reduce the amount of simulation each state of the game
     * gets.
     * 
     * @param size    the size of the game
     * @param threads how many games are being played simultaneously
     * @param timer   how long the AI is allowed to simulate the game per turn
     * @throws InterruptedException concurrency exception
     * @throws ExecutionException   concurrency exception
     */
    public static void benchMark(int size, int threads, int timer) throws InterruptedException, ExecutionException {
        System.out.println("Benchmarking AI performance on " + threads + " threads. size=" + size + ", timer=" + timer);
        ExecutorService es = Executors.newFixedThreadPool(threads);
        final Callable<GameResult> task = () -> play(new GameState(size), new AIPlayer(timer), new AIPlayer(timer), false);
        List<Future<GameResult>> list = new List<>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            list.add(es.submit(task));
        }
        int cross = 0;
        int nought = 0;
        int tie = 0;
        for (int i = 0; i < list.getSize(); i++) {
            switch (list.get(i).get().winner) {
                case CROSS: cross++; break;
                case NOUGHT: nought++; break;
                default: tie++;
            }
            double averageTime = (System.currentTimeMillis() - start) * threads * 0.001 / (i + 1);
            
            System.out.print("\raverage game=" + FORMAT.format(averageTime) + "s; winX=" + cross + ", winO=" + nought + ", tied=" + tie + "   ");
        }
        System.out.println("");
        es.shutdown();
    }
    
    /**
     * Trains the given AI from the given gamestate for the duration of the
     * timer. Usually called by the AI itself.
     * 
     * @param ai         the ai instance being trained
     * @param startState the gamestate where all simulations start from
     * @param timer      the amount of time allowed for training
     */
    public static void trainAI(AIPlayer ai, GameState startState, long timer) {
        long startTime = System.currentTimeMillis(), totalTime;
        //int simulations = 0;
        //System.out.print("Simulating...");
        while ((totalTime = System.currentTimeMillis() - startTime) < timer) {
            GameResult gr = play(startState, ai, ai, false);
            ai.learn(gr.winner, gr.history);
            //simulations++;
        }
        //System.out.println("\rRan " + simulations + " simulations from current state (" + totalTime + "ms)");
    }
    
    /**
     * Plays a game of given size.
     * 
     * @param size   size of the game
     * @param first  player going first
     * @param second player going second
     */
    public static void play(int size, Player first, Player second) {
        play(new GameState(size), first, second, true);
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
    
    // Inner class used to pass game results
    final private static class GameResult {
        private final List<GameState> history;
        private final Mark winner;

        public GameResult(List<GameState> history, Mark winner) {
            this.history = history;
            this.winner = winner;
        }
        
    }
    
}
