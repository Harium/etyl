package com.harium.etyl.linear;

import java.util.ArrayList;
import java.util.List;

import com.harium.etyl.commons.Drawable;
import com.harium.etyl.core.graphics.Graphics;

public class PolygonalRegion implements Drawable {
		
	private boolean isClosed = true; 
	private List<Point2D> list = new ArrayList<Point2D>(); 
	
	public PolygonalRegion() {
		super();
	}
	
	public void addPoint(Point2D point) {
		list.add(point);
	}

	@Override
	public void draw(Graphics g) {
		if(list.size() == 0) {
			return;
		}
				
		final int pointRadius = 3;
		
		Point2D firstPoint = list.get(0);
		
		g.fillCircle(firstPoint, pointRadius);
		
		Point2D lastPoint = firstPoint;
		
		for(int i = 1; i<list.size(); i++) {
			Point2D point = list.get(i);
			
			g.drawLine(lastPoint, point);
			
			g.fillCircle(point, pointRadius);
			
			lastPoint = point;
		}
		
		if(isClosed) {
			g.drawLine(lastPoint, firstPoint);
		}
		
	}
	
}
