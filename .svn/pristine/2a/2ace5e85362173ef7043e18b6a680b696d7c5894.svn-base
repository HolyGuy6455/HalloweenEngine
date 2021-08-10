package com.gmail.sungmin0511a.costume;

import java.awt.Graphics2D;

public class Skew implements Costume {
	int priority;
	double x;
	double y;
	
	public Skew() {
		this(0, 0);
	}
	
	/** @param x
	 * @param y */
	public Skew(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void dressUp(Graphics2D graphics) {
		x = Math.max(Math.min(x, Integer.MAX_VALUE), Integer.MIN_VALUE);
		y = Math.max(Math.min(y, Integer.MAX_VALUE), Integer.MIN_VALUE);
		graphics.shear(x, y);
	}
	
	@Override
	public int getPriority() {
		return priority;
	}
	
	/** @return the x */
	public double getX() {
		return x;
	}
	
	/** @return the y */
	public double getY() {
		return y;
	}
	
	@Override
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	/** @param x
	 *        the x to set */
	public void setX(double x) {
		this.x = x;
	}
	
	/** @param y
	 *        the y to set */
	public void setY(double y) {
		this.y = y;
	}
	
	@Override
	public void unDress(Graphics2D graphics) {
		graphics.shear(-x, -y);
	}
}
