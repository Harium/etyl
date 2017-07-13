package com.harium.etyl.ui.theme.cursor.arrow;

import java.awt.Polygon;

public class PolygonalArrow {

	protected Polygon polygon;
	
	public PolygonalArrow() {
		super();
		
		polygon = new Polygon();
	}
		
	protected void addPoint(int x, int y) {
		polygon.addPoint(x, y);
	}
	
}
