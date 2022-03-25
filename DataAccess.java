// --== CS400 File Header Information ==--
// Name: Laura Dinh
// Email: lmdinh
// Team: ED
// TA: Keren Chen
// Lecturer: Gary Dahl
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * Accesses data from the FLIGHT_FILE which includes flight information for a
 * given day
 * 
 * @author Laura Dinh
 *
 */
public class DataAccess {

	private static final String FLIGHT_FILE = "flights.csv";

	/**
	 * Creates a graph using the length of flights
	 * 
	 * @return timeGraph which contains all paths of flights for a given day with
	 *         the weights being the flight duration
	 * @throws IOException when data cannot be read
	 */
	public static CS400Graph<String> getTimeFlightGraph() throws IOException {
		CS400Graph<String> timeGraph = new CS400Graph<>();

		List<Flight> flights = getFlights();

		for (Flight flight : flights) {
			String source = flight.getSource();
			String target = flight.getTarget();

			timeGraph.insertVertex(source);
			timeGraph.insertVertex(target);
			timeGraph.insertEdge(source, target, flight.getDuration());
		}
		return timeGraph;
	}

	/**
	 * Creates a graph using the distance of flights
	 * 
	 * @return timeGraph which contains all paths of flights for a given day with
	 *         the weights being the flight distances
	 * @throws IOException when data cannot be read
	 */
	public static CS400Graph<String> getDistanceFlightGraph() throws IOException {
		CS400Graph<String> distanceGraph = new CS400Graph<>();

		List<Flight> flights = getFlights();

		for (Flight flight : flights) {
			String source = flight.getSource();
			String target = flight.getTarget();

			distanceGraph.insertVertex(source);
			distanceGraph.insertVertex(target);
			distanceGraph.insertEdge(source, target, flight.getDistance());
		}
		return distanceGraph;
	}

	/**
	 * Creates a graph using the price of flights
	 * 
	 * @return timeGraph which contains all paths of flights for a given day with
	 *         the weights being the flight price 
	 * @throws IOException when data cannot be read
	 */
	public static CS400Graph<String> getPriceFlightGraph() throws IOException {
		CS400Graph<String> priceGraph = new CS400Graph<>();

		List<Flight> flights = getFlights();

		for (Flight flight : flights) {
			String source = flight.getSource();
			String target = flight.getTarget();

			priceGraph.insertVertex(source);
			priceGraph.insertVertex(target);
			priceGraph.insertEdge(source, target, flight.getPrice());
		}
		return priceGraph;
	}

	/**
	 * Adds flights from FLIGHT_FILE and creates Flight objects
	 * 
	 * @return list of Flight objects
	 * @throws IOException when data file cannot be read
	 */
	public static List<Flight> getFlights() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(FLIGHT_FILE));
		List<Flight> flights = new ArrayList<>();

		String line = "";
		while ((line = br.readLine()) != null) {
			String[] data = line.split(","); // data from each line of FLIGHT_FILE
			String flightName = data[0].trim();
			String sourceAirport = data[1].trim();
			String targetAirport = data[2].trim();
			double duration = Double.valueOf(data[3]);
			int distance = Integer.valueOf(data[4]);
			int price = Integer.valueOf(data[5]);

			flights.add(new Flight(flightName, sourceAirport, targetAirport, duration, distance, price));
		}

		br.close();

		return flights;
	}

	/**
	 * Creates a Hashtable that uses the string "sourceAirport:targetAirport" as the
	 * key and the flight object as the value.
	 * 
	 * @return
	 * @throws IOException
	 */
	public static Hashtable<String, Flight> getFlightTable() throws IOException {
		Hashtable<String, Flight> table = new Hashtable<>(); // Keys are "{sourceAirport}:{targetAirport}"; values are
																// flight objects with corresponding data

		List<Flight> flights = getFlights();

		for (Flight flight : flights) {
			table.put(String.format("%s:%s", flight.getSource(), flight.getTarget()), flight);
		}

		return table;
	}
}