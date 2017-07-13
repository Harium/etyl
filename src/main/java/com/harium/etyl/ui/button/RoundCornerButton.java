package com.harium.etyl.ui.button;

import com.harium.etyl.commons.event.GUIEvent;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.ui.base.BaseButton;
import com.harium.etyl.ui.theme.Theme;

/**
 * 
 * @author yuripourre
 *
 */

public class RoundCornerButton extends BaseButton {

	public RoundCornerButton(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
	@Override
	public void draw(Graphics g) {
		
		Theme theme = getTheme();

		if (!mouseOver) {
			g.setColor(theme.getBaseColor());
		} else {
			if (lastEvent == GUIEvent.MOUSE_LEFT_BUTTON_DOWN) {
				g.setColor(theme.getActiveColor());
			} else {
				g.setColor(theme.getSelectionColor());
			}
		}
		
		g.fillRoundRect(x, y, w, h, style.roundness.width, style.roundness.height);
		drawLabel(g);
	}
		
}
