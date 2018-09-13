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
    
    private final UltimateTicTacToe board;
    private final Player player1;
    private final Player player2;
    
    public Game(Player player1, Player player2) {
        this.board = new UltimateTicTacToe();
        this.player1 = player1;
        this.player2 = player2;
    }
    
    public void play() {
        while (true) {
            int move = player1.move(board.toString());
            board.set(move, Mark.CROSS);
        }
    }
    
}
