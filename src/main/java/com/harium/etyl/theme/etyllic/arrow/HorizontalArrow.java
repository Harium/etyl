package com.harium.etyl.theme.etyllic.arrow;

public class HorizontalArrow extends NormalArrow {

	public HorizontalArrow(int size) {
		super(size);
		
		cursorX = size/2;
		cursorY = size/2;
	}
	
	@Override
	protected void defPoints() {
		
		final int d = size/20;
		final int s = (int) size;
		
		addPoint(0,s/2);
		addPoint(s/4,s/2+s/3);
		addPoint(s/4,s/2+d);
		addPoint(s-s/4,s/2+d);
		addPoint(s-s/4,s/2+s/3);
		addPoint(s,s/2);
		addPoint(s-s/4, s/2-s/3);
		addPoint(s-s/4,s/2-d);
		addPoint(s/4,s/2-d);
		addPoint(s/4,s/2-s/3);
		addPoint(0,s/2);
						
	}
	
}
