package com.harium.etyl.awt.components.chooser;

import java.awt.Component;

import javax.swing.JFileChooser;

public class FileChooserImagePreview extends FileChooser implements Runnable {
	
	public FileChooserImagePreview(Component component) {
		super(component);
		init();
	}
	
	public FileChooserImagePreview(Component component, String path) {
		super(component, path);
		init();
	}
	
	@Override
	public void openDialog() {
		if(opened) {
			return;
		}
		
		chooser = new JFileChooser(path);
		
		PreviewPane previewPane = new PreviewPane();
		chooser.setAccessory(previewPane);
		chooser.addPropertyChangeListener(previewPane);
		chooser.setVisible(true);
		opened = true;
		
		new Thread(this).start();
	}

}
