public class Search {

    public static int find_first_true(boolean[] A, int begin, int end) {
        while(begin != end) {
            if (A[begin]== true){
                return begin;
            }
            else{
                begin++;
            }
        }
        return end;
    }

    public static int find_first_equal(int[] A, int x) {
        boolean[] B = new boolean[A.length];
        for (int i=0; i!=A.length;i++){
            if (A[i]==x){
                B[i]=true;
            }
            else{
                B[i]= false;
            }
        }
        return find_first_true(B,0,B.length);
    }

    public static int find_first_true_sorted(boolean[] A, int begin, int end) {
        if (A.length==0){return end;}/// making sure it is not empty, if so then return
        else if(A.length==1){
            if (A[0]==true){// checks if it's only one
                return 0;//position of the true
            }
            else{
                return end;//no true return the end
            }
        }
        else{
            while(begin!=end){
                //midpoint
                int i= (end+begin)/2;
                //needs to see if it is the first true,
                if (A[i]==true && A[i-1]==false ){
                    return i;
                }
                else if(A[i]==true){
                    //check if beginning is true so that it doesn't go out of bounds
                   if (A[begin]==true) {
                       return begin;
                   }
                   else {
                       end = i - 1;
                   }
                }
                else{
                        begin=i+1;
                }
            }
        }
        return end;
    }
}

