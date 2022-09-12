import org.junit.jupiter.api.Test;

public class MergeSortTest {
    @Test
    public void test_sort(){
        Node K= new Node(0,(new Node( 5,new Node(7, new Node(4 ,null)))));
        Node L= new Node(0,(new Node( 4,new Node(5, new Node(7 ,null)))));
        Node.equals(MergeSort.sort(K),L);

        Node M= new Node(0,null);
        Node N= new Node(0,null);
        Node.equals(MergeSort.sort(M),N);

        Node O= new Node(0,(new Node( 27,new Node(-14, new Node(4 ,(new Node( -56,new Node(13, new Node(3 ,null)))))))));
        Node P= new Node(-56,(new Node( -14,new Node(0, new Node(3 ,(new Node( 4,new Node(13, new Node(27 ,null)))))))));
        Node.equals(MergeSort.sort(O),P);
    }
    @Test
    public void test_merge(){
        Node I= new Node(0,(new Node( 5,null)));
        Node J=new Node(4,(new Node( 7,null)));
        Node K= new Node(0,(new Node( 4,new Node(5, new Node(7 ,null)))));
        Node.equals(MergeSort.merge(I,J),K);

        Node L= new Node(1,null);
        Node M= new Node(0,null);
        Node N= new Node(0,new Node(1,null));
        Node.equals(MergeSort.merge(L,M),N);

        Node O= new Node(-14,(new Node( 0,new Node(4, new Node(27 ,null)))));
        Node P =new Node(-56,(new Node( 3,new Node(13, null))));
        Node Q= new Node(-56,(new Node( -14,new Node(0, new Node(3 ,(new Node( 4,new Node(13, new Node(27 ,null)))))))));
        Node.equals(MergeSort.merge(O,P),Q);
    }
    @Test
    public void test_sort_in_place(){
        Node K= new Node(0,(new Node( 5,new Node(7, new Node(4 ,null)))));
        Node L= new Node(0,(new Node( 4,new Node(5, new Node(7 ,null)))));
        Node.equals(MergeSort.sort_in_place(K),L);

        Node M= new Node(0,null);
        Node N= new Node(0,null);
        Node.equals(MergeSort.sort_in_place(M),N);

        Node O= new Node(0,(new Node( 27,new Node(-14, new Node(4 ,(new Node( -56,new Node(13, new Node(3 ,null)))))))));
        Node P= new Node(-56,(new Node( -14,new Node(0, new Node(3 ,(new Node( 4,new Node(13, new Node(27 ,null)))))))));
        Node.equals(MergeSort.sort_in_place(O),P);
    }
    @Test
    public void test_merge_in_place(){
        Node I= new Node(0,(new Node( 5,null)));
        Node J=new Node(4,(new Node( 7,null)));
        Node K= new Node(0,(new Node( 4,new Node(5, new Node(7 ,null)))));
        Node.equals(MergeSort.merge_in_place(I,J),K);

        Node L= new Node(1,null);
        Node M= new Node(0,null);
        Node N= new Node(0,new Node(1,null));
        Node.equals(MergeSort.merge_in_place(L,M),N);

        Node O= new Node(-14,(new Node( 0,new Node(4, new Node(27 ,null)))));
        Node P =new Node(-56,(new Node( 3,new Node(13, null))));
        Node Q= new Node(-56,(new Node( -14,new Node(0, new Node(3 ,(new Node( 4,new Node(13, new Node(27 ,null)))))))));
        Node.equals(MergeSort.merge_in_place(O,P),Q);
    }

}
