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
    
    public static void play(int size, Player a, Player b) {
        List<GameState> moveHistory = new List<>();
        GameState gameState = new GameState(size);
        Mark winner = Mark.NONE;
        while (winner == Mark.NONE && gameState.hasValidMoves()) {
            int move = (gameState.getTurn() == Mark.CROSS) ? a.move(gameState) : b.move(gameState);
            if (gameState.isValidMove(move)) {
                moveHistory.add(gameState);
                gameState = gameState.next(move);
                winner = gameState.resolve();
            }
        }
        a.end(winner, moveHistory);
        b.end(winner, moveHistory);
    }
    
}
