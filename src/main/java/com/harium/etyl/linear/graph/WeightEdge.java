package com.harium.etyl.linear.graph;


public class WeightEdge<N> extends GenericEdge<N> {
	
	protected int weight = 0;
	
	public WeightEdge(Node<N> origin, Node<N> destination) {
		super(origin, destination);
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
		
}
