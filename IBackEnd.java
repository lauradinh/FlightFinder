// --== CS400 File Header Information ==--
// Name: Timothy Voelker
// Email: tvoelker@wisc.edu
// Team: ED
// TA: Keren Chen
// Lecturer: Florian Heimerl

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Hashtable;

public interface IBackEnd {
    /**
     * Gets a list of airport names from the data graph.
     * @return list of airport names.
     * @throws IOException when DataAccess can't read the data file.
     */
    public List<String> getAirports() throws IOException;

    /**
     * Returns a hash table mapping strings of the form
     * "{@code sourceAirport}: {@code targetAirport} to corresponding {@code Flight}
     * objects.
     * @return a hash table mapping strings to {@code Flight} objects.
     */ 
    public Hashtable<String, Flight> getFlightTable();
    /**
     * Finds the shortest path between {@code startAirport} and {@code endAirport} based on flight time. This path is represented with a list of {@code Flight} objects.
     * @param startAirport name of the starting airport.
     * @param endAirport name of the destintation aiport.
     * @return list of {@code Flight objects} representing the optimal path from {@code startAirport} to {@code endAirport}.
     * @throws NoSuchElementException when no flight path exists between {@code startAirport} and {@code endAirport}.
     */
    public List<Flight> getShortestTimeFlightPath(String startAirport, String endAirport) throws NoSuchElementException;

    /**
     * Finds the shortest path between {@code startAirport} and {@code endAirport} based on distance. This path is represented with a list of {@code Flight} objects.
     * @param startAirport name of the starting airport.
     * @param endAirport name of the destintation aiport.
     * @return list of {@code Flight objects} representing the optimal path from {@code startAirport} to {@code endAirport}.
     * @throws NoSuchElementException when no flight path exists between {@code startAirport} and {@code endAirport}.
     */
    public List<Flight> getShortestDistanceFlightPath(String startAirport, String endAirport)
            throws NoSuchElementException;

    /**
     * Finds the shortest path between {@code startAirport} and {@code endAirport} based on price. This path is represented with a list of {@code Flight} objects.
     * @param startAirport name of the starting airport.
     * @param endAirport name of the destintation aiport.
     * @return list of {@code Flight objects} representing the optimal path from {@code startAirport} to {@code endAirport}.
     * @throws NoSuchElementException when no flight path exists between {@code startAirport} and {@code endAirport}.
     */
    public List<Flight> getCheapestFlightPath(String startAirport, String endAirport) throws NoSuchElementException;

}
