import java.util.ArrayList;
import java.util.List;

public class DijkstraGraphWithMSTBD<NodeType, EdgeType extends Number> extends DijkstraGraph<NodeType, EdgeType>
		implements DijkstraGraphWithMSTInterfaceAE<NodeType, EdgeType> {

	@Override
	public boolean removeNode(NodeType data) {
		try {
			super.removeNode(data);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean containsNode(NodeType data) {
		return super.containsNode(data);
	}

	@Override
	public boolean removeEdge(NodeType pred, NodeType succ) {
		try {
			super.removeEdge(pred, succ);
			return true;
		} catch(Exception e) {
			return false;
		}
	}

	@Override
	public boolean containsEdge(NodeType pred, NodeType succ) {
		return super.containsEdge(pred, succ);
	}

	@Override
	public EdgeType getEdge(NodeType pred, NodeType succ) {
		return super.getEdge(pred, succ);
	}

	@Override
	public boolean insertNode(NodeType data) {
		return super.insertNode(data);
	}

	@Override
	public boolean insertEdge(NodeType pred, NodeType succ, EdgeType weight) {
		return super.insertEdge(pred, succ, weight);
	}

	@Override
	public int getNumNodes() {
		return super.getNodeCount();
	}

	@Override
	public int getNumEdges() {
		return super.getEdgeCount();
	}

	@Override
	public List<NodeType> MSTData(NodeType start) {
		List<NodeType> list = new ArrayList<>();
		return list;
	}

	@Override
	public List<NodeType> shortestPathData(NodeType start, NodeType end) {
		return super.shortestPathData(start, end);
	}

}
