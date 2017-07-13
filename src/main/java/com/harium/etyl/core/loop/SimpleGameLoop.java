package com.harium.etyl.core.loop;

import com.harium.etyl.core.Core;

public class SimpleGameLoop extends GameLoopImpl {

	private static final int FPS = 60;
	private static final int UPDATE_DELAY = 1000/FPS;
	
	public SimpleGameLoop(Core core) {
		super(core);
	}

	@Override
	public boolean loop() throws Exception {
		
		long previous = System.currentTimeMillis();
		
		long countTime = previous;
		
		int fps = 0;
		
		double lag = 0.0;
		
		while (core.isRunning()) {
			
		  long now = System.currentTimeMillis();
		  long elapsed = now - previous;
		  long delta = (long) (elapsed/UPDATE_DELAY);
		  
		  previous = now;
		  
		  lag += elapsed;

		  while (lag >= UPDATE_DELAY) {
			core.update(delta);
		    lag -= UPDATE_DELAY;
		  }

		  core.render();
		  fps++;
		  
		  if(now - countTime >= 1000) {
				countTime += 1000;

				core.setFps(fps);

				fps = 0;
			}
		  
		}
		return true;		
	}
	
}
