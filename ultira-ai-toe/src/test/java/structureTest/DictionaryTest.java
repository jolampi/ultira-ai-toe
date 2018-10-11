/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structureTest;

import java.util.Random;
import org.junit.Assert;
import org.junit.Test;
import structure.Dictionary;

/**
 *
 * @author Jori Lampi
 */
public class DictionaryTest {
    
    @Test
    public void illegalInput() {
    
    }
    
    @Test
    public void settingAndGetting() {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();
        Dictionary<String, Object> dictionary = new Dictionary<>();
        dictionary.set("foo", a);
        dictionary.set("bar", b);
        dictionary.set("biz", c);
        Assert.assertEquals(a, dictionary.get("foo"));
        Assert.assertEquals(c, dictionary.get("biz"));
        Assert.assertEquals(b, dictionary.get("bar"));
    }
    
    @Test
    public void capacityTest() {
        Dictionary<Integer, Integer> dictionary = new Dictionary<>();
        Random random = new Random(2020);
        for (int i = 0; i < Dictionary.MAX_CAPASITY; i++) {
            dictionary.set(i, random.nextInt());
        }
        random = new Random(2020);
        for (int i = 0; i < Dictionary.MAX_CAPASITY; i++) {
            Assert.assertEquals("Check value", (Object)random.nextInt(), dictionary.get(i));
        }
    }
    
}
