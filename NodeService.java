package hanoi;

import java.util.ArrayList;
import java.util.List;

public class NodeService {

	public Node createStartNode() {
		
		Node startNode = new Node();
		startNode.setPoleValues(Node.POLE_0, Node.DISK_BIG, Node.DISK_MEDIUM, Node.DISK_SMALL);
		
		return startNode;
	}

	public Node createEndNode() {
		
		Node endNode = new Node();
		endNode.setPoleValues(Node.POLE_2, Node.DISK_BIG, Node.DISK_MEDIUM, Node.DISK_SMALL);
		
		return endNode;
	}

	public void solve(Node startNode, Node endNode) {

		if (startNode.equals(endNode)) {
			printSolution(endNode);
			return;
		}
		
		ArrayList<Node> procesing = new ArrayList<Node>();
		ArrayList<Node> history = new ArrayList<Node>();

		procesing.add(startNode);
		history.add(startNode);

		while (!procesing.isEmpty()) {
			Node node = procesing.get(0);
			procesing.remove(0);

			List<Node> generatedNodes = generatedNodes(node);
			
			for (Node generatedNode : generatedNodes) {
				
				if (generatedNode.isEqual(endNode)) {
					printSolution(generatedNode);
					return;
				} else if (!isNodeInList(generatedNode, history)) {
					history.add(generatedNode);
					procesing.add(generatedNode);
				}

			}
		}
	}

	private Node cloneNode(Node node) {
		
		Node cloneNode = new Node();
		
		for (int i = 0; i < Node.BOARD_SIZE; i++) {
			for (int j = 0; j < Node.BOARD_SIZE; j++) {
				cloneNode.setBoardValue(i, j, node.getBoardValue(i, j));
			}

		}
		return cloneNode;
	}
	
	private boolean isNodeInList(Node node, List<Node> nodes) {
		
		for (Node n : nodes) {
			if (n.isEqual(node)) {
				return true;
			}
		}
		return false;
	}
	
	private List<Node> generatedNodes(Node node) {
		
		List<Node> nodes = new ArrayList<Node>();
		
		for (int i = 0; i < Node.BOARD_SIZE; i++) {
			for (int j = 0; j < Node.BOARD_SIZE; j++) {
				if (node.canMove(i, j)) {
					Node newNode = cloneNode(node);
					newNode.setParent(node);
					newNode.makeMove(i, j);
					nodes.add(newNode);
				}
			}
		}

		return nodes;
	}

	private void printSolution(Node node) {
		
		List<Node> moves = new ArrayList<Node>();
		moves.add(node);
		
		while(node.getParent() != null) {
			node = node.getParent();
			moves.add(node);
		}

		for(int i = moves.size() - 1, step = 0; i >= 0; i--, step++) {
			Node moveNode = moves.get(i);
			System.out.println("Step:" + step);
			System.out.println(moveNode);
		}
	}
}
