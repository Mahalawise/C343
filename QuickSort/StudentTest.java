import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {
    @Test
    public void test(){
        Integer [] A= {20,6,10,78,50,40,13,28,3};
        ArraySequence B = new ArraySequence<Integer>(A);
        Iterator<Integer> bBegin= B.begin();
        Iterator<Integer> bEnd= B.end();
        QuickSort.quicksort(bBegin,bEnd);
        int length=1;
        while(length<A.length){
            Integer first = bBegin.get();
            bBegin.advance();
            Integer next = bBegin.get();
            assertTrue(first<=next);
            length++;
        }


    }

}
