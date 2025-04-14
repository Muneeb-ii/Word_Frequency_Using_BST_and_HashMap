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
        nodes = (Node<K,V>[]) new Node[capacity];
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

    /**
     * Returns the length of the nodes array.
     * 
     * @return the length of the nodes array
     */
    public int capacity(){
        return nodes.length;
    }

    /**
     * Returns the index of the node in the nodes array for the given key.
     * 
     * @param key the key to be hashed
     * @return the index of the node in the nodes array
     */
    public int hash(K key){
        return Math.abs(key.hashCode() % capacity());
    }

    /**
     * Return the number of elements in the nodes array (size).
     * 
     * @return the number of elements in the nodes array (size)
     */
    public int size(){
        return size;
    }

    /**
     * Resets the fields of the HashMap to their default values.
     */
    public void clear(){
        nodes  = (Node<K,V>[]) new Node[capacity()];
        size = 0;
    }

    /**
     * Returns the string representation of the HashMap.
     * 
     * @return the string representation of the HashMap
     */
    public String toString() {
         String output = "" ;
         for ( int i = 0 ; i < this.capacity() ; i ++ ) {
            Node<K,V> node = this.nodes[ i ] ;
            output += "bin " + i + ": " ;
            while (node != null) {
                output += node.toString() + " | " ;
                node = node.next ;
            }
            output += "\n" ;
         }
        return output ;
    }
    
    /**
     * Puts the given key-value pair into the HashMap.
     * If the key already exists, it updates the value.
     * If the key does not exist, it adds a new node to the front of the linked list.
     * 
     * @param key   the key to be added
     * @param value the value to be added
     * @return the old value associated with the key, or null if there was no mapping for the key
     */
    public V put(K key, V value){
        int index = hash(key);

        // Check if the map needs to be resized
        if (index >= capacity()){
            Node<K,V>[] newNodes = (Node<K,V>[]) new Node[capacity() * 2];
            for (int i = 0; i < capacity(); i++) {
                newNodes[i] = nodes[i];
            }
            nodes = newNodes;
        }

        Node<K,V> newNode = new Node<>(key, value);

        if (nodes[index] == null) {
            nodes[index] = newNode;
            size++;
            return null;
        }
        else {
            // Traverse the linked list in the nodes array to find the key
            Node<K,V> current = nodes[index];
            while (current != null) {
                if (current.getKey().equals(key)) {
                    V oldValue = current.getValue();
                    current.setValue(value);
                    return oldValue;
                }
                current = current.next;
            }

             // If the key is not found, add the new node to the front of the linked list
            newNode.next = nodes[index];
            nodes[index] = newNode;
            size++;
            return null;
        }       
    }
}