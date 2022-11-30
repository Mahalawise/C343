import java.util.Comparator;

/**
 * TODO: Complete the implementation of this class.
 *
 * A HuffmanTree represents a variable-length code such that the shorter the
 * bit pattern associated with a character, the more frequently that character
 * appears in the text to be encoded.
 */

public class HuffmanTree {

    class Node {
        protected char key;
        protected int priority;
        protected Node left, right;

        public Node(int priority, char key) {
            this(priority, key, null, null);
        }

        public Node(int priority, Node left, Node right) {
            this(priority, '\0', left, right);
        }

        public Node(int priority, char key, Node left, Node right) {
            this.key = key;
            this.priority = priority;
            this.left = left;
            this.right = right;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }

        public String toString() {
            if (isLeaf()) {
                return String.format("[%s:%d]", key, priority);
            } else {
                String l = left.toString();
                String r = right.toString();
                return String.format("(%s %s)", l, r);
            }
        }
    }

    private Node root;

    /**
     * TODO
     *
     * Creates a HuffmanTree from the given frequencies of letters in the
     * alphabet using the algorithm described in lecture.
     */
    public HuffmanTree(FrequencyTable charFreqs) {

    }

    /**
     * TODO
     *
     * Returns the character associated with the prefix of bits.
     *
     * @throws DecodeException if bits does not match a character in the tree.
     */
    public char decodeChar(String bits) {
        return '\0';
    }

    /**
     * TODO
     *
     * Returns the bit string associated with the given character. Must
     * search the tree for a leaf containing the character. Every left
     * turn corresponds to a 0 in the code. Every right turn corresponds
     * to a 1. This function is used by CodeBook to populate the map.
     *
     * @throws EncodeException if the character does not appear in the tree.
     */
    public String lookup(char ch) {
        return "";
    }

    public String toString() {
        if (root == null)
            return "";
        else
            return root.toString();
    }
}

