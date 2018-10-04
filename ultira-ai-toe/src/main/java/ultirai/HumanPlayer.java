/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultirai;

import java.util.Scanner;
import structure.List;

/**
 *
 * @author Jori Lampi
 */
public class HumanPlayer implements Player {
    
    private final Scanner scanner;

    public HumanPlayer(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public int move(GameState gameState) {
        System.out.println(gameState.toString());
        while (scanner.hasNextLine()) {
            try {
                return Integer.parseInt(scanner.nextLine().trim()) - 1;
            } catch (NumberFormatException e) {
                System.out.print("Illegal move. ");
            }
        }
        throw new IllegalStateException();
    }

    @Override
    public void end(Mark winner, List<GameState> moves) {
        System.out.println(winner.toString() + " wins.");
    }
    
}
