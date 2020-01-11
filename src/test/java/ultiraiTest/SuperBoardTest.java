/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultiraiTest;

import org.junit.Assert;
import org.junit.Test;
import ultirai.Mark;
import ultirai.SuperBoard;

/**
 *
 * @author Jori Lampi
 */
public class SuperBoardTest {

    @Test
    public void SuperBoardTest() {
        SuperBoard sb = new SuperBoard(2);
        Assert.assertArrayEquals("Board is clear at start", sb.toCharArray('x', 'o', '.'), new char[][] {{'.','.','.','.'},{'.','.','.','.'},{'.','.','.','.'},{'.','.','.','.'}});
    }
    
    @Test
    public void SuperBoardChanges() {
        SuperBoard sb = new SuperBoard(2).next(Mark.CROSS, 0,0, 1,0);
        Assert.assertArrayEquals("", sb.getSubBoardAt(0, 0).toCharArray('x', 'o', '.'), new char[][] {{'.','x'},{'.','.'}});
    }
}
