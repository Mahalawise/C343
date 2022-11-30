

import java.util.function.BiPredicate;

import static java.lang.Math.max;

/**
 * TODO: This is your second major task.
 * <p>
 * This class implements a height-balanced binary search tree,
 * using the AVL algorithm. Beyond the constructor, only the insert()
 * and remove() methods need to be implemented. All other methods are unchanged.
 */

public class AVLTree<K> extends BinarySearchTree<K> {

    /**
     * Creates an empty AVL tree as a BST organized according to the
     * lessThan predicate.
     */
    public AVLTree(BiPredicate<K, K> lessThan) {
        super(lessThan);
    }

    /**
     * TODO
     * Inserts the given key into this AVL tree such that the ordering
     * property for a BST and the balancing property for an AVL tree are
     * maintained.
     */
    public Node insert(K key) {
//        System.out.println("insert: " + key);
        // insert like normal
        // If root is null, create root node
        if (this.root == null) {
            this.root = new Node(key);
            this.numNodes = 1;
            return this.root;
        }
        // Check if in tree
        if (this.contains(key)) {
            // Return node if in tree
            return null;
        }
        Node addParent = find(key, this.root, this.root.parent);
        Node toReturn;

        // Add to correct location
        if (lessThan.test(key, addParent.data)) {
            addParent.left = new Node(key);
            addParent.updateHeight();
            addParent.left.parent = addParent;
            this.numNodes += 1;
            toReturn = addParent.left;
        } else {
            addParent.right = new Node(key);
            addParent.updateHeight();
            addParent.right.parent = addParent;
            this.numNodes += 1;
            toReturn = addParent.right;
        }
        Node temp = toReturn;
        while (temp != null) {
            temp.updateHeight();
            temp = temp.parent;
        }
        Node toCheck = toReturn;

        // check parent nodes for AVL

        while (toCheck != null) {
//            System.out.println("Checking: " + toCheck.get());
            if (!(toCheck.isAVL())) {
                // Check which fix is required
                // Fix node
                int loadFactor = (get_height(toCheck.left) - get_height(toCheck.right));
//                System.out.println("notAVL");

                // Left Left case
                if (loadFactor > 1 && lessThan.test(key, toCheck.left.data)) {
                    System.out.println("left left");
                    temp =  rightRotate(toCheck);
//                    while (temp != null) {
//                        temp.updateHeight();
//                        temp = temp.parent;
//                    }
//                    System.out.println(this);
                    return temp;
                }

                // Right Right Case
                else if (loadFactor < -1 && !(lessThan.test(key, toCheck.right.data))) {
//                    System.out.println("right right");
                    temp = leftRotate(toCheck);
//                    while (temp != null) {
//                        temp.updateHeight();
//                        temp = temp.parent;
//                    }
//                    System.out.println(this);
                    return temp;
                }

                // Left Right Case
                else if (loadFactor > 1 && !(lessThan.test(key, toCheck.left.data))) {
//                    System.out.println("left right");
                    toCheck.left = leftRotate(toCheck.left);
                    temp = rightRotate(toCheck);
//                    while (temp != null) {
//                        temp.updateHeight();
//                        temp = temp.parent;
//                    }
//                    System.out.println(this);
                    return temp;
                }

                // Right Left Case
                else if (loadFactor < -1 && lessThan.test(key, toCheck.right.data)) {
//                    System.out.println("right left");
                    toCheck.right = rightRotate(toCheck.right);
                    temp = leftRotate(toCheck);
//                    while (temp != null) {
//                        temp.updateHeight();
//                        temp = temp.parent;
//                    }
//                    System.out.println(this);
                    return temp;
                }
            }
            toCheck = toCheck.parent;
        }
        return null;
    }

