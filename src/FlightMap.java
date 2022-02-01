import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;


/** Stores a map and provides operations that facilitates a search of the map
 * 
 */
public class FlightMap {

    private Map<Character, ArrayList<Destination>> flightMap;
    private char originCity;
    private String outStr = "";


    /** FlightMap constructor
     * 
     * @param originCity - The origin city for all flight routes
     */
    public FlightMap(char originCity) {
        flightMap = new HashMap<Character, ArrayList<Destination>>();
        this.originCity = originCity;
    }


    /** Adds direct flights to the flight map 
     * 
     * @param srcCity - city that the direct flight leaves from 
     * @param dest - city that the direct flight arrives to
    */
    public void addFlight(Character srcCity, Destination dest) {
        /* if source city not already in map, create key value pair */
        if (flightMap.containsKey(srcCity) == false) {
            /* the value is an empty ArrayList */
            flightMap.put(srcCity, new ArrayList<Destination>());
        }
        /* add the destination to the current key's list of destinations */ 
        flightMap.get(srcCity).add(dest);
    }


    /** Looks for all possible flight paths
     * 
     */
    public void findFlight() {
        Destination src = new Destination(originCity, 0);
        Stack<Destination> toSearch = new Stack<Destination>();  
         /* list of destinations of direct flight for each src key */
        ArrayList<Destination> dirFlights;  
        /* set of all destinations */
        Set<Character> visited = new HashSet<Character>();  
        
        /* start search at source city */
        toSearch.push(src);
        src.addSrcCity(String.valueOf(src.getCity()));
        
        /* loop finds all flight routes and sends them to putFlight() */
        do {
            src = toSearch.pop();
            
            /* if the city is in the map and has direct flights */
            if ((dirFlights = flightMap.get(src.getCity())) != null) {
                
                /* add all direct flights to search list and send to output */
                for (Destination dest: dirFlights) {
                    
                    /* if no route to this dest has been found yet */
                    if (visited.contains(dest.getCity()) == false) {
                        visited.add(dest.getCity());
                        /* combine flight cost with rest of trip's cost */
                        dest.addCost(src.getCost());
                        dest.addSrcCity(String.valueOf(src.getSrcCities()) + String.valueOf(dest.getCity()));    
                        toSearch.push(dest);
                        putFlight(dest);
                    }
                }
                /* remove the searched city from the flightMap */
                flightMap.remove(src.getCity());
            }           
        }
        while (toSearch.isEmpty() != true);
    }


    /** Adds next trip to the string of all route information 
     * 
     * @param dest - Destination that the entire trip arrives to
    */
    public void putFlight(Destination dest) {
        String srcCities = dest.getSrcCities().replace("" , ",");
        outStr += dest.getCity() + " " + srcCities.substring(1, srcCities.length()-1) + " " + dest.getCost() + "\n";
    }


    /** Outputs the flight routes 
     * 
     * @return string of all possible trips
    */
    public String outputFlights() {
        return outStr;
    }
}
