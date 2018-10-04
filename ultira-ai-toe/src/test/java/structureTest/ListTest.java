/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structureTest;

import java.util.Random;
import org.junit.Assert;
import org.junit.Test;
import structure.List;

/**
 *
 * @author Jori Lampi
 */
public class ListTest {

    @Test
    public void listTest() {
        List<Object> list = new List<>();
        Assert.assertTrue("List empty at start", list.isEmpty());
        Assert.assertEquals("Size is 0 at start", 0, list.getSize());
        
        try {
            list.get(0);
            Assert.fail("Invalid call should throw ArrayIndexOutOfBoundsException");
        } catch (ArrayIndexOutOfBoundsException e) {}
        
        Object obj = new Object();
        list.add(obj);
        Assert.assertFalse("List not empty after adding element", list.isEmpty());
        Assert.assertEquals("List size increases after adding element", 1, list.getSize());
        Assert.assertSame("Can get the added element back", obj, list.get(0));
        
        list.clear();
        Assert.assertTrue("List empty after clearing", list.isEmpty());
        Assert.assertEquals("Size is 0 after clearing", 0, list.getSize());
    }
    
    @Test
    public void orderTest() {
        List<Object> list = new List<>();
        Random random = new Random(1338);
        for (int i = 0; i < 10; i++) {
            list.add(random.nextInt());
        }
        random = new Random(1338);
        for (int i = 0; i < 10; i++) {
            Assert.assertEquals(random.nextInt(), (int) list.get(i));
        }
    }
    
    @Test
    public void capacityTest() {
        final List<Object> list = new List<>();
        final Object obj = new Object();
        for (int i = 0; i < 100000000; i++) {
            list.add(obj);
        }
    }
    
}
