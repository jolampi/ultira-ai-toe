/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultirai;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Jori Lampi
 */
public class Main {
    
    public static void main(String[] args) {
        
        Player player = new ScannerPlayer(new Scanner(System.in));
        Player ai = new AIPlayer(new Random());
        Game game = new Game(player, ai);
        game.play();
    }
    
}
