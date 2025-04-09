/**
 * Author: Muneeb Azfar Nafees
 * 
 * Purpose of class: To implement a Binary Search Tree Map.
 */

public class BSTMap<K, V> implements MapSet<K, V>{

    private Node<K,V> root;
    private int size;
    private Comparator<K> comparator;
    
    private static class Node<K, V> extends KeyValuePair<K, V>{
        Node<K, V> left;
        Node<K, V> right;

        public Node(K k, V v) {
            super(k, v);
            left = null;
            right = null;
        }
    }
}