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
import static org.junit.Assert.*;
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void NoWinnerAtStart() {
        TicTacToe ttt = new TicTacToe();
        Assert.assertEquals(ttt.evaluate(), Mark.NONE);
    }
    
    @Test
    public void WinningEvaluationWorks() {
        TicTacToe ttt = new TicTacToe();
        Assert.assertEquals(ttt.evaluate(), Mark.NONE);
        for (int i = 0; i < ttt.getSize(); i++) {
            ttt.set(i, 0, Mark.CROSS);
        }
        Assert.assertEquals(ttt.evaluate(), Mark.CROSS);
        for (int i = 0; i < ttt.getSize(); i++) {
            ttt.set(0, i, Mark.NOUGHT);
        }
        Assert.assertEquals(ttt.evaluate(), Mark.NOUGHT);
        for (int i = 0; i < ttt.getSize(); i++) {
            ttt.set(i, i, Mark.CROSS);
        }
        Assert.assertEquals(ttt.evaluate(), Mark.CROSS);
        for (int i = 0; i < ttt.getSize(); i++) {
            ttt.set(ttt.getSize()-1-i, i, Mark.NOUGHT);
        }
        Assert.assertEquals(ttt.evaluate(), Mark.NOUGHT);
    }
    
}
