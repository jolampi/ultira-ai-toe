/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structure;

import java.util.HashMap;

/**
 *
 * @author Jori Lampi
 * @param <K>
 * @param <E>
 */
public class Dictionary<K,E> {
    
    private final HashMap<K,E> dictionary;
    
    public Dictionary() {
        this.dictionary = new HashMap<>();
    }
    
    public void set(K key, E value) {
        dictionary.put(key, value);
    }
    
    public E get(K key) {
        return dictionary.get(key);
    }
    
    public boolean hasKey(K key) {
        return dictionary.containsKey(key);
    }
    
}
