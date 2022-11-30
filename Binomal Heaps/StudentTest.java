import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Random;
import java.util.function.BiPredicate;

public class StudentTest {
    @Test
    public void test() throws Exception{
        BiPredicate<Integer,Integer> a = (Integer x, Integer y) -> x<=y;
        BinomialQueue<Integer> b = new BinomialQueue<>(a);
        BiPredicate<Integer,Integer> c = (Integer x, Integer y) -> x<=y;
        BinomialQueue<Integer> d = new BinomialQueue<>(c);

        BiPredicate<Integer,Integer> e = (Integer x, Integer y) -> x<=y;
        BinomialQueue<Integer> f = new BinomialQueue<>(e);
        Random random = new Random();
        //push
        for (int i=0; i<100; i++) {
            int key = random.nextInt(100);
            b.push(key);
            assertTrue(b.isHeap());
        }
        //pop
        for (int i=0; i<50; i++) {
            b.pop();
            assertTrue(b.isHeap());
        }
        for (int i=0; i<1000; i++) {
            int key = random.nextInt(10000);
            d.push(key);
            assertTrue(d.isHeap());
        }
        for (int i=0; i<1000; i++) {
            int key = random.nextInt(10000);
            f.push(key);
            assertTrue(f.isHeap());
        }
    }
}
