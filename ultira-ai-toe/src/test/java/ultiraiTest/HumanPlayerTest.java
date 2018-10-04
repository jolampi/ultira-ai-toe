/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultiraiTest;

import java.io.ByteArrayInputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;
import org.junit.Assert;
import org.junit.Test;
import ultirai.GameState;
import ultirai.HumanPlayer;
import ultirai.Player;

/**
 *
 * @author Jori Lampi
 */
public class HumanPlayerTest {
    
    @Test
    public void humanPlayerTest() {
        final GameState gs = new GameState(3);
        final Player human;
        String input;
        
        // Input sanitazion
        input = "3asd6\n";
        human = new HumanPlayer(new Scanner(input));
        try {
            int x = human.move(gs);
            Assert.fail('"' + input + '"' + " should be invalid input but returned " + x);
        } catch (Exception e) {
            if (e instanceof NoSuchElementException) { Assert.fail("Having no input left should throw IllegalStateException."); }
            if (e instanceof NumberFormatException) { Assert.fail("NumberFormatExceptions should be handled."); }
            if (false == e instanceof IllegalStateException) { Assert.fail(e.toString()); }
        }
    }
    
    @Test
    public void inputParseTest() {
        final GameState gs = new GameState(3);
        String input = "";
        Player human;
        
        // Simple parse tests
        try {
            input = "5";
            Assert.assertEquals("Wrong result.", 4, new HumanPlayer(new Scanner(input)).move(gs));
            input = "  6 ";
            Assert.assertEquals("Wrong result.", 5, new HumanPlayer(new Scanner(input)).move(gs));
        } catch (IllegalStateException e) {
            Assert.fail("Input \"" + input + "\" caused IllegalStateException.");
        }
        
        input = "1 \n 2 13 \n 4 \n";
        human = new HumanPlayer(new Scanner(input));
        for (int i : new int[] {0, 3}) {
            try {
                Assert.assertEquals("Wrong result.", i, human.move(gs));
            } catch (Exception e) {
                if (e instanceof IllegalStateException) { Assert.fail("Caught unexpected IllegalStateException. Make sure to handle ALL input."); }
                Assert.fail(e.toString());
            }
        }
    }
    
}
