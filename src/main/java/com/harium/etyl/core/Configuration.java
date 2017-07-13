package com.harium.etyl.core;

import com.harium.etyl.commons.module.Module;

import java.util.Map;

/**
 * 
 * @author yuripourre
 *
 */

public class Configuration {

	private static Configuration instance = null;

	private boolean timerClick = false;
	private boolean numpadMouse = false;

	Map<Module, Map<String, Object>> configurations;

	private Configuration(){
		super();
	}

	public static Configuration getInstance() {
		if(instance==null){
			instance = new Configuration();
		}

		return instance;
	}

	public boolean isTimerClick() {
		return timerClick;
	}

	/**
	 * 
	 * @param timerClick
	 */
	public void setTimerClick(boolean timerClick) {
		this.timerClick = timerClick;
	}

	public boolean isNumpadMouse() {
		return numpadMouse;
	}

	/**
	 * 
	 * @param numpadMouse
	 */
	public void setNumpadMouse(boolean numpadMouse) {
		this.numpadMouse = numpadMouse;
	}

}
