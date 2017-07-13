package com.harium.etyl.linear.graph;

public class GenericEdge <N> {

	protected Node<N> origin;
	
	protected Node<N> destination;
	
	public GenericEdge(Node<N> origin, Node<N> destination) {
		super();
		
		setOrigin(origin);
		setDestination(destination);		
	}

	public Node<N> getOrigin() {
		return origin;
	}

	public void setOrigin(Node<N> origin) {
		this.origin = origin;
	}

	public Node<N> getDestination() {
		return destination;
	}

	public void setDestination(Node<N> destination) {
		this.destination = destination;
		
		if(destination == destination.getParent()) {
			destination.setParent(origin);
		}
	}
		
}
