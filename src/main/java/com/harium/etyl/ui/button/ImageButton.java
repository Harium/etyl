package com.harium.etyl.ui.button;

import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.ui.base.BaseButton;
import com.harium.etyl.layer.ImageLayer;
import com.harium.etyl.layer.StaticLayer;

/**
 * 
 * @author yuripourre
 *
 */

public class ImageButton extends BaseButton {

	protected String normal;
	protected String sobMouse;
	protected String click;

	protected String sound = null;
	
	protected ImageLayer layer;

	public ImageButton(int x, int y, int w, int h, String normal){
		this(x,y,w,h, normal, normal, normal);
	}

	public ImageButton(int x, int y, int w, int h, String normal, String sobMouse){
		this(x,y,w,h,normal, sobMouse, sobMouse);
	}
	
	public ImageButton(int x, int y, int w, int h, String normal, String sobMouse, String click){
		super(x,y,w,h);
		this.normal = normal;
		this.sobMouse = sobMouse;
		this.click = click;
		
		this.layer = new ImageLayer(x,y,normal);
	}

	public ImageButton(int x, int y, StaticLayer normal, StaticLayer sobMouse) {
		this(x,y,normal.getW(),normal.getH(),normal.getPath(),sobMouse.getPath());
	}

	public ImageButton(int x, int y, StaticLayer normal, StaticLayer sobMouse, StaticLayer click) {
		this(x,y,normal.getW(),normal.getH(),normal.getPath(),sobMouse.getPath(),click.getPath());
	}

	public void setSound(String sound){
		this.sound = sound;
	}

	public void draw(Graphics g) {
		layer.draw(g);
		drawLabel(g);
	}
		
	@Override
	protected void leftClick(){
		layer.setPath(click);
	}
	
	@Override
	protected void rightClick(){
		layer.setPath(click);
	}

	@Override
	protected void middleClick(){
		layer.setPath(click);
	}

	@Override
	protected void justOnMouse(){
		layer.setPath(sobMouse);
	}
	
	@Override
	public void mouseOut(){
		super.mouseOut();
		layer.setPath(normal);
	}

	@Override
	public void setX(int x){
		this.x = x;

		centralizaLayer();		
	}

	@Override
	public void setY(int y){
		this.y = y;

		centralizaLayer();		
	}

	public void setCoordenadas(int x, int y){
		this.x = x;
		this.y = y;

		centralizaLayer();
	}

	private void centralizaLayer(){
		layer.setX(x);
		layer.setY(y);
	}

}
