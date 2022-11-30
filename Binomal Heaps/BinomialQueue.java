import java.util.function.BiPredicate;

class BinomialHeap<K> {
    K key;
    int height;
    PList<BinomialHeap<K>> children;
    BiPredicate<K, K> lessEq;

    BinomialHeap(K k, int h, PList<BinomialHeap<K>> kids, BiPredicate<K, K> le) {
        this.key = k;
        this.height = h;
        this.children = kids;
        this.lessEq = le;
    }

    /*
     * @precondition this.height == other.height
     */
    BinomialHeap<K> link(BinomialHeap<K> other) {
        if (this.height != other.height)
            throw new UnsupportedOperationException("attempt to link trees of different height");
        if (lessEq.test(other.key, this.key)) {
            PList<BinomialHeap<K>> kids = PList.addFront(other, this.children);
            return new BinomialHeap<>(this.key, this.height + 1, kids, lessEq);
        } else {
            PList<BinomialHeap<K>> kids = PList.addFront(this, other.children);
            return new BinomialHeap<>(other.key, other.height + 1, kids, lessEq);
        }
    }

    /**
     * TODO
     * <p>
     * The isHeap method checks whether or not the subtree of this node
     * satisfies the heap property.
     */
    boolean isHeap() {
        // If only one node, return true
        if(this.height !=0) {
            ///base case if children is greater than parent return false
            if (!(lessEq.test(PList.getFirst(this.children).key,this.key))) {
                return false;
            } else {
                //must check the children's children
                if(PList.getFirst(this.children).children != null){
                    return PList.getFirst(PList.getFirst(this.children).children).isHeap();
                }
                if(PList.getNext(this.children) != null){
                    return PList.getFirst(PList.remove(PList.getFirst(this.children),children)).isHeap();
                }
            }
        }
        return true;
    }


    public String toString() {
        String ret = "(" + key.toString();
        if (children != null)
            ret = ret + " " + children.toString();
        return ret + ")";
    }

}

public class BinomialQueue<K> {
    PList<BinomialHeap<K>> forest;
    BiPredicate<K, K> lessEq;

    public BinomialQueue(BiPredicate<K, K> le) {
        forest = null;
        lessEq = le;
    }

    public void push(K key) {
        BinomialHeap<K> heap = new BinomialHeap<>(key, 0, null, lessEq);
        this.forest = insert(heap, this.forest);
    }

    public K pop() {
        BinomialHeap<K> max = PList.find_max(this.forest, (h1,h2) -> this.lessEq.test(h1.key, h2.key));
//        System.out.println("removing: " + max);
        this.forest = PList.remove(max, this.forest);
        PList<BinomialHeap<K>> kids = PList.reverse(max.children, null);
        this.forest = merge(this.forest, kids);
        return max.key;
    }

    public boolean isEmpty() {
        return forest == null;
    }

    /**
     * TODO
     * The isHeap method returns whether or not the Binomial Queue (a forest of Binomial Trees)
     * satisfies the heap property.
     */
    public boolean isHeap() {
        if (this.isEmpty()) {
            return true;
        }
        PList<BinomialHeap<K>> forest = this.forest;
        BinomialHeap<K> node = PList.getFirst(forest);
        while (node!= null) {
            if (!(node.isHeap())) {
                return false;
            }
            if (PList.getNext(forest) != null) {
                node = PList.getFirst(PList.getNext(forest));
                forest = PList.getNext(forest);
            } else {
                break;
            }
        }
        return true;
    }

    public String toString() {
        if (this.forest == null)
            return "";
        else
            return this.forest.toString();
    }

    /**********************************
     * Helper functions
     */

    /*
     * Take forest and ensure matching height trees are combined
     */
    static <K> PList<BinomialHeap<K>> chain(PList<BinomialHeap<K>> n) {
        while (PList.getNext(n) != null) {
            if (PList.getFirst(n).height == PList.getFirst(PList.getNext(n)).height) {
                BinomialHeap<K> first= PList.getFirst(n).link(PList.getFirst(PList.getNext(n)));
                n = PList.addFront(first, PList.getNext(PList.getNext(n)));
            } else {
                break;
            }
        }
        return n;
    }

    /**
     * TODO
     * The insert method is analogous to binary addition. That is,
     * it inserts the node n into the list ns to produce a new list
     * that is still sorted by height.
     *
     * @param <K> The type of the keys.
     * @param n   The node to insert (must not be null).
     * @param ns  The binomial forest into which to insert, ordered by height. (may be null)
     * @return A new binomial forest that includes the new node.
     */
    static <K> PList<BinomialHeap<K>>
    insert(BinomialHeap<K> n, PList<BinomialHeap<K>> ns) {
        if (ns == null) {
            return PList.addFront(n, null);
        } else {
            ns = PList.addFront(n, ns);
            ns = chain(ns);
            return ns;
        }
    }

    /**
     * TODO
     * The merge method is analogous to the merge part of merge sort. That is,
     * it takes two lists that are sorted (by height) and returns a new list that
     * contains the elements of both lists, and the new list is sorted by height.
     *
     * @param ns1
     * @param ns2
     * @return A list that is sorted and contains all and only the elements in ns1 and ns2.
     */
    static <K> PList<BinomialHeap<K>>
    merge(PList<BinomialHeap<K>> ns1, PList<BinomialHeap<K>> ns2) {
        BinomialHeap<K> ns1key;
        BinomialHeap<K> ns2key;
        if (ns1 == null) {
            return ns2;
        } else{
           ns1 = chain(ns1);
           ns1key = PList.getFirst(ns1);
        }
        if (ns2 == null){
            return ns1;
        }else{
            ns2 = chain(ns2);
            ns2key = PList.getFirst(ns2);
        }
       if(ns1key.height < ns2key.height){
           return new PList<BinomialHeap<K>>(ns1key, merge(PList.getNext(ns1),ns2));
       } else if (ns1key.height > ns2key.height){
           return new PList<BinomialHeap<K>>(ns2key, merge(ns1,PList.getNext(ns2)));
       } else{
           ns1= PList.addFront(ns2key.link(ns1key), PList.getNext(ns1));
           return merge(ns1,PList.getNext(ns2));
       }
    }
}