    public void remove(K key) {
        // Remove like normal and check AVL from parent of removed node
//        System.out.println("Removing: " + key);
        if (this.root == null) {
            return;
        }
        Node update = null;
        Node test = null;
        if (this.contains(key)) {
            // Remove node
            Node toRemove = find(key, this.root, this.root.parent);
            test = toRemove.parent;
            // No children
            if (toRemove.left == null && toRemove.right == null) {
                // Clear if one node
                if (this.root == toRemove) {
                    this.clear();
                    return;
                } else {
                    // Remove node
                    if (toRemove.parent.left == toRemove) {
                        toRemove.parent.left = null;
                    } else {
                        toRemove.parent.right = null;
                    }
                    update = toRemove;
                }
                this.numNodes -= 1;
            }
            // One right child
            else if (toRemove.left == null) {
                // Replace data
                toRemove.data = toRemove.right.first().data;
                // get inOrder successor
                Node secRemove = toRemove.right.first();
                // Successor has right child
                if (secRemove.parent != toRemove) {
                    if (secRemove.right != null) {
                        secRemove.parent.left = secRemove.right;
                        secRemove.right.parent = secRemove.parent;
                        update = secRemove;//
                    } else {
                        secRemove.parent.left = null;
                        update = secRemove;//
                    }
                } else {
                    toRemove.right = secRemove.right;
                    if (toRemove.right != null) {
                        toRemove.right.parent = toRemove;
                    }
                    update = secRemove;
                }
                this.numNodes -= 1;
            }
            // One left child
            else if (toRemove.right == null) {
                // Replace data
                toRemove.data = toRemove.left.last().data;
                // get inOrder successor
                Node secRemove = toRemove.left.last();
                if (secRemove.parent != toRemove) {
                    if (secRemove.left != null) {
                        secRemove.parent.right = secRemove.left;
                        secRemove.left.parent = secRemove.parent;
                        update = secRemove;//
                    } else {
                        secRemove.parent.right = null;
                        update = secRemove;//
                    }
                } else {
                    toRemove.left = secRemove.left;
                    if (toRemove.left != null) {
                        toRemove.left.parent = toRemove;
                    }
                    update = secRemove;
                }
                this.numNodes -= 1;
            }
            // Two children
            else {
                // Replace data
                toRemove.data = toRemove.right.first().data;
                // get inOrder successor
                Node secRemove = toRemove.right.first();
                // Successor has right child
                if (secRemove.parent != toRemove) {
                    if (secRemove.right != null) {
                        secRemove.parent.left = secRemove.right;
                        secRemove.right.parent = secRemove.parent;
                        update = secRemove;//
                    } else {
                        secRemove.parent.left = null;
                        update = secRemove;//
                    }
                } else {
                    toRemove.right = secRemove.right;
                    if (toRemove.right != null) {
                        toRemove.right.parent = toRemove;
                    }
                    update = secRemove;
                }
                this.numNodes -= 1;

            }
            // Update heights
            Node temp = update;
            while (temp != null) {
                temp.updateHeight();
                temp = temp.parent;
            }
        }

        for (K k : this.keys()) {
            Node temp = this.find(k, this.root, this.root.parent) ;
            while (temp != null) {
                temp.updateHeight();
                temp = temp.parent;
            }
        }

        // ROTATE
        Node toCheck = test;
//        System.out.println(test);
        while (toCheck != null) {
//            System.out.println("Checking: " + toCheck.get());
            if (!(toCheck.isAVL())) {
                // Check which fix is required
                // Fix node
                int loadFactor = (get_height(toCheck.left) - get_height(toCheck.right));
//                System.out.println("notAVL");

                // Left Left case
                if (loadFactor > 1 && !(lessThan.test(toCheck.data, toCheck.left.data))) {
//                    System.out.println("left left");
                    rightRotate(toCheck);
//                    while (temp != null) {
//                        temp.updateHeight();
//                        temp = temp.parent;
//                    }
//                    System.out.println(this);
//                    return temp;
                }

                // Right Right Case
                else if (loadFactor < -1 && (lessThan.test(toCheck.data, toCheck.right.data))) {
//                    System.out.println("right right");
                    leftRotate(toCheck);
//                    while (temp != null) {
//                        temp.updateHeight();
//                        temp = temp.parent;
//                    }
//                    System.out.println(this);
//                    return temp;
                }

                // Left Right Case
                else if (loadFactor > 1 && (lessThan.test(toCheck.data, toCheck.left.data))) {
//                    System.out.println("left right");
                    toCheck.left = leftRotate(toCheck.left);
                    rightRotate(toCheck);
//                    while (temp != null) {
//                        temp.updateHeight();
//                        temp = temp.parent;
//                    }
//                    System.out.println(this);
//                    return temp;
                }

                // Right Left Case
                else if (loadFactor < -1 && !(lessThan.test(toCheck.data, toCheck.right.data))) {
//                    System.out.println("right left");
                    toCheck.right = rightRotate(toCheck.right);
                    leftRotate(toCheck);
//                    while (temp != null) {
//                        temp.updateHeight();
//                        temp = temp.parent;
//                    }
//                    System.out.println(this);
//                    return temp;
                }
            }
            toCheck = toCheck.parent;
        }

        // Update heights for all
        for (K k : this.keys()) {
            Node temp = this.find(k, this.root, this.root.parent) ;
            while (temp != null) {
                temp.updateHeight();
                temp = temp.parent;
            }
        }
    }

    public Node rightRotate(Node y) {
        Node x = y.left;
        Node T1 = y.right;
        Node T3 = x.left;
        Node T2 = x.right;
        K yData = y.data;
        K xData = x.data;

        y.data = xData;
        y.right = new Node(yData);
        y.right.parent = y;
        y.right.left = T2;
        if (T2 != null) {
            T2.parent = y.right;
            T2.updateHeight();
//            System.out.println(T2.height);
        }
        y.right.right = T1;
        if (T1 != null) {
            T1.parent = y.right;
            T1.updateHeight();
//            System.out.println(T1.height);
        }
        y.left = T3;
        if (T3 != null) {
            T3.parent = y;
            T3.updateHeight();
//            System.out.println(T3.height);
        }

        Node temp = y.right;
        while (temp != null) {
//            System.out.println("temp: " + temp.height);
            temp.updateHeight();
//            System.out.println(temp.get() + " " + temp.parent);
            temp = temp.parent;
        }
//        System.out.println("y: " + y.height);

        return y;
    }
    public Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = x.right.left;
        Node T1 = x.left;
        Node T3 = x.right.right;
        K yData = y.data;
        K xData = x.data;

        // Perform rotation
        x.data = yData;
        x.left = new Node(xData);
        x.left.parent = x;
        x.left.right = T2;
        if (T2 != null) {
            T2.parent = x.left;
            T2.updateHeight();
        }
        x.left.left = T1;
        if (T1 != null) {
            T1.parent = x.left;
            T1.updateHeight();
        }
        x.right = T3;
        if (T3 != null) {
            T3.parent = x;
            T3.updateHeight();
        }

        Node temp = x.left;
        while (temp != null) {
//            System.out.println(temp.height);
            temp.updateHeight();
            temp = temp.parent;
        }
//        System.out.println("X: " + x.height);


        return x;
    }
}