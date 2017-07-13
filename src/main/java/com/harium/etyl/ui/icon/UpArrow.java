package com.harium.etyl.ui.icon;

public class UpArrow extends PolygonalIcon {

	public UpArrow(int x, int y, float size) {
		super(x, y, size);				
	}	
	
	protected void initPolygon(int x, int y) {
		polygon.reset();
		
		polygon.addPoint((int)(x+size/2), (int)y);
		polygon.addPoint((int)x, (int)(y+size));
		polygon.addPoint((int)(x+size), (int)(y+size));
	}

}
