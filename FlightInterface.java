/**
 * Interface for a tree where edge weights are the cost of flights between
 * airports. Should store the cost of this flight, its destination and origin
 * airports. The cost should be stored as an int, and the two airports as
 * strings
 */
public interface FlightInterface extends Comparable<FlightInterface> {

	@Override
	/**
	 * compares this flights edge weight with another by taking the mean of each of
	 * the weights
	 */
	public int compareTo(FlightInterface flight);

	/**
	 * returns the cost of this flight
	 */
	public double getCost();

	/**
	 * returns the emissions of this flight
	 */
	public double getEmissions();

	/**
	 * returns the time of this flight
	 */
	public double getTime();

	/**
	 * returns the origin airport of this flight
	 */
	public String getOrigin();

	/**
	 * returns the destination airport of this flight
	 */
	public String getDestination();


	public int intValue();

	public long longValue();

	public float floatValue();

	public double doubleValue();

}
