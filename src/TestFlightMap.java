import org.junit.Test;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class TestFlightMap {
    private FlightMap tester;
    private int input;
    private int expectedOutput;

    
    /** Constructor method to accept each input-output pair*/
    public TestFlightMap (int in, int expectedOut) {
    this.input = in;
    this.expectedOutput = expectedOut;
    }


    @Before /** Set up method to create the test fixture */
    public void initialize() { 
        tester = new FlightMap(); 
    }


    @Parameterized.Parameters /** Store the test data */
    public static Collection<Object[]> valuePairs() {
        return Arrays.asList(new Object[][] { { 0, 0 }, { 1, 1 }, { 2, 1 }, { 3, 1 }, { 100, 10 } });
    }

    @Test /** Parameterized JUnit test method*/
    public void testIsqrt() {
        assertEquals("square root for " + input + " ", expectedOutput, tester.isqrt(input));
    }
}

