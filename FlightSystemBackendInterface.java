import java.io.FileNotFoundException;
import java.util.List;

public interface FlightSystemBackendInterface {
	
	// public FlightSystemBackendBD(DijkstraGraphWithMSTInterfaceAE<String,Number> graph, FlightReaderInterface airportReader)
	
	public void loadData(String filename) throws FileNotFoundException;

	public void insertAirport(String airportName);

	public void insertFlights(FlightInterface flight);

	public List<String> findMostEfficientPath(String origin, String destination);

	public List<String> findMST(String origin); // param will take in an input for a factor and search for a MST
														// based on the factor input

	public String getStatistics(); // returns the total number of airports and flights available in the graph
}
