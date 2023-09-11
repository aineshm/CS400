
/**
 * Placeholder class for Flight (for backend developer role)
 * @author aineshmohan
 *
 */
public class FlightBD extends Number implements FlightInterface {

	double cost;
	double emissions;
	double time;
	String origin;
	String destination;

	public FlightBD(double cost, double emissions, double time, String origin, String destination) {
		this.cost = cost;
		this.emissions = emissions;
		this.time = time;
		this.origin = origin;
		this.destination = destination;
	}

	@Override
	public int compareTo(FlightInterface flight) {
		FlightBD other = (FlightBD) flight;
		if(this.doubleValue()>other.doubleValue()){
			return 1;
		}
		else if(this.doubleValue()<other.doubleValue()) {
			return -1;
		}
		return 0;
	}

	@Override
	public double getCost() {
		return cost;
	}

	@Override
	public double getEmissions() {
		return emissions;
	}

	@Override
	public double getTime() {
		return time;
	}

	@Override
	public String getOrigin() {
		return origin;
	}

	@Override
	public String getDestination() {
		return destination;
	}

	@Override
	public int intValue() {
		return 0;
	}

	@Override
	public long longValue() {
		return 0;
	}

	@Override
	public float floatValue() {
		return 0;
	}

	@Override
	public double doubleValue() {
		return (cost + emissions + time) / 3.0;
	}

}