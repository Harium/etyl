package com.harium.etyl.ui.theme.cursor;

import com.harium.etyl.ui.theme.ArrowTheme;
import com.harium.etyl.ui.theme.cursor.arrow.MouseArrow;


public class ArrowThemeImpl implements ArrowTheme {

	protected MouseArrow normalArrow;
	protected MouseArrow clickArrow;
	protected MouseArrow linkArrow;
	protected MouseArrow helpArrow;
	protected MouseArrow textArrow;
	protected MouseArrow waitArrow;
	protected MouseArrow moveArrow;
	protected MouseArrow horizontalArrow;
	protected MouseArrow verticalArrow;
	protected MouseArrow diagonalArrow;
	protected MouseArrow invertedDiagonalArrow;
	
	public ArrowThemeImpl() {
		super();
	}
	
	public MouseArrow getNormalArrow() {
		return normalArrow;
	}

	public MouseArrow getClickArrow() {
		return clickArrow;
	}
	
	public MouseArrow getLinkArrow() {
		return linkArrow;
	}

	public MouseArrow getHelpArrow() {
		return helpArrow;
	}

	public MouseArrow getTextArrow() {
		return textArrow;
	}

	public MouseArrow getWaitArrow() {
		return waitArrow;
	}
	
	public MouseArrow getMoveArrow() {
		return moveArrow;
	}
	
	public MouseArrow getHorizontalArrow() {
		return horizontalArrow;
	}

	public MouseArrow getVerticalArrow() {
		return verticalArrow;
	}

	public MouseArrow getDiagonalArrow() {
		return diagonalArrow;
	}

	public MouseArrow getInvertedDiagonalArrow() {
		return invertedDiagonalArrow;
	}
	
}
