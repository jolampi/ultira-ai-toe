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
 * 
 * @author Jori Lampi
 */
public class ListTest {

    @Test
    public void listTest() {
        List<Object> list = new List<>();
        Assert.assertTrue("List empty at start", list.isEmpty());
        Assert.assertEquals("Size is 0 at start", 0, list.getSize());
        
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
    public void boundTest() {
        List<Object> list = new List<>();
        list.add(new Object());
        try {
            list.get(-1);
            Assert.fail();
        } catch (ArrayIndexOutOfBoundsException e) {}
        list.get(0);
        try {
            list.get(1);
            Assert.fail();
        } catch (ArrayIndexOutOfBoundsException e) {}
    }
    
    @Test
    public void orderTest() {
        List<Object> list = new List<>();
        Random random = new Random(1338);
        for (int i = 0; i < 1000; i++) {
            list.add(random.nextInt());
        }
        random = new Random(1338);
        for (int i = 0; i < 1000; i++) {
            Assert.assertEquals(random.nextInt(), (int) list.get(i));
        }
    }
    
    @Test
    public void capacityTest() {
        final List<Object> list = new List<>();
        final Object obj = new Object();
        for (int i = 0; i < List.MAX_CAPASITY; i++) {
            list.add(obj);
        }
        try {
            list.add(obj);
            Assert.fail();
        } catch (OutOfMemoryError e) {}
    }
    
}
