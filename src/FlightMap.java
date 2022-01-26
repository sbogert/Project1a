import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class FlightMap {
    // The FlightMap class, implemented in FlightMap.java, stores a map and provides operations that facilitate a search of the map. Feel free to implement FlightMap in any way you like.

    private Map<Character, ArrayList<Destination>> flightMap;
    private char originCity;
    private String outStr = "";


    /** constructor for FlightMap */
    public FlightMap(char originCity) {
        flightMap = new HashMap<Character, ArrayList<Destination>>();
        this.originCity = originCity;
    }


    /** addFlight adds flights to the flight map */
    public void addFlight(Character srcCity, Destination dest) {
        // if source city not already in map, create key value pair
        if (flightMap.containsKey(srcCity) == false) {
            // the value is an empty ArrayList
            flightMap.put(srcCity, new ArrayList<Destination>());
        }
        // add the destination to the list of destinations for the current key
        flightMap.get(srcCity).add(dest);
    }


    /** findFlight looks for all possible flight paths */
    public void findFlight() {
        Destination src = new Destination(originCity, 0);
        Stack<Destination> toSearch = new Stack<Destination>();     // stack of cities to search with dfs
        ArrayList<Destination> dirFlights;     // list of values for each src key
        Set<Character> visited = new HashSet<Character>();  // set of all cities that are a destination
        toSearch.push(src);
        src.addSrcCity(String.valueOf(src.getCity()));
        
        // while loop continues until all valid cities have been searched for possible flights
        do {
            src = toSearch.pop();
            // if the city is in the map and has direct flights
            if ((dirFlights = flightMap.get(src.getCity())) != null) {
                // add all direct flights to search list and send to output
                for (Destination dest: dirFlights) {
                    if (visited.contains(dest.getCity()) == false) {
                        visited.add(dest.getCity());
                        dest.addCost(src.getCost());
                        dest.addSrcCity(String.valueOf(src.getSrcCities()) + String.valueOf(dest.getCity()));    
                        toSearch.push(dest);
                        putFlight(dest);
                    }
                }
                // remove the searched city from the flightMap
                flightMap.remove(src.getCity());
            }
            // remove city from list of cities to search
            // toSearch.remove(0);
           
        }
        while (toSearch.isEmpty() != true);
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
