package com.harium.etyl.ui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import com.harium.etyl.commons.event.KeyEvent;
import com.harium.etyl.commons.event.PointerEvent;
import com.harium.etyl.commons.event.GUIEvent;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.ui.base.BaseButton;

/**
 * 
 * @author yuripourre
 *
 */

public class ButtonList extends View{
	
	List<BaseButton> buttons = new ArrayList<BaseButton>();
	
	public ButtonList(int x, int y, int w, int h){
		super(x, y, w, h);
	}

	@Override
	public GUIEvent updateMouse(PointerEvent event){
		
		for(BaseButton button: buttons){
			button.updateMouse(event);
		}
		
		return GUIEvent.NONE;
	}

	@Override
	public void updateEvent(GUIEvent event) {
		// TODO Auto-generated method stub
		
	}
	
	//TODO Desenha o botao ou o scroll 
	public void draw(Graphics g){
		
		//BufferedImage bimg = g.getBimg();
		
		//g.setBimg(bimg.getSubimage(0, y, w, h));
		
		for(BaseButton button: buttons){
			if(button.getY()<h-100){
				button.draw(g);
			}else{
				break;
			}
		}
		
		//g.setBimg(bimg);
		
		drawScroll(g);
		
		
	}
	
	private void drawScroll(Graphics g){
		g.setColor(Color.GREEN);
		g.fillRect(x+w-10,y,5,h);
		
	}
	
	public void add(BaseButton button){
		buttons.add(button);
	}
	
	public void clear(){
		buttons.clear();
	}
	
	@Override
	public void setLocation(int offsetX, int offsetY){
		for(BaseButton button: buttons){
			button.setLocation(offsetX, offsetY);
		}
		
		this.x += offsetX;
		this.y += offsetY;
	}

	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {
		// TODO Auto-generated method stub
		return GUIEvent	.NONE;
	}

}
