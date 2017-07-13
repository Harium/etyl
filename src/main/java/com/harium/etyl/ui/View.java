package com.harium.etyl.ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.harium.etyl.commons.Drawable;
import com.harium.etyl.commons.event.Action;
import com.harium.etyl.commons.event.GUIEvent;
import com.harium.etyl.commons.event.PointerEvent;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.ui.style.Style;
import com.harium.etyl.ui.theme.Theme;
import com.harium.etyl.ui.theme.ThemeManager;
import com.harium.etyl.commons.layer.Layer;

/**
 * 
 * @author yuripourre
 *
 */

public abstract class View extends Layer implements GUIComponent, Drawable {

	protected GUIEvent lastEvent = GUIEvent.NONE;

	protected boolean onFocus = false;
	protected boolean mouseOver = false;
	protected boolean disabled = false;
	protected boolean clipOnDraw = false;

	protected View root = null;

	protected List<View> views = new ArrayList<View>();

	protected List<Action> actions = new ArrayList<Action>();

	//GUIAction's Map
	protected Map<GUIEvent,Action> actionMap = new HashMap<GUIEvent, Action>();

	public Style style = new Style();

	private static long lastId = 0; 
	private long id = generateId();

	public View(int x, int y) {
		super(x,y,1,1);
	}

	public View(int x, int y, int w, int h) {
		super(x,y,w,h);
	}

	public View() {
		super(0, 0);
	}

	public void rebuild() {

	}

	public GUIEvent getLastEvent() {
		return lastEvent;
	}

	/**
	 * 
	 * @param lastEvent
	 */
	public void setLastEvent(GUIEvent lastEvent) {
		this.lastEvent = lastEvent;
	}

	public boolean isMouseOver() {
		return mouseOver;
	}

	/**
	 * 
	 * @param mouseOver
	 */
	public void setMouseOver(boolean mouseOver) {
		this.mouseOver = mouseOver;
	}

	public boolean isOnFocus() {
		return onFocus;
	}

	/**
	 * 
	 * @param focus
	 */
	public void setOnFocus(boolean focus) {
		this.onFocus = focus;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public List<Action> getActions() {
		return actions;
	}

	/**
	 * 
	 * @param actions
	 */
	public void setActions(List<Action> actions) {
		this.actions = actions;
	}

	public synchronized List<View> getViews() {
		return views;
	}

	public void clearComponents(){
		this.views.clear();
	}

	/**
	 * 
	 * @param component
	 */
	public void remove(View component){
		this.views.remove(component);
	}

	/**
	 * 
	 * @param components
	 */
	public void removeAll(Collection<? extends View> components){
		this.views.removeAll(components);
	}

	/**
	 * @param component
	 */
	public void add(View component) {
		component.setRoot(this);
		this.views.add(component);
	}

	/**
	 * 
	 * @param components
	 */
	public void addAll(Collection<? extends View> components) {

		for(View component: components)	{
			add(component);
		}

	}

	/**
	 * 
	 * @param x
	 * @param y
	 */
	public void translateComponents(int x, int y){
		for(View component: views){
			translateComponent(x, y, component);
		}
	}

	private void translateComponent(int x, int y, View component){

		component.setLocation(x, y);

		for(View child: component.views){
			translateComponent(x, y, child);
		}

	}	


	/**
	 * Method to execute component's associated actions
	 * 
	 * @param event
	 */	
	public void executeAction(GUIEvent event) {

		if(actionMap.containsKey(event)) {
			actionMap.get(event).executeAction();
		}

	}

	/**
	 * 
	 * @param event
	 * @param action
	 */
	public void addAction(GUIEvent event, Action action){
		actionMap.put(event, action);
	}

	protected void setRoot(View root){
		this.root = root;
	}

	public View findNext(){

		if(root!=null){

			Iterator<View> it = root.getViews().iterator();

			while(it.hasNext()){

				if(this.equals(it.next())){

					if(it.hasNext()){
						return it.next();
					}
				}

			}

		}

		return null;

	}

	public void resize(int width, int height) {
		// TODO Auto-generated method stub
	}

	public GUIEvent updateMouse(PointerEvent event) {
		if(!isMouseOver()) {
			if(onMouse(event)) {
				mouseIn();
				return GUIEvent.MOUSE_IN;
			}
		} else {
			if(!onMouse(event)) {
				mouseOut();
				return GUIEvent.MOUSE_OUT;
			}
		}

		return GUIEvent.NONE;
	}

	public void mouseIn() {
		setMouseOver(true);
		updateEvent(GUIEvent.MOUSE_IN);
	}	

	public void mouseOut() {
		setMouseOver(false);
		updateEvent(GUIEvent.MOUSE_OUT);
	}

	/* Util methods to Resize
	@Override
	public void draw(Graphics g) {
		this.draw(g, x, y, w, h);
	}

	public abstract void draw(Graphics g, int x, int y, int w, int h);
	 */

	public Theme getTheme() {
		return ThemeManager.getInstance().getTheme();
	}

	//Style Helper Methods
	protected int top() {
		return y+style.margin.top;
	}

	protected int left() {
		return x+style.margin.left;
	}

	protected int width() {
		return w-style.margin.left-style.margin.right;
	}

	protected int height() {
		return h-style.margin.top-style.margin.bottom;
	}

	protected int horizontalMargin() {
		return style.margin.right+style.margin.left;
	}

	protected int verticalMargin() {
		return style.margin.top+style.margin.bottom;
	}

	public long getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		View other = (View) obj;
		if (id != other.id)
			return false;
		return true;
	}

	private static long generateId() {
		lastId++;
		return lastId;
	}

	/**
	 * Method to update the size
	 */
	public void resize() {
		resize(w, h);
	}

	public boolean isClipOnDraw() {
		return clipOnDraw;
	}

	public void drawWithChildren(Graphics g) {
		if (!visible) {
			return;
		}
		this.draw(g);

		for (View child: views) {
			child.drawWithChildren(g);
		}
	}

	public void cascadeClipOnDraw(boolean clipOnDraw) {
		this.clipOnDraw = clipOnDraw;
		for (View child: views) {
			child.cascadeClipOnDraw(clipOnDraw);
		}
	}

	public void copy(View view) {
		clipOnDraw = view.clipOnDraw;
		mouseOver = view.mouseOver;
		onFocus = view.onFocus;
		disabled = view.disabled;
		actionMap = view.actionMap;
		actions = view.actions;
		root = view.root;

		views.clear();
		for (View child : view.getViews()) {
			child.rebuild();
			views.add(child);
		}
	}

}
