
public class HashMapTester {
    
    /** 
     * Testing put and resize 
     */
    public static void test1(){
        //Adds some values, makes sure they're in the Hashmap, makes sure it resizes
        System.out.println("-".repeat(30) + "\nTest1: ");
        String expected = "Hashmap after adding 5, 100, 3\nbin 0: <100 -> 2> | \nbin 1: <5 -> 1> | \nbin 2: <30 -> 3> | \nbin 3: \n\nHashmap after adding 12\nbin 0: \nbin 1: \nbin 2: \nbin 3: \nbin 4: <100 -> 2> | <12 -> 4> | \nbin 5: <5 -> 1> | \nbin 6: <30 -> 3> | \nbin 7: \n\nHashmap after adding 4, 6\nbin 0: \nbin 1: \nbin 2: \nbin 3: \nbin 4: <100 -> 2> | <12 -> 4> | <4 -> 5> | \nbin 5: <5 -> 1> | \nbin 6: <30 -> 3> | <6 -> 6> | \nbin 7: \n";
        System.out.println("Expected output: " + expected );
        System.out.println("-".repeat(15) + "\n");
        String actual = ""; 
        //Start it with 4 bins so it's small.
        MapSet<Integer, String> map = new HashMap<>(4, 0.75);
        map.put( 5, "" + 1 );
        map.put( 100, "" + 2 );
        map.put( 30, "" + 3 );
        actual += "Hashmap after adding 5, 100, 3\n" + map ;
        map.put( 12, "" + 4 );
        actual += "\nHashmap after adding 12\n" + map ;
        map.put( 4, "" + 5 );
        map.put( 6, "" + 6 );
        actual += "\nHashmap after adding 4, 6\n" + map ; 
        System.out.println("Actual output: " + actual );
        System.out.println("For test 1: expected and actual output are equal == " + actual.equals( expected ) );
    }

    /** 
     * Testing get 
     */
    public static void test2(){
        //Adds some values, gets them, and makes sure the key is correct
        System.out.println("-".repeat(30) + "\nTest2: ");
        MapSet<Integer, String> map = new HashMap<>(4, 0.75);
        map.put( 5, "" + 1 );
        map.put( 100, "" + 2 );
        map.put( 30, "" + 3 );
        map.put( 12, "" + 4 );
        System.out.println("For test 2: the following values should be equal: " ); 
        System.out.println( "Get 5   --- Key is " + map.get( 5 ) + ", should be 1" );
        System.out.println( "Get 100 --- Key is " + map.get( 100 ) + ", should be 2" );
        System.out.println( "Get 30  --- Key is " + map.get( 30 ) + ", should be 3" );
        System.out.println( "Get 12  --- Key is " + map.get( 12 ) + ", should be 4" );
    }

    /** 
     * Testing that size, capacity entry set are correct after put and resize
     */
    public static void test3(){
        //Put some keys and values into the hashmap
        //Print out the map, size, capacity and entryset and validate that they are all correct
        System.out.println("-".repeat(30) + "\nTest3: ");
        String expected = "bin 0: \nbin 1: \nbin 2: \nbin 3: \nbin 4: <100 -> 2> | <12 -> 4> | <4 -> 5> | \nbin 5: <5 -> 1> | \nbin 6: <30 -> 3> | <6 -> 6> | \nbin 7: \n";
        String actual = ""; 
        HashMap<Integer, String> map = new HashMap<>(4, 0.75);
        map.put( 5, "" + 1 );
        map.put( 100, "" + 2 );
        map.put( 30, "" + 3 );
        map.put( 12, "" + 4 );
        map.put( 4, "" + 5 );
        map.put( 6, "" + 6 );
        System.out.println("Expected Final Hashmap:\n" + expected );
        System.out.println("-".repeat(15) + "\n");
        actual += map;
        System.out.println("Actual Final Hashmap:\n" + actual );
        System.out.println("For test 3: the following values should be equal: " );
        System.out.println("Expected and actual output are equal == " + actual.equals(expected));
        System.out.println( "Size: " + map.size() + ", should be 6" );
        System.out.println( "Capacity: " + map.capacity() + ", should be 8" );
        System.out.println( "Entry set: " + map.entrySet() + ", should be  [<100 -> 2>, <12 -> 4>,  <4 -> 5>, <5 -> 1>, <30 -> 3>, <6 -> 6>]" );
    }

    /** 
     * Testing remove
     */
    public static void test4(){
        //Put around 20 values into the hashmap
        //Remove a few of them at a time and print the size after
        //Make sure it resizes when it's supposed to
        System.out.println("-".repeat(30) + "\nTest4: ");
        HashMap<Integer, String> map = new HashMap<>(4, 0.75);
        for (int i = 0; i < 20; i++){
            map.put( i, "" + i );
        }
        System.out.println("For test 4: the following values should be equal: " );
        System.out.println("Capacity" + " == " + map.capacity() + ", should be 32" );
        System.out.println( "Size: " + map.size() + ", should be 20" );
        for (int i = 0; i < 14; i++){
            map.remove( i );
            System.out.println( "Size after removing " + i + ": " + map.size() + ", should be " + (20 - i - 1) );
        }
        System.out.println("Capacity" + " == " + map.capacity() + ", should be 32" );
        map.remove(15);
        System.out.println( "Size after removing " + 15 + ": " + map.size() + ", should be 5" );
        System.out.println("Capacity" + " == " + map.capacity() + ", should be 16" );
    }

    /** 
     * Testing remove at scale
     */
    public static void test5(){
        //Add a lot of key/value pairs (e.g. 1000).
        //Remove them one at a time
        //make sure the size stays between the bounds its supposed to
        //Make sure the associated values are correct
        System.out.println("-".repeat(30) + "\nTest5: ");
        HashMap<Integer, String> map = new HashMap<>(4, 0.75);
        for (int i = 0; i < 1000; i++){
            map.put( i, "" + i );
        }
        System.out.println("For test 5: the following values should be equal: " );
        System.out.println("Capacity" + " == " + map.capacity() + ", should be 2048" );
        System.out.println( "Size: " + map.size() + ", should be 1000" );
        for (int i = 0; i < 999; i++){
            map.remove( i );
            System.out.println( "Size after removing " + i + ": " + map.size() + ", should be greater than or equals to fC/4: " + (map.size() >= (map.capacity()*0.75)/4) + " and less than or equals to fC: " + (map.size() <= map.capacity()*0.75) );
        }
    }

    public static void main(String[] args){
        test1();
        test2();
        test3();
        test4();
        test5();
    }

}