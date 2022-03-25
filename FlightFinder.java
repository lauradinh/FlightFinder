// --== CS400 File Header Information ==--
// Name: Timothy Voelker
// Email: tvoelker@wisc.edu
// Team: ED
// Role: Back End Developer 2
// TA: Keren Chen
// Lecturer: Florian Heimerl

import java.io.IOException;
import java.util.Hashtable;
import java.util.NoSuchElementException;

/**
 * Entry point for the Flight Finder application
 * @author Timothy Voelker
 */
public class FlightFinder {

    private static final String MALFORMED_URL_ERROR_MESSAGE = "Sorry, the page you tried to get doesn't exist. Did you change the URL?";

    /**
     * Main logic for the Flight Finder application. Determines which pages
     * to show based on URL parameters.
     * @param args commandline arguments; index 0 for query string.
     */
    public static void main(String[] args) throws IOException {
        // 
        // Search page
        // 

        // If no parameters were passed, then print the search page
        if (args.length == 0 || args[0].isEmpty()) {
            System.out.print(AppPages.searchPage()); // Print the search page
            return; // Exit
        }

        final Hashtable<String, String> parameters = getURLParameters(args.length > 0 ? args[0] : null); // Parse URL parameters
        final IBackEnd backend; // Declare the backend object

        try {
            backend = new BackEnd(); // Initialize the backend
        } catch (IOException e) { // The data file wasn't found or couldn't be read
            System.out.print(AppPages.errPage("Sorry, there was an error on the server.")); // Print the error page
            return; // Exit
        }

        // 
        // Info page
        // 

        // Check if the info parameter was provided
        if (parameters.containsKey("info")) {
            // Check if the value for info is "info"
            if (parameters.get("info").equals("info")) {
                System.out.print(AppPages.errPage(MALFORMED_URL_ERROR_MESSAGE)); // Print the error page with URL error
                return; // Exit
            }

            // The info parameter is formatted like "sourceAirport-targetAirport," so split on '-'
            String[] sourceTarget = parameters.get("info").split("-");

            Flight infoFlight = backend.getFlightTable().get(String.format("%s:%s", sourceTarget[0], sourceTarget[1])); // Flight to get info on

            // Check if the flight was found in the table
            if (infoFlight == null) {
                infoFlight = new Flight("No Flight Found", "null", "null", -1d, -1, -1); // Make a dummy flight so the info page 
                // has something to return when no 
                // flight between airports exists
            }

            System.out.print(AppPages.info(infoFlight)); // Print the info page
            return; // Exit
        }

        // 
        // Result page
        // 

        // Check if required URL parameters are missing
        if (!parameters.containsKey("start") || !parameters.containsKey("end") || !parameters.containsKey("type")) {
            System.out.print(AppPages.errPage(MALFORMED_URL_ERROR_MESSAGE)); // Print the error page with URL error
            return; // Exit
        }

        try {
            final String start = parameters.get("start");
            final String end = parameters.get("end");

            // Call backend method based on value of the type parameter
            switch (parameters.get("type")) {
                case "0": // Time
                    System.out.print(AppPages.resultPage(backend.getShortestTimeFlightPath(start, end)));
                    break;
                case "1": // Cost
                    System.out.print(AppPages.resultPage(backend.getCheapestFlightPath(start, end)));
                    break;
                case "2": // Distance
                    System.out.print(AppPages.resultPage(backend.getShortestDistanceFlightPath(start, end)));
                    break;
                default:
                    System.out.print(AppPages.errPage(MALFORMED_URL_ERROR_MESSAGE)); // The type was something other than time (0), cost (1), and distance (2)
                    return; // Exit
            }
        } catch (NoSuchElementException e) { // No flight path found
            System.out.print(AppPages.errPage("Sorry, there is no flight path between these two airports."));
        }
    }

    /**
     * Returns a hash table mapping URL parameter names to URL parameter values.
     * @return hash table parse of URL parameters.
     */
    static Hashtable<String, String> getURLParameters(String queryString) {
        final Hashtable<String, String> parametersTable = new Hashtable<>();

        if (queryString == null || queryString.isEmpty())
            return parametersTable; // Return an empty hashtable if no query string is provided

        // URL parameters are separated by "&," so split on '&'
        final String[] parameterPairs = queryString.split("&");

        for (String parameterPair : parameterPairs) {
            // Keys and values are separated by "=," so split on '='
            final String[] keyValue = parameterPair.split("=");

            // If there is no explicit value provided, make the value the key
            String value = keyValue.length > 1
                    // Convert URL char codes to corresponding characters
                    ? keyValue[1].replaceAll("%27", "'").replaceAll("%20", " ").replaceAll("\\+", " ")
                    : keyValue[0];

            parametersTable.put(keyValue[0], value);
        }

        return parametersTable;
    }

}