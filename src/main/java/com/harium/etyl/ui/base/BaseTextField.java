package com.harium.etyl.ui.base;

import java.awt.FontMetrics;

import com.harium.etyl.commons.event.GUIEvent;
import com.harium.etyl.commons.event.KeyEvent;
import com.harium.etyl.commons.event.KeyState;
import com.harium.etyl.commons.event.MouseEvent;
import com.harium.etyl.commons.event.PointerEvent;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.ui.textfield.TextFieldView;
import com.harium.etyl.ui.theme.Theme;

/**
 * TextField component
 * 
 * @author yuripourre
 *
 */

public class BaseTextField extends TextFieldView {

	public BaseTextField(int x, int y, int w, int h) {
		super(x,y,w,h);
	}

	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {

		if (event.getState() == KeyState.TYPED) {
			//Update component with typed events
			if (event.getChar()!='\0') {
				updateChar(event.getChar());
			}
		}

		GUIEvent internalEvent = GUIEvent.NONE;
		//TODO Next Component
		//Update component with Pressed Events
		if (event.getState() == KeyState.PRESSED) {
			internalEvent = updatePressed(event);
			if (internalEvent != GUIEvent.NONE) {
				return internalEvent;		
			}
		} else if(event.getState() == KeyState.RELEASED) {
			internalEvent = updateReleased(event);
			if (internalEvent != GUIEvent.NONE) {
				return internalEvent;		
			}
		}

		//minMark = getMinMark();
		//maxMark = getMaxMark();

		return GUIEvent.NONE;
	}

	public GUIEvent updateMouse(PointerEvent event) {
		GUIEvent value = super.updateMouse(event);

		if (value != GUIEvent.NONE) {
			updateEvent(value);
			return value;
		}

		if (event.isButtonDown(MouseEvent.MOUSE_BUTTON_LEFT)) {
			if (mouseOver) {
				if(!onFocus) {
					value = GUIEvent.GAIN_FOCUS;
					updateEvent(value);
					return value;
				}
			} else if(onFocus) {
				value = GUIEvent.LOST_FOCUS;
				updateEvent(value);
				return value;
			}

		} else if (mouseOver) {
			if (!onFocus) {
				value = GUIEvent.MOUSE_OVER;

			} else {
				value = GUIEvent.MOUSE_OVER_WITH_FOCUS;
			}

		}

		updateEvent(value);
		return value;
	}

	private GUIEvent updatePressed(KeyEvent event) {

		if (!shift) {
			if ((event.isKeyDown(KeyEvent.VK_SHIFT_RIGHT))||(event.isKeyDown(KeyEvent.VK_SHIFT_LEFT))) {
				shift = true;
				maxMark = cursor;
				minMark = cursor;
			}
		}

		if (event.isKeyDown(KeyEvent.VK_END)) {
			cursor = text.length();
		}
		else if (event.isKeyDown(KeyEvent.VK_HOME)) {
			moveCursorToStart();
			//move cursor to start
		}

		if (!control) {
			if (event.isKeyDown(KeyEvent.VK_CTRL_RIGHT)||event.isKeyDown(KeyEvent.VK_CTRL_LEFT)) {
				control = true;
			}
		}

		if (event.isKeyDown(KeyEvent.VK_LEFT)) {
			if (shift && !control) {

				if(minMark == cursor) {
					minMark--;
					cursor--;
				} else if(maxMark == cursor) {
					maxMark--;
					cursor--;
				}

			} else if (control) {
				leftWithControl();
			} else {
				leftNormal();
			}
		}
		else if (event.isKeyDown(KeyEvent.VK_RIGHT)) {

			if (shift && !control) {
				if(maxMark == cursor) {
					maxMark++;
					cursor++;
				} else if(maxMark == cursor) {
					maxMark++;
					cursor++;
				}
			} else if (control) {
				rightWithControl();
			} else {
				rightNormal();
			}
		}

		if (event.isKeyDown(KeyEvent.VK_TAB)) {

			return GUIEvent.NEXT_COMPONENT;
		}

		return GUIEvent.NONE;
	}

	private boolean reachStart() {
		return cursor <= 0;
	}

	private boolean reachEnd() {
		return cursor >= text.length();
	}

	private GUIEvent updateReleased(KeyEvent event) {
		if (control) {
			if (event.isKeyDown(KeyEvent.VK_CTRL_RIGHT)||event.isKeyDown(KeyEvent.VK_CTRL_LEFT)) {
				control = false;
			}
		}

		if (shift) {
			if (event.isKeyUp(KeyEvent.VK_SHIFT_RIGHT)||event.isKeyUp(KeyEvent.VK_SHIFT_LEFT)) {
				shift = false;
				minMark = cursor;
				maxMark = cursor;
			}
		}

		return GUIEvent.NONE;
	}

