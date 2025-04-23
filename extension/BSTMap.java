import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

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
        if (root == null) return null;
        Node<K, V> toDelete = root;
        Node<K, V> parent = null;

        while (toDelete != null && comparator.compare(key, toDelete.getKey()) != 0){
            parent = toDelete;
            if (comparator.compare(key, toDelete.getKey()) < 0){
                toDelete = toDelete.left;
            } 
            else {
                toDelete = toDelete.right;
            }
        }

        if (toDelete == null) return null; // key not found


        V oldValue = toDelete.getValue();
        handleReplacement(toDelete, parent);
        size--;
        return oldValue;
    }

    /**
     * Helper method to handle the replacement of a node in the BSTMap.
     * Uses a recursive call to remove the in‐order successor when the node has two children.
     * 
     * @param toDelete       the node to be deleted
     * @param toDeleteParent the parent of the node to be deleted
     */
    private void handleReplacement(Node<K,V> toDelete, Node<K,V> toDeleteParent) {
        Node<K,V> replacement;
        if (toDelete.left == null && toDelete.right == null) {
            // Case: no children.
            replacement = null;
        } else if (toDelete.left == null) {
            // Case: only right child.
            replacement = toDelete.right;
        } else if (toDelete.right == null) {
            // Case: only left child.
            replacement = toDelete.left;
        } else {
            // Find the in-order successor (smallest node in the right subtree) and track its parent.
            Node<K,V> successorParent = toDelete;
            Node<K,V> successor = toDelete.right;
            while (successor.left != null) {
                successorParent = successor;
                successor = successor.left;
            }
            // Recursively remove the successor from its original location.
            handleReplacement(successor, successorParent);
            // Use the successor as the replacement.
            replacement = successor;
            // Attach the left child of the node being deleted to the successor.
            replacement.left = toDelete.left;
            // Attach the right child only if the successor isn't the direct right child.
            if (toDelete.right != successor) {
                replacement.right = toDelete.right;
            }
        }
        // Update parent's pointer.
        if (toDeleteParent == null) {
            root = replacement;
        } else if (toDeleteParent.left == toDelete) {
            toDeleteParent.left = replacement;
        } else {
            toDeleteParent.right = replacement;
        }
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
        ArrayList<V> values = new ArrayList<>();
        ArrayList<K> keys = keySet();
        for (K key : keys){
            values.add(get(key));
        }
        return values;
    }

    /**
     * Returns an ArrayList of each {@code KeyValuePair} in the map in the same
     * order as the keys as returned by keySet().
     * 
     * @return an ArrayList of each {@code KeyValuePair} in the map in the same
     *         order as the keys as returned by keySet().
     */
    public ArrayList<KeyValuePair<K, V>> entrySet(){
        ArrayList<KeyValuePair<K,V>> keyValuePairs = new ArrayList<>();
        ArrayList<K> keys = keySet();
        for (K key : keys){
            keyValuePairs.add(new KeyValuePair<>(key, get(key)));
        }
        return keyValuePairs;
    }

    /**
     * Returns the maximal number of iterations to find any particular element of
     * the Map.
     * 
     * @return the maximum depth of the BSTMap
     */
    public int maxDepth(){
        return maxDepth(root);
    }

    /**
     * Helper method to get the maximum depth of the BSTMap.
     * 
     * @param curr the current node
     * @return the maximum depth of the BSTMap
     */
    private int maxDepth(Node<K,V> curr){
        if (curr == null) return 0;
        int leftDepth = maxDepth(curr.left);
        int rightDepth = maxDepth(curr.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }

    /**
     * Returns the number of unbalanced keys in the BSTMap.
     * A key is considered unbalanced if the difference between the heights of its
     * left and right subtrees is greater than 1.
     * 
     * @return the number of unbalanced keys in the BSTMap
     */
    public int numberOfUnbalancedKeys(){ 
        return unbalancedKeys(root);
    }

    /**
     * Helper method to count the number of unbalanced keys in the BSTMap.
     * 
     * @param node the current node
     * @return the number of unbalanced keys in the BSTMap
     */
    private int unbalancedKeys(Node<K,V> node){
        if(node==null) return 0;

        int leftHeight = maxDepth(node.left);
        int rightHeight = maxDepth(node.right);

        int balanceFactor = Math.abs(leftHeight-rightHeight);

        int count = balanceFactor>1? 1:0;

        return count + unbalancedKeys(node.left) + unbalancedKeys(node.right);
    }

    public static void main(String[] args){
        BSTMap<Integer, String> map = new BSTMap<>();
        Random rand = new Random();

        System.out.println("-".repeat(50));
        System.out.println("Testing unbalanced keys in BSTMap\n");

        map.put(1, "1");
        map.put(2, "2");
        map.put(3, "3");
        map.put(4, "4");
        map.put(5, "5");
        map.put(6, "6");

        System.out.println("Map: \n" + map);
        System.out.println("Size: " + map.size() + ", Should be 6");
        System.out.println("Unbalanced Keys: " + map.numberOfUnbalancedKeys() + ", Should be 4");

        System.out.println("-".repeat(50));
        System.out.println("Exploration using Random BSTMap\n");
        map.clear();

        //Insert 10 random keys into the map
        for (int i = 0; i < 10; i++){
            int key = rand.nextInt(10000);
            map.put(key, String.valueOf(key));
        }
        
        //Print the number of unbalanced keys and the size of the map
        System.out.println("Map: \n" + map);
        System.out.println("Size: " + map.size());
        System.out.println("Unbalanced Keys: " + map.numberOfUnbalancedKeys());

        System.out.println("-".repeat(20));
        System.out.println("Add 20 more random keys\n");
        //Insert 20 more random keys into the map
        for (int i = 0; i < 20; i++){
            int key = rand.nextInt(10000);
            map.put(key, String.valueOf(key));
        }
        
        //Print the number of unbalanced keys and the size of the map
        System.out.println("Map: \n" + map);
        System.out.println("Size: " + map.size());
        System.out.println("Unbalanced Keys: " + map.numberOfUnbalancedKeys());

        System.out.println("-".repeat(20));
        System.out.println("Add 30 more random keys\n");
        //Insert 30 more random keys into the map
        for (int i = 0; i < 30; i++){
            int key = rand.nextInt(10000);
            map.put(key, String.valueOf(key));
        }

        System.out.println("Map: \n" + map);
        System.out.println("Size: " + map.size());
        System.out.println("Unbalanced Keys: " + map.numberOfUnbalancedKeys());

        System.out.println("-".repeat(20));
        System.out.println("Add 100 more random keys\n");
        //Insert 30 more random keys into the map
        for (int i = 0; i < 100; i++){
            int key = rand.nextInt(10000);
            map.put(key, String.valueOf(key));
        }
        
        System.out.println("Size: " + map.size());
        System.out.println("Unbalanced Keys: " + map.numberOfUnbalancedKeys());
    }
}