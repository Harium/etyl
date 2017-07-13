package com.harium.etyl.i18n;

import com.harium.etyl.commons.module.Module;
import com.harium.etyl.commons.context.Context;
import com.harium.etyl.commons.event.GUIEvent;
import com.harium.etyl.commons.event.KeyEvent;
import com.harium.etyl.commons.event.PointerEvent;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.ui.theme.Theme;
import com.harium.etyl.ui.theme.ThemeManager;

public class LanguageModule implements Module {

    private static LanguageModule instance;

    private Language language = Language.ENGLISH_US;

    private LanguageModule() {
        super();
    }

    public static LanguageModule getInstance() {
        if (instance == null) {
            instance = new LanguageModule();
        }

        return instance;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    @Override
    public void updateMouse(PointerEvent event) {

    }

    @Override
    public void updateKeyboard(KeyEvent event) {

    }

    @Override
    public void updateGuiEvent(GUIEvent event) {
        if (event == GUIEvent.LANGUAGE_CHANGED) {
            changeLanguage(language);
        }
    }

    public void changeLanguage(Language language) {
        LanguageModule.getInstance().setLanguage(language);

        if (language == Language.JAPANESE) {
            ThemeManager.getInstance().getTheme().setFontName(Theme.FONT_JAPANESE);
        } else {
            ThemeManager.getInstance().getTheme().setFontName(Theme.FONT_DEFAULT);
        }

        ThemeManager.getInstance().getTheme().reloadFonts();
    }

    @Override
    public void init(Context context) {

    }

    @Override
    public void dispose(Context context) {

    }

    @Override
    public void draw(Graphics g) {

    }

    @Override
    public void update(long now) {

    }
}
