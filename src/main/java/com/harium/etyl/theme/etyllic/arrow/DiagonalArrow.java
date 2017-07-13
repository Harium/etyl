package com.harium.etyl.theme.etyllic.arrow;

public class DiagonalArrow extends NormalArrow {

	public DiagonalArrow(int size) {
		super(size);
		
		cursorX = size/2;
		cursorY = size/2;
	}
	
	@Override
	protected void defPoints() {
		
		final int d = size/20;
		final int s = (int) size;
		
		addPoint(s, 0);
		addPoint(s, s/3);
		addPoint(s-s/5+d, s/5+d);
		
		addPoint(s/5+d, s-s/5+d);
		
		addPoint(s/3, s);
		addPoint(0, s);
		addPoint(0, s-s/3);
		
		addPoint(s/5-d, s-s/5-d);
		addPoint(s-s/5-d, s/5-d);
		addPoint(s-s/3, 0);
		addPoint(s, 0);
				
	}
	
}
