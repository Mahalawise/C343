import java.util.Comparator;
import java.util.Map;
import java.util.Objects;

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
        //creating a forest of nodes comparator
        Heap<Node> forest = new Heap<Node>(new Compares());
        //get the characters of the frequency table
        if (!charFreqs.isEmpty()) {
            for (Map.Entry<Character,Integer> chars : charFreqs.entrySet()) {
                forest.push(new Node(chars.getValue(),chars.getKey()));
            }
            ///creating the huffman tree
            Node combines = null;
            //root node
            if(forest.size()==1){
                Node n = forest.pop();
                this.root = new Node(n.priority,n.key);
            }
            while(forest.size() != 1){
                Node a = forest.pop();
                Node b = forest.pop();
                combines = new Node(a.priority+b.priority, a,b);
                forest.push(combines);
            }
            this.root=combines;
            System.out.println(forest.toString());
        }
    }

    /**
     * TODO
     *
     * Returns the character associated with the prefix of bits.
     *
     * @throws DecodeException if bits does not match a character in the tree.
     */
    public char decodeChar(String bits) {
        //setting node to root
        Node curr = this.root;
        if(bits.isEmpty() && curr!=null){
            return curr.key;
        }
        for(char c: bits.toCharArray()){
            if(curr==null){
                throw new DecodeException(bits);
            }

            if (curr.isLeaf() && curr != this.root){
                return curr.key;
            }
            //left path
            if(c == '0'){
                curr = curr.left;
            }
            //right path
            if(c == 1){
                curr=curr.right;
            }
        }

        if(curr.isLeaf()&& curr != this.root){
            return curr.key;
        }
        throw new DecodeException(bits);
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
        //recursion
        if(this.root.key==ch){
            return "";
        }
        String character = recursion(this.root,ch,"");
        if(character!=""){
            return character;
        }
        throw new EncodeException(ch);
    }


    public String recursion(Node n,char c, String s){
        if(n ==null){
            return "";
        } else if(n.key == c){
            return s;
        } else {
            String left = recursion(n.left, c, s + "0");
            String right = recursion(n.right, c, s + "1");
            if (!Objects.equals(right, "")){
                return left;
            }
            if(!Objects.equals(left,"")){
                return right;
            }
        }
        return "";
    }

    public String toString() {
        if (root == null)
            return "";
        else
            return root.toString();
    }

    ///must create a comparator class in order to use the heap class
    public static class Compares implements Comparator<Node>{
        ///creating a min heap
        public int compare(Node o1, Node o2) {
            return o1.priority- o2.priority;
        }
    }
}

