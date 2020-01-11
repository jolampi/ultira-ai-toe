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
    public void settingAndGetting() {
        Dictionary<String, Object> dictionary = new Dictionary<>();
        String key = "foo";
        Assert.assertFalse(dictionary.hasKey(key));
        Assert.assertNull(dictionary.get(key));
        dictionary.set(key, null);
        Assert.assertTrue(dictionary.hasKey(key));
        Assert.assertNull(dictionary.get(key));
        dictionary.set(key, new Object());
        Assert.assertNotNull(key);
    }
    
    @Test
    public void readAndWrite() {
        Dictionary<Long, Integer> dictionary = new Dictionary<>();
        Random random = new Random(1984);
        for (int i = 0; i < 1000000; i++) {
            Long key = random.nextLong();
            Integer value = random.nextInt();
            dictionary.set(key, value);
            Assert.assertEquals(value, dictionary.get(key));
        }
    }
    
    // Disabled for now because struggles with lower-spec systems
    /* @Test
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
        try {
            dictionary.set(Dictionary.MAX_CAPASITY, 0);
            Assert.fail("Exceeding capasity should throw OutOfMemoryException");
        } catch (OutOfMemoryError e) {}
    } */
    
}
