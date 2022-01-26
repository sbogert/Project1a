import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SearchMap {
    private static FlightMap flightMap;
    static char originCity = ' ';

    
    /** handles command-line informationadn file read and write */
    public static void main(String args[]) {
        System.out.println(args[0]);
        // read in the flight information from input file
        readInFile(args[0]);
        flightMap.findFlight();
        String outStr = flightMap.outputFlights();
       
        writeOutFile(args[1], outStr);
    }
    

    /** read in flight information from input file */
    public static void readInFile(String fileIn) {
        String s = "";
        int dirCost = 0;
        Character srcCity = ' ';
        char destCity = ' ';

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileIn));
            // read in first line to get origin city
            originCity = br.readLine().charAt(0);
            flightMap = new FlightMap(originCity);
            
            // while loop reads each line of input file
            while ((s = br.readLine()) != null) {
                srcCity = s.charAt(0);
                destCity = s.charAt(2);
                dirCost = Integer.parseInt(s.substring(4));
                
                // add to flight map
                Destination dest = new Destination(destCity, dirCost);
                flightMap.addFlight(srcCity, dest);
            }
            br.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("\nThe file " + fileIn +  " could not be found");
        }      
        catch (IOException e) {
            System.out.println("\nIOException thrown\n");
        }  
        catch (NullPointerException e) {
            System.out.println("Object information is incomplete");
        }
        catch (NumberFormatException e) {
            System.out.println("Number format exception thrown");
        }
        catch (Exception e) {
            System.out.println("\nother Exception thrown\n");
            e.printStackTrace();
        } 
    }


    /** function that writes the solution to the output file */
    public static void writeOutFile(String fileOut, String trips) {
        String [] sepTrips = trips.split("\n");

        try {
            // writing to the output file
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileOut));
            writer.write("Destination\t\tFlight Route from " + originCity + "\t\tTotal Cost\n");
            // for loop goes through each trip and writes the details to the output file
            for (String s : sepTrips) {
                String [] tripDeets = s.split(" ");
                writer.write(tripDeets[0] + "\t\t\t\t"+ tripDeets[1]);
                // formatting spaces
                for (int i = 0; i < 23-tripDeets[1].length(); i++) {
                    writer.write(" ");
                }
                writer.write("$" + tripDeets[2] + "\n");
            }
        writer.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
