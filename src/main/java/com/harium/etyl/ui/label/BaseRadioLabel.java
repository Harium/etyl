package com.harium.etyl.ui.label;

import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.ui.Label;
import com.harium.etyl.ui.theme.Theme;

public class BaseRadioLabel extends Label {

	public BaseRadioLabel(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	@Override
	public void draw(Graphics g) {
		Theme theme = getTheme();
		
		//DrawShadow
		if(theme.isShadow()) {
			g.setColor(theme.getShadowColor());

			g.fillCircle(w/2, h/2, w/4+1);
		}

		g.setColor(theme.getTextColor());
		g.fillCircle(w/2, h/2, w/4);	
	}
	
}
