import java.util.ArrayList;

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
        this(16,0.75);
    }

    /**
     * Constructor for the HashMap class.
     * Initializes the size to 0 and the nodes array to the given capacity.
     * 
     * @param capacity the initial capacity of the HashMap
     */
    public HashMap(int capacity){
        this(capacity, 0.75);
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
        nodes = (Node<K,V>[]) new Node[capacity];
        this.size = 0;
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
     * If the key does not exist, it adds a new node to the end of the linked list.
     * 
     * @param key   the key to be added
     * @param value the value to be added
     * @return the old value associated with the key, or null if there was no mapping for the key
     */
    public V put(K key, V value){
        if(size + 1 > capacity() * maxLoadFactor){
            // Resize the nodes array if the load factor is exceeded
            ArrayList<KeyValuePair<K,V>> entrySet = entrySet();
            Node<K,V>[] newNodes = (Node<K,V>[]) new Node[capacity() * 2];
            nodes = newNodes;
            size = 0;

            for (KeyValuePair<K,V> entry : entrySet) {
                put(entry.getKey(), entry.getValue());
            }
        }
        
        int index = hash(key);

        Node<K,V> newNode = new Node<>(key, value);

        if (nodes[index] == null) {
            nodes[index] = newNode;
            size++;
            return null;
        }
        else {
            // Traverse the linked list in the nodes array to find the key
            Node<K,V> previous = null;
            Node<K,V> current = nodes[index];
            while (current != null) {
                if (current.getKey().equals(key)) {
                    V oldValue = current.getValue();
                    current.setValue(value);
                    return oldValue;
                }
                previous = current;
                current = current.next;
            }

             // If the key is not found, add the new node to the end of the linked list
            previous.next = newNode;
            return null;
        }       
    }

    /**
     * Returns the value associated with the given key.
     * If the key does not exist, it returns null.
     * 
     * @param key the key to be searched
     * @return the value associated with the key, or null if the key does not exist
     */
    public V get(K key){
        int index  = hash(key);

        Node<K,V> curNode = nodes[index];

        while(curNode != null){
            if(curNode.getKey().equals(key)){
                V value = curNode.getValue();
                return value;
            }
            curNode = curNode.next;
        }

        return null;
    }

    /**
     * Returns {@code true} if this map contains a mapping for the
     * specified key to a value.
     *
     * @param key The key whose presence in this map is to be tested
     * @return {@code true} if this map contains a mapping for the specified
     *         key to a value.
     */
    public boolean containsKey(K key){
        return true;
    }

    /**
     * Removes the mapping for a key from this map if it is present. More formally,
     * if this map contains a mapping from key {@code k} to value {@code v} such
     * that {@code key.equals(k)}, that mapping is removed. (The map can contain at
     * most one such mapping.)
     * 
     * @param key key whose mapping is to be removed from the map
     * @return the previous value associated with {@code key}, or
     *         {@code null} if there was no mapping for {@code key}.
     */
    public V remove(K key){
        return null;
    }

    /**
     * Returns an ArrayList of all the keys in the map.
     * 
     * @return an ArrayList of all the keys in the map.
     */
    public ArrayList<K> keySet(){
        return null;
    }

    /**
     * Returns an ArrayList of all the values in the map in the same order as the
     * keys as returned by keySet().
     * 
     * @return an ArrayList of all the values in the map in the same order as the
     *         keys as returned by keySet().
     */
    public ArrayList<V> values(){
        return null;
    }

    /**
     * Returns an ArrayList of each {@code KeyValuePair} in the map in the same
     * order as the keys as returned by keySet().
     * 
     * @return an ArrayList of each {@code KeyValuePair} in the map in the same
     *         order as the keys as returned by keySet().
     */
    public ArrayList<KeyValuePair<K, V>> entrySet(){
        ArrayList<KeyValuePair<K, V>> entrySet = new ArrayList<>();

        for (int i = 0; i < capacity(); i++) {
            Node<K,V> current = nodes[i];
            while (current != null) {
                entrySet.add(current);
                current = current.next;
            }
        }
        return entrySet;
    }

    /**
     * Returns the maximal number of iterations to find any particular element of
     * the Map.
     * 
     * @return
     */
    public int maxDepth(){
        return 0;
    }
}