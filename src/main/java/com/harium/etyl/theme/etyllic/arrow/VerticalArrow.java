package com.harium.etyl.theme.etyllic.arrow;

public class VerticalArrow extends NormalArrow {

	public VerticalArrow(int size) {
		super(size);
		
		cursorX = size/2;
		cursorY = size/2;
	}
	
	@Override
	protected void defPoints() {
		
		final int d = size/20;
		final int s = (int) size;
		
		addPoint(s/2, 0);
		addPoint(s/2+s/3, s/4);
		addPoint(s/2+d, s/4);
		addPoint(s/2+d, s-s/4);
		addPoint(s/2+s/3, s-s/4);
		addPoint(s/2, s);
		addPoint(s/2-s/3, s-s/4);
		addPoint(s/2-d, s-s/4);
		addPoint(s/2-d, s/4);
		addPoint(s/2-s/3, s/4);
		addPoint(s/2, 0);
						
	}
	
}
