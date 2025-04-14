/**
 * Author: Muneeb Azfar Nafees
 * 
 * Purpose of class: To test the BSTMap class.
 */

public class BSTMapTester {
    
    public static void test1(){
        System.out.println("-".repeat(30) + "\nTest1: ");

        String expectedResult = "\t\t<7 -> 7>\n\t<6 -> 6>\n\t\t<5 -> 5>\n<4 -> 4>\n\t\t<3 -> 3>\n\t<2 -> 2>\n\t\t<1 -> 1>";
        System.out.println("Expected result: \n" + expectedResult );
        System.out.println( "-".repeat(10) );

        MapSet<Integer, String> map = new BSTMap<>();
        for(int i : new int[] {4, 2, 6, 1, 3, 5, 7}){
            map.put(i, "" + i);
        }
        System.out.println("Your result: \n" + map );
        System.out.println("-".repeat(30));
    }

    public static void test2(){
        //Note: This one is implemented for you, but you need to figure out what it should look like!
        System.out.println("-".repeat(30) + "\nTest2: ");
        
        String expectedResult = "\t\t\t\t\t\t<7 -> 7>\n\t\t\t\t\t<6 -> 6>\n\t\t\t\t<5 -> 5>\n\t\t\t<4 -> 4>\n\t\t<3 -> 3>\n\t<2 -> 2>\n<1 -> 1>";
        System.out.println("Expected result: \n" + expectedResult );
        System.out.println( "-".repeat(10) );

        BSTMap<Integer, String> map = new BSTMap<>();
        for(int i : new int[] {1, 2, 3, 4, 5, 6, 7}){
            map.put(i, "" + i);
        } 

        System.out.println(map);
        System.out.println("size: " + map.size() + " == " + 7);
        System.out.println("maxDepth: " + map.maxDepth() + " == " + 7);
        System.out.println("entrySet: " + map.entrySet() + " == " + "[<1 -> 1>, <2 -> 2>, <3 -> 3>, <4 -> 4>, <5 -> 5>, <6 -> 6>, <7 -> 7>]");
        System.out.println("-".repeat(30));
    }

    public static void test3(){
        //This test should put a bunch of key/value pairs into the BSTMap, and remove them 
        //one by one--not in order, checking that the size of the BSTMap and its remaining 
        //Values stay correct.
        System.out.println("-".repeat(30) + "\nTest3: ");

        int[] keys = {4, 2, 6, 1, 3, 5, 7};
        MapSet<Integer, String> map = new BSTMap<>();
        for(int i : keys){
            map.put(i, "" + i);
        }

        System.out.println("size: " + map.size() + " == " + 7);
        System.out.println("values" + map.values() + " == " + "[1, 2, 3, 4, 5, 6, 7]");
        System.out.println( "-".repeat(10) );

        map.remove(7);
        System.out.println("size: " + map.size() + " == " + 6);
        System.out.println("values" + map.values() + " == " + "[1, 2, 3, 4, 5, 6]");
        System.out.println( "-".repeat(10) );

        map.remove(2);
        System.out.println("size: " + map.size() + " == " + 5);
        System.out.println("values" + map.values() + " == " + "[1, 3, 4, 5, 6]");
        System.out.println( "-".repeat(10) );

        map.remove(3);
        System.out.println("size: " + map.size() + " == " + 4);
        System.out.println("values" + map.values() + " == " + "[1, 4, 5, 6]");
        System.out.println( "-".repeat(10) );

        map.remove(4);
        System.out.println("size: " + map.size() + " == " + 3);
        System.out.println("values" + map.values() + " == " + "[1, 5, 6]");
        System.out.println( "-".repeat(10) );

        map.remove(1);
        System.out.println("size: " + map.size() + " == " + 2);
        System.out.println("values" + map.values() + " == " + "[5, 6]");
        System.out.println( "-".repeat(10) );

        map.remove(5);
        System.out.println("size: " + map.size() + " == " + 1);
        System.out.println("values" + map.values() + " == " + "[6]");
        System.out.println( "-".repeat(10) );

        map.remove(6);
        System.out.println("size: " + map.size() + " == " + 0);
        System.out.println("values" + map.values() + " == " + "[]");
        System.out.println( "-".repeat(30) );


    }

    public static void test4(){
        //This test should put a bunch of key/value pairs into the BSTMap, 
        //and check that the correct value gets returned when each key is removed.
        System.out.println("-".repeat(30) + "\nTest4: ");

        int[] keys = {4, 2, 6, 1, 3, 5, 7};
        MapSet<Integer, String> map = new BSTMap<>();
        for(int i : keys){
            map.put(i, "" + i);
        }

        System.out.println("removed " + map.remove(5) + " == " + 5);
        System.out.println("removed " + map.remove(2) + " == " + 2);
        System.out.println("removed " + map.remove(1) + " == " + 1);
        System.out.println("removed " + map.remove(3) + " == " + 3);
        System.out.println("removed " + map.remove(4) + " == " + 4);
        System.out.println("removed " + map.remove(6) + " == " + 6);
        System.out.println("removed " + map.remove(7) + " == " + 7);

        System.out.println("size: " + map.size() + " == " + 0);
        System.out.println("values" + map.values() + " == " + "[]");
        System.out.println( "-".repeat(30) );
    }

    public static void main(String[] args){
        test1();
        test2();
        test3();
        test4();
    }

}