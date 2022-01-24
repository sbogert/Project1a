public class Destination {
   
    private char destCity;
    private int cost;
    private char [] srcCities;

    /** data structure for the list of values in the flight map
     * each object in the list is a different destination city and price of the direct flight
     */
    public void Destination(char destCity, int cost) {
        this.destCity = destCity;
        this.cost = cost;
    }

    /** add a city to the flight path of destination city */
    public void addSrcCity(char src) {
        srcCities.add(src);
    }

    /** returns a string of all cities travelled to before destination was reached */
    public String getSrcCities() {
        String srcCitiesStr = srcCitiesStr.copyValueOf(SrcCities);
        return srcCitiesStr;
    }


    public char getDestCity() {
        return this.destCity;
    }

    public int getCost() {
        return this.cost;
    }

    public void addCost(int x) {
        this.cost += x;
    }
}
