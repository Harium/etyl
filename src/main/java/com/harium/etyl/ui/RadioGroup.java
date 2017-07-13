package com.harium.etyl.ui;

import java.util.ArrayList;
import java.util.List;

import com.harium.etyl.ui.base.BaseRadioButton;

/**
 * 
 * @author yuripourre
 *
 */

public class RadioGroup {

	private BaseRadioButton checked;
	private List<BaseRadioButton> radios;
	
	public RadioGroup(){
		super();
		
		radios = new ArrayList<BaseRadioButton>();
	}
	
	public void add(BaseRadioButton radio){
		this.radios.add(radio);
		
		radio.setGroup(this);		
	}
	
	public void remove(BaseRadioButton radio) {
		this.radios.remove(radio);
		if (checked == radio) {
			checked = null;
		}
	}
	
	public void check(BaseRadioButton radio) {
		this.checked = radio;
		radio.setChecked(true);
		for(BaseRadioButton rad: radios){
			if (rad != radio) {
				if (rad.isChecked()) {
					rad.setChecked(false);
				}
			}
		}
	}
	
	public BaseRadioButton getChecked() {
		return checked;
	}
	
	public String getValue() {
		if (checked != null) {
			return checked.getValue();
		}
		
		return "";
	}
}
