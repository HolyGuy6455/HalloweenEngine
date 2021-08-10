package com.gmail.sungmin0511a.costume;

import java.awt.Graphics2D;

public class FadeIn extends Opacity {
	protected double degree;
	protected boolean fadeIn;

	public FadeIn() {
		super();
		degree = 0.1;
		fadeIn = true;
	}
	
	@Override
	public void dressUp(Graphics2D graphics) {
		if (fadeIn) {
			transparency += degree;
			if (transparency > 1)
				transparency = 1;
		} else {
			transparency -= degree;
			if (transparency < 0)
				transparency = 0;
		}
		super.dressUp(graphics);
	}
	
	public void fadeIn(boolean b) {
		this.fadeIn = b;
	}

	public double getDegree() {
		return degree;
	}
	
	public void setDegree(double degree) {
		this.degree = degree;
	}

	@Override
	public void unDress(Graphics2D graphics) {
		super.unDress(graphics);
	}
}
