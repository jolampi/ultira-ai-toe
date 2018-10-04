/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structure;

/**
 *
 * @author Jori Lampi
 * @param <E>
 */
public class List<E> {
    
    private static final int DEFAULT_CAPACITY = 10;
    
    private Object[] list;
    private int n;
    
    public List() {
        this.list = new Object[DEFAULT_CAPACITY];
        this.n = 0;
    }
    
    public int getSize() {
        return n;
    }
    
    public void add(E e) {
        if (n == list.length) { grow(); }
        list[n++] = e;
    }
    
    private void grow() {
        int newSize = (list.length < Integer.MAX_VALUE / 2) ? list.length << 1 : Integer.MAX_VALUE;
        Object[] newList = new Object[newSize];
        System.arraycopy(list, 0, newList, 0, list.length);
        list = newList;
    }
    
    public boolean isEmpty() {
        return (n == 0);
    }
    
    @SuppressWarnings("unchecked")
    public E get(int index) {
        checkIndex(index);
        return (E) list[index];
    }
    
    private void checkIndex(int index) {
        if (index < 0 || n <= index) { throw new ArrayIndexOutOfBoundsException(); }
    }
    
    public void clear() {
        list = new Object[DEFAULT_CAPACITY];
        n = 0;
    }
    
}
