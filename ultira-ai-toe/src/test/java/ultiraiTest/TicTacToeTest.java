/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultiraiTest;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ultirai.Mark;
import ultirai.TicTacToe;

/**
 *
 * @author Jori Lampi
 */
public class TicTacToeTest {
    
    public TicTacToeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void WinningEvaluationDefault() {
        TicTacToe ttt = new TicTacToe();
        int size = ttt.getSize();
        Assert.assertEquals("Start of game", Mark.NONE, ttt.evaluate());
        
        // Test rows
        for (int y = 1; y <= size; y++) {
            for (int x = 1; x <= size; x++) {
                ttt.set(toIndex(x, y, size), Mark.CROSS);
            }
            Assert.assertEquals("Row " + y + "winner", Mark.CROSS, ttt.evaluate());
            ttt.clearBoard();
        }
        
        // Test columns
        for (int x = 1; x <= size; x++) {
            for (int y = 1; y <= size; y++) {
                ttt.set(toIndex(x, y, size), Mark.NOUGHT);
            }
            Assert.assertEquals("Vertical column" + x + " winner", Mark.NOUGHT, ttt.evaluate());
            ttt.clearBoard();
        }
        
        // Test diagonal
        
        for (int i = 1; i <= size; i++) {
            ttt.set(toIndex(i, i, size), Mark.CROSS);
        }
        Assert.assertEquals("Diagonal row \\ winner", Mark.CROSS, ttt.evaluate());
        ttt.clearBoard();
        
        for (int i = 1; i <= size; i++) {
            ttt.set(toIndex(i, size+1-i, size), Mark.NOUGHT);
        }
        Assert.assertEquals("Diagonal row / winner", Mark.NOUGHT, ttt.evaluate());
        ttt.clearBoard();
    }
    
    private int toIndex(int x, int y, int size) {
            return (y-1) * size + x;
    }
    
}
