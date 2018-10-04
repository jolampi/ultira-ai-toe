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
public class ScannerPlayer implements Player {
    
    private final Scanner scanner;

    public ScannerPlayer(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public int move(GameState gameState) {
        System.out.println(gameState.toString());
        return scanner.nextInt()-1;
    }

    @Override
    public void end(Mark winner, List<GameState> moves) {
        System.out.println(winner.toString() + " wins.");
    }
    
}
