import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class SearchTest {

    @Test
    public void test_find_first_true() {
        boolean[] A = {true, false, true, false, true};
        assertEquals(Search.find_first_true(A, 1, 3), 2);
    }

    @Test
    public void test_find_first_equal() {
        int[] A = {32, 11, 4, 5, 99, 5, 32, 75};
        int[] B = {1};
        int[] C = {0,1,1,1,1,1};
        int[] D= {};
        assertEquals(Search.find_first_equal(A, 5), 3);
        assertEquals(Search.find_first_equal(B, 5), 1);
        assertEquals(Search.find_first_equal(C, 1), 1);
        assertEquals(Search.find_first_equal(D, 1), 0);
    }

    @Test
    public void test_find_first_true_sorted() {
        boolean[] A = {false, false, true, true, true, true, true};
        assertEquals(Search.find_first_true_sorted(A,1,2 ), 2);
        assertEquals(Search.find_first_true_sorted(A,0,1 ), 1);
        assertEquals(Search.find_first_true_sorted(A,2,5 ), 2);
        boolean[] B = { };
        assertEquals(Search.find_first_true_sorted(B,1,2 ), 2);
        boolean[] C = {true};
        assertEquals(Search.find_first_true_sorted(C,0,2 ), 0);
        boolean[] D = {false};
        assertEquals(Search.find_first_true_sorted(D,0,2 ), 2);
        boolean[] E = {true,true,true,true,true};
        assertEquals(Search.find_first_true_sorted(E,0,4 ), 0);
    }
}
