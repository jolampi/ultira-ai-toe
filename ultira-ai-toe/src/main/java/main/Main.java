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
import ultirai.GameState;
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
//        Game.trainAI(ai, new GameState(3), 10000);
        Game.play(3, ai, human);
    }
    
}
