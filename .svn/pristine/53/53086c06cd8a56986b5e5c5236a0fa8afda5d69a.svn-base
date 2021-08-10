package com.gmail.sungmin0511a.costume;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Stroke;

public class LineStyle implements Costume {
	Stroke beforeStroke;
	int priority;
	Stroke stroke;
	
	public LineStyle() {
		stroke = new BasicStroke();
	}
	
	public LineStyle(float width, int cap, int join) {
		stroke = new BasicStroke(width, cap, join);
	}
	
	@Override
	public void dressUp(Graphics2D graphics) {
		beforeStroke = graphics.getStroke();
		graphics.setStroke(stroke);
	}
	
	@Override
	public int getPriority() {
		return priority;
	}
	
	@Override
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	@Override
	public void unDress(Graphics2D graphics) {
		graphics.setStroke(beforeStroke);
	}
}
