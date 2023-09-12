import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Iterator;

/**
 * Generic implementation of an RBT purposed as a Database
 * 
 * Includes a filtering system that makes use of an RBT Iterator that allows
 * users to insert a String parameter and filter results based on it
 *
 * @param <T> - generic type
 */
public class DatabaseRBTAE<T extends Comparable<T>> extends RedBlackTree<T> implements DatabaseRBTInterfaceAE<T> {

	/**
	 * Constructor - calls the super constructor to instantiate a null root object
	 * and size as 0
	 */
	public DatabaseRBTAE() {
		super();

	}

	/**
	 * Insert method - inserts a new node given data of type T to the DatabaseRBTAE
	 * object
	 * 
	 * @param data - data that is to be stored in the new node
	 * @return true if the insert is successful, false otherwise
	 */
	@Override
	public boolean insert(T data) {

		return super.insert(data); // uses the parent class RedBlackTree's insert method to add data
	}

	/**
	 * Inserts multiple nodes to the DatabaseRBTAE object given a List of data with
	 * type T
	 * 
	 * @param data - a List object of several data
	 */
	@Override
	public void insertMultiple(List<T> data) {

		for (T newNodeData : data) { // iterates through the List
			this.insert(newNodeData); // calls the insert method for each of them
		}

	}

	/**
	 * eraseAll resets the DatabaseRBTAE object and allows a new tree to be built
	 */
	@Override
	public void eraseAll() {

		root.data = null; // resets the root to have no data
		root.context[1] = null; // removes any links that the root node has to its left and right child nodes
		root.context[2] = null;

	}

	/**
	 * Getter method for the total number of nodes stored in the DatabaseRBTAE
	 * object
	 * 
	 * @return size - the number of nodes in the RBT
	 */
	@Override
	public int numVals() {
		return size;
	}

	/**
	 * Obtains the toString() of the node that contains the data parameterized This
	 * method uses the super class's findNodeWithData() method to obtain a node
	 * 
	 * @param data - data that must be found in node
	 * @return string value of the Node's data that contains the input parameter
	 * 
	 */
	@Override
	public T get(T input) {
		try{
			return super.findNodeWithData(input).data;
		}catch(NullPointerException e){
			return null;
		}	
	}

	/**
	 * Filter method makes use of a private iterator class to iterate through the
	 * BST and see if the toString for each Node's data matches the String parameter
	 * 
	 * @param parameter - string to filter based on
	 * @return a List object of toStrings that contain the parameter
	 * 
	 */
	@Override
	public List<T> filter(String parameter) {
		List<T> filteredOutput = new ArrayList<T>(); // initializes a new List object which will contain the final results

		RBTIterator<T> iterator = new RBTIterator<T>(this.root); // initializes a new iterator object

		T currentNodeData = null; // sets current node to null;

		while (iterator.hasNext()) { // loop runs while the iterator hasNext()
			currentNodeData = iterator.peekVal(); // sets currentNode to the node at the top of the stack
			if (currentNodeData.toString().contains(parameter)) {
				filteredOutput.add(currentNodeData);
			}

			iterator.next();

		}

		return filteredOutput;
	}

	/**
	 * In order iterator helper class for filter method
	 *
	 */
	private class RBTIterator<T extends Comparable<T>> implements Iterator<T> {

		private Stack<Node<T>> stack; // instantiates a stack of Node objects

		/**
		 * Constructs an iterator object. Pushes the root node to the left of a newly
		 * instantiated stack
		 * 
		 * @param root - root node of the RBT
		 */
		public RBTIterator(Node<T> root) {
			stack = new Stack<>();
			pushLeft(root);
		}

		/**
		 * Checks if the stack is empty
		 * 
		 * @return true if stack is not-empty, false otherwise
		 */
		@Override
		public boolean hasNext() {
			return !stack.isEmpty();
		}

		/**
		 * Goes to the next object in the stack Since it goes in order, it pushes the
		 * current nodes' right child left in the stack
		 * 
		 * @return the data stored in the Node object
		 */
		@Override
		public T next() {
			Node<T> current = stack.pop();
			pushLeft(current.context[2]);
			return current.data;
		}

		/**
		 * pushLeft methods pushes nodes down the stack and keeps going to the left so
		 * that it reaches the smallest node
		 * 
		 * @param node - node at which to start pushing left
		 */
		private void pushLeft(Node<T> node) {
			while (node != null) {
				stack.push(node);
				node = node.context[1];
			}
		}
		
		/**
		 * Peeks the actual data of the node objects in the stack
		 * 
		 * @return data contained in the top of the stack
		 */
		private T peekVal() {
			return stack.peek().data;
		}

	}

}
