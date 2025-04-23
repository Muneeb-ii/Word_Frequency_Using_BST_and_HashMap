/**
 * Author: Muneeb Azfar Nafees
 * 
 * Purpose of the class: To provide a custom implementation of a map using an array list.
 */

import java.util.ArrayList;

public class ArrayListMap<K, V> implements MapSet<K, V>{
    private ArrayList<KeyValuePair<K, V>> list;
    private int size;

    /**
     * Constructor to initialize the ArrayListMap.
     */
    public ArrayListMap() {
        list = new ArrayList<KeyValuePair<K, V>>();
        size = 0;
    }
}