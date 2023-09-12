import java.util.List;

public interface DatabaseRBTInterfaceAE <T extends Comparable<T>> extends SortedCollectionInterface<T> {

		public boolean insert(T data); // amended from parent class to add to count field to store total number of values in the RBT
		
		public void insertMultiple(List<T> data); // inserts multiple nodes at once
		
		public void eraseAll(); // clears all nodes at once; resets RBT
		
		public int numVals(); // returns number of values in the RBT;
	
		public T get(T data); // gets the node's data's toString 
		
		public List<T> filter (String parameter); // acts as a filter to filter out nodes based on a certain parameter; checks the Nodes’ data’s toString() to see if it contains the parameter and outputs a List of toStrings of the Nodes from the RBT
		
}
