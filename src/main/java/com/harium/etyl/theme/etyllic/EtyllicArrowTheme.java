package com.harium.etyl.theme.etyllic;

import com.harium.etyl.ui.theme.cursor.ArrowThemeImpl;
import com.harium.etyl.ui.theme.cursor.arrow.MouseArrow;
import com.harium.etyl.theme.etyllic.arrow.DiagonalArrow;
import com.harium.etyl.theme.etyllic.arrow.HorizontalArrow;
import com.harium.etyl.theme.etyllic.arrow.InvertedDiagonalArrow;
import com.harium.etyl.theme.etyllic.arrow.MoveArrow;
import com.harium.etyl.theme.etyllic.arrow.NormalArrow;
import com.harium.etyl.theme.etyllic.arrow.TextArrow;
import com.harium.etyl.theme.etyllic.arrow.VerticalArrow;
import com.harium.etyl.theme.etyllic.arrow.WaitingArrow;


public class EtyllicArrowTheme extends ArrowThemeImpl {

	protected int arrowSize = 22;
	
	public EtyllicArrowTheme() {
		
		normalArrow = new NormalArrow(arrowSize);
		clickArrow = new NormalArrow(arrowSize);
		linkArrow = new NormalArrow(arrowSize);
		helpArrow = new NormalArrow(arrowSize);
		textArrow = new TextArrow(arrowSize);
		
		waitArrow = new WaitingArrow(arrowSize);
		
		moveArrow = new MoveArrow(arrowSize);
		
		horizontalArrow = new HorizontalArrow(arrowSize);
		verticalArrow = new VerticalArrow(arrowSize);		
		diagonalArrow = new DiagonalArrow(arrowSize);
		invertedDiagonalArrow = new InvertedDiagonalArrow(arrowSize);
		
	}

	public int getArrowSize() {
		return arrowSize;
	}

	public void setArrowSize(int arrowSize) {
		this.arrowSize = arrowSize;
	}

	public void setNormalArrow(MouseArrow normalArrow) {
		this.normalArrow = normalArrow;
	}

	public void setClickArrow(MouseArrow clickArrow) {
		this.clickArrow = clickArrow;
	}

	public void setTextArrow(MouseArrow textArrow) {
		this.textArrow = textArrow;
	}

	public void setWaitArrow(MouseArrow waitArrow) {
		this.waitArrow = waitArrow;
	}
	
	
}
