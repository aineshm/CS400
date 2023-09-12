import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class FlightReaderBD implements FlightReaderInterface{

	@Override
	public List<FlightInterface> readPostsFromFile(String filename) throws FileNotFoundException {
		List<FlightInterface> list = new ArrayList<>();
		list.add(new FlightBD(1.0,1.0,1.0,"MIA","JFK"));
		list.add(new FlightBD(1.0,1.0,1.0,"JFK","ORD"));
		list.add(new FlightBD(1.0,1.0,1.0,"ORD","LAX"));
		list.add(new FlightBD(1.0,1.0,1.0,"LAX","MIA"));
		return list;
	}

}
