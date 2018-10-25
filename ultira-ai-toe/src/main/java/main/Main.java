/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import ultirai.AIPlayer;
import ultirai.Game;
import ultirai.Player;
import ultirai.HumanPlayer;

/**
 *
 * @author Jori Lampi
 */
public class Main {
    
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        boolean demo = false;
        boolean benchmark = false;
        int threads = 2;
        int size = 3;
        int timer = 1000;
        for (String arg : args) {
            if (arg.matches("-demo")) { demo = true; }
            if (arg.matches("-size=\\d+")) { size = Math.max(3, Math.min(Integer.parseInt(arg.split("=")[1]), 6)); }
            if (arg.matches("-benchmark")) { benchmark = true; }
            if (arg.matches("-threads=\\d+")) { threads = Math.max(1, Math.min(Integer.parseInt(arg.split("=")[1]), 8)); }
            if (arg.matches("-timer=\\d+")) { timer = Math.max(0, Integer.parseInt(arg.split("=")[1])); }
        }
        
        Player human = new HumanPlayer(new Scanner(System.in));
        Random random = new Random();
        AIPlayer ai = new AIPlayer(random, timer);
        
        if (benchmark) {
            Game.benchMark(size, threads, timer);
        } else if (demo) {
            Game.play(size, ai, ai);
        } else if (random.nextBoolean()) {
            Game.play(size, human, ai);
        } else {
            Game.play(size, ai, human);
        }
    }
    
}
