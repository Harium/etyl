package com.harium.etyl.ui.base;

import com.harium.etyl.commons.event.GUIEvent;
import com.harium.etyl.commons.event.KeyEvent;
import com.harium.etyl.commons.event.PointerEvent;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.ui.ViewGroup;
import com.harium.etyl.ui.style.Roundness;

/**
 * Panel component
 * 
 * @author yuripourre
 *
 */

public class BasePanel extends ViewGroup {
	
	public BasePanel(int x, int y, int w, int h) {
		super(x,y,w,h);
	}
	
	@Override
	public GUIEvent updateMouse(PointerEvent event) {
		// TODO Auto-generated method stub
		return GUIEvent.NONE;
	}

	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {
		// TODO Auto-generated method stub
		return GUIEvent.NONE;
	}

	@Override
	public void updateEvent(GUIEvent event) {
		
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(getTheme().getPanelColor());
		roundPanel(g, left(), top(), width(), height(), style.roundness);
	}
	
	public static void roundPanel(Graphics g, int x, int y, int w, int h, Roundness roundness) {
		g.fillArc(x, y, roundness.width*2, roundness.height*2, 90, 90);
		g.fillRect(x+roundness.width, y, w-roundness.width*2, roundness.height);
		g.fillArc(x+w-roundness.width*2, y, roundness.width*2, roundness.height*2, 0, 90);
		
		g.fillRect(x, y+roundness.height, w, h-roundness.height*2);
		
		g.fillArc(x, y+h-roundness.height*2, roundness.width*2, roundness.height*2, 180, 90);
		g.fillRect(x+roundness.width, y+h-roundness.height, w-roundness.width*2, roundness.height);
		g.fillArc(x+w-roundness.width*2, y+h-roundness.height*2, roundness.width*2, roundness.height*2, 270, 90);
	}

}
