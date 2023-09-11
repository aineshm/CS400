import java.util.List;

public interface DijkstraGraphWithMSTInterfaceAE<NodeType, EdgeType extends Number>
		extends GraphADT<NodeType, EdgeType> {

//public DijkstraGraphWithMSTInterface();

	/**
	 * Inserts a node into the graph
	 */
	public boolean insertNode(NodeType data);

	/**
	 * Inserts an edge into the graph
	 */
	public boolean insertEdge(NodeType pred, NodeType succ, EdgeType weight);

	/**
	 * Returns the number of nodes in the graph
	 */
	public int getNumNodes();

	/**
	 * returns the number of edges in the graph
	 */
	public int getNumEdges();

	/**
	 * Returns the data stored in the graph nodes by computing a MST and returning
	 * the data in the order the nodes are visited
	 */
	public List<NodeType> MSTData(NodeType start);

	/**
	 * Returns the data stored in the nodes along the shortest path of the graph
	 */
	public List<NodeType> shortestPathData(NodeType start, NodeType end);
}
