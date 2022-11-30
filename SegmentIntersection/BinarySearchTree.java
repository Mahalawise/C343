import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiPredicate;

import static java.lang.Integer.max;

/**
 * TODO: This is your first major task.
 * <p>
 * This class implements a generic unbalanced binary search tree (BST).
 */

public class BinarySearchTree<K> implements OrderedSet<K> {

    /**
     * A Node is a Location (defined in OrderedSet.java), which means
     * that it can be the return value of a search on the tree.
     */

    public class Node implements Location<K> {

        protected K data;
        protected Node left, right;
        protected Node parent;
        protected int height;

        /**
         * Constructs a leaf node with the given key.
         */
        public Node(K key) {
            this(key, null, null);
        }

        /**
         * TODO
         * <p>
         * Constructs a new node with the given values for fields.
         */
        public Node(K data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
            this.parent = null;
            this.height = 0;
        }

        /*
         * Provide the get() method required by the Location interface.
         */
        @Override
        public K get() {
            return this.data;
        }

        /**
         * Return true iff this node is a leaf in the tree.
         */
        protected boolean isLeaf() {
            return left == null && right == null;
        }

        /**
         * TODO
         * <p>
         * Performs a local update on the height of this node. Assumes that the
         * heights in the child nodes are correct. Returns true iff the height
         * actually changed. This function *must* run in O(1) time.
         */
        protected boolean updateHeight() {
            int oldHeight = this.height;
            int setHeight = max(get_height(this.left), get_height(this.right)) + 1;
            boolean toReturn = oldHeight != setHeight;
            this.height = setHeight;
            return toReturn;
        }

        /**
         * TODO
         * <p>
         * Returns the location of the node containing the inorder predecessor
         * of this node.
         */
        public Node next() {
            if (this.right == null) {
                return this.nextAncestor();
            }
            return this.right.first();
        }

        /**
         * TODO
         * <p>
         * Returns the location of the node containing the inorder successor
         * of this node.
         */
        public Node previous() {
            if (this.left == null) {
                return this.prevAncestor();
            }
            return this.left.last();
        }

        /**
         * TODO
         * <p>
         * This method should return the closest ancestor of node q
         * whose key is less than q's key. It is not necessary to
         * to perform key comparisons to implement this method.
         */
        public Node prevAncestor() {
            Node n = this;
            while (n.parent != null) {
                if (n == n.parent.right) {
                    return n.parent;
                }
                n = n.parent;
            }
            return null;
        }

        /**
         * TODO
         * <p>
         * This method should return the closest ancestor of node q
         * whose key is greater than q's key. It is not necessary to
         * to perform key comparisons to implement this method.
         */
        public Node nextAncestor() {
            Node n = this;
            while (n.parent != null) {
                if (n == n.parent.left) {
                    return n.parent;
                }
                n = n.parent;
            }
            return null;
        }

        /*
         * TODO
         * This method should return the node in the subtree rooted at 'this'
         * that has the smallest key.
         */
        public Node first() {
            Node n = this;
            if (n == null) {
                return null;
            }
            while (n.left != null) {
                n = n.left;
            }
            return n;
        }

        /*
         * TODO
         * This method should return the node in the subtree rooted at 'this'
         * that has the largest key.
         */
        public Node last() {
            Node n = this;
            if (n == null) {
                return null;
            }
            while (n.right != null) {
                n = n.right;
            }
            return n;
        }

        public boolean isAVL() {
            int h1, h2;
            h1 = get_height(left);
            h2 = get_height(right);
            return Math.abs(h2 - h1) < 2;
        }

        public String toString() {
            // Changed to inorder
            return toStringInorder(this);
        }

    }

    protected Node root;
    protected int numNodes;
    protected BiPredicate<K, K> lessThan;

    /**
     * Constructs an empty BST, where the data is to be organized according to
     * the lessThan relation.
     */
    public BinarySearchTree(BiPredicate<K, K> lessThan) {
        this.lessThan = lessThan;
    }

    /**
     * TODO
     * <p>
     * Looks up the key in this tree and, if found, returns the
     * location containing the key.
     */
    public Node search(K key) {
        if (this.root != null) {
            if (this.find(key, this.root, this.root.parent).data.equals(key)) {
                return this.find(key, this.root, this.root.parent);
            }
        }
        return null;
    }

