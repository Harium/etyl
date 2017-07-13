package com.harium.etyl.ui.style;

public class Bounds {
	
	public int top, bottom, left, right;
	
	public Bounds() {
		super();
	}
	
	public Bounds(int size) {
		this(size, size, size, size);
	}
	
	public Bounds(int top, int right, int bottom, int left) {
		super();
		this.top = top;
		this.right = right;
		this.bottom = bottom;
		this.left = left;
	}
}
