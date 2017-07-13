package com.harium.etyl.linear.graph;

import java.util.ArrayList;
import java.util.List;

import com.harium.etyl.linear.Point2D;

public class GenericGraph<T, E extends GenericEdge<T>> {

	protected List<Node<T>> nodes;
	
	protected List<E> edges;
	
	public GenericGraph() {
		super();
		
		nodes = new ArrayList<Node<T>>();
		edges = new ArrayList<E>();
	}

	public List<Node<T>> getNodes() {
		return nodes;
	}

	public void setNodes(List<Node<T>> nodes) {
		this.nodes = nodes;
	}

	public void addNode(Node<T> node) {
		this.nodes.add(node);
	}
	
	public Node<T> addNode(int index, Point2D point) {
		
		Node<T> node = new Node<T>(point.getX(), point.getY());
		this.nodes.add(index, node);
		
		return node;
	}
	
	public Node<T> addNode(Point2D point) {
		
		Node<T> node = new Node<T>(point.getX(), point.getY());
		this.nodes.add(node);
		
		return node;
	}

	public List<E> getEdges() {
		return edges;
	}
	
	public List<E> getEdges(Node<T> node) {
		
		List<E> nodeEdges = new ArrayList<E>();
		
		for(E edge:edges) {
			if(edge.getOrigin()==node||edge.getDestination()==node) {
				nodeEdges.add(edge);
			}
		}
		
		return nodeEdges;
	}
	
	public void removeNode(Node<T> node) {
		nodes.remove(node);
		removeEdgesFromNode(node);
	}
	
	public void removeEdgesFromNode(Node<T> node) {
		for (int i = edges.size()-1;i>=0;i--) {
			
			E edge = edges.get(i);
			
			if (edge.getOrigin() == node || edge.getDestination() == node) {
				edges.remove(i);
			}
		}
	}
	
	public boolean hasEdge(Node<Integer> node, Node<Integer> otherNode) {
		for(E edge:edges) {
			if ((edge.getOrigin()==node||edge.getDestination()==node) ||
			   (edge.getOrigin()==otherNode||edge.getDestination()==otherNode)) {
				return true;
			}
		}
		
		return false;
	}

	public void setEdges(List<E> edges) {
		this.edges = edges;
	}
	
	public void addEdge(E edge) {
		this.edges.add(edge);
	}
			
	public void clear() {
		nodes.clear();
		edges.clear();
	}

	public List<Node<T>> neighbors(Node<T> node) {
		Node<T> parent = node.getParent();
		
		List<Node<T>> neighbors = new ArrayList<Node<T>>();
		
		if(parent == null) {
			return neighbors;
		}
		
		for(E edge:getEdges(node)) {
			
			Node<T> origin = edge.getOrigin();
			
			if(!neighbors.contains(origin)) {
				neighbors.add(origin);	
			}
			
			Node<T> destination = edge.getDestination();
			
			if(!neighbors.contains(destination)) {
				neighbors.add(destination);	
			}
		}
		
		return neighbors;
	}
	
	public boolean isEmpty() {
		return nodes.size() == 0;
	}

	public void moveNodes(int x, int y) {
		for(Node<T> node: nodes) {
			node.getPoint().setOffset(x, y);
		}
	}
	
}
