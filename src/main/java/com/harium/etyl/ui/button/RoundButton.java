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

public class RoundButton extends BaseButton {

	public RoundButton(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	@Override
	public void draw(Graphics g){

		Theme theme = getTheme();

		if(!mouseOver){
			
			if(onFocus){
				
				g.setColor(theme.getButtonOnFocus());
				
			}else{
				
				g.setColor(theme.getBaseColor());
				
			}

		} else {		
			if (lastEvent == GUIEvent.MOUSE_LEFT_BUTTON_DOWN) {
				g.setColor(theme.getActiveColor());
			} else {
				g.setColor(theme.getSelectionColor());
			}
		}

		g.fillOval(x,y,w,h);
		drawLabel(g);
	}

}
