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
import ultirai.Board;
import ultirai.Mark;

/**
 *
 * @author Jori Lampi
 */
public class BoardTest {
    
    public BoardTest() {
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
    public void boardInitialization() {
        Board board = new Board(3);
        Assert.assertArrayEquals("Board is clear at start", board.toCharArray('x', 'o', '.'), new char[][] {{'.','.','.'}, {'.','.','.'}, {'.','.','.'}});
    }
    
    @Test
    public void boardChanges() {
        Board board = new Board(3).next(Mark.CROSS, 0, 0);
        Assert.assertArrayEquals("X in (0,0)", board.toCharArray('x', 'o', '.'), new char[][] {{'x','.','.'}, {'.','.','.'}, {'.','.','.'}});
        Board board2 = board.next(Mark.NOUGHT, 1, 2);
        Assert.assertArrayEquals("O in (1,2)", board2.toCharArray('x', 'o', '.'), new char[][] {{'x','.','.'}, {'.','.','.'}, {'.','o','.'}});
        Assert.assertArrayEquals("Previous board doesn't change", board.toCharArray('x', 'o', '.'), new char[][] {{'x','.','.'}, {'.','.','.'}, {'.','.','.'}});
    }
    
    @Test
    public void equality() {
        Board board = new Board(3);
        Assert.assertEquals("Board equals with its reference", true, board.equals(board));
        Assert.assertEquals("Board not equals with null", false, board.equals(null));
        Assert.assertEquals("Board not equals with different class object", false, board.equals(new Object()));
        Assert.assertEquals("Board equals with same sized board", true, board.equals(new Board(3)));
        Assert.assertEquals("Board not equals with different sized board", false, board.equals(new Board(5)));
    }
    
    @Test
    public void resolving() {
        Board board = new Board(3);
        Assert.assertEquals("No winner at start", Mark.NONE, board.resolve());
        for (int i = 0; i < 3; i++) {
            board = board.next(Mark.CROSS, 0, i).next(Mark.CROSS, 1, i).next(Mark.CROSS, 2, i);
            Assert.assertEquals("Row " + (i+1) + " winner", Mark.CROSS, board.resolve());
            board = board.next(Mark.NONE, 0, i).next(Mark.NONE, 1, i).next(Mark.NONE, 2, i);
        }
    }
    
}
