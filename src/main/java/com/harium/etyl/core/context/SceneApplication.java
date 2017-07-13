package com.harium.etyl.core.context;

import com.harium.etyl.commons.context.Application;

public abstract class SceneApplication extends Application {

    /**
     * Scene Graph
     */
    protected Scene scene;

    public SceneApplication(int w, int h) {
        super(w, h);
        scene = new Scene();
        components.add(scene);
    }

    public SceneApplication(int x, int y, int w, int h) {
        super(x, y, w, h);
        scene = new Scene();
        components.add(scene);
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }
}
