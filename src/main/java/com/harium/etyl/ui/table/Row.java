package com.harium.etyl.ui.table;

import java.util.HashMap;
import java.util.Map;

public class Row {

	Map<String, String> values = new HashMap<String, String>();
	
	public Row() {
		super();
	}
	
	public Row(String[]...pairs) {
		super();
		for (String[] pair: pairs) {
			this.putValue(pair[0], pair[1]);
		}
	}
	
	public String putValue(String header, String value) {
		return values.put(header, value);
	}
	
	public String getValue(String header) {
		return values.get(header);
	}

}
