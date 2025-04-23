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

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key, the old
     * value is replaced. Does nothing if {@code value} is {@code null}.
     *
     * @param key   key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with {@code key}, or
     *         {@code null} if there was no mapping for {@code key}.
     */
    public V put(K key, V value){
        for(int i = 0; i<list.size(); i++){
            if(list.get(i).getKey().equals(key)){
                V oldValue = list.get(i).getValue();
                list.get(i).setValue(value);
                return oldValue;
            }
        }
        list.add(new KeyValuePair<K, V>(key, value));
        size++;
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
        for(int i = 0; i<list.size(); i++){
            if(list.get(i).getKey().equals(key)){
                return true;
            }
        }
        return false;
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
        for(int i = 0; i<list.size(); i++){
            if(list.get(i).getKey().equals(key)){
                return list.get(i).getValue();
            }
        }
        return null;
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
        for(int i = 0; i<list.size(); i++){
            if(list.get(i).getKey().equals(key)){
                V oldValue = list.get(i).getValue();
                list.remove(i);
                size--;
                return oldValue;
            }
        }
        return null;
    }

    /**
     * Returns an ArrayList of all the keys in the map.
     * 
     * @return an ArrayList of all the keys in the map.
     */
    public ArrayList<K> keySet(){
        ArrayList<K> keys = new ArrayList<>();
        for(int i = 0; i<list.size(); i++){
            keys.add(list.get(i).getKey());
        }
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
        for(int i = 0; i<list.size(); i++){
            values.add(list.get(i).getValue());
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
        return list;
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
     * Removes all of the mappings from this map.
     * The map will be empty after this call returns.
     */
    public void clear(){
        list.clear();
        size = 0;
    }

    /**
     * Returns the maximal number of iterations to find any particular element of
     * the Map.
     * 
     * @return
     */
    public int maxDepth(){
        return list.size();
    }
}
