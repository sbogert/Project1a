import org.junit.Test;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class TestFlightMap {
    private FlightMap tester;
    private int input;
    private int expectedOutput;

    
    /** Constructor method to accept each input-output pair*/
    public TestFlightMap (char srcCity, char destCity, int cost, int expectedOut) {
    this.input = in;
    this.expectedOutput = expectedOut;
    }


    @Before /** Set up method to create the test fixture */
    public void initialize() { 
        tester = new FlightMap(); 
    }


    @Parameterized.Parameters /** Store test input and correct output */
    public static Collection<Object[]> valuePairs() {
        return Arrays.asList(new Object[][] { { 0, 0 }, { 1, 1 }, { 2, 1 }, { 3, 1 }, { 100, 10 } });
    }

    @Test /** Parameterized JUnit test method*/
    public void testOutputFlight() {
        assertEquals("square root for " + input + " ", expectedOutput, tester.outputFlight(input));
    }
}

