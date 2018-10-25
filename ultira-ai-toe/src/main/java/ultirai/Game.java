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
    
    public static void benchMark(int size, int threads, int traintime) throws InterruptedException, ExecutionException {
        System.out.println("Benchmarking AI performance on " + threads + " threads. size=" + size + ", traintime=" + traintime);
        ExecutorService es = Executors.newFixedThreadPool(threads);
        final Callable<GameResult> task = () -> play(new GameState(size), new AIPlayer(traintime), new AIPlayer(traintime), false);
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
            
            System.out.print("\raverage game=" + FORMAT.format(averageTime) + "s; winX=" + cross + ", winO=" + nought + ", tied=" + tie);
        }
        System.out.println("");
        es.shutdown();
    }
    
    public static void trainAI(AIPlayer ai, GameState startState, long timeLimit) {
        long startTime = System.currentTimeMillis(), totalTime;
        int simulations = 0;
        //System.out.print("Simulating...");
        while ((totalTime = System.currentTimeMillis() - startTime) < timeLimit) {
            GameResult gr = play(startState, ai, ai, false);
            ai.learn(gr.winner, gr.history);
            simulations++;
        }
        //System.out.println("\rRan " + simulations + " simulations from current state (" + totalTime + "ms)");
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
