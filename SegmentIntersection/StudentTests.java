import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class StudentTests {
    ///Test BST property
    ///test if the tree contains correct trees
    ///test the parent pointers
    //test the tree1 property
    ///test next and previous
    //test that the height of the tree is O(log n)
    ///keys
    ///search and contains
    ///isEmpty and size
    ///test insert
    ///test remove
    @Test
    public void test() throws Exception {
//        ArrayList<Integer> array1 = new ArrayList<>();
//        Random random = new Random();
////        BinaryTree<Integer> tree1 = new BinaryTree<>(array1);
//        ArrayList<Integer> array2 = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            array2.add(i);
//        }
//        BinarySearchTree tree1 = new BinarySearchTree<>((Integer x, Integer y) -> x < y);
//
////        for (int i = 0; i < random.nextInt(100) + 1; i++) {
////            int j=random.nextInt(100);
////            array1.add(j);
////            tree1.insert(j);
////        }
////        BinarySearchTree tree2 = new BinarySearchTree<>((Integer x, Integer y) -> x < y);
////        for (int i = 0; i < 10; i++) {
////            tree2.insert(i);
////        }
//        BinarySearchTree tree3 = new BinarySearchTree<>((Integer x, Integer y) -> x < y);
//        tree3.insert(4);
//        tree3.insert(8);
//        tree3.insert(0);
//        tree3.insert(2);
//        tree3.insert (6);
//        tree3.insert(10);
//        tree3.insert(4);
//        tree3.insert(8);
//        tree3.insert(0);
//        tree3.insert(2);
//        tree3.insert(6);
//        tree3.insert(10);
//
//        tree3.remove(4);
//        System.out.println(tree3);
//        tree3.remove(8);
//        System.out.println(tree3);
//        tree3.remove(0);
//        System.out.println(tree3);
//        tree3.remove(2);
//    System.out.println(tree3);
//        tree3.remove(6);

        AVLTree<Integer> tree1 = new AVLTree<>((Integer x, Integer y) -> x < y);
        for (int i = 0; i != 500; i++) {
            int test = new Random().nextInt(100000);
            tree1.insert(test);

            assertTrue(tree1.keys().contains(test));

            for (int j = 0; j != tree1.keys().size() - 1; j++) {

                assertTrue(tree1.keys().get(j) < tree1.keys().get(j + 1));
                if (tree1.get_height(tree1.find(tree1.keys().get(j), tree1.root, tree1.root.parent)) == 0) {
                    assertTrue(tree1.find(tree1.keys().get(j), tree1.root, tree1.root.parent).left == null && tree1.find(tree1.keys().get(j), tree1.root, tree1.root.parent).right == null);
                }
                // Check height property
                if (tree1.find(tree1.keys().get(j), tree1.root, tree1.root.parent).isLeaf()) {
                    assertTrue(tree1.find(tree1.keys().get(j), tree1.root, tree1.root.parent).height == 0);
                }
                assertTrue(tree1.find(tree1.keys().get(j), tree1.root, tree1.root.parent).height == Math.max(tree1.get_height(tree1.find(tree1.keys().get(j), tree1.root, tree1.root.parent).left), tree1.get_height(tree1.find(tree1.keys().get(j), tree1.root, tree1.root.parent).right)) + 1);
                // Check tree1 property
                assertTrue(Math.abs(tree1.get_height(tree1.find(tree1.keys().get(j), tree1.root, tree1.root.parent).left) - tree1.get_height(tree1.find(tree1.keys().get(j), tree1.root, tree1.root.parent).right)) < 2);
                // Check parents
                if (tree1.find(tree1.keys().get(j), tree1.root, tree1.root.parent) != tree1.root) {
                    assertTrue(tree1.find(tree1.keys().get(j), tree1.root, tree1.root.parent).parent != null);
                    // if parent.left != null
                    if (tree1.find(tree1.keys().get(j), tree1.root, tree1.root.parent).parent.left != null && tree1.find(tree1.keys().get(j), tree1.root, tree1.root.parent).parent.right != null) {
                        assertTrue(tree1.find(tree1.keys().get(j), tree1.root, tree1.root.parent) == tree1.find(tree1.keys().get(j), tree1.root, tree1.root.parent).parent.left || tree1.find(tree1.keys().get(j), tree1.root, tree1.root.parent) == tree1.find(tree1.keys().get(j), tree1.root, tree1.root.parent).parent.right);
                    }
                }
            }
        }




        // Remove
        for (int i = 0; i != 400; i++) {
            int test = new Random().nextInt(tree1.keys().size());
            int checker = tree1.keys().get(test);
            tree1.remove(tree1.find(tree1.keys().get(test), tree1.root, tree1.root.parent).data);

            assertFalse(tree1.contains(checker));
            assertFalse(tree1.keys().contains(checker));

            for (int j = 0; j != tree1.keys().size() - 1; j++) {
                assertTrue(tree1.keys().get(j) < tree1.keys().get(j + 1));
                if (tree1.get_height(tree1.find(tree1.keys().get(j), tree1.root, tree1.root.parent)) == 0) {
                    assertTrue(tree1.find(tree1.keys().get(j), tree1.root, tree1.root.parent).left == null && tree1.find(tree1.keys().get(j), tree1.root, tree1.root.parent).right == null);
                }
                if (tree1.find(tree1.keys().get(j), tree1.root, tree1.root.parent).isLeaf()) {
                    assertTrue(tree1.find(tree1.keys().get(j), tree1.root, tree1.root.parent).height == 0);}
                assertTrue(tree1.find(tree1.keys().get(j), tree1.root, tree1.root.parent).height == Math.max(tree1.get_height(tree1.find(tree1.keys().get(j), tree1.root, tree1.root.parent).left), tree1.get_height(tree1.find(tree1.keys().get(j), tree1.root, tree1.root.parent).right)) + 1);
                assertTrue(Math.abs(tree1.get_height(tree1.find(tree1.keys().get(j), tree1.root, tree1.root.parent).left) - tree1.get_height(tree1.find(tree1.keys().get(j), tree1.root, tree1.root.parent).right)) < 2);
                // Check parents
                if (tree1.find(tree1.keys().get(j), tree1.root, tree1.root.parent) != tree1.root) {
                    assertTrue(tree1.find(tree1.keys().get(j), tree1.root, tree1.root.parent).parent != null);
                    if (tree1.find(tree1.keys().get(j), tree1.root, tree1.root.parent).parent.left != null && tree1.find(tree1.keys().get(j), tree1.root, tree1.root.parent).parent.right != null) {
                        assertTrue(tree1.find(tree1.keys().get(j), tree1.root, tree1.root.parent) == tree1.find(tree1.keys().get(j), tree1.root, tree1.root.parent).parent.left || tree1.find(tree1.keys().get(j), tree1.root, tree1.root.parent) == tree1.find(tree1.keys().get(j), tree1.root, tree1.root.parent).parent.right);
                    }
                }
            }
        }
    }


}
