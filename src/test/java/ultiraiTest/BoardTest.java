/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultiraiTest;

import org.junit.Assert;
import org.junit.Test;
import ultirai.Board;
import ultirai.Mark;

/**
 *
 * @author Jori Lampi
 */
public class BoardTest {
    
    @Test
    public void illegalArgumentTest() {
        for (int i : new int[] {-100, -10, -1, 0}) {
            try {
                Board board = new Board(i);
                Assert.fail("Non-positive constructor arguments for size shouldn't be accepted.");
            } catch (Exception e) {
                if (e instanceof IllegalArgumentException) { continue; }
                Assert.fail("Illegal arguments should be handled with IllegalArgumentException.");
            }
        }
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
        Assert.assertTrue("Board equals with its reference", board.equals(board));
        Assert.assertFalse("Board not equals with null", board.equals(null));
        Assert.assertFalse("Board not equals with different class object", board.equals(new Object()));
        Assert.assertTrue("Board equals with same sized board", board.equals(new Board(3)));
        Assert.assertFalse("Board not equals with different sized board", board.equals(new Board(5)));
    }
    
    @Test
    public void resolving() {
        Board board = new Board(10);
        Assert.assertEquals("No winner at start", Mark.NONE, board.resolve());
        
        for (int y = 0; y < 10; y++) {
            // row
            board = new Board(10);
            for (int x = 0; x < 10; x++) {
                board = board.next(Mark.CROSS, x, y);
            }
            Assert.assertEquals("Horizontal row " + y + " winner", Mark.CROSS, board.resolve());
            
            // column
            board = new Board(10);
            for (int x = 0; x < 10; x++) {
                board = board.next(Mark.NOUGHT, y, x);
            }
            Assert.assertEquals("Vertical row " + y + " winner", Mark.NOUGHT, board.resolve());
        }
        
        // diagonal
        board = new Board(10);
        for (int i = 0; i < 10; i++) {
            board = board.next(Mark.CROSS, i, i);
        }
        Assert.assertEquals("Diagonal row winner", Mark.CROSS, board.resolve());
        
        // back-diagonal (?)
        board = new Board(10);
        for (int i = 0; i < 10; i++) {
            board = board.next(Mark.NOUGHT, i, board.getSize() - 1 - i);
        }
        Assert.assertEquals("Back-diagonal row winner", Mark.NOUGHT, board.resolve());
    }
    
}
