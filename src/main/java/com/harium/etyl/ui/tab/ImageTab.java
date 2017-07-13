package com.harium.etyl.ui.tab;

import com.harium.etyl.commons.event.GUIEvent;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.ui.base.BaseButton;
import com.harium.etyl.ui.theme.Theme;
import com.harium.etyl.ui.theme.ThemeManager;

/**
 * 
 * @author yuripourre
 *
 */

public class ImageTab extends BaseButton {

	private boolean active = false;
	
	public ImageTab(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	@Override
	public void draw(Graphics g){

		Theme theme = ThemeManager.getInstance().getTheme();
		
		if(!mouseOver){

			if (!active) {
				g.setColor(theme.getBaseColor());
			} else {
				g.setColor(theme.getSelectionColor());
			}

		} else {
			
			if (lastEvent == GUIEvent.MOUSE_LEFT_BUTTON_DOWN) {
				g.setColor(theme.getActiveColor());
			} else {
				g.setColor(theme.getSelectionColor());
			}
		}
		
		g.fillArc(x, y, style.roundness.width*2, style.roundness.height*2, 90, 90);
		g.fillRect(x+style.roundness.width, y, w-style.roundness.width*2, style.roundness.height);
		g.fillArc(x+w-style.roundness.width*2, y, style.roundness.width*2, style.roundness.height*2, 0, 90);
		
		g.fillRect(x, y+style.roundness.height, w, h-style.roundness.height);
				
		//Draw Icon
		drawLabel(g);
		
	}
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
