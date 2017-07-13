package com.harium.etyl.linear.graph;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.harium.etyl.linear.graph.common.IntegerEdge;
import com.harium.etyl.linear.graph.common.IntegerGraph;

public class ComplexGraphTest {

	private Node<Integer> root;
	
	private Node<Integer> firstChild;
	
	private GenericComplexGraph<Integer, IntegerEdge> graph;
	
	@Before
	public void setUp() {
	
		graph = new IntegerGraph();
		
		root = new Node<Integer>();
		root.setLocation(80, 190);
		
		firstChild = new Node<Integer>();
		Node<Integer> secondChild = new Node<Integer>();
		Node<Integer> thirdChild = new Node<Integer>();
		
		Node<Integer> firstChildSon = new Node<Integer>();
		
		//Add three child nodes
		graph.addNode(root);
		graph.addNode(firstChild);
		graph.addNode(secondChild);
		graph.addNode(thirdChild);
		
		graph.addEdge(new IntegerEdge(root, firstChild));
		graph.addEdge(new IntegerEdge(root, secondChild));
		graph.addEdge(new IntegerEdge(root, thirdChild));
		
		graph.addEdge(new IntegerEdge(firstChild, firstChildSon));
		graph.addEdge(new IntegerEdge(firstChild, new Node<Integer>()));
				
	}	
	
	@Test
	public void testAddEdges() {

		Assert.assertEquals(6, graph.getNodes().size());
		
		//Just two nodes have at least one child
		Assert.assertEquals(2, graph.getAllEdges().size());
				
		Assert.assertEquals(3, graph.getEdges(root).size());
		
		Assert.assertEquals(2, graph.getEdges(firstChild).size());
		
	}
	
}
