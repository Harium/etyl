package com.harium.etyl.ui.theme;

import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.commons.Drawable;
import com.harium.etyl.core.input.mouse.MouseStateListener;
import com.harium.etyl.ui.theme.listener.ArrowThemeListener;


public interface ArrowDrawer extends MouseStateListener, Drawable, ArrowThemeListener {
	void drawTimerArc(Graphics g, int arc);
	void setLocation(int mouseX, int mouseY);
}
