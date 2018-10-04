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
import ultirai.HumanPlayer;

/**
 *
 * @author Jori Lampi
 */
public class Main {
    
    public static void main(String[] args) {
        
        Player human = new HumanPlayer(new Scanner(System.in));
        AIPlayer ai = new AIPlayer(new Random());
        for (int i = 0; i < 100000; i++) {
            Game.play(3, ai, ai);
        }
        ai.setDebug(true);
        Game.play(3, ai, human);
    }
    
}
