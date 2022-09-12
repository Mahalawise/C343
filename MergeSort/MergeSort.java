public class MergeSort {
    static Node sort(Node N) {
        //return new linked list
        //leave the original unchanged
        //split the node in half
        //sort the halves
        //run it through merge
        if(N==null || N.next == null){
            return N;
        }
        ///finding the length, diving it by 2
        int length=((Node.length(N))/2);
        Node nleft=Node.copy_range(Node.nth_node(N,0),Node.nth_node(N,length));
        Node nright= Node.copy_range(Node.nth_node(N,length),Node.nth_node(N,Node.length(N)));
        return merge(sort(nleft),sort(nright));

    }
    static Node sort_in_place(Node N){
        //change the pointers
        if(N==null || N.next == null){
            return N;
        }
        int length=((Node.length(N))/2);
        Node nleft=N;
        Node nright=Node.nth_node(N,length);
        Node.nth_node(N,length-1).next=null;

        return merge_in_place(sort_in_place(nleft),sort_in_place(nright));
    }
    static Node merge(Node A, Node B) {
        //compare the nodes
        //merge them
        ///return new linked list
        Node merged;
        //base case
        if (A == null) {
            return B;
        }
        if (B == null){
            return A;
        }
        if(A.data <= B.data){
            //make recursive for efficiency
            merged=new Node(A.data,merge(A.next,B));
        }
        else{
            merged=new Node(B.data,merge(A,B.next));
        }
        return merged;

    }
    static Node merge_in_place(Node A, Node B) {
        //rearranges the node pointer
        Node merged;
        //base case
        if (A == null) {
            return B;
        }
        if (B == null){
            return A;
        }
        if(A.data <= B.data){
            //make recursive for efficiency
            merged=A;
            merged.next=merge_in_place(A.next,B);
        }
        else{
            merged=B;
            merged.next=merge_in_place(A,B.next);
        }
        return merged;
    }
}
