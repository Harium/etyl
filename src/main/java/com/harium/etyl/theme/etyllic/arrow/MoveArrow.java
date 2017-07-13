package com.harium.etyl.theme.etyllic.arrow;

public class MoveArrow extends NormalArrow {

	public MoveArrow(int size) {
		super(size);
		
		cursorX = size/2;
		cursorY = size/2;
	}
	
	@Override
	protected void defPoints() {
		
		final int d = size/20;
		final int s = (int) size;
		final int p = s/5;
		final int q = s/4;
		
		addPoint(s/2, 0);
		addPoint(s/2+q, p);
		addPoint(s/2+d, p);	
		addPoint(s/2+d, s/2-d);
		
		addPoint(s-p, s/2-d);
		addPoint(s-p,s/2-q);
		addPoint(s,s/2);
		addPoint(s-p,s/2+q);
		addPoint(s-p, s/2+d);
		addPoint(s/2+d, s/2+d);
		
		addPoint(s/2+d, s-p);		
		addPoint(s/2+d+p, s-p);
		addPoint(s/2, s);
		addPoint(s/2-d-p, s-p);
		addPoint(s/2-d, s-p);
		
		addPoint(s/2-d, s/2+d);
		
		addPoint(p, s/2+d);
		addPoint(p, s/2+q);
		addPoint(0,s/2);
		addPoint(p,s/2-q);
		addPoint(p, s/2-d);
		addPoint(s/2-d, s/2-d);
		
		addPoint(s/2-d, p);
		addPoint(s/2-q, p);
		
	}
	
}
