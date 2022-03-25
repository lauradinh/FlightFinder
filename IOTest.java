// --== CS400 File Header Information ==--
// Name: Conrad Wiebe
// Email: cwiebe2@wisc.edu
// Team: ED
// TA: Keren Chen
// Lecturer: Gary Dahl
// Notes to Grader: Pow
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Stream;
public class IOTest {

    @Test
    public void testFlightsRead() {
        try {
            String path = "./Flights.csv";
            ArrayList<Object> flights = new ArrayList<>();
            Stream<String> lines = Files.lines(new File(path).toPath());
            // Create whatever low level object we choose to hold the data
            lines.forEach(line -> {flights.add(null);});
            lines.close();
            Iterator<String> linesIter = Files.lines(new File(path).toPath()).iterator();
            Iterator<Object> flightsIter = flights.iterator();
            // call toString on each created object and compare to the data file
            while(linesIter.hasNext()) {
                assertTrue(flightsIter.next().equals(linesIter.next()));
            }

        } catch (Exception e) {
            fail();
        }
    }

    public void testScheduleRead() {
        // This is just copied code
        try {
            String path = "./Flights.csv";
            ArrayList<Object> flights = new ArrayList<>();
            Stream<String> lines = Files.lines(new File(path).toPath());
            // Create whatever low level object we choose to hold the data
            lines.forEach(line -> {flights.add(null);});
            lines.close();
            Iterator<String> linesIter = Files.lines(new File(path).toPath()).iterator();
            Iterator<Object> flightsIter = flights.iterator();
            // call toString on each created object and compare to the data file
            while(linesIter.hasNext()) {
                assertTrue(flightsIter.next().equals(linesIter.next()));
            }

        } catch (Exception e) {
            fail();
        }
    }
}
