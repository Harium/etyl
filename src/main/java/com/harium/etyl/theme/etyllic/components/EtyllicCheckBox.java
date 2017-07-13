package com.harium.etyl.theme.etyllic.components;

import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.ui.base.BaseCheckBox;

/**
 * 
 * @author yuripourre
 *
 */

public class EtyllicCheckBox extends BaseCheckBox {

	public EtyllicCheckBox(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
	@Override
	public void draw(Graphics g) {
		super.draw(g);
		
		if (checked) {
			checker.draw(g);
		}
	}

}
