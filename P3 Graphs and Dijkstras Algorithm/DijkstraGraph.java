// --== CS400 File Header Information ==--
// Name: Ainesh Mohan
// Email: amohan28@wisc.edu
// Group and Team: BS blue
// Group TA: Samuel Church
// Lecturer: Gary Dahl
// Notes to Grader: N/A

import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.NoSuchElementException;
//import static org.junit.jupiter.api.Assertions.*;
//import org.junit.jupiter.api.Test;

/**
 * This class extends the BaseGraph data structure with additional methods for
 * computing the total cost and list of node data along the shortest path
 * connecting a provided starting to ending nodes. This class makes use of
 * Dijkstra's shortest path algorithm.
 */
public class DijkstraGraph<NodeType, EdgeType extends Number> extends BaseGraph<NodeType, EdgeType>
		implements GraphADT<NodeType, EdgeType> {

	/**
	 * While searching for the shortest path between two nodes, a SearchNode
	 * contains data about one specific path between the start node and another node
	 * in the graph. The final node in this path is stored in it's node field. The
	 * total cost of this path is stored in its cost field. And the predecessor
	 * SearchNode within this path is referened by the predecessor field (this field
	 * is null within the SearchNode containing the starting node in it's node
	 * field).
	 *
	 * SearchNodes are Comparable and are sorted by cost so that the lowest cost
	 * SearchNode has the highest priority within a java.util.PriorityQueue.
	 */
	protected class SearchNode implements Comparable<SearchNode> {
		public Node node;
		public double cost;
		public SearchNode predecessor;

		public SearchNode(Node node, double cost, SearchNode predecessor) {
			this.node = node;
			this.cost = cost;
			this.predecessor = predecessor;
		}

		public int compareTo(SearchNode other) {
			if (cost > other.cost)
				return +1;
			if (cost < other.cost)
				return -1;
			return 0;
		}
	}

	/**
	 * This helper method creates a network of SearchNodes while computing the
	 * shortest path between the provided start and end locations. The SearchNode
	 * that is returned by this method is represents the end of the shortest path
	 * that is found: it's cost is the cost of that shortest path, and the nodes
	 * linked together through predecessor references represent all of the nodes
	 * along that shortest path (ordered from end to start).
	 *
	 * @param start the data item in the starting node for the path
	 * @param end   the data item in the destination node for the path
	 * @return SearchNode for the final end node within the shortest path
	 * @throws NoSuchElementException when no path from start to end is found or
	 *                                when either start or end data do not
	 *                                correspond to a graph node
	 */
	protected SearchNode computeShortestPath(NodeType start, NodeType end) {
		// TODO: implement in step 6

		if (start == null || end == null) {
			throw new NoSuchElementException("Start/End nodes not valid");
		}

		// checks if the nodes hashtable contains start or end given data
		if (!nodes.containsKey(start) || !nodes.containsKey(end)) {
			throw new NoSuchElementException("Start/End nodes not valid");
		}

		Node startNode = nodes.get(start); // initializes startNode to given data

		// initializes the priority queue which tracks the shortest possible paths
		PriorityQueue<SearchNode> pq = new PriorityQueue<SearchNode>();

		// initializes visited Hashtable which tracks the visited nodes
		Hashtable<NodeType, SearchNode> visited = new Hashtable<>();

		// initializing a SearchNode which is the initial node in the path
		SearchNode startSearch = new SearchNode(startNode, 0, null);
		pq.add(startSearch); // startSearch searchNode added to the priority queue

		while (!pq.isEmpty()) { // runs the loop while the priority queue is not empty

			SearchNode currentNode = pq.poll(); // assigns currentNode the value of the value with the highest priority
												// in
			// the queue

			if (currentNode.node.data.equals(end)) { // if the currentNode has reached the end, the currentNode is
														// returned
				return currentNode;
			}

			// if the visited hashtable does not contain the currentNode's data, the
			// following code is executed (Runs Dijsktra's Algorithm)
			if (!visited.contains(currentNode.node.data)) {

				visited.put(currentNode.node.data, currentNode); // adds the currentNode to the visited hashtable
				Node current = nodes.get(currentNode.node.data); // creates a current Node type that gets the node from
																	// CurrentNode

				for (int i = 0; i < current.edgesLeaving.size(); i++) { // for loop goes through all the edges leaving
																		// the node current

					// re-initializes the currentNode to contain the next node, the total cost with
					// the next node, and reassigns the predecessor as the current value

					SearchNode nextNode = new SearchNode(current.edgesLeaving.get(i).successor,
							current.edgesLeaving.get(i).data.doubleValue() + currentNode.cost, currentNode);

					pq.add(nextNode); // adds the new currentNode to the priority queue
				}
			}

		}

		if (!visited.containsKey(end)) { // if the visited hashtable does not contain the end node, an exception is
											// thrown

			throw new NoSuchElementException("Start/end nodes invalid");
		}

		return pq.peek(); // returns the priority queue node with highest priority
	}

	/**
	 * Returns the list of data values from nodes along the shortest path from the
	 * node with the provided start value through the node with the provided end
	 * value. This list of data values starts with the start value, ends with the
	 * end value, and contains intermediary values in the order they are encountered
	 * while traversing this shorteset path. This method uses Dijkstra's shortest
	 * path algorithm to find this solution.
	 *
	 * @param start the data item in the starting node for the path
	 * @param end   the data item in the destination node for the path
	 * @return list of data item from node along this shortest path
	 */
	public List<NodeType> shortestPathData(NodeType start, NodeType end) {

		// Step 1: Compute the shortest path from "start" to "end" and store the result
		// in "currentNode"
		SearchNode currentNode = computeShortestPath(start, end);

		// Step 2: Create an empty list to store the node data of the nodes in the
		// shortest path
		List<NodeType> paths = new ArrayList<NodeType>();

		// Step 3: Add the data of the "end" node (i.e., "currentNode") to the "paths"
		// list at the beginning (index 0)
		paths.add(0, currentNode.node.data);

		// Step 4: Traverse back from "currentNode" to "start" node along the computed
		// shortest path
		while (currentNode.predecessor != null) {
			// Add the data of the predecessor of "currentNode" (i.e., the node visited
			// before "currentNode" in the shortest path) to the "paths" list at the
			// beginning (index 0)
			paths.add(0, currentNode.predecessor.node.data);

			// Update "currentNode" to be its predecessor, effectively moving up the path
			// towards the "start" node
			currentNode = currentNode.predecessor;
		}

		// Step 5: Return the "paths" list, which now contains the node data of the
		// nodes in the shortest path in the correct order
		return paths;
	}

	/**
	 * Returns the cost of the path (sum over edge weights) of the shortest path
	 * freom the node containing the start data to the node containing the end data.
	 * This method uses Dijkstra's shortest path algorithm to find this solution.
	 *
	 * @param start the data item in the starting node for the path
	 * @param end   the data item in the destination node for the path
	 * @return the cost of the shortest path between these nodes
	 */
	public double shortestPathCost(NodeType start, NodeType end) {

		SearchNode current = computeShortestPath(start, end); // computes the shortestpath and stores it to current

		return current.cost; // returns the cost of the current shortestPath
	}

//	/**
//	 * Test1 - tests valid inputs for start and end nodes, and finds shortest path
//	 * in two scenarios:
//	 * 
//	 * Scenario 1 - the only path from A to F is through C,D,E Scenario 2 - there is
//	 * a new edge from B to F with length 1.9
//	 * 
//	 * (Scenarios from lecture with Prof Dahl)
//	 */
//	@Test
//	public void test1() {
//		DijkstraGraph<String, Double> testGraph = new DijkstraGraph<String, Double>();
//
//		// adds nodes and edges based on scenario 1 above
//		testGraph.insertNode("A");
//		testGraph.insertNode("B");
//		testGraph.insertNode("C");
//		testGraph.insertNode("D");
//		testGraph.insertNode("E");
//		testGraph.insertNode("F");
//
//		testGraph.insertEdge("A", "B", 1.0);
//		testGraph.insertEdge("A", "C", 2.0);
//		testGraph.insertEdge("C", "D", 2.0);
//		testGraph.insertEdge("D", "E", 2.0);
//		testGraph.insertEdge("E", "F", 2.0);
//
//		assertEquals(8.0, testGraph.computeShortestPath("A", "F").cost); // checks cost with expected cost
//		List<String> expectedPath = new ArrayList<String>(); // creates an expected path List and adds the nodes covered
//		expectedPath.add("A");
//		expectedPath.add("C");
//		expectedPath.add("D");
//		expectedPath.add("E");
//		expectedPath.add("F");
//
//		assertEquals(expectedPath, testGraph.shortestPathData("A", "F")); // checks if the shortest path found matches
//
//		// inserts new edge based on scenario 2 above
//		testGraph.insertEdge("B", "F", 1.9);
//
//		assertEquals(2.9, testGraph.computeShortestPath("A", "F").cost); // checks the new cost with the expected cost
//		assertEquals(2.9, testGraph.shortestPathCost("A", "F")); // checks cost using the shortestPathCost
//
//		expectedPath = new ArrayList<String>();
//		expectedPath.add("A");
//		expectedPath.add("B");
//		expectedPath.add("F");
//
//		assertEquals(expectedPath, testGraph.shortestPathData("A", "F"));
//
//	}
//
//	/**
//	 * Test 2 - Tests a non-path input for a small graph
//	 * 
//	 */
//	@Test
//	public void test2() {
//		DijkstraGraph<String, Double> testGraph = new DijkstraGraph<String, Double>();
//		// inserting nodes
//		testGraph.insertNode("A");
//		testGraph.insertNode("B");
//		testGraph.insertNode("C");
//		testGraph.insertNode("D");
//		testGraph.insertNode("E");
//		testGraph.insertNode("F");
//
//		// inserting edges
//		testGraph.insertEdge("A", "B", 1.0);
//		testGraph.insertEdge("A", "C", 2.0);
//		testGraph.insertEdge("C", "D", 2.0);
//		testGraph.insertEdge("D", "E", 2.0);
//		testGraph.insertEdge("E", "F", 2.0);
//		testGraph.insertEdge("B", "F", 1.9);
//
//		Exception exception = assertThrows(NoSuchElementException.class, () -> {
//			testGraph.computeShortestPath("B", "D"); // test a path where the 2 nodes are not connected
//		});
//
//		assertEquals("Start/end nodes invalid", exception.getMessage());
//	}
//
//	/**
//	 * Test 3 - checks for invalid paths in smaller graph
//	 * 
//	 * Checks if NoSuchElementException is accurately thrown for each invalid case
//	 */
//	@Test
//	public void test3() {
//		DijkstraGraph<String, Double> testGraph = new DijkstraGraph<String, Double>();
//		// inserting nodes
//		testGraph.insertNode("A");
//		testGraph.insertNode("B");
//		testGraph.insertNode("C");
//		testGraph.insertNode("D");
//		testGraph.insertNode("E");
//		testGraph.insertNode("F");
//
//		testGraph.insertEdge("A", "B", 1.0);
//		testGraph.insertEdge("A", "C", 2.0);
//		testGraph.insertEdge("C", "D", 2.0);
//		testGraph.insertEdge("D", "E", 2.0);
//		testGraph.insertEdge("E", "F", 2.0);
//		testGraph.insertEdge("B", "F", 1.9);
//
//		Exception exception = assertThrows(NoSuchElementException.class, () -> {
//			testGraph.computeShortestPath(null, "D"); // tests for null start node
//		});
//
//		assertEquals("Start/End nodes not valid", exception.getMessage());
//
//		exception = assertThrows(NoSuchElementException.class, () -> {
//			testGraph.computeShortestPath("A", null); // tests for null end node
//		});
//
//		assertEquals("Start/End nodes not valid", exception.getMessage());
//
//		exception = assertThrows(NoSuchElementException.class, () -> {
//			testGraph.computeShortestPath("H", "B"); // tests for invalid start node (node doesn't exist)
//		});
//
//		assertEquals("Start/End nodes not valid", exception.getMessage());
//
//		exception = assertThrows(NoSuchElementException.class, () -> {
//			testGraph.computeShortestPath("A", "L"); // tests for invalid end node (node doesn't exist)
//		});
//
//		assertEquals("Start/End nodes not valid", exception.getMessage());
//
//	}
//
//	/**
//	 * Test 4 - conducts simple tests on a larger graph
//	 * 
//	 * Tests path cost from A to A and D to I
//	 * 
//	 * Tests path from D to I using an arrayList of expectedOutput
//	 */
//	@Test
//	public void test4() {
//		DijkstraGraph<String, Double> testGraph = new DijkstraGraph<String, Double>();
//		// inserting nodes
//		testGraph.insertNode("A");
//		testGraph.insertNode("B");
//		testGraph.insertNode("D");
//		testGraph.insertNode("E");
//		testGraph.insertNode("F");
//		testGraph.insertNode("G");
//		testGraph.insertNode("H");
//		testGraph.insertNode("I");
//		testGraph.insertNode("L");
//		testGraph.insertNode("M");
//
//		// inserting edges
//		testGraph.insertEdge("A", "B", 1.0);
//		testGraph.insertEdge("A", "M", 5.0);
//		testGraph.insertEdge("A", "H", 8.0);
//		testGraph.insertEdge("B", "M", 3.0);
//		testGraph.insertEdge("D", "A", 7.0);
//		testGraph.insertEdge("D", "G", 2.0);
//		testGraph.insertEdge("F", "G", 9.0);
//		testGraph.insertEdge("G", "L", 7.0);
//		testGraph.insertEdge("H", "B", 6.0);
//		testGraph.insertEdge("H", "I", 2.0);
//		testGraph.insertEdge("I", "H", 2.0);
//		testGraph.insertEdge("I", "D", 1.0);
//		testGraph.insertEdge("I", "L", 5.0);
//		testGraph.insertEdge("M", "E", 3.0);
//		testGraph.insertEdge("M", "F", 4.0);
//
//		// checks path costs from A to A (same node)
//		assertEquals(0, testGraph.shortestPathCost("A", "A"));
//		// checks path costs from D to I - should result in cost of 17
//		assertEquals(17, testGraph.shortestPathCost("D", "I"));
//
//		List<String> expectedOutput = new ArrayList<String>();
//
//		expectedOutput.add("D");
//		expectedOutput.add("A");
//		expectedOutput.add("H");
//		expectedOutput.add("I");
//
//		assertEquals(expectedOutput, testGraph.shortestPathData("D", "I")); // checks if output is accurate based on
//																			// expectations
//	}
//
//	/**
//	 * Tests invalid paths on a larger tree
//	 * 
//	 * Checks if NoSuchElementException is accurately thrown for each invalid case
//	 */
//	@Test
//	public void test5() {
//		DijkstraGraph<String, Double> testGraph = new DijkstraGraph<String, Double>();
//		// adding nodes
//		testGraph.insertNode("A");
//		testGraph.insertNode("B");
//		testGraph.insertNode("D");
//		testGraph.insertNode("E");
//		testGraph.insertNode("F");
//		testGraph.insertNode("G");
//		testGraph.insertNode("H");
//		testGraph.insertNode("I");
//		testGraph.insertNode("L");
//		testGraph.insertNode("M");
//
//		// adding edges
//		testGraph.insertEdge("A", "B", 1.0);
//		testGraph.insertEdge("A", "M", 5.0);
//		testGraph.insertEdge("A", "H", 8.0);
//		testGraph.insertEdge("B", "M", 3.0);
//		testGraph.insertEdge("D", "A", 7.0);
//		testGraph.insertEdge("D", "G", 2.0);
//		testGraph.insertEdge("F", "G", 9.0);
//		testGraph.insertEdge("G", "L", 7.0);
//		testGraph.insertEdge("H", "B", 6.0);
//		testGraph.insertEdge("H", "I", 2.0);
//		testGraph.insertEdge("I", "H", 2.0);
//		testGraph.insertEdge("I", "D", 1.0);
//		testGraph.insertEdge("I", "L", 5.0);
//		testGraph.insertEdge("M", "E", 3.0);
//		testGraph.insertEdge("M", "F", 4.0);
//
//		Exception exception = assertThrows(NoSuchElementException.class, () -> {
//			testGraph.computeShortestPath(null, "D"); // tests for null start node
//		});
//
//		assertEquals("Start/End nodes not valid", exception.getMessage());
//
//		exception = assertThrows(NoSuchElementException.class, () -> {
//			testGraph.computeShortestPath("A", null); // tests for null end node
//		});
//
//		assertEquals("Start/End nodes not valid", exception.getMessage());
//
//		exception = assertThrows(NoSuchElementException.class, () -> {
//			testGraph.computeShortestPath("V", "B"); // tests for invalid start node (node doesn't exist)
//		});
//
//		assertEquals("Start/End nodes not valid", exception.getMessage());
//
//		exception = assertThrows(NoSuchElementException.class, () -> {
//			testGraph.computeShortestPath("A", "X"); // tests for invalid end node (node doesn't exist)
//		});
//
//		assertEquals("Start/End nodes not valid", exception.getMessage());
//
//	}
}
