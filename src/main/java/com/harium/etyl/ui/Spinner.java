package com.harium.etyl.ui;

import com.harium.etyl.commons.event.Action;
import com.harium.etyl.commons.event.GUIEvent;
import com.harium.etyl.commons.event.KeyEvent;
import com.harium.etyl.commons.event.PointerEvent;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.ui.listener.ValueListener;
import com.harium.etyl.ui.spinner.composer.SpinnerComposer;
import com.harium.etyl.ui.spinner.composer.VerticalComposer;

/**
 * 
 * @author yuripourre
 *
 */

public abstract class Spinner<T extends Number> extends View {

	protected SpinnerComposer composer;

	protected Button plus;
	protected Button minus;
	protected TextView currentValue;
	protected Panel panel;

	protected T value;
	protected T step;
	protected T maxValue;
	protected T minValue;

	protected ValueListener<T> listener;

	public Spinner(int x, int y, int w, int h) {
		super(x, y, w, h);

		rebuild();
	}

	public void rebuild() {
		panel = new Panel(x, y, w, h);
		composer = buildComposer();
		configureButtons();
	}

	protected SpinnerComposer buildComposer() {
		return new VerticalComposer(x, y, w, h);
	}

	private void configureButtons() {

		int border = 1;
		int buttonWidth = w/6;

		//TODO change size based on fontSize
		currentValue = new TextView(x, y + h/2 - 10, w - buttonWidth, h/2 - 10);
		currentValue.setText("0");

		plus = composer.buildPlusButton(x+w-buttonWidth-border, y+border, buttonWidth, h/2-border);
		plus.addAction(GUIEvent.MOUSE_LEFT_BUTTON_UP, new Action(this, "addReload"));

		minus = composer.buildMinusButton(x+w-buttonWidth-border, y+h/2+border, buttonWidth, h/2-border*2);
		minus.addAction(GUIEvent.MOUSE_LEFT_BUTTON_UP, new Action(this, "subReload"));		
	}

	//Should be private
	public void addReload() {
		add();		
		reload();
	}

	//Should be private
	public void subReload() {
		subtract();
		reload();
	}

	protected void reload() {
		if (listener != null) {
			listener.onChange(value);
		}

		String result = value.toString();
		currentValue.setText(result);
	}

	public abstract void add();
	public abstract void subtract();

	@Override
	public GUIEvent updateMouse(PointerEvent event) {
		plus.updateMouse(event);
		minus.updateMouse(event);

		return GUIEvent.NONE;
	}

	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {
		return GUIEvent.NONE;
	}

	public void mouseOut() {
		super.mouseOut();

		plus.setMouseOver(false);
		minus.setMouseOver(false);
	}

	@Override
	public void updateEvent(GUIEvent event) {
		// TODO Auto-generated method stub
	}

	@Override
	public void draw(Graphics g) {
		drawPanel(g);
		drawCurrentValue(g);
		drawButtons(g);
	}

	protected void drawPanel(Graphics g) {
		panel.draw(g);
	}

	protected void drawCurrentValue(Graphics g) {
		currentValue.draw(g);
	}

	protected void drawButtons(Graphics g) {
		plus.draw(g);
		minus.draw(g);
	}

	public void setValue(T value){
		this.value = value;
		reload();
	}

	public T getValue() {
		return this.value;
	}

	public T getStep() {
		return step;
	}

	public void setStep(T step) {
		this.step = step;
	}

	public T getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(T maxValue) {
		this.maxValue = maxValue;
	}

	public T getMinValue() {
		return minValue;
	}

	public void setMinValue(T minValue) {
		this.minValue = minValue;
	}

	public ValueListener<T> getListener() {
		return listener;
	}

	public void setListener(ValueListener<T> listener) {
		this.listener = listener;
	}

	@Override
	public void setBounds(int x, int y, int w, int h) {
		panel.setBounds(x, y, w, h);

		int border = 1;
		int buttonWidth = w/6;

		plus.setBounds(x+w-buttonWidth-border, y+border, buttonWidth, h/2-border);
		minus.setBounds(x+w-buttonWidth-border, y+h/2+border, buttonWidth, h/2-border*2);

		currentValue.setBounds(x, y + h/2 - 10, w - buttonWidth, h/2 - 10);
	}

}
