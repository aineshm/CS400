import java.io.FileNotFoundException;
import java.util.List;

/**
 * Interface to read from a list of flights
 */
public interface FlightReaderInterface {

    // creates a list of Flight Interfaces depending
    // on the tree to be built. The constructor should take in a parameter from the backend that
    // sets the datatype for the list
    public List<FlightInterface> readPostsFromFile(String filename) throws FileNotFoundException;
    
}
