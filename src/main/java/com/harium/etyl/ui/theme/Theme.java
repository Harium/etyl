package com.harium.etyl.ui.theme;

import com.harium.etyl.commons.graphics.Color;
import com.harium.etyl.i18n.Language;
import com.harium.etyl.i18n.LanguageChangeListener;
import com.harium.etyl.ui.style.Style;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yuripourre
 */

public abstract class Theme implements ThemeFactory, LanguageChangeListener {

    public Theme() {
        super();
        String defaultFont = "dialog";

        fonts = new HashMap<>();
        // This font is included in every JVM
        fonts.put(Language.ENGLISH_US, defaultFont);
        this.fontName = defaultFont;

        reloadFonts();
    }

    private Font buttonFont;
    private Font font;

    private int fontSize = 14;
    private int fontStyle = Font.PLAIN;
    private String fontName;

    private Style style = new Style();

    private Map<Language, String> fonts;

    public void reloadFonts() {
        buttonFont = new Font(fontName, fontStyle, fontSize);
        font = new Font(fontName, fontStyle, fontSize);

        //font = new Font("Dialog", fontStyle, fontSize);
        //font = new Font("DialogInput", fontStyle, fontSize);
        //font = new Font("Serif", fontStyle, fontSize);
        //font = new Font("SansSerif", fontStyle, fontSize);
        //font = new Font("Monospaced", fontStyle, fontSize);
    }

    protected Color baseColor = new Color(0x22, 0xa9, 0x11, 0xa0);
    protected Color borderColor = new Color(0x00, 0x00, 0x00, 0xa0);
    protected Color selectionColor = new Color(0x22, 0xca, 0x33, 0xa0);
    protected Color backgroundColor = new Color(0xff, 0xff, 0xff, 0xa0);
    protected Color activeColor = new Color(0x22, 0x87, 0x11, 0xa0);

    protected boolean shadow = true;
    protected Color shadowColor = new Color(0x33, 0x33, 0x33, 0xa0);

    protected Color barColor = new Color(0x00, 0x00, 0x00, 0xa0);
    protected Color barOnMouseColor = new Color(0x33, 0x33, 0x33, 0xa0);

    //protected Color buttonColor = new Color(0x00,0x00, 0x00, 0xa0);
    protected Color buttonDisabledColor = new Color(0x55, 0x55, 0x55, 0xb0);
    protected Color buttonOnFocus = new Color(0x33, 0x33, 0x99, 0xa0);
    //protected Color buttonOnMouse = new Color(0x33, 0x33, 0x33, 0xa0);
    //protected Color buttonOnClick = new Color(0x99, 0x99, 0x99, 0xa0);

    protected Color textColor = new Color(0xff, 0xff, 0xff);
    protected Color textSelectedColor = new Color(0xff, 0xff, 0xff);
    protected Color textMarkColor = selectionColor;

    protected Color textFieldColor = new Color(0x88, 0x88, 0x88);
    protected Color textFieldWithoutFocusColor = new Color(0xaa, 0xaa, 0xaa, 0xcc);
    protected Color textFieldOnMouseColor = selectionColor;

    protected Color panelColor = new Color(0x00, 0x00, 0x00, 0xA0);

    protected Color mouseArrowColor = new Color(0xff, 0xff, 0xff);
    protected Color mouseArrowBorderColor = new Color(0x00, 0x00, 0x00);

    protected Color windowBackgroundColor = new Color(0x55, 0x55, 0x55, 0xdd);

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public int getFontStyle() {
        return fontStyle;
    }

    public void setFontStyle(int fontStyle) {
        this.fontStyle = fontStyle;
    }

    public String getFontName() {
        return fontName;
    }

    public void setFontName(String fontName) {
        this.fontName = fontName;
    }

    public boolean isShadow() {
        return shadow;
    }

