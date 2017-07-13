package com.harium.etyl.ui.textfield;

import com.harium.etyl.ui.View;

public abstract class TextFieldView extends View {

	public static final int TEXT_BACKSPACE = 8;
	public static final int TEXT_TAB = 9;
	public static final int TEXT_ENTER = 10;
	public static final int TEXT_ESC = 27;
	public static final int TEXT_DELETE = 127;
	
	protected String text = "";

	protected int cursor = 0;
	protected int fixMark = -1;

	protected int minMark = 0;
	protected int maxMark = 0;

	protected int maxLength = 0;
	protected boolean shift = false;
	protected boolean control = false;

	private OnTextChangedListener onTextChangeListener;
	
	public TextFieldView(int x, int y, int w, int h) {
		super(x,y,w,h);

		clearField();
	}
	
	public void clearField() {
		cursor = 0;
		fixMark = -1;

		minMark = 0;
		maxMark = 0;
		
		text = "";
		
		notifyTextChanged();
	}
	
	//Text Methods
	protected void leftNormal() {

		if (cursor>0) {
			cursor--;
		}

	}

	protected void leftWithControl() {
		cursor = findPreviousBreak(cursor);
	}
	
	private int findPreviousBreak(int cursor) {
		int index = cursor;
		
		if (index > 0) {			
			int i = cursor - 2;

			for(; i > 0; i--) {
				if (text.charAt(i)==' ') {
					i++;
					break;
				}
			}

			index = i;
		}
		return index;
	}

	protected void rightWithControl() {
		cursor = findNextBreak(cursor);
	}
	
	private int findNextBreak(int cursor) {
		int index = cursor+1;

		for(int i = index; i < text.length(); i++) {
			if (text.charAt(i)==' ') {
				index = i;
				break;
			}
		}
		return index;
	}

	protected void rightNormal() {
		if (cursor<text.length()) {
			cursor++;
		}
	}

	protected void eraseAsBackSpace() {

		//if (fixMark==-1&&cursor>0) {
		if (!isSelected()) {
			if (cursor > 0 && cursor < text.length()) {
				String t1 = text.substring(0,cursor-1);
				String t2 = text.substring(cursor,text.length());

				text = t1+t2;
			} else if (cursor > 0) {
				text = text.substring(0,cursor-1);
			}

			leftNormal();

		} else {
			deleteMark();
		}
		
		notifyTextChanged();

	}

	protected void eraseAsDelete() {

		if (fixMark==-1) {

			if (cursor<text.length()) {
				
				String t1 = text.substring(0,cursor);
				String t2 = text.substring(cursor+1,text.length());

				text = t1+t2;
			}

		} else {

			deleteMark();
		}
		
		notifyTextChanged();
	}

	private void deleteMark() {
		
		//System.out.println("deleteMark "+text.length());
		
		String t1 = text.substring(0,minMark);
		String t2 = text.substring(maxMark,text.length());
		
		text = t1 + t2;

		cursor = minMark;
		maxMark = cursor;
		//fixMark = -1;
	}

	protected void addChar(char c) {

		if (cursor<text.length()) {
			
			String t1 = text.substring(0,cursor);
			t1+=c;
			String t2 = text.substring(cursor,text.length());

			text = t1+t2;			
		} else {
			
			text+=c;
		}

		fixMark = -1;
		cursor++;
		
		notifyTextChanged();
	}

	protected int getMinMark() {

		if (fixMark<0) {
			return 0;
		}

		if (cursor<fixMark) {
			return cursor;
		}
		else{
			return fixMark;
		}

	}

	protected int getMaxMark() {

		if (fixMark<0) {
			return 0;
		}

		if (cursor<fixMark) {
			return fixMark;
		}
		else{
			return cursor;
		}
	}

	protected void moveCursorToStart() {
		cursor = 0;
	}
	
	protected void moveCursorToEnd() {
		cursor = text.length();
	}
	

	protected void notifyTextChanged() {
		if (onTextChangeListener == null)
			return;
	}
	
	public OnTextChangedListener getOnTextChangeListener() {
		return onTextChangeListener;
	}

	public void setOnTextChangeListener(OnTextChangedListener onTextChangeListener) {
		this.onTextChangeListener = onTextChangeListener;
	}
	
	public boolean isSelected() {
		return shift;
	}

	public void copy(TextFieldView view) {
		super.copy(view);
		text = view.text;

		cursor = view.cursor;
		fixMark = view.fixMark;

		minMark = view.minMark;
		maxMark = view.maxMark;

		maxLength = view.maxLength;
		shift = view.shift;
		control = view.control;

		onTextChangeListener = view.onTextChangeListener;
	}
}
