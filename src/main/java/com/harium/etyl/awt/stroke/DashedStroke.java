package com.harium.etyl.awt.stroke;

import java.awt.BasicStroke;

public class DashedStroke extends BasicStroke {
	
	private final static float dash[] = { 10.0f };
	
	public DashedStroke() {
		super(1.0f, BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f);
	}
	
}
