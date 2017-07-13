package com.harium.etyl.loader;

import java.awt.GraphicsEnvironment;

public class SystemFontLoader extends LoaderImpl {

	private static SystemFontLoader instance = null;
	
	public static SystemFontLoader getInstance() {
		if (instance == null) {
			instance = new SystemFontLoader();
		}

		return instance;
	}
	
	@Override
	public void initLoader() {

		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		
		String systemFonts[] = ge.getAvailableFontFamilyNames();
		
		FontLoader.getInstance().setSystemFonts(systemFonts);
		
	}

}
