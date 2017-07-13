package com.harium.etyl.i18n;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author yuripourre
 *
 */

public class Dictionary {
	
	protected Language defaultLanguage = Language.ENGLISH_US;
	protected Map<Language, Map<String, String>> vocabularies;

	public Dictionary() {
		vocabularies = new HashMap<Language, Map<String, String>>();
	}

	public Map<String, String> getDictionary(Language language) {
		return vocabularies.get(language);
	}

	public void addLanguage(Language language) {
		addLanguage(language, new HashMap<String, String>());
	}
	
	public void addLanguage(Language language, Map<String, String> vocabulary) {
		vocabularies.put(language, vocabulary);
	}
	
	public void setDefaultLanguage(Language language) {
		this.defaultLanguage = language;
	}

	public Map<String, String> getVocabulary(Language language) {
		return vocabularies.get(language);
	}

	public void clear() {
		vocabularies.clear();
	}

	public String getText(Language language, String key) {
		Map<String, String> words;
		
		if (vocabularies.containsKey(language)) {
			words = vocabularies.get(language);
		} else {
			words = vocabularies.get(defaultLanguage);
		}
		
		return words.get(key);
	}

	public Language getDefaultLanguage() {
		return defaultLanguage;
	}
	
}
