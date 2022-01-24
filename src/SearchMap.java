public class SearchMap {
    // The file SearchMap.java contains the “client” code – the main function that handles command-line options and file read and write -- that uses a FlightMap. 
    private FlightMap flightMap;
    char originCity = '';


    public static void main(String args[]) {
        // read in the flight information from input file
        readInFile(args[1]);
        String outStr = flightMap.outputFlights();
        writeOutFile(args[2], outStr);
    }
    

    /** read in flight information from input file */
    public void readInFile(String fileIn) {
        String s = "";
        int dirCost = 0;
        char srcCity = '';
        char destCity = '';

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileIn));
            // read in first line to get origin city
            originCity = br.readLine().charAt(0);
            flightMap = new FlightMap(originCity);
            
            // while loop reads each line of input file
            while ((s = br.readLine()) != null) {
                srcCity = s.charAt(0);
                destCity = s.charAt(1);
                dirCost = Integer.parseInt(s.substring(2));
                
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
    public void writeOutFile(String fileOut, String trips) {
        String [] sepTrips = trips.split("\n");

        try {
            // writing to the output file
            BufferedWriter writer = new BufferedWriter(fileOut);
            writer.write("Destination\tFlight Route from " + originCity + "\tTotal Cost");
            // for loop goes through each trip and writes the details to the output file
            for (String s : sepTrips) {
                String [] tripDeets = s.split(" ");
                writer.write("%15" + tripDeets[0] + "%23" + tripDeets[1] + tripDeets[2]);
            }
        writer.close();
        }
        catch (Exception e) {
            e.printStackTrace()
        }
    }
}
