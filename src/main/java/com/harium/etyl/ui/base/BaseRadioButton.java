package com.harium.etyl.ui.base;

import com.harium.etyl.commons.event.GUIEvent;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.ui.RadioGroup;
import com.harium.etyl.ui.label.BaseRadioLabel;

/**
 * Radio Button component
 * 
 * @author yuripourre
 *
 */

public class BaseRadioButton extends BaseCheckBox {

	private RadioGroup group;
	private String value;

	public BaseRadioButton(int x, int y) {
		this(x,y, 22, 22);
	}
	
	public BaseRadioButton(int x, int y, int w, int h) {
		super(x,y,w,h);
		checker = new BaseRadioLabel(x, y, w, h);
	}

	@Override
	protected void leftClick() {
		check();
	}
	
	@Override
	public void updateEvent(GUIEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Graphics g) {
		g.setColor(getTheme().getBackgroundColor());
		g.fillOval(x, y, w, h);
		
		if(!mouseOver) {
			g.setColor(getTheme().getTextFieldWithoutFocusColor());
		}else{
			g.setColor(getTheme().getTextFieldOnMouseColor());
		}
		
		g.drawOval(x, y, w, h);
		
		g.setColor(getTheme().getTextFieldWithoutFocusColor());
		
		if (isChecked()) {
			g.fillCircle(x+w/2, y+h/2, w/5);
		}
	}

	public RadioGroup getGroup() {
		return group;
	}

	public void setGroup(RadioGroup group) {
		this.group = group;
	}

	@Override
	public boolean onMouse(int mx, int my) {
		return colideCirclePoint(mx, my);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public boolean isChecked() {
		if (group != null) {
			return this == group.getChecked();
		}
		return checked;
	}
	
	public void check() {
		if(!isChecked()) {
			if (group != null) {
				group.check(this);
			}
		}
	}

	public void copy(BaseRadioButton view) {
		super.copy(view);
		group = view.group;
		value = view.value;
	}
	
}
