/**
 * 
 */
package com.gmail.sungmin0511a.costume;

import java.awt.Color;
import java.awt.Graphics2D;

/** @author ½Å¼º¹Î */
public class Painting implements Costume {
	int priority;
	private Color beforeColor;

	private Color myColor;
	
	public Painting(Color myColor) {
		this.myColor = myColor;
	}
	
	@Override
	public void dressUp(Graphics2D graphics) {
		beforeColor = graphics.getColor();
		graphics.setColor(myColor);
	}
	
	public Color getMyColor() {
		return myColor;
	}
	
	@Override
	public int getPriority() {
		return priority;
	}

	public void setMyColor(Color myColor) {
		this.myColor = myColor;
	}
	
	@Override
	public void setPriority(int priority) {
		this.priority = priority;
	}

	@Override
	public void unDress(Graphics2D graphics) {
		graphics.setColor(beforeColor);
	}
}
