package com.harium.etyl.ui.base;

import org.junit.Assert;
import org.junit.Test;

import com.harium.etyl.commons.event.KeyEvent;
import com.harium.etyl.commons.event.KeyState;
import com.harium.etyl.ui.textfield.TextFieldView;

public class BaseTextFieldTest {

	@Test
	public void testAddChars() {
		BaseTextField text = new BaseTextField(0,0,260,360);
		
		Assert.assertEquals("", text.getText());
		
		text.updateKeyboard(fakeTypeEvent(KeyEvent.VK_A, 'a'));
		Assert.assertEquals("a", text.getText());
		
		text.updateKeyboard(fakeTypeEvent(KeyEvent.VK_B, 'b'));
		Assert.assertEquals("ab", text.getText());
		
		text.updateKeyboard(fakeTypeEvent(KeyEvent.VK_BACK_SPACE, (char)TextFieldView.TEXT_BACKSPACE));
		Assert.assertEquals("a", text.getText());
	}
	
	@Test
	public void testMoveCursor() {
		BaseTextField text = new BaseTextField(0,0,260,360);
				
		text.updateKeyboard(fakeTypeEvent(KeyEvent.VK_A, 'a'));
		text.updateKeyboard(fakeTypeEvent(KeyEvent.VK_B, 'b'));
		Assert.assertEquals(2, text.getCursor());
		
		backspace(text);
		Assert.assertEquals(1, text.getCursor());
		
		text.updateKeyboard(fakeTypeEvent(KeyEvent.VK_B, 'b'));
		Assert.assertEquals(2, text.getCursor());
		text.updateKeyboard(new KeyEvent(KeyEvent.VK_LEFT, KeyState.PRESSED));
		Assert.assertEquals(1, text.getCursor());
		text.updateKeyboard(new KeyEvent(KeyEvent.VK_LEFT, KeyState.PRESSED));
		Assert.assertEquals(0, text.getCursor());
		
		text.updateKeyboard(fakeTypeEvent(KeyEvent.VK_B, 'b'));
		Assert.assertEquals("bab", text.getText());
	}
	
	@Test
	public void testMoveSelection() {
		BaseTextField text = new BaseTextField(0,0,260,360);
		text.setText("bababa");
		text.setCursor(6);
		
		text.updateKeyboard(new KeyEvent(KeyEvent.VK_SHIFT_LEFT, KeyState.PRESSED));
		Assert.assertEquals(true, text.isSelected());
		text.updateKeyboard(new KeyEvent(KeyEvent.VK_LEFT, KeyState.PRESSED));
		text.updateKeyboard(new KeyEvent(KeyEvent.VK_LEFT, KeyState.PRESSED));
		
		backspace(text);
		Assert.assertEquals("baba", text.getText());
		Assert.assertEquals(4, text.getCursor());		
	}
	
	@Test
	public void testInsertInMiddle() {
		BaseTextField text = new BaseTextField(0,0,260,360);
		text.setText("acd");
		text.setCursor(3);
		
		text.updateKeyboard(new KeyEvent(KeyEvent.VK_LEFT, KeyState.PRESSED));
		text.updateKeyboard(new KeyEvent(KeyEvent.VK_LEFT, KeyState.PRESSED));
		text.updateKeyboard(fakeTypeEvent(KeyEvent.VK_B, 'b'));
		
		Assert.assertEquals("abcd", text.getText());
		Assert.assertEquals(2, text.getCursor());
	}
	
	private void backspace(BaseTextField textfield) {
		textfield.updateKeyboard(fakeTypeEvent(KeyEvent.VK_BACK_SPACE, (char)TextFieldView.TEXT_BACKSPACE));
	}
	
	private KeyEvent fakeTypeEvent(int key, char c) {
		KeyEvent event = new KeyEvent(key, c, KeyState.TYPED);
		return event;
	}
	
}
