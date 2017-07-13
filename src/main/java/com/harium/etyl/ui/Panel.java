package com.harium.etyl.ui;

import com.harium.etyl.ui.base.BasePanel;
import com.harium.etyl.ui.base.UIViewGroup;
import com.harium.etyl.ui.theme.ThemeManager;

/**
 * @author yuripourre
 */

public class Panel extends UIViewGroup {

    private BasePanel panel;

    public Panel(int x, int y, int w, int h) {
        super(x, y, w, h);
        this.panel = ThemeManager.getInstance().getTheme().createPanel(x, y, w, h);
        delegateView(panel);
    }

    public void rebuild() {
        BasePanel view = ThemeManager.getInstance().getTheme().createPanel(x, y, w, h);
        view.copy(delegatedView);

        delegateView(view);
    }

    public Panel() {
        this(0, 0, 0, 0);
    }

}
