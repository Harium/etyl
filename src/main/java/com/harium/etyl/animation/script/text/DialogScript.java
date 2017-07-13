package com.harium.etyl.animation.script.text;

import com.harium.etyl.core.animation.script.AnimationScript;
import com.harium.etyl.layer.TextLayer;

public class DialogScript extends AnimationScript {

	private String fullText;
	
	private TextLayer target;
	
	public DialogScript(TextLayer target, long time) {
		super(time);
		
		this.target = target;
		
		fullText = target.getText();
	}
	
	@Override
	public void calculate(double factor) {
		
		int lastChar = (int)(fullText.length()*factor);
		
		target.setText(fullText.substring(0, lastChar));
	}
	
}
