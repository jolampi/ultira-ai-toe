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
        Mark winner = Mark.NONE;
        boolean turn = true;
        do {
            Mark mark = (turn) ? Mark.CROSS : Mark.NOUGHT;
            GameState gs = new GameState(game, mark);
            if (!gs.isValidMoves()) { break; }
            int move = (turn) ? player1.move(gs) : player2.move(gs);
            if (game.getActiveBoardIndex() == 0) {
                game.setActiveBoard(move);
            } else {
                boolean success = game.set(move, mark);
                if (success) {
                    game.setActiveBoard((game.get(move) == Mark.NONE) ? move : 0);
                    turn = !turn;
                }
            }
        } while ((winner = game.evaluate()) == Mark.NONE);
        player1.end(winner);
        player2.end(winner);
        game.clearBoard();
    }
    
}
