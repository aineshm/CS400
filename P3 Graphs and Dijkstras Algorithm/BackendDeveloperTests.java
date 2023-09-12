import static org.junit.jupiter.api.Assertions.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;

/**
 * Tester for BackendDeveloper role code; tests based on Placeholders created by
 * BD role
 *
 */
public class BackendDeveloperTests {

	/**
	 * Tests the backend loadData command based on placeholders - should not throw
	 * any exceptions Checks the number of Airports and Flights loaded into the
	 * graph object created
	 */
	@Test
	public void test1() {
		// instantiating a graph, reader and backend object for the tester
		DijkstraGraphWithMSTBD<String, Number> graph = new DijkstraGraphWithMSTBD<String, Number>();
		FlightReaderBD reader = new FlightReaderBD();
		FlightSystemBackendBD backend = new FlightSystemBackendBD(graph, reader);

		// try-catching the load data command
		try {
			backend.loadData("abcd");
		} catch (FileNotFoundException e) {
			// should not throw a FNF exception based on placeholder FlightReaderBD
			fail("Exception should not be thrown"); // if an exception is caught, test fails
		}

		assertEquals(graph.getNodeCount(), 4); // graph should have 4 nodes
		assertEquals(graph.getEdgeCount(), 4); // graph should have 4 edges
	}

	
	
	/**
	 * Tests the backend insertAirport and insertFlight methods, checks the updated
	 * node count as well
	 */
	@Test
	public void test2() {
		// instantiating a graph, reader and backend object for the tester
		DijkstraGraphWithMSTBD<String, Number> graph = new DijkstraGraphWithMSTBD<String, Number>();
		FlightReaderBD reader = new FlightReaderBD();
		FlightSystemBackendBD backend = new FlightSystemBackendBD(graph, reader);

		FlightBD test = new FlightBD(1.0, 1.0, 1.0, "LAX", "IND"); // should not be able to add this edge to an empty
																	// tree
		try {
			backend.insertFlights(test);
			fail("Should not be able to successfully insert the flight");
		} catch (Exception e) {
			// does nothing
		}

		// testing this on a non-empty graph
		// should not accept this new Flight as there is an Airport that has not been
		// loaded into the system
		try {
			backend.loadData("abcd");
			backend.insertFlights(test); // should not be able to add this edge as there is no Airport named "IND"
			fail("Should not be able to successfully insert the flight");
		} catch (FileNotFoundException e) {
			// does nothing
		} catch (NoSuchElementException nse) {
			// does nothing
		}

		backend.insertAirport("IND"); // inserting new airport
		assertEquals(graph.getNodeCount(), 5); // graph should have 5 nodes now

		// testing this on a non-empty graph
		// should accept this new Flight as there is a newly added Airport that has been
		// loaded into the system
		try {
			backend.insertFlights(test); // should not be able to add this edge as there is no Airport named "IND"

		} catch (NoSuchElementException e) {
			fail("Should not be able to successfully insert the flight"); // should not catch any exception
		}

		assertEquals(graph.getEdgeCount(), 5); // graph should now have 5 edges
	}
	
	

	/**
	 * Tests the findMST() backend method based on the placeholder class
	 */
	@Test
	public void test3() {
		// instantiating a graph, reader and backend object for the tester
		DijkstraGraphWithMSTBD<String, Number> graph = new DijkstraGraphWithMSTBD<String, Number>();
		FlightReaderBD reader = new FlightReaderBD();
		FlightSystemBackendBD backend = new FlightSystemBackendBD(graph, reader);

		// try-catching the load data command
		try {
			backend.loadData("abcd");
		} catch (FileNotFoundException e) {
			// does nothing
		}

		assertEquals(backend.findMST("LAX"), new ArrayList<String>()); // should return a null list based on placeholder
	}
	
	

	/**
	 * Testing backend class's findMostEfficientPath() method based on placeholders
	 */
	@Test
	public void test4() {
		// instantiating a graph, reader and backend object for the tester
		DijkstraGraphWithMSTBD<String, Number> graph = new DijkstraGraphWithMSTBD<String, Number>();
		FlightReaderBD reader = new FlightReaderBD();
		FlightSystemBackendBD backend = new FlightSystemBackendBD(graph, reader);

		// try-catching the load data command
		try {
			backend.loadData("abcd");
		} catch (FileNotFoundException e) {
			// does nothing
		}
		List<String> expectedOutput = new ArrayList<>();
		expectedOutput.add("MIA");
		assertEquals(backend.findMostEfficientPath("MIA", "MIA"), expectedOutput); // makes sure the algorithm output is
																					// accurate

		expectedOutput.add("JFK");
		expectedOutput.add("ORD");
		expectedOutput.add("LAX");
		assertEquals(backend.findMostEfficientPath("MIA", "LAX"), expectedOutput); // makes sure the algorithm output
																					// matches ExpectedOutput
	}
	
	

	/**
	 * Tests the getStatistics() method in the backcend class
	 */
	@Test
	public void test5() {
		// instantiating a graph, reader and backend object for the tester
		DijkstraGraphWithMSTBD<String, Number> graph = new DijkstraGraphWithMSTBD<String, Number>();
		FlightReaderBD reader = new FlightReaderBD();
		FlightSystemBackendBD backend = new FlightSystemBackendBD(graph, reader);

		// try-catching the load data command
		try {
			backend.loadData("abcd");
		} catch (FileNotFoundException e) {
			// does nothing
		}

		String output = backend.getStatistics();
		// checks if getStatistics() gives the correct output
		assertEquals(output, "Total number of flights: 4" + "\n" + "Total number of Airports: 4");
	}

}
