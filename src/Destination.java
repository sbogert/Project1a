public class Destination {
   
    private Character city;
    private int cost;
    private String srcCities = "";

    /** data structure for the list of values in the flight map
     * each object in the list is a different destination city and price of the direct flight
     */
    public Destination(Character city, int cost) {
        this.city = city;
        this.cost = cost;
    }

    /** add a city to the flight path of destination city */
    public void addSrcCity(String src) {
        srcCities += src;
    }

    /** returns a string of all cities travelled to before destination was reached */
    public String getSrcCities() {
        return srcCities;
    }


    public Character getCity() {
        return this.city;
    }

    public int getCost() {
        return this.cost;
    }

    public void addCost(int x) {
        this.cost += x;
    }
}
