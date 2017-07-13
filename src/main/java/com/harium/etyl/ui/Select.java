package com.harium.etyl.ui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import com.harium.etyl.commons.event.Action;
import com.harium.etyl.commons.event.GUIEvent;
import com.harium.etyl.commons.event.KeyEvent;
import com.harium.etyl.commons.event.PointerEvent;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.ui.base.BaseButton;
import com.harium.etyl.ui.icon.DownArrow;
import com.harium.etyl.ui.list.Option;

public class Select extends View {
	
	private List<Option> options = new ArrayList<Option>();
	
	private BaseButton button;
	
	private int selectedOption = 0;
	
	private boolean showOptions = false;
	
	public Select(int x, int y, int w, int h) {
		super(x,y,w,h);

		int buttonSize = h;

		button = new BaseButton(x+w-buttonSize,y,buttonSize,buttonSize);

		DownArrow arrow = new DownArrow(-buttonSize/4, -buttonSize/3, buttonSize/2);
		button.setLabel(arrow);
		button.addAction(GUIEvent.MOUSE_LEFT_BUTTON_UP, new Action(this, "swapShowOptions"));

		add(button);
	}

	public void rebuild() {
		button.rebuild();
		//Rebuild Options
	}

	public void swapShowOptions() {
		showOptions = !showOptions;
	}
	
	public void addOption(Option option) {
		options.add(option);
		//optionsPanel.addOption(option);
	}
	
	
	@Override
	public void updateEvent(GUIEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics g) {
		
		g.setColor(Color.BLACK);
		g.drawRect(x, y, w, h);
		
		int fontSize = g.getFont().getSize()+1;
		int textOffset = 2;
		
		g.drawString(options.get(selectedOption).getLabel(), x+textOffset, y+fontSize);
		
		if(showOptions) {
		
			float initialY = y+h;
			float finalY = h*(options.size()+1);
			
			g.setColor(Color.WHITE);
			g.fillRect(x, initialY, w, finalY);
			
			g.setColor(Color.BLACK);
			int i=0;			
			
			for(Option option: options) {
				g.drawString(option.getLabel(), x+textOffset, y+h*(2+i));
				
				i++;
			}

			g.setColor(Color.BLACK);
			g.drawRect(x, initialY, w, finalY);
		}
	}

	@Override
	public GUIEvent updateMouse(PointerEvent event) {
		return GUIEvent	.NONE;
	}

	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {
		// TODO Auto-generated method stub
		return GUIEvent	.NONE;
	}

	

}