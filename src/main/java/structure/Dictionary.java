/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structure;

/**
 * Self-implemented Dictionary data structure.
 * 
 * @author Jori Lampi
 * @param <K> type of keys used to store values - should have proper equals()
 *            and hashCode() methods implemented
 * @param <E> value tied to the key
 */
public class Dictionary<K,E> {

    /**
     * The maximum amount of elements that can be contained. Verified to work
     * with tests.
     */
    public final static int MAX_CAPASITY = 1 << 24;
    
    // When more space is reserved to reduce possible hash collisions
    private final static double FILL_PERCENT = 0.9;
    
    // Helps with calculations
    private final static int INTERNAL_MAX_CAPACITY = (int) (MAX_CAPASITY / FILL_PERCENT);
    
    private final static int START_CAPASITY = 16;
    
    private Object[] buckets;
    private int elements;
    
    
    /**
     * Creates a new dictionary. 
     */
    public Dictionary() {
        this.buckets = new Object[START_CAPASITY];
        this.elements = 0;
    }
    
    /**
     * Set the given key to point to the given value.
     * <p>
     * Any previous value will be overwritten.
     * 
     * @param key     key used to access stored element
     * @param element element being stored
     */
    public void set(K key, E element) {
        if (elements > FILL_PERCENT * buckets.length) { moreBuckets(); }
        addToBucket(key, element);
        elements++;
    }
    
    private void moreBuckets() {
        // Check that we haven't already reached the max capasity.
        if (buckets.length >= INTERNAL_MAX_CAPACITY) { throw new OutOfMemoryError("Exceeded capasity."); }
        Object[] oldBuckets = buckets;
        // Double the size or set to max capacity, which ever is smaller.
        int newSize = Math.min(2 * oldBuckets.length, INTERNAL_MAX_CAPACITY);
        redistribute(newSize);
    }
    
    @SuppressWarnings("unchecked")
    private void redistribute(int newSize) {
        Object[] oldBuckets = buckets;
        buckets = new Object[newSize];
        for (Object object : oldBuckets) {
            Node node = (Node) object;
            while (node != null) {
                addToBucket(node.key, node.element);
                node = node.next;
            }
        }
    }
    
    private int getBucket(Object o) {
        int hash = o.hashCode() % buckets.length;
        // Fix negative modulos to the right range
        return (hash >= 0) ? hash : hash + buckets.length;
    }
    
    // Buckets are linked structures of elements.
    @SuppressWarnings("unchecked")
    private void addToBucket(Object key, E element) {
        int bucket = getBucket(key);
        Node node = (Node) buckets[bucket];
        if (node == null) {
            buckets[bucket] = new Node(key, element);
            return;
        }
        while(true) {
            if (node.key.equals(key)) {
                node.element = element;
                return;
            }
            if (node.next == null) {
                node.next = new Node(key, element);
                return;
            }
            node = node.next;
        }
    }
    
    /**
     * Return the value pointed to by key, or null if such value doesn't exist.
     * Note that the value in itself might be null. The method hasKey() can be
     * used to distinguish between two.
     * @param key the key used to find the element
     * @return element pointed by the key or null if not one found
     */
    public E get(K key) {
        Node node = getNode(key);
        return (node == null) ? null : node.element;
    }
    
    /**
     * Tells whether a key has any value associated to it in this dictionary.
     * @param key the key being searched for
     * @return true if the key is pointed to a value
     */
    public boolean hasKey(K key) {
        return (getNode(key) != null);
    }
    
    @SuppressWarnings("unchecked")
    private Node getNode(Object key) {
        Node node = (Node) buckets[getBucket(key)];
        while (node != null) {
            if (node.key.equals(key)) { return node; }
            node = node.next;
        }
        return null;
    }
    
    /**
     * Internal class used for linking different bucket values together
     */
    private final class Node {
        final Object key;
        E element;
        Node next;

        Node(Object key, E element) {
            this.key = key;
            this.element = element;
            this.next = null;
        }
        
    }
    
}
