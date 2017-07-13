package com.harium.etyl.awt;

import com.harium.etyl.awt.core.AWTCore;
import com.harium.etyl.commons.context.Application;
import com.harium.etyl.core.graphics.Graphics;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

public class AWTCoreTest {

    private AWTCore innerCore;
    private Application fakeApplication;

    @Before
    public void setUp() {
        AWTWindow window = new AWTWindow(0, 0, 800, 600);

        fakeApplication = createFakeApplication();
        window.setApplication(fakeApplication);

        Frame component = new Frame();
        innerCore = new AWTCore(component, 800, 600);
        innerCore.replaceWindow(window);

        // Wait to load application
        while (!fakeApplication.isLoaded()) {
            innerCore.updateApplication(fakeApplication, System.currentTimeMillis());
        }
    }

    public Application createFakeApplication() {
        Application app = new Application(100, 100) {
            @Override
            public void load() {
                loading = 100;
            }

            @Override
            public void draw(Graphics g) {
            }
        };

        return app;
    }

    @Test
    public void testUpdateUnLockedContext() {
        Assert.assertEquals(fakeApplication, innerCore.getWindow().getContext());
    }

}
