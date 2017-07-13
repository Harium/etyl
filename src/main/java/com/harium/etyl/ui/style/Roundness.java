package com.harium.etyl.ui.style;

public class Roundness {
	
	public int width;
	public int height;
	
	public Roundness() {
		super();
	}
	
	public Roundness(int radius) {
		this(radius, radius);
	}
	
	public Roundness(int width, int height) {
		super();
		this.width = width;
		this.height = height;
	}

	public Roundness(Roundness roundness) {
		super();
		this.width = roundness.width;
		this.height = roundness.height;
	}
	
}
