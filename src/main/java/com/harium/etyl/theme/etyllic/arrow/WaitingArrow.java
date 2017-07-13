package com.harium.etyl.theme.etyllic.arrow;

/**
 * 
 * @author yuripourre
 *
 */

public class WaitingArrow extends NormalArrow {

	public WaitingArrow(int size) {
		super(size);
		
		cursorX = size/2;
		cursorY = size/2;
	}

	@Override
	protected void defPoints() {
		addPoint(0, 0);
		addPoint(size, 0);
		addPoint((int)(size*0.65),(int)(size*0.5));

		addPoint(size,size);
		addPoint(0,size);

		addPoint((int)(size*0.45),(int)(size*0.5));
	}


}
