import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {

    @Test
    public void lookupHuff() {
        HuffmanTree ht = new HuffmanTree(new FrequencyTable("aaaabbc"));
        assertEquals("1", ht.lookup('a'));
        assertEquals("01", ht.lookup('b'));
        assertEquals("00", ht.lookup('c'));
    }

    @Test
    public void decodeHuff() {
        HuffmanTree ht = new HuffmanTree(new FrequencyTable("aaaabbc"));
        assertEquals('a', ht.decodeChar("1"));
        assertEquals('b', ht.decodeChar("01"));
        assertEquals('c', ht.decodeChar("00"));
    }

    /**
     * TODO
     *
     * Create many more tests to thoroughly exercise your HuffmanTree implementation.
     */

    /**********************************************************************************
     * When you've reached this point, run the Driver and start writing your report.
     **********************************************************************************/

}