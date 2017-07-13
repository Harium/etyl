package com.harium.etyl.theme.etyllic.arrow;

public class InvertedDiagonalArrow extends NormalArrow {

	public InvertedDiagonalArrow(int size) {
		super(size);
		
		cursorX = size/2;
		cursorY = size/2;
	}
	
	@Override
	protected void defPoints() {
		
		final int d = size/20;
		final int s = (int) size;
		
		addPoint(0, 0);
		addPoint(0, s/3);
		addPoint(s/5-d, s/5+d);
		
		addPoint(s-s/5-d, s-s/5+d);
		
		addPoint(s-s/3, s);
		addPoint(s, s);
		addPoint(s, s-s/3);
		
		addPoint(s-s/5+d, s-s/5-d);
		addPoint(s/5+d, s/5-d);
		addPoint(s/3, 0);
		addPoint(0, 0);
		
	}
	
}
