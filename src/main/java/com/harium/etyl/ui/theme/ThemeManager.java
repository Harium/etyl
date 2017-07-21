package com.harium.etyl.ui.theme;

import com.harium.etyl.ui.theme.listener.ArrowThemeListener;
import com.harium.etyl.ui.theme.listener.ThemeListener;
import com.harium.etyl.theme.etyllic.EtylArrowTheme;
import com.harium.etyl.theme.etyllic.EtyllicTheme;


public class ThemeManager {

	private Theme theme;

	private ArrowTheme arrowTheme;
	
	private ThemeListener themeListener = null;
	
	private ArrowThemeListener arrowThemeListener = null;
	
	private static ThemeManager instance = null;
	
	public ThemeManager() {
		super();
		
		arrowTheme = new EtylArrowTheme();
		theme = new EtyllicTheme();
	}
	
	public static ThemeManager getInstance() {
		if(instance==null){
			instance = new ThemeManager();
		}

		return instance;
	}
		
	public Theme getTheme() {
		return theme;
	}

	public void setTheme(Theme theme) {
		this.theme = theme;
		
		notifyThemeListener();
	}
	
	public ArrowTheme getArrowTheme() {
		return arrowTheme;
	}

	/**
	 * 
	 * @param arrowTheme
	 */
	public void setArrowTheme(ArrowTheme arrowTheme) {
		this.arrowTheme = arrowTheme;
		
		notifyArrowListener();
	}
	
	private void notifyArrowListener() {
		
		if(arrowThemeListener == null) return;
			
		arrowThemeListener.updateArrowTheme(arrowTheme);
		
	}

	public void setThemeListener(ThemeListener themeListener) {
		this.themeListener = themeListener;		
	}
		
	public ArrowThemeListener getArrowThemeListener() {
		return arrowThemeListener;
	}

	private void notifyThemeListener() {
		
		if(themeListener == null) return;
			
		themeListener.updateTheme(theme);
		
	}
	
	public ThemeListener getThemeListener() {
		return themeListener;
	}

	public void setArrowThemeListener(ArrowThemeListener arrowThemeListener) {
		this.arrowThemeListener = arrowThemeListener;
	}

}