import java.util.ArrayList;
import java.util.Comparator;

/**
 * Author: Muneeb Azfar Nafees
 * 
 * Purpose of class: To implement a Binary Search Tree Map.
 */

public class BSTMap<K, V> implements MapSet<K, V>{

    private Node<K,V> root;
    private int size;
    private Comparator<K> comparator;

    /**
     * Creates a node using the given key and value.
     */
    private static class Node<K, V> extends KeyValuePair<K, V>{
        private Node<K, V> left;
        private Node<K, V> right;

        /**
         * Constructor for the Node class.
         * @param k the key
         * @param v the value
         */
        public Node(K k, V v) {
            super(k, v);
            left = null;
            right = null;
        }
    }

    /**
     * Constructor for the BSTMap class.
     * 
     * @param comparator the comparator to be used for sorting the keys
     */
    public BSTMap(Comparator<K> comparator){
        root = null;
        size = 0;

        if(comparator != null){
            this.comparator = comparator;
        }
        else{
            this.comparator = new Comparator<K>(){
                @Override
                public int compare(K o1, K o2) {
                    return ((Comparable<K>) o1).compareTo(o2);
                }
            };
        }
    }

    /**
     * Default constructor for the BSTMap class.
     * This constructor uses the comparator as null.
     */
    public BSTMap(){
        this(null);
    }

    /**
     * Helper method to print the BSTMap in a readable format.
     * 
     * @param cur the current node
     * @param curDepth the current depth of the node
     * @param sb the StringBuilder to append the string to
     */
    private void toString(Node<K, V> cur, int curDepth, StringBuilder sb) {
        if (cur == null)
            return;
    
        toString(cur.right, curDepth + 1, sb);
        sb.append("\t".repeat(curDepth) + cur + "\n");
        toString(cur.left, curDepth + 1, sb);
    }

    /**
     * Prints the BSTMap in a readable format.
     * 
     * @return the string representation of the BSTMap
     */
    public String toString() {
        if (size() == 0) return "(empty BST)";
        StringBuilder sb = new StringBuilder();
        toString(this.root, 0, sb);
        return sb.toString();
    }

    /**
     * Returns the number of key-value mappings in this map.
     * 
     * @return the number of key-value mappings in this map
     */
    public int size(){
        return size;
    }

    /**
     * Clears the BSTMap.
     * This method sets the root to null and size to 0.
     * It effectively removes all the elements from the BSTMap.
     */
    public void clear(){
        root = null;
        size = 0;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key, the old
     * value is replaced. Does nothing if value is null.
     *
     * @param key   key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with key, or
     *         null if there was no mapping for key.
     */
    public V put(K key, V value){
        if (root == null) {
            root = new Node<>(key, value);
            size++;
            return null;
        }
        else{
            return put(key, value, root);
        }
    }

    /**
     * Helper method to put a key-value pair in the BSTMap.
     * 
     * @param key   the key to be added
     * @param value the value to be added
     * @param cur   the current node
     * @return      the previous value associated with the key, or null if there was no mapping for the key
     */
    private V put(K key, V value, Node<K, V> cur) {
        if (comparator.compare(key, cur.getKey()) < 0){
            if (cur.left != null){
                return put(key, value, cur.left);
            } 
            else {
                cur.left = new Node<>(key, value);
                size++;
                return null;
            }
        } 
        else if (comparator.compare(key, cur.getKey()) > 0){
            if (cur.right != null){
                return put(key, value, cur.right);
            } 
            else {
                cur.right = new Node<>(key, value);
                size++;
                return null;
            }
        } 
        else {
            V oldValue = cur.getValue();
            cur.setValue(value);
            return oldValue;
        }
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
        return containsKey(key, root);
    }

    /**
     * Helper method to check if the BSTMap contains a key.
     * 
     * @param key  the key to be checked
     * @param curr the current node
     * @return true if the key is found, false otherwise
     */
    private boolean containsKey(K key, Node<K,V> curr){
        if (curr == null) return false;
        if (comparator.compare(key, curr.getKey()) < 0){
            return containsKey(key, curr.left);
        } 
        else if (comparator.compare(key, curr.getKey()) > 0){
            return containsKey(key, curr.right);
        } 
        else {
            return true;
        }
    } 

    /**
     * Returns the value to which the specified key is mapped,
     * or {@code null} if this map contains no mapping for the key.
     * 
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or
     *         {@code null} if this map contains no mapping for the key
     */
    public V get(K key){
        return get(key, root);
    }

    /**
     * Helper method to get a value from the BSTMap using the given key.
     * 
     * @param key   the key to be searched
     * @param cur   the current node
     * @return      the value associated with the key, or null if the key is not found
     */
    private V get(K key, Node<K, V> cur) {
        if (comparator.compare(key, cur.getKey()) < 0){
            if (cur.left != null){
                return get(key, cur.left);
            } 
            else {
                return null;
            }
        } 
        else if (comparator.compare(key, cur.getKey()) > 0){
            if (cur.right != null){
                return get(key, cur.right);
            } 
            else {
                return null;
            }
        } 
        else {
            return cur.getValue();
        }
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
        ArrayList<K> keys  = new ArrayList<>();
        return keySet(root, keys);
    }

    /**
     * Helper method to get all the keys in sorted order from the BSTMap.
     * 
     * @param keys the ArrayList to store the keys
     * @param cur  the current node
     * @return the ArrayList of keys
     */
    private ArrayList<K> keySet(Node<K, V> cur, ArrayList<K> keys){
        if (cur == null) return keys;
        keySet(cur.left, keys);
        keys.add(cur.getKey());
        keySet(cur.right, keys);
        return keys;
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
        return null;
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