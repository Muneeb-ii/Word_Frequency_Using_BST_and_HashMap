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
        nodes  = (Node<K,V>[]) new Node[16];
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

        if(containsKey(key)){
            // If the key already exists, update the value and return the old value
            V oldValue = get(key);
            int index = hash(key);
            Node<K,V> current = nodes[index];
            while (current != null) {
                if (current.getKey().equals(key)) {
                    current.setValue(value);
                    return oldValue;
                }
                current = current.next;
            }
            return oldValue;
        }
        // If the key does not exist, add a new node to the end of the linked list

        int index = hash(key);

        Node<K,V> newNode = new Node<>(key, value);

        if (nodes[index] == null) {
            nodes[index] = newNode;
            size++;
            upsize();
            return null;
        }
        else {
            // Traverse the linked list in the nodes array to find the key
            Node<K,V> previous = null;
            Node<K,V> current = nodes[index];
            while (current != null) {
                previous = current;
                current = current.next;
            }

             // If the key is not found, add the new node to the end of the linked list
            previous.next = newNode;
            size++;
            upsize();
            return null;
        }       
    }

    /**
     * Upsizes the nodes array if fC>n.
     */
    private void upsize(){
        if(size > capacity() * maxLoadFactor){
            // Resize the nodes array if the fC > n
            ArrayList<KeyValuePair<K,V>> entrySet = entrySet();
            Node<K,V>[] newNodes = (Node<K,V>[]) new Node[capacity() * 2];
            nodes = newNodes;
            size = 0;

            for (KeyValuePair<K,V> entry : entrySet) {
                put(entry.getKey(), entry.getValue());
            }
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
        ArrayList<K> keys = keySet();
        for (K k : keys) {
            if (k.equals(key)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns an ArrayList of all the keys in the map.
     * 
     * @return an ArrayList of all the keys in the map.
     */
    public ArrayList<K> keySet(){
        ArrayList<K> keySet = new ArrayList<>();

        for (int i = 0; i < capacity(); i++) {
            Node<K,V> current = nodes[i];
            while (current != null) {
                keySet.add(current.getKey());
                current = current.next;
            }
        }
        return keySet;
    }

    /**
     * Returns an ArrayList of all the values in the map in the same order as the
     * keys as returned by keySet().
     * 
     * @return an ArrayList of all the values in the map in the same order as the
     *         keys as returned by keySet().
     */
    public ArrayList<V> values(){
        ArrayList<V> valueSet = new ArrayList<>();

        for (int i = 0; i < capacity(); i++) {
            Node<K,V> current = nodes[i];
            while (current != null) {
                valueSet.add(current.getValue());
                current = current.next;
            }
        }
        return valueSet;
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
        int depth = 0;

        for(int i=0; i<capacity(); i++){
            int curDepth = 0;
            Node<K,V> current = nodes[i];
            while (current !=  null){
                curDepth++;
                current=current.next;
            }
            if (curDepth>depth){
                depth = curDepth;
            }
        }

        return depth; 
    }

    /**
     * Removes the key-value pair with the given key from the HashMap.
     * 
     * @param key the key to be removed
     * @return the value associated with the removed key, or null if the key does not exist
     */
    public V remove(K key){
        int index = hash(key);
        Node<K,V> current = nodes[index];
        Node<K,V> previous = null;

        while (current != null) {
            if (current.getKey().equals(key)) {
                if (previous == null) {
                    nodes[index] = current.next;
                } else {
                    previous.next = current.next;
                }
                size--;
                // Check if the size is less than the threshold to downsize: fC/4
                downsize();
                return current.getValue();
            }
            previous = current;
            current = current.next;
        }
        return null;
    }

    /**
     * Upsizes the nodes array if fC>n.
     */
    private void downsize(){
        if(size < (capacity() * maxLoadFactor)/4){
            // Resize the nodes array if the fC > n
            ArrayList<KeyValuePair<K,V>> entrySet = entrySet();
            Node<K,V>[] newNodes = (Node<K,V>[]) new Node[capacity()/2];
            nodes = newNodes;
            size = 0;

            for (KeyValuePair<K,V> entry : entrySet) {
                put(entry.getKey(), entry.getValue());
            }
        }
    }
}