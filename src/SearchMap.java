import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/** Contains the main method, also reads and writes the input and output files */
public class SearchMap {
    
    private static FlightMap flightMap;
    static char originCity = ' ';

    
    /** Main function thathandles command-line information and file read and write
     * 
     * @param args[] - Command line arguments, which are the locations of the input and output files
     */
    public static void main(String args[]) {
        try {
            readInFile(args[0]);        /* read in the direct flights */
            flightMap.findFlight();     /* create a flight map and routes */
            String outStr = flightMap.outputFlights();      
            writeOutFile(args[1], outStr);     /* write routes to output file */
        }
        catch (FileNotFoundException fnfe) {
            System.out.println("\nThe file could not be found\n");
        }      
        catch (IOException ioe) {
            System.out.println("\nIOException thrown\n");
        }  
    }
    

    /** Read in the flight information from input file
     * 
     * @param fileIn - Name or location of the input file containing the flight information
     * 
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void readInFile(String fileIn) throws FileNotFoundException, IOException {
        String s = "";
        int dirCost = 0;
        Character srcCity = ' ';
        char destCity = ' ';

        BufferedReader br = new BufferedReader(new FileReader(fileIn));
        
        /* read in first line to get the origin city */
        originCity = br.readLine().charAt(0);
        flightMap = new FlightMap(originCity);
        
        /* loop reads each line of the input file to create a flight map */
        while ((s = br.readLine()) != null) {
            srcCity = s.charAt(0);
            destCity = s.charAt(2);
            dirCost = Integer.parseInt(s.substring(4));
            
            /* add flight to flight map */
            Destination dest = new Destination(destCity, dirCost);
            flightMap.addFlight(srcCity, dest);
        }
        br.close();
    }


    /** Writes the flight information to the output file
     * 
     * @param fileOut - Name or location of file to write output to
     * @param trips - String of all flight routes and costs 
     * 
     * @throws IOException
     */
    public static void writeOutFile(String fileOut, String trips) throws IOException {
        
        /* array of strings, each string is a line in output.txt */
        String [] sepTrips = trips.split("\n");

        /* writing to the output file */
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileOut));
        writer.write("Destination\t\tFlight Route from " + originCity + "\t\tTotal Cost\n");
        
        /* loop separates the details of each trip and writes to output */
        for (String s : sepTrips) {
            String [] tripDeets = s.split(" ");
            writer.write(tripDeets[0] + "\t\t\t\t"+ tripDeets[1]);
            
            /* formatting the spacing */
            for (int i = 0; i < 24-tripDeets[1].length(); i++) {
                writer.write(" ");
            }
            writer.write("$" + tripDeets[2] + "\n");
        }
        writer.close();
    }
}
