// --== CS400 File Header Information ==--
// Name: Conrad Wiebe
// Email: cwiebe2@wisc.edu
// Team: ED
// TA: Keren Chen
// Lecturer: Gary Dahl
// Notes to Grader: Kachow

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.HashSet;

public class BackendTest {

	@Test
	public void testGetAirports() {
		List<String> expected = Arrays.asList("O'Hare International Airport", "Charlotte Douglas International Airport",
				"Seattle-Tacoma International Airport", "Salt Lake City International Airport",
				"Hartsfield-Jackson Atlanta International Airport", "John F. Kennedy Airport",
				"Dallas/Fort Worth International Airport", "Denver International Airport",
				"Los Angeles International Airport", "General Mitchell International Airport",
				"San Francisco International Airport", "Orlando International Airport");
		try {
			BackEnd b = new BackEnd();
			List<String> actual = b.getAirports();
			assertTrue(new HashSet<>(actual).equals(new HashSet<>(actual)));
		}

		catch (Exception e) {
			fail();
		}

	}

	@Test
	public void testGetShortestTimeFlightPath() {
		// double expected1 = 1.83; // O-Hare -> Charlotte
		// double expected2 = 7.73; // O-Hare -> Dallas/Fort-Worth
		// double expected3 = 4.98; // General Mitchell -> Los Angeles

		List<Flight> expected1 = Arrays.asList(new Flight("Boeing 600", "O'Hare International Airport",
				"Charlotte Douglas International Airport", 1.83, 725, 104));
		List<Flight> expected2 = Arrays.asList(
				new Flight("Boeing 600", "O'Hare International Airport", "Charlotte Douglas International Airport",
						1.83, 725, 104),
				new Flight("Boeing 614", "Charlotte Douglas International Airport",
						"Dallas/Fort Worth International Airport", 5.9, 1097, 127));
		List<Flight> expected3 = Arrays.asList(
				new Flight("Boeing 615", "General Mitchell International Airport", "Denver International Airport", 2.58,
						995, 119),
				new Flight("Boeing 608", "Denver International Airport", "Los Angeles International Airport", 2.4, 1106,
						36));

		try {
			BackEnd b = new BackEnd();
			assertTrue(b.getShortestTimeFlightPath("O'Hare International Airport",
					"Charlotte Douglas International Airport").toString().equals(expected1.toString()));
			assertTrue(b.getShortestTimeFlightPath("O'Hare International Airport",
					"Dallas/Fort Worth International Airport").toString().equals(expected2.toString()));
			assertTrue(b.getShortestTimeFlightPath("General Mitchell International Airport",
					"Los Angeles International Airport").toString().equals(expected3.toString()));
		}

		catch (Exception e) {
			fail();
		}

	}

	@Test
	public void testGetShortestDistanceFlightPath() {
		List<Flight> expected1 = Arrays.asList(new Flight("Boeing 602", "O'Hare International Airport",
				"Salt Lake City International Airport", 3.25, 1421, 149));
		List<Flight> expected2 = Arrays.asList(
				new Flight("Boeing 602", "O'Hare International Airport", "Salt Lake City International Airport", 3.25,
						1421, 149),
				new Flight("Boeing 622", "Salt Lake City International Airport", "Los Angeles International Airport",
						1.8, 717, 59),
				new Flight("Boeing 609", "Los Angeles International Airport",
						"Hartsfield-Jackson Atlanta International Airport", 4, 2222, 119),
				new Flight("Boeing 611", "Hartsfield-Jackson Atlanta International Airport", "John F. Kennedy Airport",
						2, 296, 64));

		try {
			BackEnd b = new BackEnd();
			assertTrue(b.getShortestDistanceFlightPath("O'Hare International Airport",
					"Salt Lake City International Airport").toString().equals(expected1.toString()));
			assertTrue(b.getShortestDistanceFlightPath("O'Hare International Airport", "John F. Kennedy Airport")
					.toString().equals(expected2.toString()));
		} catch (Exception e) {
			fail();
		}

	}

	@Test
	public void testGetCheapestFlightPath() {
		List<Flight> expected1 = Arrays.asList(new Flight("Boeing 602", "O'Hare International Airport",
				"Salt Lake City International Airport", 3.25, 1421, 149));
		List<Flight> expected2 = Arrays.asList(
				new Flight("Boeing 602", "O'Hare International Airport", "Salt Lake City International Airport", 3.25,
						1421, 149),
				new Flight("Boeing 622", "Salt Lake City International Airport", "Los Angeles International Airport",
						1.8, 717, 59),
				new Flight("Boeing 609", "Los Angeles International Airport",
						"Hartsfield-Jackson Atlanta International Airport", 4, 2222, 119),
				new Flight("Boeing 611", "Hartsfield-Jackson Atlanta International Airport", "John F. Kennedy Airport",
						2, 296, 64));

		try {
			BackEnd b = new BackEnd();
			assertTrue(b.getCheapestFlightPath("O'Hare International Airport", "Salt Lake City International Airport").toString()
					.equals(expected1.toString()));
			assertTrue(b.getCheapestFlightPath("O'Hare International Airport", "John F. Kennedy Airport").toString()
					.equals(expected2.toString()));
		}

		catch (Exception e) {
			fail();
		}

	}
}
