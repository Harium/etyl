package com.harium.etyl;

import com.harium.etyl.awt.core.AWTCore;
import com.harium.etyl.awt.engine.AWTEngine;
import com.harium.etyl.commons.context.Application;
import com.harium.etyl.commons.event.GUIEvent;
import com.harium.etyl.commons.module.Module;
import com.harium.etyl.core.Engine;
import com.harium.etyl.core.EtylFrame;
import com.harium.etyl.core.animation.Animation;
import com.harium.etyl.i18n.LanguageModule;
import com.harium.etyl.loader.Loader;
import com.harium.etyl.loader.image.ImageLoader;
import com.harium.etyl.ui.UI;
import com.harium.etyl.util.PathHelper;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public abstract class Etyl extends JFrame implements EtylFrame {

    private static final long serialVersionUID = 4588303747276461888L;

    private AWTCore core;
    private Engine engine;

    protected int w = 640;
    protected int h = 480;
    protected String icon = "";

    private Application application;

    public Etyl(int width, int height) {
        super();

        this.w = width;
        this.h = height;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void init(String path) {
        initCore();
        addModules();
        setPath(path);

        this.application = startApplication();

        startCore();
        updateIcon();
    }

    @Override
    public void init() {
        initCore();
        addModules();
        initialSetup("");

        this.application = startApplication();

        startCore();
        updateIcon();
    }

    private void initCore() {
        engine = new AWTEngine(this, w, h);

        core = engine.getCore();
        core.setEngine(this);
    }

    private void startCore() {
        core.startCore(application);
        core.startEngine();

        addComponentListener(core);
    }

    protected void addModule(Module module) {
        core.addModule(module);
    }

    private void addModules() {
        addModule(Animation.getInstance());
        addModule(UI.getInstance());
        addModule(LanguageModule.getInstance());
    }

    protected void initialSetup(String suffix) {
        String defaultPath = PathHelper.currentFileDirectory().toString();
        setPath(defaultPath + suffix);
    }

    protected void setPath(URL url) {
        setPath(url.toString());
    }

    protected void setPath(String path) {
        core.setPath(path);
        engine.init();
    }

    protected String getPath() {
        return core.getPath();
    }

    @Override
    public void paint(Graphics g) {
        core.paint(g);
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    public void draw() {
        repaint();
    }

    public void addLoader(Loader loader) {
        engine.addLoader(loader);
    }

    protected void hideCursor() {
        engine.hideCursor();
    }

    @Override
    public void updateSuperEvent(GUIEvent event) {
        engine.updateSuperEvent(event);
    }

    protected void setIcon(String icon) {
        this.icon = icon;
    }

    private void updateIcon() {
        if (!icon.isEmpty()) {
            setIconImage(ImageLoader.getInstance().getImage(icon));
        }
    }

}