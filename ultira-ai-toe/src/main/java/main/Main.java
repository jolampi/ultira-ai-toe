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
        boolean demo = false;
        int size = 3;
        for (String arg : args) {
            if (arg.matches("\\-demo")) { demo = true; }
            if (arg.matches("\\-size=\\d")) { size = Math.max(3, Math.min(Integer.parseInt(arg.split("=")[1]), 6)); }
        }
        
        Player human = new HumanPlayer(new Scanner(System.in));
        Random random = new Random();
        AIPlayer ai = new AIPlayer(random);
        
        if (demo) {
            Game.play(size, ai, ai);
        } else if (random.nextBoolean()) {
            Game.play(size, human, ai);
        } else {
            Game.play(size, ai, human);
        }
    }
    
}
