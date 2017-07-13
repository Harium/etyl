package com.harium.etyl.ui.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.harium.etyl.commons.event.GUIEvent;
import com.harium.etyl.commons.event.KeyEvent;
import com.harium.etyl.commons.event.MouseEvent;
import com.harium.etyl.commons.event.PointerEvent;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.ui.View;
import com.harium.etyl.ui.listener.RowListener;
import com.harium.etyl.ui.table.Column;
import com.harium.etyl.ui.table.Row;

/**
 * Table component
 * 
 * @author yuripourre
 *
 */

public class BaseTable extends View {

	boolean showContours = true;
	boolean showHeaders = true;

	private Row onMouseRow;
	private Row selectedRow;

	private List<Row> rows = new ArrayList<Row>();
	private List<String> headers = new ArrayList<String>();
	private Map<String, Column> columns = new HashMap<String, Column>();

	private RowListener rowListener = ROW_LISTENER_DUMMY;

	private static final RowListener ROW_LISTENER_DUMMY = new RowListener() {
		@Override
		public void onMouse(Row row) {}

		@Override
		public void onClick(Row row) {}
	}; 

	public BaseTable(int x, int y, int w, int h) {
		super(x,y,w,h);
	}

	@Override
	public GUIEvent updateMouse(PointerEvent event) {
		int mx = event.getX();
		int my = event.getY();

		checkSelectedRow(mx, my);

		if (event.isButtonDown(MouseEvent.MOUSE_BUTTON_LEFT)) {
			if (onMouseRow != null) {
				selectedRow = onMouseRow;
				rowListener.onClick(onMouseRow);				
			}
		}

		return GUIEvent.NONE;
	}

	private void checkSelectedRow(int mx, int my) {
		if (rows.isEmpty()) {
			return;
		}

		int rh = 0;

		if(showHeaders) {
			rh = headerSize();
		}

		onMouseRow = null;

		if(mx > x && mx < x+w) {
			if(my > y && my < y + h) {
				int index = (my-y-rh)/rowSize();
				//if (index >= scrollOffset && index <= scrollOffset+visibleRows()) {
				if (index >= 0 && index < rows.size()) {
					Row row = rows.get(index);

					if(row != onMouseRow) {
						onMouseRow = rows.get(index);
						rowListener.onMouse(row);
					}
				}
			}
		}

	}

	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {
		// TODO Auto-generated method stub
		return GUIEvent.NONE;
	}

	@Override
	public void updateEvent(GUIEvent event) {

	}
	
	@Override
	public void draw(Graphics g) {
		g.setClip(left(), top(), width(), height());
		g.setFont(getTheme().getFont());

		g.setColor(getTheme().getBackgroundColor());
		g.fillRect(left(), top(), width(), height());

		g.setColor(getTheme().getTextColor());
		if(showContours) {
			g.drawRect(left(), top(), width(), height());
		}

		drawRows(g);
		if (showHeaders) {
			drawHeaders(g);
		}
		
		g.resetClip();
	}

	private int headerSize() {
		float size = getTheme().getFontSize()*1.4f;
		
		return (int)size;
	}

	private int rowSize() {
		return getTheme().getFontSize();
	}

	private void drawHeaders(Graphics g) {

		float yOffset = (int)(headerSize()-getTheme().getFontSize()*0.5); 
		int lastCW = 0;

		int hy = (int)yOffset+top();

		for (int i = 0; i < headers.size(); i++) {
			String header = headers.get(i);
			int cw = columns.get(header).size;
			String label = headers.get(i);

			int hx = left()+lastCW;

			g.drawString(label, style.padding.left+hx, style.padding.top+hy);
			lastCW += cw;
			if (showContours) {
				g.drawLine(hx, top(), hx, top()+height());
			}
		}

		if (showContours) {
			g.drawLine(left(), top()+headerSize(), left()+width(), top()+headerSize());
		}
	}

	private void drawRows(Graphics g) {

		int yOffset = style.padding.top;
		if (showHeaders) {
			yOffset = headerSize();
		}

		yOffset += style.margin.top;

		int ry = yOffset + top() + rowSize()/2;

		for (int i = 0; i < rows.size(); i++) {
			if (i > visibleRows()) {
				break;
			}

			Row row = rows.get(i);

			int lastCW = 0;

			if (row == onMouseRow || row == selectedRow) {
				g.setColor(getTheme().getSelectionColor());
				int sy = headerSize()+rowSize()*i;
				g.fillRect(left(), top()+sy, width(), rowSize());
				g.setColor(getTheme().getTextSelectedColor());
			}

			for (String header : headers) {
				int cw = columns.get(header).size;
				int rx = x+lastCW;
				String label = row.getValue(header);
				g.drawString(label, style.padding.left+rx, style.padding.top+ry);
				lastCW += cw;
			}

			if (row == onMouseRow || row == selectedRow) {
				g.setColor(getTheme().getTextColor());
			}

			ry += rowSize();
		}
	}

	public int visibleRows() {
		int rh = 0;

		if(showHeaders) {
			rh = headerSize();
		}

		int max = (h-rh)/rowSize(); 

		if(rows.size()<max) {
			return rows.size();
		}

		return max;
	}

	public void add(Row row) {
		rows.add(row);
	}

	public Row addRow(String ... values) {
		Row row = new Row();
		int i = 0;
		for (String header : headers) {
			row.putValue(header, values[i]);
			i++;
		}
		add(row);
		return row;
	}

	public void setHeaders(String... headers) {
		for(String header : headers) {
			addHeader(header);
		}
		
		resizeColumns();
	}

	private void resizeColumns() {
		//Update column sizes
		for(String header : this.headers) {
			columns.get(header).size = w/this.headers.size();
		}
	}

	@Override
	public void resize() {
		super.resize();
		resizeColumns();
	}
	
	public void addHeader(String header) {
		headers.add(header);
		columns.put(header, new Column());
	}

	public List<String> getHeaders() {
		return headers;
	}

	public void setHeaders(List<String> headers) {
		this.headers = headers;
	}

	public RowListener getRowListener() {
		return rowListener;
	}

	public void setRowListener(RowListener rowListener) {
		this.rowListener = rowListener;
	}

	public void selectRow(int index) {
		selectedRow = rows.get(index);
	}

	public void selectRow(Row row) {
		for(Row r:rows) {
			if(row == r) {
				selectedRow = r;
				break;
			}
		}
	}

	public void selectRow(String column, String value) {
		for(Row r:rows) {
			if(r.getValue(column).equals(value)) {
				selectedRow = r;
				break;
			}
		}
	}

	public void copy(BaseTable view) {
		super.copy(view);

		showContours = view.showContours;
		showHeaders = view.showHeaders;

		onMouseRow = view.onMouseRow;
		selectedRow = view.selectedRow;

		rows = view.rows;
		headers = view.headers;
		columns = view.columns;

		rowListener = view.rowListener;
	}
}
