package com.harium.etyl.ui;

import java.awt.Color;

import com.harium.etyl.commons.event.Action;
import com.harium.etyl.commons.event.GUIEvent;
import com.harium.etyl.commons.event.KeyEvent;
import com.harium.etyl.commons.event.MouseEvent;
import com.harium.etyl.commons.event.PointerEvent;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.ui.base.BaseButton;
import com.harium.etyl.ui.icon.DownArrow;
import com.harium.etyl.ui.icon.UpArrow;
import com.harium.etyl.ui.panel.ScrollBackground;

/**
 * 
 * @author yuripourre
 * 
 */

public class ScrollView extends View {

    private View component;

    private int buttonSize = 20;
    private int scrollAmount = 10;
    private int scrollCursor = 0;
    private int steps = 0;
    private float scrollFactor = 1;
    private float offset = 0;
    private float knobPosition = 0;

    private BaseButton upButton;
    private BaseButton downButton;
    private BaseButton knob;
    private ScrollBackground track;

    public ScrollView(int x, int y, int w, int h) {
        super(x, y, w, h);

        clipOnDraw = true;
        buildButtons();
    }

    public void rebuild() {
        buildButtons();
    }
    
    private void buildButtons() {
    	upButton = new BaseButton(x + w - buttonSize, y, buttonSize, buttonSize);
        upButton.setLabel(new UpArrow((-buttonSize / 3), -buttonSize / 3, buttonSize / 2));
        upButton.addAction(GUIEvent.MOUSE_LEFT_BUTTON_UP, new Action(this, "scrollUp"));
        upButton.setVisible(false);

        downButton = new BaseButton(x + w - buttonSize, y + h - buttonSize, buttonSize, buttonSize);
        downButton.setLabel(new DownArrow(-buttonSize / 3, -buttonSize / 3, buttonSize / 2));
        downButton.addAction(GUIEvent.MOUSE_LEFT_BUTTON_UP, new Action(this, "scrollDown"));
        downButton.setVisible(false);

        track = new ScrollBackground(x + w - buttonSize, y + buttonSize, buttonSize, h - buttonSize * 2);
        track.setVisible(false);

        add(track);
        add(upButton);
        add(downButton);
    }
    
    @Override
    public void resize() {
    	upButton.setBounds(x + w - buttonSize, y, buttonSize, buttonSize);
    	downButton.setBounds(x + w - buttonSize, y + h - buttonSize, buttonSize, buttonSize);
    	track.setBounds(x + w - buttonSize, y + buttonSize, buttonSize, h - buttonSize * 2);
    	
    	/*if(component != null) {
    		setComponent(component);
    	}*/
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(left(), top(), width(), height());
    }
    
    @Override
    public void drawWithChildren(Graphics g) {
    	if (clipOnDraw) {
    		g.setClip(x, y, w, h);
    	}
    	super.drawWithChildren(g);
    	if (clipOnDraw) {
    		g.resetClip();	
    	}
	}
    
    @Override
    public void updateEvent(GUIEvent event) {

    }

    @Override
    public GUIEvent updateMouse(PointerEvent event) {

        if (mouseOver) {

            if (event.isButtonDown(MouseEvent.MOUSE_WHEEL_DOWN)) {

                for (int i = 0; i < event.getAmount(); i++) {
                    scrollDown();
                }
            }

            if (event.isButtonDown(MouseEvent.MOUSE_WHEEL_UP)) {

                for (int i = event.getAmount(); i < 0; i++) {
                    scrollUp();
                }
            }

            if (knob.isMouseOver()) {

                if (event.isButtonDown(MouseEvent.MOUSE_BUTTON_LEFT)) {
                    //TODO Mouse dragged with knob move scroll
                }

            }
        }

        return GUIEvent.NONE;
    }

    @Override
    public GUIEvent updateKeyboard(KeyEvent event) {

        if (event.isKeyDown(KeyEvent.VK_TAB)) {
            return GUIEvent.NEXT_COMPONENT;
        }

        return GUIEvent.NONE;
    }

    public void setComponent(View component) {
    	views.clear();
    	views.add(component);

        //Build Buttons based on size
    	buildButtons();

    	this.component = component;
    	component.cascadeClipOnDraw(false);
    	    	
        knobPosition = buttonSize;
        resetScroll();
        this.component.setBounds(x, y, component.getW(), component.getH());
        this.component.resize();
    }

    private void resetScroll() {
        if (component.getH() <= h) {
            return;
        }

        scrollCursor = 0;
        scrollFactor = ((float) (h) / (float) component.getH());
        
        float utilScrollH = (h - buttonSize * 2);
        float scrollSize = utilScrollH * scrollFactor;

        float dif = component.getH() - h;
        steps = (int) Math.abs(dif / scrollAmount);
        
        offset = (utilScrollH-scrollSize) / steps;

        if (knob == null) {
            knob = new BaseButton(x + w - buttonSize, y + (int) (knobPosition), buttonSize, ((int) (scrollSize)));
            add(knob);
        } else {
            knob.setBounds(x + w - buttonSize, y + (int) (knobPosition), buttonSize, ((int) (scrollSize)));
        }
        knob.setVisible(false);

        showButtons();
    }

    private void showButtons() {
        track.setVisible(true);
        upButton.setVisible(true);
        downButton.setVisible(true);
        knob.setVisible(true);
    }

    public void scrollDown() {
        if (scrollCursor < steps) {
            scrollCursor++;
            component.offsetY(-scrollAmount);
            component.resize();
            knobPosition = buttonSize+offset*scrollCursor;
            knob.setY(y + (int) knobPosition);
        }
    }

    public void scrollUp() {

        if (scrollCursor > 0) {
            scrollCursor--;
            component.offsetY(+scrollAmount);
            component.resize();
            knobPosition = buttonSize+offset*scrollCursor;
            knob.setY(y + (int) knobPosition);
        }
    }

    public BaseButton getKnob() {
        return knob;
    }

    public int getButtonSize() {
        return buttonSize;
    }

    public void setButtonSize(int buttonSize) {
        this.buttonSize = buttonSize;
    }

}