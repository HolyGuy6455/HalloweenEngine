/**
 * 
 */
package com.gmail.sungmin0511a.costume;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

/** @author ?ż??? */
public class Rotation extends PointCostume {
	int priority;
	private double degree;
	
	public Rotation() {
		this(0.0);
	}

	/** @param degree */
	public Rotation(double degree) {
		super(AffineTransform.getRotateInstance(degree));
		this.degree = degree;
	}
	
	@Override
	public void dressUp(Graphics2D graphics) {
		graphics.rotate(degree);
	}
	
	@Override
	public void dressUp(Point2D position) {
		double X = position.getX();
		double Y = position.getY();
		position.setLocation((Math.cos(degree) * X - Math.sin(degree) * Y),
				(Math.sin(degree) * X + Math.cos(degree) * Y));
	}
	
	public double getDegree() {
		return degree;
	}
	
	@Override
	public int getPriority() {
		return priority;
	}

	public void setDegree(double degree) {
		this.degree = degree;
	}
	
	@Override
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	@Override
	public void unDress(Graphics2D graphics) {
		graphics.rotate(-degree);
	}
	
	@Override
	public void unDress(Point2D position) {
		double X = position.getX();
		double Y = position.getY();
		position.setLocation((Math.cos(-degree) * X - Math.sin(-degree) * Y),
				(Math.sin(-degree) * X + Math.cos(-degree) * Y));
	}
}
