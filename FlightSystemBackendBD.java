import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Backend Class for Flight System that uses Dijkstra's algorithm
 * 
 * Makes use of DataWrangler and AlgorithmEngineer code to create the backend of
 * the software
 *
 */
public class FlightSystemBackendBD implements FlightSystemBackendInterface {

	// defining private data fields
	private DijkstraGraphWithMSTInterfaceAE<String, Number> graph; // graph used for the Flight System
	private FlightReaderInterface airportReader; // reads Nodes into the

	/**
	 * Constructor for FlightSystemBackendBD class
	 * 
	 * @param graph         - takes in the AE DijkstraGraphWithMST
	 * @param airportReader - takes in the DW data reader
	 */
	public FlightSystemBackendBD(DijkstraGraphWithMSTInterfaceAE<String, Number> graph,
			FlightReaderInterface airportReader) {
		this.airportReader = airportReader;
		this.graph = graph;
	}

	/**
	 * Loads data from a file using the DW reader object Goes through each flight
	 * object and adding the airports and flights one at a time
	 * 
	 * @param filename - file to be imported's name
	 * @throws FileNotFoundException - throws this exception if the file is not
	 *                               found in the system
	 */
	@Override
	public void loadData(String filename) throws FileNotFoundException {
		// inputs a casted List of flights from the airportReader
		List<FlightInterface> flights = (ArrayList<FlightInterface>) airportReader.readPostsFromFile(filename);
		List<String> airportsIncluded = new ArrayList<String>(); // creates a new arrayList of Strings to track the
																	// airports that have been tracked/added to the
																	// Graph

		for (FlightInterface i : flights) { // iterates through the flights List

			// if the airportsIncluded List is null or doesn't contain the origin airport,
			// it will be added to the graph; it is also added to the list of
			// airportsIncluded so that it isn't re-added in the future
			if (airportsIncluded == null || !airportsIncluded.contains(i.getOrigin())) {
				airportsIncluded.add(i.getOrigin());
				this.insertAirport(i.getOrigin());
			}
			// if the airportsIncluded List doesn't contain the destination airport of the
			// flight, it will be added to the graph; it is also added to the list of
			// airportsIncluded so that it isn't re-added in the future
			if (!airportsIncluded.contains(i.getDestination())) {
				airportsIncluded.add(i.getDestination());
				this.insertAirport(i.getDestination());
			}
		}

		for (FlightInterface i : flights) { // adds the flights between airports
			this.insertFlights(i);
		}

	}

	/**
	 * Uses the graph's insertNode method to insert the airport to the graph
	 * 
	 * @param airportName - the airport's name to be added as the data in the graph
	 *                    to a node
	 */
	@Override
	public void insertAirport(String airportName) {
		graph.insertNode(airportName);

	}

	/**
	 * Uses the graph's insertEdge method to insert a flight to the graph
	 * 
	 * @param flight - an object implementing FlightInterface to be added to the
	 *               Graph as Edges
	 */
	@Override
	public void insertFlights(FlightInterface flight) throws NoSuchElementException {
		if (!graph.containsNode(flight.getOrigin()) || !graph.containsNode(flight.getDestination())) {
			throw new NoSuchElementException("Flight System Does Not Contian this airport");
		}
		graph.insertEdge(flight.getOrigin(), flight.getDestination(), flight.doubleValue());

	}

	/**
	 * Uses graph's shortestPathData() algorithm to return the shortest path as a
	 * List of Strings
	 * 
	 * @param origin      - the desired origin airport for the trip
	 * @param destination - the desired destination airport for the trip
	 * @return the most efficient path based on the edge costs and the
	 *         origin/destinations set
	 */
	@Override
	public List<String> findMostEfficientPath(String origin, String destination) {
		return graph.shortestPathData(origin, destination);
	}

	/**
	 * Finds the Most efficient path from an origin airport around to every other
	 * airport using the graph's MSTData() algorithm
	 * 
	 * @param origin - the origin airport for the trip to every single airport
	 * @return the MST of the graph
	 */
	@Override
	public List<String> findMST(String origin) {
		return graph.MSTData(origin);
	}

	/**
	 * Gets the statistics for the graph - returns a string that mentions the total
	 * number of flights and airports in the graph
	 * 
	 * @return a string with information about the graph
	 */
	@Override
	public String getStatistics() {
		String stats = "Total number of flights: " + graph.getEdgeCount() + "\n" + "Total number of Airports: "
				+ graph.getNodeCount();
		return stats;
	}

}