    /**
     * TODO
     * <p>
     * Returns the height of this tree. Runs in O(1) time!
     */
    public int height() {
        if (root != null) {
            return root.height;
        }
        return 0;
    }

    /**
     * TODO
     * <p>
     * Clears all the keys from this tree. Runs in O(1) time!
     */
    public void clear() {
        this.root = null;
        this.numNodes = 0;
    }

    /**
     * Returns the number of keys in this tree.
     */
    public int size() {
        return numNodes;
    }

    /**
     * TODO
     * <p>
     * Inserts the given key into this BST, as a leaf, where the path
     * to the leaf is determined by the predicate provided to the tree
     * at construction time. The parent pointer of the new node and
     * the heights in all node along the path to the root are adjusted
     * accordingly.
     * <p>
     * Note: we assume that all keys are unique. Thus, if the given
     * key is already present in the tree, nothing happens.
     * <p>
     * Returns the location where the insert occurred (i.e., the leaf
     * node containing the key), or null if the key is already present.
     */
    public Node insert(K key) {
        System.out.println("Inserting: " + key);
        // If root is null, create root node
        if (this.root == null) {
            this.root = new Node(key);
            this.numNodes = 1;
            return this.root;
        }
        // Added
//        if (this.root.data == null) {
//            this.root.data = key;
//            this.numNodes = 1;
//            return this.root;
//        }
        //
        // Check if in tree
        if (this.contains(key)) {
            // Return node if in tree
            System.out.println("Already in");
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
        System.out.println(this);
        return toReturn;
    }

    /**
     * Returns a textual representation of this BST.
     */
    public String toString() {
        // changed to inorder
        return toStringInorder(root);
    }

    /**
     * Returns true iff the given key is in this BST.
     */
    public boolean contains(K key) {
        Node p = search(key);
        return p != null;
    }

    /**
     * TODO
     * <p>
     * Removes the key from this BST. If the key is not in the tree,
     * nothing happens.
     */
    public void remove(K key) {
////////////////////////
        System.out.println("Removing: " + key);
        if (this.root == null) {
            return;
        }
        if (this.contains(key)) {
            // Remove node
            Node toRemove = find(key, this.root, this.root.parent);
            Node update = null;
            // No children
            if (toRemove.left == null && toRemove.right == null) {
                // Clear if one node
                if (this.root == toRemove) {
                    this.root = null;
//                    this.root = new Node(null);
                    this.numNodes = 0;
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
        ////////////////////////////
        System.out.println(this);
    }

    /**
     * TODO
     * <p>
     * Returns a sorted list of all the keys in this tree.
     */
    public List<K> keys() {
        List<K> keyList = new ArrayList<K>();
        if (this.root == null) {
            return keyList;
        }
        Node curr = this.root.first();
        while (curr != null) {
            keyList.add(curr.data);
            curr = curr.next();
        }
        return keyList;
    }

    /**
     * Finds the node with the specified key, or if there is none, the parent of
     * where such a node would be.
     * @param key
     * @param curr  The current node.
     * @param parent  The parent of the current node.
     * @return A node whose data == key or else the parent of where the node would be.
     */
    protected Node find(K key, Node curr, Node parent) {
        if (curr == null)
            return parent;
        else if (lessThan.test(key, curr.data))
            return find(key, curr.left, curr);
        else if (lessThan.test(curr.data, key))
            return find(key, curr.right, curr);
        else
            return curr;
    }

    private String toStringInorder(Node p) {
        if (p == null)
            return ".";
        String left = toStringInorder(p.left);
        if (left.length() != 0) left = left + " ";
        String right = toStringInorder(p.right);
        if (right.length() != 0) right = " " + right;
        String data = p.data.toString();
        // return "(" + left + data + right + ")";
        return "(" + left + data + "[" + p.height + "]" + right + ")";
    }

    private String toStringPreorder(Node p) {
        if (p == null)
            return ".";
        String left = toStringPreorder(p.left);
        if (left.length() != 0) left = " " + left;
        String right = toStringPreorder(p.right);
        if (right.length() != 0) right = " " + right;
        String data = p.data.toString();
        return "(" + data + "[" + p.height + "]" + left + right + ")";
    }

    /**
     * The get_height method returns the height of the Node n, which may be null.
     */
    protected int get_height(Node n) {
        if (n == null) return -1;
        else return n.height;
    }
}
