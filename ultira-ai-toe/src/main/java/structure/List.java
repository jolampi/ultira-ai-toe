/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structure;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Jori Lampi
 * @param <E>
 */
public class List<E> {
    
    private final ArrayList<E> list;
    
    public List() {
        this.list = new ArrayList();
    }
    
    public int size() {
        return list.size();
    }
    
    public void add(E e) {
        list.add(e);
    }
    
    public E get(int index) {
        return list.get(index);
    }
    
    public E[] toArray(E[] array) {
        return list.toArray(array);
    }
    
    public void clear() {
        list.clear();
    }
    
}
