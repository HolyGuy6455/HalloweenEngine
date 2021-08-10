package com.gmail.sungmin0511a.drawAbles;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;

public class StringChild extends Child {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4041132256728152858L;
	Font font;
	String string;
	
	public StringChild(String string) {
		super();
		this.string = string;
	}
	
	@Override
	public boolean contains(Child child) {
		// TODO StringChild.contains(Child child)
		return false;
	}
	
	@Override
	public boolean contains(Point point) {
		// TODO StringChild.contains(Point point)
		return false;
	}
	
	@Override
	public void draw(Graphics2D g) {
		if (string != null && string != "") {
			if (font != null) {
				Font before = g.getFont();
				g.setFont(font);
				g.translate(0, font.getSize());
				g.drawString(string, 0, 0);
				g.translate(0, -font.getSize());
				g.setFont(before);
			} else {
				g.translate(0, g.getFont().getSize());
				g.drawString(string, 0, 0);
				g.translate(0, -g.getFont().getSize());
			}
		}
	}
	
	public Font getFont() {
		return font;
	}

	public String getString() {
		return string;
	}
	
	public void setFont(Font font) {
		this.font = font;
	}

	public void setString(String string) {
		this.string = string;
	}
}
