package com.harium.etyl.linear.graph;

import com.harium.etyl.linear.Point2D;

public class Node<T> {
	
	private T data;
	
	private Node<T> parent;
	
	private Point2D point;
	
	public Node() {
		this(0, 0);
	}
	
	public Node(T data) {
		this(0, 0);
		setData(data);
	}
	
	public Node(double x, double y) {
		super();
		point = new Point2D(x, y);
		parent = this;
	}
	
	public Node(Point2D point) {
		super();
		
		this.point = point;		
	}

	public Point2D getPoint() {
		return point;
	}

	public void setPoint(Point2D point) {
		this.point = point;
	}
	
	public void setLocation(double x, double y) {
		point.setLocation(x, y);
	}

	public Node<T> getParent() {
		return parent;
	}

	public void setParent(Node<T> parent) {
		this.parent = parent;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
		
}
