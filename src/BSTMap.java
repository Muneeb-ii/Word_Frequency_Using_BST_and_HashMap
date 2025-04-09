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
     * @return the string representation of the BSTMap
     */
    public String toString() {
        if (size() == 0) return "(empty BST)";
        StringBuilder sb = new StringBuilder();
        toString(this.root, 0, sb);
        return sb.toString();
    }

    /**
     * Return the size of the BSTMap.
     * @return the size of the BSTMap
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
        put(key, value, root);
    }

    /**
     * Helper method to put a key-value pair in the BSTMap.
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
                return null;
            }
        } 
        else if (comparator.compare(key, cur.getKey()) > 0){
            if (cur.right != null){
                return put(key, value, cur.right);
            } 
            else {
                cur.right = new Node<>(key, value);
                return null;
            }
        } 
        else {
            V oldValue = cur.getValue();
            cur.setValue(value);
            return oldValue;
        }
}