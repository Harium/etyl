package com.harium.etyl.animation.timer;

public class Timer {

	long lastUpdate = 0;
	
	public boolean schedule(long now, int interval) {
		if (lastUpdate + interval <= now) {
			lastUpdate = now;
			return true;
		}
		
		return false;
	}
	
	public static boolean schedule(long now, long lastUpdate, int interval) {
		if (lastUpdate + interval <= now) {
			return true;
		}
		
		return false;
	}
	
}
