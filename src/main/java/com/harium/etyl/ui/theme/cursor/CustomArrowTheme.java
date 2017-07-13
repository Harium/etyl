package com.harium.etyl.ui.theme.cursor;

import com.harium.etyl.ui.theme.cursor.arrow.ImageMouseArrow;

/**
 * 
 * @author yuripourre
 *
 */

public class CustomArrowTheme extends ArrowThemeImpl {

	public CustomArrowTheme(String normalArrowPath) {
		
		normalArrow = new ImageMouseArrow(normalArrowPath);
		clickArrow = normalArrow;
		linkArrow = normalArrow;
		helpArrow = normalArrow;
		textArrow = normalArrow;
		
		waitArrow = normalArrow;
		
		horizontalArrow = normalArrow;
		verticalArrow = normalArrow;
		diagonalArrow = normalArrow;
		invertedDiagonalArrow = normalArrow;
		
	}

	public void setNormalArrow(ImageMouseArrow normalArrow) {
		this.normalArrow = normalArrow;
	}

	public void setClickArrow(ImageMouseArrow clickArrow) {
		this.clickArrow = clickArrow;
	}
	
	public void setLinkArrow(ImageMouseArrow linkArrow) {
		this.linkArrow = linkArrow;
	}
	
	public void setHelpArrow(ImageMouseArrow helpArrow) {
		this.helpArrow = helpArrow;
	}

	public void setTextArrow(ImageMouseArrow textArrow) {
		this.textArrow = textArrow;
	}

	public void setWaitArrow(ImageMouseArrow waitArrow) {
		this.waitArrow = waitArrow;
	}

	public void setHorizontalArrow(ImageMouseArrow horizontalArrow) {
		this.horizontalArrow = horizontalArrow;
	}

	public void setVerticalArrow(ImageMouseArrow verticalArrow) {
		this.verticalArrow = verticalArrow;
	}

	public void setDiagonalArrow(ImageMouseArrow diagonalArrow) {
		this.diagonalArrow = diagonalArrow;
	}

	public void setInvertedDiagonalArrow(ImageMouseArrow invertedDiagonalArrow) {
		this.invertedDiagonalArrow = invertedDiagonalArrow;
	}
			
}
