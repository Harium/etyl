package com.harium.etyl.ui;

import java.util.HashMap;
import java.util.Map;

import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.ui.style.Orientation;

/**
 * 
 * @author yuripourre
 *
 */

public abstract class ViewGroup extends View {

	protected static final int DYNAMIC = -20;
	
	protected int rowSize = DYNAMIC;
	protected boolean shrink = true;
	
	private Map<Long, Float> weights = new HashMap<Long, Float>();
	protected Orientation orientation = Orientation.VERTICAL;
	
	public ViewGroup(int x, int y, int w, int h) {
		super(x,y,w,h);
		clipOnDraw = true;
	}
	
	@Override
	public void add(View component) {
		this.add(component, 1);
	}
	
	public void add(View component, float weight) {
		super.add(component);
		weights.put(component.getId(), weight);
		
		resize();
	}
	
	@Override
	public void resize() {
		final int vx = left();
		final int vw = width();
		final int vy = top();
		final int vh = height();
				
		int lastPosition = 0;
		int totalSpace = 0;
		
		if (orientation == Orientation.VERTICAL) {
			totalSpace = vh-style.padding.top-style.padding.bottom;
		} else {
			totalSpace = vw-style.padding.left-style.padding.right;
		}
		
		int count = 0;
		for(View view: views) {
			count ++;
			
			int size = rowSize;
			
			if (rowSize == DYNAMIC) {
				size = (int)viewSize(totalSpace, view);
				if (count == views.size()) {
					size--;
				}
			}

			//Vertical Panel
			if (orientation == Orientation.VERTICAL) {
				view.setBounds(vx+style.padding.left, vy+lastPosition, vw-style.padding.left-style.padding.right-1, size);
			} else {
				view.setBounds(vx+lastPosition, vy+style.padding.top, size, vh-style.padding.top-style.padding.bottom-1);
			}
			
			lastPosition += size;
			view.resize();
		}
		
		if (rowSize != DYNAMIC && shrink) {
			if (orientation == Orientation.VERTICAL) {
				this.setH(count * rowSize);
			} else if (orientation == Orientation.HORIZONTAL) {
				this.setW(count * rowSize);
			}
		}
	}
	
	@Override
	public void setBounds(int x, int y, int w, int h) {
		super.setBounds(x, y, w, h);
		resize();
	}
	
	private float viewSize(int total, View view) {
		float sizeUnit = total/weightSum();
		float size = weights.get(view.getId())*sizeUnit;
		
		return size;
	}
		
	private float weightSum() {
		float sum = 0;
		
		for (Float weight: this.weights.values()) {
			sum += weight;
		}
		return sum;
	}
	
	public Orientation getOrientation() {
		return orientation;
	}

	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}

	public void setRowSize(int rowSize) {
		this.rowSize = rowSize;
	}
		
	public boolean isShrink() {
		return shrink;
	}

	public void setShrink(boolean shrink) {
		this.shrink = shrink;
	}

	@Override
    public void drawWithChildren(Graphics g) {
		if (!visible) {
			return;
		}
		if (isClipOnDraw()) {
    		g.setClip(x, y, w, h);
    	}
    	super.drawWithChildren(g);
    	if (isClipOnDraw()) {
    		g.resetClip();	
    	}
	}

	public void copy(ViewGroup view) {
		super.copy(view);
		rowSize = view.rowSize;
		shrink = view.shrink;
		orientation = view.orientation;

		for (Map.Entry<Long, Float> entry : view.weights.entrySet()) {
			weights.put(entry.getKey(), entry.getValue());
		}
	}

}
