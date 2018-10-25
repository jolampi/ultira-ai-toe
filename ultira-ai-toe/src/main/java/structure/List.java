/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structure;

/**
 * Self-implemented List data structure.
 * 
 * @author Jori Lampi
 * @param <E> The type of elements stored in this list
 */
public class List<E> {
    
    /**
     * The maximum amount of elements that can be contained. Verified to work
     * with tests.
     */
    public final static int MAX_CAPASITY = 1 << 24;
    
    // Smallest possible size for the list
    private final static int MIN_CAPACITY = 10;
    
    private Object[] list;
    private int n;
    
    /**
     * Creates a new list.
     */
    public List() {
        this.list = new Object[MIN_CAPACITY];
        this.n = 0;
    }
    
    /**
     * @return the number of elements stored in this list
     */
    public int getSize() {
        return n;
    }
    
    /**
     * Add given element to the end of this list.
     * 
     * @param e element being added
     */
    public void add(E e) {
        if (n == list.length) { grow(); }
        list[n++] = e;
    }
    
    private void grow() {
        if (n == MAX_CAPASITY) { throw new OutOfMemoryError("Exceeded capasity"); }
        int newSize = Math.min(2 * list.length, MAX_CAPASITY);
        Object[] newList = new Object[newSize];
        System.arraycopy(list, 0, newList, 0, list.length);
        list = newList;
    }
    
    /**
     * @return true if the list doesn't have any elements
     */
    public boolean isEmpty() {
        return (n == 0);
    }
    
    /**
     * Return the element in a given position.
     * 
     * @param index position of the element returned.
     * @return element in position
     */
    @SuppressWarnings("unchecked")
    public E get(int index) {
        checkIndex(index);
        return (E) list[index];
    }
    
    // Check array bounds
    private void checkIndex(int index) {
        if (index < 0 || n <= index) { throw new ArrayIndexOutOfBoundsException(); }
    }
    
    /**
     * Removes all elements from this list. The size of this list will be 0
     * after this operation.
     */
    public void clear() {
        list = new Object[MIN_CAPACITY];
        n = 0;
    }
    
}
