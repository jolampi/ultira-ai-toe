/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultirai;

import java.util.Scanner;

/**
 * An implementation of Player interface that uses a Scanner to choose moves.
 * 
 * @author Jori Lampi
 */
public class HumanPlayer implements Player {
    
    private final Scanner scanner;

    /**
     * Creates a new instance of HumanPlayer.
     * 
     * @param scanner the scanner being used with this instance
     */
    public HumanPlayer(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public int move(GameState gameState) {
        while (scanner.hasNextLine()) {
            try {
                int move = Integer.parseInt(scanner.nextLine().trim()) - 1;
                if (gameState.isValidMove(move)) { return move; }
            } catch (NumberFormatException e) {}
            System.out.println("Illegal move.");
        }
        throw new IllegalStateException("Scanner is empty.");
    }
    
}
