/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultiraiTest;

import java.util.Arrays;
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
    
    @Test
    public void WinningEvaluationWorks() {
        TicTacToe ttt = new TicTacToe();
        int size = ttt.getSize();
        Assert.assertEquals("Start of game", Mark.NONE, ttt.evaluate());
        for (int r = 0; r < size; r++) {
            for (int i = 0; i < size; i++) {
                ttt.set(i, r, Mark.CROSS);
            }
            Assert.assertEquals("Horizontal row " + r + " winner", Mark.CROSS, ttt.evaluate());
            for (int i = 0; i < size; i++) {
                ttt.set(r, i, Mark.NOUGHT);
            }
            Assert.assertEquals("Vertical row " + r + " winner", Mark.NOUGHT, ttt.evaluate());
            ttt.clearBoard();
        }
        ttt.clearBoard();
        for (int i = 0; i < size; i++) {
            ttt.set(i, i, Mark.CROSS);
        }
        Assert.assertEquals("Diagonal row \\ winner", Mark.CROSS, ttt.evaluate());
        for (int i = 0; i < size; i++) {
            ttt.set(size-1-i, i, Mark.NOUGHT);
        }
        Assert.assertEquals("Diagonal row / winner", Mark.NOUGHT, ttt.evaluate());
        ttt.clearBoard();
        Assert.assertEquals("Board cleared winner", Mark.NONE, ttt.evaluate());
    }
    
    @Test
    public void CharacterArrayTest() {
        TicTacToe ttt = new TicTacToe();
        ttt.set(0, 0, Mark.CROSS);
        Assert.assertArrayEquals(new char[][] {{'X','.','.'},{'.','.','.'},{'.','.','.'}}, ttt.toCharArray());
        ttt.set(1, 0, Mark.CROSS);
        ttt.set(2, 2, Mark.NOUGHT);
        Assert.assertArrayEquals(new char[][] {{'x','x',' '},{' ',' ',' '},{' ',' ','o'}}, ttt.toCharArray('x', 'o', ' '));
    }
    
}
