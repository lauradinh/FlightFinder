// --== CS400 File Header Information ==--
// Name: Timothy Voelker
// Email: tvoelker@wisc.edu
// Team: ED
// Role: Back End Developer 2
// TA: Keren Chen
// Lecturer: Florian Heimerl

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Stores backend logic for the Flight Finder application
 * @author Timothy Voelker
 */
public class BackEnd implements IBackEnd {
    CS400Graph<String> timeGraph;
    CS400Graph<String> distanceGraph;
    CS400Graph<String> priceGraph;
    Hashtable<String, Flight> flightTable;

    /**
     * Default constructor for BackEnd. Initializes time, distance, and price graphs.
     * @throws IOException when DataAccess can't read the data file.
     */
    public BackEnd() throws IOException {
        timeGraph = DataAccess.getTimeFlightGraph();
        distanceGraph = DataAccess.getDistanceFlightGraph();
        priceGraph = DataAccess.getPriceFlightGraph();
        flightTable = DataAccess.getFlightTable();
    }

    @Override
    public List<String> getAirports() throws IOException {
        // Convert graph table keys into a list
        Set<String> airports = DataAccess.getTimeFlightGraph().vertices.keySet();
        List<String> list = new ArrayList<>();

        for (String airport : airports) {
            list.add(airport);
        }

        return list;
    }

    @Override
    public Hashtable<String, Flight> getFlightTable() {
        return flightTable;
    }

    @Override
    public List<Flight> getShortestTimeFlightPath(String startAirport, String endAirport)
            throws NoSuchElementException {
        return getBestPath(startAirport, endAirport, timeGraph); // Best path by time
    }

    @Override
    public List<Flight> getShortestDistanceFlightPath(String startAirport, String endAirport)
            throws NoSuchElementException {
        return getBestPath(startAirport, endAirport, distanceGraph); // Best path by distance
    }

    @Override
    public List<Flight> getCheapestFlightPath(String startAirport, String endAirport) throws NoSuchElementException {
        return getBestPath(startAirport, endAirport, priceGraph); // Best path by price
    }

    /**
     * Finds and returns the shortest flight path from {@code startAirport} to {@code endAirport} based on the criteria 
     * represented by the {@code graph}. This path is represented with a list of {@code Flight} objects.
     * @param startAirport name of the starting airport.
     * @param endAirport name of the destintation aiport.
     * @param graph the data structure storing the connections and weights (time, distance, or price) between airports.
     * @return list of {@code Flight objects} representing the optimal path from {@code startAirport} to {@code endAirport}.
     */
    private List<Flight> getBestPath(String startAirport, String endAirport, CS400Graph<String> graph) {
        List<String> airports = graph.shortestPath(startAirport, endAirport); // Get the shortest path from the graph
        List<Flight> flightPath = new ArrayList<Flight>();

        // Convert list of airport names into list of flights
        for (int i = 1; i < airports.size(); i++) {
            flightPath.add(flightTable.get(String.format("%s:%s", airports.get(i - 1), airports.get(i))));
        }

        return flightPath;
    }

}