package com.harium.etyl.i18n;

import java.util.HashMap;
import java.util.Map;

public class DefaultDictionary extends Dictionary {
	
	public static final String MESSAGE_FULLSCREEN = "fullscreen";
	
	public DefaultDictionary() {
		super();
		defaultLanguage = Language.ENGLISH_US;
		populateVocabulary();
	}
	
	private void populateVocabulary() {
		Map<String,String> usVocabulary = new HashMap<String,String>();
		usVocabulary.put(MESSAGE_FULLSCREEN, "Press ESC to exit Fullscreen.");
		
		Map<String,String> brVocabulary = new HashMap<String,String>();
		brVocabulary.put(MESSAGE_FULLSCREEN, "Pressione ESC para sair da Tela Cheia.");
		
		vocabularies.put(Language.ENGLISH_US, usVocabulary);
		vocabularies.put(Language.PORTUGUESE_BRAZIL, brVocabulary);
	}

}
