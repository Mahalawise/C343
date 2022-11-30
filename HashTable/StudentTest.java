import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Random;

public class StudentTest {
    @Test
    public void test() throws Exception{
        HashTable<Integer,Integer> h = new HashTable<>(17);
        Random random = new Random();
        HashTable<Integer,Integer> c = new HashTable<>(17);
        c.put(70,100);
        c.put(80,16);
        c.put(60,60);
        c.put(30,190);
        c.put(10,10);
        //testing insert
        for (int i=0; i<100; i++){
            int key = random.nextInt(10000);
            int value = random.nextInt(10000);
            h.put(key,value);
            assertTrue(h.containsKey(key));
            assertEquals(value,h.get(key));
        }

        //testing remove
        int removed=0;
        while (removed !=70){
//            int remove= 70;
            int remove = random.nextInt(10000);
            if(h.containsKey(remove)) {
                h.remove(remove);
                assertFalse(h.containsKey(remove));
                removed++;
            }
        }
    }

}
