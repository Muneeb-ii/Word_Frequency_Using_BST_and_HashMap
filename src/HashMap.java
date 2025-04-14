/**
 * Author: Muneeb Azfar Nafees
 * 
 * Purpose of the class: A simple implementation of a HashMap.
 */

public class HashMap<K,V> implements MapSet<K,V> {

    private int size;
    private Node<K,V>[] nodes;
    private double maxLoadFactor;

    /**
     * Creates a node using the given key and value.
     */
    private static class Node<K, V> extends KeyValuePair<K, V>{
        private Node<K, V> next;

        /**
         * Constructor for the Node class.
         * @param k the key
         * @param v the value
         */
        public Node(K k, V v) {
            super(k, v);
            next = null;
        }
    }

    /**
     * Constructor for the HashMap class.
     * Initializes the size to 0 and the nodes array to a default size of 16.
     */
    public HashMap(){
        this(16);
    }

    /**
     * Constructor for the HashMap class.
     * Initializes the size to 0 and the nodes array to the given capacity.
     * 
     * @param capacity the initial capacity of the HashMap
     */
    public HashMap(int capacity){
        size = 0;
        nodes = new Node[capacity];
    }

    /**
     * Constructor for the HashMap class.
     * Initializes the size to 0, the nodes array to the given capacity,
     * and the max load factor to the given load factor.
     * 
     * @param capacity   the initial capacity of the HashMap
     * @param loadFactor the maximum load factor of the HashMap
     */
    public HashMap(int capacity, double loadFactor){
        this(capacity);
        this.maxLoadFactor = loadFactor;
    }

}