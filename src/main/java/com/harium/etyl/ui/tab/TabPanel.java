package com.harium.etyl.ui.tab;

import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.ui.Panel;
import com.harium.etyl.ui.base.BasePanel;

/**
 * 
 * @author yuripourre
 *
 */

public class TabPanel extends Panel {

	public TabPanel(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(getTheme().getSelectionColor());
		BasePanel.roundPanel(g, x, y, w, h, style.roundness);
	}
		
}
