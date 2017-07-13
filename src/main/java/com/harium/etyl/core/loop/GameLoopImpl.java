package com.harium.etyl.core.loop;

import com.harium.etyl.core.Core;

public abstract class GameLoopImpl implements GameLoop {

	protected Core core;
	
	public GameLoopImpl(Core core) {
		super();
		
		this.core = core;		
	}
	
}
