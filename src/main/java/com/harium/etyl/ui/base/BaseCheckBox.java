package com.harium.etyl.ui.base;

import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.ui.Label;
import com.harium.etyl.ui.label.BaseCheckerLabel;

/**
 * Checkbox component
 * 
 * @author yuripourre
 *
 */

public class BaseCheckBox extends BaseButton {

	protected boolean checked = false;
	protected Label checker;

	public BaseCheckBox(int x, int y) {
		this(x, y, 22, 22);
	}

	public BaseCheckBox(int x, int y, int w, int h) {
		super(x, y, w, h);
		checker = new BaseCheckerLabel(x, y, w, h);
	}

	@Override
	protected void leftClick() {
		swapChecked();
	}

	private void swapChecked() {
		checked = !checked;		
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(getTheme().getBackgroundColor());
		g.fillRect(x, y, w, h);
		
		if(!mouseOver) {
			g.setColor(getTheme().getTextFieldWithoutFocusColor());
		}else{
			g.setColor(getTheme().getTextFieldOnMouseColor());
		}
		
		g.drawRect(x, y, w, h);
		
		if(isChecked()) {
			checker.draw(g);
		}
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public Label getChecker() {
		return checker;
	}

	public void setChecker(Label checker) {
		this.checker = checker;

		checker.setX(x+(w/2-checker.getW()/2)+checker.getX());
		checker.setY(y+(h/2-checker.getH()/2)+checker.getY());

		checker.setContentBounds(x, y, w, h);
	}

	public void copy(BaseCheckBox view) {
		super.copy(view);
		checked = view.checked;
		checker = view.checker;
	}
}
