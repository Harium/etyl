package com.harium.etyl.core.loop;

import com.harium.etyl.core.Core;

/**
 * A Game loop with frame skip
 * Based on code at http://obviam.net/index.php/the-android-game-loop/
 */

public class FrameSkippingLoop extends GameLoopImpl {

    private static final float ONE_SECOND = 1000f;
    // Larger = More Smoothing
    private static final float SMOOTHING = 0.1f;
    // Desired fps
    private final static int MAX_FPS = 50;
    // Maximum number of frames to be skipped
    private final static int MAX_FRAME_SKIPS = 5;
    // The frame period
    private final static int FRAME_PERIOD = 1000 / MAX_FPS;
    // Frames per second
    private float fps = 0;

    public FrameSkippingLoop(Core core) {
        super(core);
    }

    @Override
    public boolean loop() throws Exception {

        long beginTime;        // the time when the cycle begun
        long deltaTime;        // the time it took for the cycle to execute
        int sleepTime;        // ms to sleep (<0 if we're behind)
        int framesSkipped;    // number of frames being skipped

        int frames = 0;
        long startTime = System.currentTimeMillis();

        while (core.isRunning()) {
            beginTime = System.currentTimeMillis();
            framesSkipped = 0;    // resetting the frames skipped

            core.update(1);
            core.render();
            // calculate how long did the cycle take
            deltaTime = System.currentTimeMillis() - beginTime;
            // calculate sleep time
            sleepTime = (int) (FRAME_PERIOD - deltaTime);

            if (sleepTime > 0) {
                // if sleepTime > 0 we're OK
                try {
                    // send the thread to sleep for a short period
                    // very useful for battery saving
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                }
            }

            while (sleepTime < 0 && framesSkipped < MAX_FRAME_SKIPS) {
                // we need to catch up
                // update without rendering
                core.update(1);
                // add frame period to check if in next frame
                sleepTime += FRAME_PERIOD;
                framesSkipped++;
            }

            // Calculate FPS
            long diff = beginTime - startTime;
            if (diff >= ONE_SECOND) {
                float secondsElapsed = diff / ONE_SECOND;
                float current = frames / secondsElapsed;
                fps = (fps * SMOOTHING) + (current * (1.0f - SMOOTHING));
                // fps = current;
                core.setFps(fps);
                startTime = System.currentTimeMillis();
                frames = 0;
            } else {
                frames++;
            }
        }
        return true;
    }

}
