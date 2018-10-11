/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structure;

/**
 *
 * @author Jori Lampi
 * @param <K>
 * @param <E>
 */
public class Dictionary<K,E> {
    
    // Assured with tests to work with this many values  
    public static final int MAX_CAPASITY = 2 << 22;
    
    private static final double FILL_PERCENT = 0.9;
    private static final int INTERNAL_MAX_CAPACITY = (int) (MAX_CAPASITY / FILL_PERCENT);
    
    private Object[] buckets;
    private int elements;
    
    public Dictionary() {
        this.buckets = new Object[16];
        this.elements = 0;
    }
    
    public void set(K key, E element) {
        if (elements > FILL_PERCENT * buckets.length) { moreBuckets(); }
        addToBucket(key, element);
        elements++;
    }
    
    @SuppressWarnings("unchecked")
    private void moreBuckets() {
        if (buckets.length >= INTERNAL_MAX_CAPACITY) { throw new OutOfMemoryError("Exceeded capasity."); }
        Object[] oldBuckets = buckets;
        buckets = new Object[Math.min(2 * oldBuckets.length, INTERNAL_MAX_CAPACITY)];
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
        return (hash >= 0) ? hash : hash + buckets.length;
    }
    
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
    
    public E get(K key) {
        Node node = getNode(key);
        return (node == null) ? null : node.element;
    }
    
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
    
    private final class Node {
        private final Object key;
        private E element;
        public Node next;

        public Node(Object key, E element) {
            this.key = key;
            this.element = element;
            this.next = null;
        }
        
    }
    
}
