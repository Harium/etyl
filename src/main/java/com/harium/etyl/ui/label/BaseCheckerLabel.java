package com.harium.etyl.ui.label;

import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.ui.Label;
import com.harium.etyl.ui.theme.Theme;

public class BaseCheckerLabel extends Label {

	public BaseCheckerLabel(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	@Override
	public void draw(Graphics g) {
		Theme theme = getTheme();
		
		//DrawShadow
		if(theme.isShadow()) {
			g.setColor(theme.getShadowColor());

			g.drawLine(x+3,y+3,x+w/2+1, y+h/2+1);
			g.drawLine(x+w/2+1, y+h/2+1,x+w+5+1,y-5+1);
		}

		g.setColor(theme.getTextColor());

		g.drawLine(x+2,y+2,x+w/2, y+h/2);
		g.drawLine(x+w/2, y+h/2,x+w+5,y-5);	
	}
	
}
