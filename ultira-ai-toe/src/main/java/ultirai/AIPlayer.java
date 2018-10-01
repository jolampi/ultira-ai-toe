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
    private final Dictionary<GameState, GameStateData> data;
    private final List<Turn> moveHistory;
    private boolean debug;

    public AIPlayer(Random random) {
        this.random = random;
        this.data = new Dictionary<>();
        this.moveHistory = new List<>();
        this.debug = false;
    }
    
    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    @Override
    public int move(GameState gameState) {
        if (!data.hasKey(gameState)) {
            data.set(gameState, new GameStateData(gameState));
        }
        int move = data.get(gameState).randomMove();
        moveHistory.add(new Turn(gameState, move));
        return move;
    }

    @Override
    public void end(Mark winner) {
        for (int i = 0; i < moveHistory.size(); i++) {
            Turn turn = moveHistory.get(i);
            data.get(turn.gameState).update(turn.move, turn.gameState.getTurn() == winner);
        }
        moveHistory.clear();
    }
        
        
        
    private class GameStateData {
        private final Dictionary<Integer, Entry> moves;
        
        public GameStateData(GameState gameState) {
            this.moves = new Dictionary<>();
            List<Integer> validMoves = gameState.getValidMoves();
            for (int i = 0; i < validMoves.size(); i++) {
                moves.set(i, new Entry());
            }
        }
        
        public int randomMove() {
            List<Integer> list = moves.keys();
            int move = list.get(random.nextInt(list.size()));
            if (debug) { debugPrint(list, move); }
            return move;
        }
        
        public void update(int move, boolean win) {
            Entry entry = moves.get(move);
            if (win) { entry.wins++; }
            entry.tries++;
        }
        
        private void debugPrint(List<Integer> keys, int move) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < keys.size(); i++) {
                int key = keys.get(i);
                if (key == move) { sb.append('['); }
                sb.append(key+1).append(':').append(moves.get(key).wins).append('/').append(moves.get(key).tries);
                if (key == move) { sb.append(']'); }
                if (i + 1 < keys.size()) { sb.append(", "); }
            }
            System.out.println(sb.toString());
        }
        
    }
    
    private class Entry {
        public int wins;
        public int tries;
        
        public Entry() {
            this.wins = 0;
            this.tries = 0;
        }
    }
    
    private class Turn {
        public GameState gameState;
        public int move;

        public Turn(GameState gameState, int move) {
            this.gameState = gameState;
            this.move = move;
        }
    }
    
}
