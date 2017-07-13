package com.harium.etyl.input;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.harium.etyl.commons.event.KeyEvent;
import com.harium.etyl.commons.event.KeyState;

/**
 * Joystick abstraction (works only on Linux)
 *
 */
public class Joystick {

	private int id;

	private int lastXEvent = KeyEvent.VK_JOYSTICK_CENTER_X;
	private int lastYEvent = KeyEvent.VK_JOYSTICK_CENTER_Y;
	
	private List<KeyEvent> list;
	
	private FileInputStream inputStream;
	
	private static final int JS_EVENT_BUTTON = 0x01;
	private static final int JS_EVENT_AXIS = 0x02;
	private static final int JS_EVENT_INIT = 0x80;

	public static final int MAX_AXIS_MOVEMENT = 32767;
	public static final int MIN_AXIS_MOVEMENT = -32767;
	
	private static final String JOYSTICK_DIRECTORY = "/dev/input/js";

	public Joystick(int id) throws FileNotFoundException{
		super();

		this.id = id;

		inputStream = new FileInputStream(JOYSTICK_DIRECTORY+id);
		
		list = new ArrayList<KeyEvent>();
	}

	public FileInputStream getInputStream() {
		return inputStream;
	}

	public List<KeyEvent> listen() {
		
		if(!list.isEmpty()) {
			list.clear();
		}
		
		byte[] buf = new byte[8];

		int n = 0;

		try {
			n = inputStream.read(buf, 0, 8);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		readFromBuffer(buf, n);
				
		return list;
	}

	private void readFromBuffer(byte[] buf, int n) {
		if (n == 8) {
			long time = buf[3] << 24 | (buf[2] & 0xff) << 16 | (buf[1] & 0xff) << 8 | (buf[0] & 0xff);
			int value = buf[5] << 8 | (buf[4] & 0xff);
			int type = buf[6] & 0xff;
			int channel = buf[7] & 0xff;
			
			if (type == JS_EVENT_AXIS) {

				switch (channel) {

				case 0:
				case 2:

					axisXEvent(time, value);
					break;

				case 1:
				case 3:

					axisYEvent(time, value);
					break;

				default:
					break;
				}

			} else if (type == JS_EVENT_BUTTON) {
				buttonEvent(time, value, channel);
			}			
			
		} else {
			System.err.println("only " + n + " of 8 bytes read");
		}
	}

	private void buttonEvent(long time, int value, int channel) {
		int buttonCode = (KeyEvent.VK_JOYSTICK_BUTTON_1)+channel;

		if (value == 1) {
			list.add(new KeyEvent(id, buttonCode, 0,  KeyState.PRESSED, time));
		} else {
			list.add(new KeyEvent(id, buttonCode, 0, KeyState.RELEASED, time));
		}
	}

	private void axisYEvent(long time, int value) {
		if (value > 0) {
			lastYEvent = KeyEvent.VK_JOYSTICK_DOWN;
			list.add(new KeyEvent(id, lastYEvent, value, KeyState.PRESSED, time));
		} else if(value < 0) {
			lastYEvent = KeyEvent.VK_JOYSTICK_UP;
			list.add(new KeyEvent(id, lastYEvent, value, KeyState.PRESSED, time));
		} else {
			list.add(new KeyEvent(id, lastYEvent, value, KeyState.RELEASED, time));
			list.add(new KeyEvent(id, KeyEvent.VK_JOYSTICK_CENTER_Y, value,  KeyState.RELEASED, time));
		}
	}

	private void axisXEvent(long time, int value) {
		if (value > 0) {
			lastXEvent = KeyEvent.VK_JOYSTICK_RIGHT;
			list.add(new KeyEvent(id, lastXEvent, value, KeyState.PRESSED, time));
		} else if(value < 0) {
			lastXEvent = KeyEvent.VK_JOYSTICK_LEFT;
			list.add(new KeyEvent(id, lastXEvent, value, KeyState.PRESSED, time));
		} else {
			list.add(new KeyEvent(id, lastXEvent, value, KeyState.RELEASED, time));
			list.add(new KeyEvent(id, KeyEvent.VK_JOYSTICK_CENTER_X, value,  KeyState.RELEASED, time));
		}
	}

}
