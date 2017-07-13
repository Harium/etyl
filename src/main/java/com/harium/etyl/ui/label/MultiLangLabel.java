package com.harium.etyl.ui.label;

import java.util.Map;

import com.harium.etyl.commons.event.GUIEvent;
import com.harium.etyl.i18n.Language;
import com.harium.etyl.i18n.LanguageModule;

/**
 * 
 * @author yuripourre
 *
 */

public class MultiLangLabel extends TextLabel {
	
	private Map<Language,String> texts;
	
	public MultiLangLabel(Map<Language,String> texts) {
		this(0, 0, texts);
	}
	
	public MultiLangLabel(int x, int y, Map<Language,String> texts) {
		this(x,y,0,texts);
	}
	
	public MultiLangLabel(int x, int y, int w, Map<Language,String> texts) {
		super(x, y, w);
		
		this.texts = texts;

		Language lang = LanguageModule.getInstance().getLanguage();
		reloadText(lang);
	}

	@Override
	public void updateEvent(GUIEvent event){
				
		if(event==GUIEvent.LOST_FOCUS){
			
			onFocus = false;
			
		}else if(event==GUIEvent.GAIN_FOCUS){
			
			onFocus = true;
			
		}else if(event==GUIEvent.LANGUAGE_CHANGED){
			
			reloadText(LanguageModule.getInstance().getLanguage());
			
		}
		
	}
	
	public Map<Language, String> getTexts() {
		return texts;
	}

	public void setTexts(Map<Language, String> texts) {
		this.texts = texts;
	}
	
	private void reloadText(Language lang){
		if(texts.containsKey(lang)){
			setText(texts.get(lang));
		}else{
			setText(texts.get(Language.ENGLISH_US));
		}		
	}
		
}
