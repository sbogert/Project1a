include java.util.Map;
include java.util.HashMap;


public class FlightMap {
    // The FlightMap class, implemented in FlightMap.java, stores a map and provides operations that facilitate a search of the map. Feel free to implement FlightMap in any way you like.

    private Map<char, Destination[]> flightMap;
    private char originCity;
    private String outStr = "";


    /** constructor for FlightMap */
    public void FlightMap(char originCity) {
        flightMap = new HashMap<char, Destination[]>();
        this.originCity = originCity;
    }


    /** addFlight adds flights to the flight map */
    public void addFlight(char srcCity, Destination dest) {
        // if source city not already in map, create key value pair
        if (flightMap.containsKey(srcCity) == NULL) {
            // the value is an empty ArrayList
            flightMap.put(srcCity, new ArrayList<Destination>());
        }
        // add the destination to the list of destinations for the current key
        flightMap.get(srcCity).add(dest);
    }


    /** findFlight looks for all possible flight paths */
    public void findFlight() {
        Destination src = new Destination(originCity, 0);
        Destination[] toSearch;     // list of cities to search
        Destination[] srcValues;        // list of values for each src key
        toSearch.add(src);
        
        // while loop continues until all valid cities have been searched for possible flights
        while (!toSearch.isEmpty()) {
            // if the city is in the map and has direct flights
            if ((srcValues = flightMap.get(src)) != null) {
                // add all direct flights to search list and send to output
                for (Destination dest: srcValues) {
                    dest.addCost(src.getCost());
                    dest.addSrcCity(src.getDestCity());
                    toSearch.add(dest);
                    putFlight(dest);
                }
                // remove the searched city from the flightMap
                flightMap.remove(src.getDestCity());
            }
            // remove city from list of cities to search
            toSearch.remove(0);
            src = toSearch[0];
        }
    }


    /** adds next trip to string of all possible flights */
    public void putFlight(Destination dest) {
        outStr += dest.getCity() + " " + dest.getSrcCities() + " " + dest.getCost() + "\n";
    }

    /** return the string of all possible trips */
    public String outputFlights() {
        return outStr;
        // maybe for outputting to file, read each part of the string that is separated by spaces
    }
}
