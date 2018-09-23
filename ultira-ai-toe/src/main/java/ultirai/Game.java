/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultirai;

/**
 *
 * @author Jori Lampi
 */
public class Game {
    
    private final UltimateTicTacToe game;
    private final Player player1;
    private final Player player2;
    
    public Game(Player player1, Player player2) {
        this.game = new UltimateTicTacToe();
        this.player1 = player1;
        this.player2 = player2;
    }
    
    public void play() {
        boolean turn = true;
        do {
            Player player = (turn) ? player1 : player2;
            int move = player.move(new GameState(game));
            // Next turn only if a mark was added to the board
            turn = (game.set(move, (turn) ? Mark.CROSS : Mark.NOUGHT)) ? !turn : turn;
        } while (game.evaluate() == Mark.NONE);
    }
    
}
