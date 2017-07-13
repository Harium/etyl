package com.harium.etyl.ui.icon;

public class DownArrow extends PolygonalIcon {

	public DownArrow(int x, int y, float size){
		super(x, y, size);
	}	
	
	protected void initPolygon(int x, int y){
		polygon.reset();
		
		polygon.addPoint(x, y);
		polygon.addPoint((int)(x+size), (int)y);
		polygon.addPoint((int)(x+size/2), (int)(y+size));
	}

}
