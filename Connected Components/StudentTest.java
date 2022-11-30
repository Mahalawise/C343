import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {
    @Test
    public void test() {
//        int size = 500;
//        /// initialize it
//        UndirectedAdjacencyList U = new UndirectedAdjacencyList(size);
        UndirectedAdjacencyList L = new UndirectedAdjacencyList(500);
//        //empty hashmap
//        Map<Integer, Integer> M = new HashMap<>();
        Map<Integer, Integer> Z = new HashMap<>();
//        Map<Integer, Integer> Correct = new HashMap<>();
//        //result
//        ArrayList<Integer> A = new ArrayList<Integer>();
        ArrayList<Integer> B = new ArrayList<Integer>();
//        /// add edges
//        L.addEdge(0, 1);
//        B.add(1);
//        L.addEdge(1, 2);
//        B.add(2);
//        L.addEdge(2, 3);
//        B.add(3);
//        L.addEdge(3, 4);
//        B.add(4);
//        L.addEdge(4, 5);
//        B.add(5);
//        L.addEdge(5, 6);
//        B.add(6);
//        L.addEdge(6, 7);
//        B.add(7);
//        L.addEdge(7, 8);
//        B.add(8);
//        L.addEdge(8, 9);
//        B.add(9);
//        L.addEdge(9, 10);
//        B.add(10);
//
////        U.addEdge(15, 16);
////        B.add(16);
////        U.addEdge(16, 17);
////        B.add(17);
////        U.addEdge(17, 18);
////        B.add(18);
////        U.addEdge(15, 18);
//        for (int i = 0; i < 500; i++) {
//            U.addEdge(0, i);
//            A.add(i);
//
//        }
//        Correct.put(0, 0);
//        Correct.put(1, 0);
//        Correct.put(2, 0);
//        Correct.put(3, 0);
//        Correct.put(4, 0);
//        Correct.put(5, 0);
//        Correct.put(6, 0);
//        Correct.put(7, 0);
//        Correct.put(8, 0);
//        Correct.put(9, 0);
//        Correct.put(10, 0);
//        Correct.put(11, 11);
//        Correct.put(12, 12);
//        Correct.put(13, 13);
//        Correct.put(14, 14);
//        Correct.put(15, 15);
//        Correct.put(16, 15);
//        Correct.put(17, 15);
//        Correct.put(18, 15);
//        Correct.put(19, 19);
//        Correct.put(20, 20);
//        Correct.put(21, 21);
//        Correct.put(22, 22);
//        Correct.put(23, 23);
//        Correct.put(24, 24);
//
//        /// add result to hashmap and then check map
//        ConnectedComponents.connected_components(U, M);//pushes the result onto the hashmap
//        ConnectedComponents.connected_components(L, Z);
//        System.out.println("M" + M);
//        for (Map.Entry<Integer, Integer> entry : M.entrySet()) {
//            assertEquals(0, (int) entry.getValue());
//        }
//        for (Map.Entry<Integer, Integer> entry : Z.entrySet()) {
//            if (entry.getKey() <= 10) {
//                assertEquals(0, (int) entry.getValue());
//            }
//            if (entry.getKey() == entry.getValue()) {
//                System.out.println("getKey" + entry.getKey());
//                System.out.println("getValue" + entry.getKey());
//                System.out.println("A" + B.contains(entry.getKey()));
//                assertFalse(B.contains(entry.getKey()));
//            } else {
//                assertTrue(B.contains(entry.getKey()));
//            }
////            for (Map.Entry<Integer, Integer> valid : Correct.entrySet()) {
////                if (entry.getKey() == valid.getKey()) {
////                    assertEquals(valid.getValue(), entry.getValue());
////
////                }
////            }
//        }
//    }
        int size = 25;
        /// initialize it
        UndirectedAdjacencyList U = new UndirectedAdjacencyList(size);
        //empty hashmap
        Map<Integer, Integer> M = new HashMap<>(size);
        Map<Integer, Integer> Correct = new HashMap<>();
        //result
        ArrayList<Integer> A = new ArrayList<Integer>();
        /// add edges
        U.addEdge(0, 1);
        A.add(1);
        U.addEdge(1, 2);
        A.add(2);
        U.addEdge(2, 3);
        A.add(3);
        U.addEdge(3, 4);
        A.add(4);
        U.addEdge(4, 5);
        A.add(5);
        U.addEdge(5, 6);
        A.add(6);
        U.addEdge(6, 7);
        A.add(7);
        U.addEdge(7, 8);
        A.add(8);
        U.addEdge(8, 9);
        A.add(9);
        U.addEdge(9, 10);
        A.add(10);

        U.addEdge(15, 16);
        A.add(16);
        U.addEdge(16, 17);
        A.add(17);
        U.addEdge(17, 18);
        A.add(18);
        U.addEdge(15, 18);

        Correct.put(0,0);
        Correct.put(1,0);
        Correct.put(2,0);
        Correct.put(3,0);
        Correct.put(4,0);
        Correct.put(5,0);
        Correct.put(6,0);
        Correct.put(7,0);
        Correct.put(8,0);
        Correct.put(9,0);
        Correct.put(10,0);
        Correct.put(11,11);
        Correct.put(12,12);
        Correct.put(13,13);
        Correct.put(14,14);
        Correct.put(15,15);
        Correct.put(16,15);
        Correct.put(17,15);
        Correct.put(18,15);
        Correct.put(19,19);
        Correct.put(20,20);
        Correct.put(21,21);
        Correct.put(22,22);
        Correct.put(23,23);
        Correct.put(24,24);

        for (int i = 0; i < 500; i++) {
            L.addEdge(0, i);
            B.add(i);

        }


        /// add result to hashmap and then check map
        ConnectedComponents.connected_components(U, M);//pushes the result onto the hashmap
        ConnectedComponents.connected_components(L, Z);
        System.out.println("M"+M);
        System.out.println("Correct"+Correct);

        for (Map.Entry<Integer,Integer> entry : M.entrySet()) {
            if (entry.getKey() <= 10) {
                assertEquals(0, (int) entry.getValue());
            }
            if (entry.getKey() == entry.getValue()) {
                System.out.println("getKey" + entry.getKey());
                System.out.println("getValue" + entry.getKey());
                System.out.println("A" + A.contains(entry.getKey()));
                assertFalse(A.contains(entry.getKey()));
            }
            for (Map.Entry<Integer,Integer> valid : Correct.entrySet()){
//                    assertEquals(valid.getKey(),entry.getKey());
                System.out.println("getKey" + valid.getKey());
                System.out.println("getValue" + valid.getKey());
                if(valid.getKey() == entry.getKey()){
                    assertEquals(valid.getKey(),valid.getKey());
                    assertEquals(valid.getValue(),entry.getValue());
                } else{
                    assertFalse(valid.getKey()==entry.getKey());
                }
            }

        }
        for (Map.Entry<Integer, Integer> enter : Z.entrySet()) {
            assertEquals(0, (int) enter.getValue());
        }
    }
}
