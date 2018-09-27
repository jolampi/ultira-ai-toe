/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.Random;
import java.util.Scanner;
import ultirai.AIPlayer;
import ultirai.Game;
import ultirai.Player;
import ultirai.ScannerPlayer;

/**
 *
 * @author Jori Lampi
 */
public class Main {
    
    public static void main(String[] args) {
        
        Player player = new ScannerPlayer(new Scanner(System.in));
        AIPlayer ai = new AIPlayer(new Random());
        ai.setTraining(true);
        Game game = new Game(ai, ai);
        for (int i = 0; i < 10000; i++) {
            game.play();
        }
        ai.setTraining(false);
        new Game(ai, player).play();
    }
    
}
