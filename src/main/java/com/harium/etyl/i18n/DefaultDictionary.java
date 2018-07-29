package com.harium.etyl.i18n;

import java.util.HashMap;
import java.util.Map;

public class DefaultDictionary {

    private static Dictionary dictionary;

    public static final String MESSAGE_FULLSCREEN = "fullscreen";

    public static Dictionary getInstance() {
        if (dictionary == null) {
            dictionary = new Dictionary();
            dictionary.setDefaultLanguage(Language.ENGLISH_US);

            populateVocabulary(dictionary);
        }
        return dictionary;
    }

    private static void populateVocabulary(Dictionary dictionary) {
        Map<String, String> usVocabulary = new HashMap<String, String>();
        usVocabulary.put(MESSAGE_FULLSCREEN, "Press ESC to exit fullscreen.");

        Map<String, String> brVocabulary = new HashMap<String, String>();
        brVocabulary.put(MESSAGE_FULLSCREEN, "Pressione ESC para sair da tela cheia.");

        dictionary.sentences.put(Language.ENGLISH_US, usVocabulary);
        dictionary.sentences.put(Language.PORTUGUESE_BRAZIL, brVocabulary);
    }

}
