package com.harium.etyl.awt;

import java.awt.Cursor;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.MemoryImageSource;

import com.harium.etyl.awt.core.input.AWTKeyboard;
import com.harium.etyl.core.InnerCore;
import com.harium.etyl.core.graphics.Monitor;
import com.harium.etyl.core.input.keyboard.Keyboard;
import com.harium.etyl.core.input.mouse.Mouse;

/**
 * 
 * @author yuripourre
 *
 */

public class FullScreenWindow extends Frame {
	
	private static final long serialVersionUID = -5176767672500250086L;
	
	private InnerCore core;
	
	private int w;
	private int h;
		
	public FullScreenWindow(InnerCore core, Monitor selectedMonitor) {
		this.core = core;
		
		this.w = selectedMonitor.getW();
		this.h = selectedMonitor.getH();
		
		this.setBounds(selectedMonitor.getX(), selectedMonitor.getY(), selectedMonitor.getW(), selectedMonitor.getH());
		
		//These configurations are needed in some cases
		setUndecorated(true);
		setResizable(true);
				
		hideDefaultCursor();
		
		setVisible(true);
		setAlwaysOnTop(true);
		setListeners();
	}
	
	private void setListeners() {
		Mouse mouse = core.getControl().getMouse();
		Keyboard keyboard = core.getControl().getKeyboard();
		
		addMouseMotionListener( mouse );
		addMouseWheelListener( mouse );
		addMouseListener( mouse );
		addKeyListener( (AWTKeyboard) keyboard );
	}
		
	public void draw(Image volatileImage) {		
		Image resized = volatileImage.getScaledInstance(w, h, Image.SCALE_REPLICATE);
	    
		getGraphics().drawImage(resized, 0, 0, null);
	}
	
	private void hideDefaultCursor() {
		int[] pixels = new int[16 * 16];
		Cursor transparentCursor = Toolkit.getDefaultToolkit().createCustomCursor(
				Toolkit.getDefaultToolkit().createImage( new MemoryImageSource(16, 16, pixels, 0, 16))
				, new Point(0, 0), "invisibleCursor");
		setCursor( transparentCursor );
		
		//core.showCursor();
	}
	
}
