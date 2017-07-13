package com.harium.etyl.core.loop;

import com.harium.etyl.core.Core;

/**
 * A Game loop with frame skip
 * Based on code at http://obviam.net/index.php/the-android-game-loop/
 */

public class FrameSkippingLoop extends GameLoopImpl {

	// desired fps
	private final static int MAX_FPS = 50;
	// maximum number of frames to be skipped
	private final static int MAX_FRAME_SKIPS = 5;
	// the frame period
	private final static int FRAME_PERIOD = 1000 / MAX_FPS;

	public FrameSkippingLoop(Core core) {
		super(core);
	}

	@Override
	public boolean loop() throws Exception {

		long beginTime;		// the time when the cycle begun
		long timeDiff;		// the time it took for the cycle to execute
		int sleepTime;		// ms to sleep (<0 if we're behind)
		int framesSkipped;	// number of frames being skipped 

		sleepTime = 0;
		
		core.setFps(MAX_FPS);

		while (core.isRunning()) {

			beginTime = System.currentTimeMillis();
			framesSkipped = 0;	// resetting the frames skipped
 
			core.update(1);
				
			core.render();
			// calculate how long did the cycle take
			timeDiff = System.currentTimeMillis() - beginTime;
			// calculate sleep time
			sleepTime = (int)(FRAME_PERIOD - timeDiff);

			if (sleepTime > 0) {
				// if sleepTime > 0 we're OK
				try {
					// send the thread to sleep for a short period
					// very useful for battery saving
					Thread.sleep(sleepTime);	
				} catch (InterruptedException e) {}
			}

			while (sleepTime < 0 && framesSkipped < MAX_FRAME_SKIPS) {
				// we need to catch up
				// update without rendering
				core.update(1);
				// add frame period to check if in next frame
				sleepTime += FRAME_PERIOD;	
				framesSkipped++;
			}
		}
		return true;
	}

}