    public void setShadow(boolean shadow) {
        this.shadow = shadow;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackGroundColor(Color backGroundColor) {
        this.backgroundColor = backGroundColor;
    }

    public Color getBarColor() {
        return barColor;
    }

    public void setBarColor(Color barColor) {
        this.barColor = barColor;
    }

    public Color getBarOnMouseColor() {
        return barOnMouseColor;
    }

    public void setBarOnMouseColor(Color barOnMouseColor) {
        this.barOnMouseColor = barOnMouseColor;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public Color getButtonDisabledColor() {
        return buttonDisabledColor;
    }

    public void setButtonDisabledColor(Color buttonDisabledColor) {
        this.buttonDisabledColor = buttonDisabledColor;
    }

    public Color getButtonOnFocus() {
        return buttonOnFocus;
    }

    public void setButtonOnFocus(Color buttonOnFocus) {
        this.buttonOnFocus = buttonOnFocus;
    }

    public Color getTextColor() {
        return textColor;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }

    public Color getTextSelectedColor() {
        return textSelectedColor;
    }

    public void setTextSelectedColor(Color textSelectedColor) {
        this.textSelectedColor = textSelectedColor;
    }

    public Color getTextFieldColor() {
        return textFieldColor;
    }

    public void setTextFieldColor(Color textFieldColor) {
        this.textFieldColor = textFieldColor;
    }

    public Color getTextFieldWithoutFocusColor() {
        return textFieldWithoutFocusColor;
    }

    public void setTextFieldWithoutFocusColor(Color textFieldWithoutFocusColor) {
        this.textFieldWithoutFocusColor = textFieldWithoutFocusColor;
    }

    public Color getSelectionColor() {
        return selectionColor;
    }

    public Color getActiveColor() {
        return activeColor;
    }

    public Color getPanelColor() {
        return panelColor;
    }

    public void setPanelColor(Color panelColor) {
        this.panelColor = panelColor;
    }

    public Color getMouseArrowColor() {
        return mouseArrowColor;
    }

    public void setMouseArrowColor(Color mouseArrowColor) {
        this.mouseArrowColor = mouseArrowColor;
    }

    public Color getMouseArrowBorderColor() {
        return mouseArrowBorderColor;
    }

    public void setMouseArrowBorderColor(Color mouseArrowBorderColor) {
        this.mouseArrowBorderColor = mouseArrowBorderColor;
    }

    public Color getTextMarkColor() {
        return textMarkColor;
    }

    public void setTextMarkColor(Color textMarkColor) {
        this.textMarkColor = textMarkColor;
    }

    public Color getShadowColor() {
        return shadowColor;
    }

    public void setShadowColor(Color shadowColor) {
        this.shadowColor = shadowColor;
    }

    public Color getWindowBackgroundColor() {
        return windowBackgroundColor;
    }

    public void setWindowBackgroundColor(Color windowBackgroundColor) {
        this.windowBackgroundColor = windowBackgroundColor;
    }

    public Color getTextFieldOnMouseColor() {
        return textFieldOnMouseColor;
    }

    public void setTextFieldOnMouseColor(Color textFieldOnMouseColor) {
        this.textFieldOnMouseColor = textFieldOnMouseColor;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public Font getButtonFont() {
        return buttonFont;
    }

    public void setButtonFont(Font buttonFont) {
        this.buttonFont = buttonFont;
    }

    public Color getBaseColor() {
        return baseColor;
    }

    public void setBaseColor(Color baseColor) {
        this.baseColor = baseColor;
        this.selectionColor = baseColor.brighter(16);
        this.activeColor = baseColor.darker(16);
    }

    public Style getStyle() {
        return style;
    }

    private String getFontName(Language language) {
        if (fonts.containsKey(language)) {
            return fonts.get(language);
        } else {
            return fonts.get(Language.ENGLISH_US);
        }
    }

    @Override
    public void changeLanguage(Language language) {
        String fontName = getFontName(language);
        this.fontName = fontName;

        reloadFonts();
    }
}