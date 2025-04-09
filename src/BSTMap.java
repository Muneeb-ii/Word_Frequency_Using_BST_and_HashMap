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
     * Adds a key-value pair to the map.
     * If the key already exists, it updates the value.
     * If the value is null, it adds the key with a the given value.
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
}