	//TODO escreve texto.sub(0,minMark);
	//Para não sair da caixa
	public void draw(Graphics g) {
		Theme theme = getTheme();

		//g.setImage(layer.getBuffer());
		//int x = 0;
		//int y = 0;
		g.setClip(left(), top(), width(), height());

		//TODO
		//g.setFont(theme.getFont());

		//Para poder ser usado pelo password
		String text = this.getText();

		int fontSize = theme.getFontSize();

		FontMetrics metrics = g.getGraphics().getFontMetrics();
		//3 is necessary to show the cursor
		//3 = 1px bordex+1px padding +1px cursor
		float dif = w-3-metrics.stringWidth(text);

		//Remover
		if (onFocus) {
			g.setColor(theme.getTextFieldColor());
		} else {
			g.setColor(theme.getTextFieldWithoutFocusColor());
		}

		if (mouseOver) {
			g.setColor(theme.getTextFieldOnMouseColor());	
		}

		//g.drawRect(x,y,w,h);
		g.drawRect(left(), top(), width(), height());

		g.setColor(theme.getTextColor());

		if (minMark == 0 && maxMark == 0) {
			if (dif > 0) {
				drawText(g, left(), top()+h/2+fontSize/2, text);
			} else {
				drawText(g, (int)(left()+dif), top()+h/2+fontSize/2, text);
			}
		} else {
			/** Desenha Mark **/

			// get metrics from the graphics

			int cx = metrics.stringWidth(text.substring(0,minMark));

			int cxm = metrics.stringWidth(text.substring(minMark,maxMark));

			//Draw selected text
			g.setColor(theme.getSelectionColor());

			//fill Mark Rect
			g.fillRect(x+cx+2,y+2,cxm, h-3);

			//Invert textColor

			//Por enquanto escreve normal
			//g.setColor(theme.getTextMarkColor());
			//g.setColor(Color.BLACK);
			g.setColor(theme.getTextColor());
			drawText(g, x, y+h/2+fontSize/2, text.substring(0,minMark));

			g.setColor(theme.getTextSelectedColor());
			drawSelectedText(g, x+cx, y+h/2+fontSize/2, text.substring(minMark,maxMark));

			g.setColor(theme.getTextColor());
			drawText(g, x+cx+cxm, y+h/2+fontSize/2, text.substring(maxMark,text.length()));
		}

		if (onFocus) {

			g.setColor(theme.getTextFieldColor());
			//Draw Cursor

			int cx = metrics.stringWidth(text.substring(0,cursor));
			cx+=x+1;

			int padding = 3;

			if (dif>0) {
				g.drawLine(cx+1, y+padding, cx+1, y+h-padding);
			} else {
				g.drawLine(dif+cx, y+padding, dif+cx, y+h-padding);
			}

		}

		g.resetClip();
		/*g.resetImage();
		layer.draw(g);
		layer.resetImage();*/
	}

	private void drawText(Graphics g, int x, int y, String text) {
		g.drawStringShadow(text, x, y, getTheme().getShadowColor());
	}

	private void drawSelectedText(Graphics g, int x, int y, String text) {
		g.drawStringShadow(text, x, y, getTheme().getBackgroundColor());
	}

	@Override
	protected void notifyTextChanged() {
		super.notifyTextChanged();
	}

	@Override
	public void updateEvent(GUIEvent event) {

		switch (event) {

		case GAIN_FOCUS:
			onFocus = true;
			break;

		case LOST_FOCUS:

			onFocus = false;
			//System.out.println("TextField, lost focus");

			break;

		case MOUSE_OVER_WITH_FOCUS:
		case MOUSE_OVER:

			mouseOver = true;

			break;

		case MOUSE_OUT:
			mouseOver = false;

			break;

		default:
			break;
		}
	}

	private void updateChar(char c) {

		if (TEXT_BACKSPACE == (int)c) {

			eraseAsBackSpace();

		} else if (TEXT_DELETE == (int)c) {

			eraseAsDelete();

		} else if (TEXT_ENTER == (int)c || 
				TEXT_TAB == (int)c || 
				TEXT_ESC == (int)c) {

		}
		else {
			if (maxLength>0) {
				if (text.length()<maxLength) {
					addChar(c);
				}
			} else {
				addChar(c);
			}
		}
	}

	public String getText() {
		//Remove Tabs
		text = text.replace("\n", "").replace("\r", "");
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getCursor() {
		return cursor;
	}

	public void setCursor(int cursor) {
		this.cursor = cursor;
	}

}