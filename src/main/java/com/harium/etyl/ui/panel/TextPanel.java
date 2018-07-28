package com.harium.etyl.ui.panel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import com.harium.etyl.commons.event.GUIEvent;
import com.harium.etyl.commons.event.KeyEvent;
import com.harium.etyl.commons.event.PointerEvent;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.ui.View;

public class TextPanel extends View{

	private Color backgroundcolor = Color.WHITE;
	private Color bordercolor = Color.BLACK;
	private float borderWidth = 2f;

	private int paddingTop = 5;//pixels;
	private int paddingRight = 4;//pixels;
	
	private int spacing = 0;
	
	//TODO theme.fontSize
	private float fontSize = 20;
	
	private List<String> lines = new ArrayList<String>();

	public TextPanel(int w, int h){
		super(0,0,w,h);
	}
	
	public TextPanel(int x, int y, int w, int h){
		super(x,y,w,h);
	}
	
	public void addLine(String line){
		
		lines.add(line);
		
		//This +1 is because String is rendered from bottom to top 
		if(h<(lines.size()+1)*fontSize){
			h += fontSize;
		}

	}

	@Override
	public void updateEvent(GUIEvent event) {
		// TODO Auto-generated method stub
	}

	@Override
	public void draw(Graphics g) {
		
		g.setColor(backgroundcolor);
		g.fillRect(x,y,w,h);
		
		g.getGraphics().setStroke(new BasicStroke(borderWidth));
		g.setColor(bordercolor);
		g.drawRect(x,y,w,h);
		g.getGraphics().setStroke(new BasicStroke(1f));

		int i=0;
		
		for(String line: lines){
			
			g.drawString(line, x+paddingRight, y+paddingTop+(int)(fontSize+(i*fontSize+spacing)));
			
			i++;
			
		}

	}

	@Override
	public GUIEvent updateMouse(PointerEvent event) {
		// TODO Auto-generated method stub
		return GUIEvent.NONE;
	}

	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {
		// TODO Auto-generated method stub
		return GUIEvent.NONE;
	}

}