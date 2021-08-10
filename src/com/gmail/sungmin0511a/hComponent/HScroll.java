package com.gmail.sungmin0511a.hComponent;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.Iterator;

import com.gmail.sungmin0511a.costume.Costume;
import com.gmail.sungmin0511a.costume.Location;
import com.gmail.sungmin0511a.costume.Painting;
import com.gmail.sungmin0511a.drawAbles.Child;
import com.gmail.sungmin0511a.drawAbles.ShapedChild;

public class HScroll extends Child {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2766550715960809140L;
	public int height;
	public int width;
	protected Child handle;
	protected Location location;

	public HScroll() {
		Ellipse2D ellipse2d = new Ellipse2D.Double(0, 0, 20, 20);
		handle = new ShapedChild(ellipse2d);
		location = new Location();
		handle.addCostume(new Painting(Color.black));
		handle.addCostume(location);
		width = 100;
		height = 10;
		addCostume(new Painting(Color.black));
		addEventListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				super.mouseDragged(e);
				Point2D point2d = globalToLocal(e.getPoint());
				location.setX(point2d.getX());
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				Point2D point2d = globalToLocal(e.getPoint());
				location.setX(point2d.getX());
			}
		});
	}
	
	@Override
	public boolean contains(Child child) {
		return false;
		// TODO HScroll.contains()
	}
	
	@Override
	public boolean contains(Point point) {
		return (point.x < width && point.x > 0 && point.y < height && point.y > 0);
	}
	
	@Override
	public void draw(Graphics2D graphics) {
		graphics.drawRect(0, 0, width, height);
		if (handle == null || !handle.isVisible())
			return;
		// ÄÚ½ºÆ¬ dressUp
		Iterator<Costume> iterator2 = handle.getCostumes().iterator();
		while (iterator2.hasNext())
			((Costume) iterator2.next()).dressUp(graphics);
		// Child.draw(Graphics);
		handle.draw(graphics);
		// ÄÚ½ºÆ¬ unDress
		iterator2 = handle.getCostumes().descendingIterator();
		while (iterator2.hasNext())
			((Costume) iterator2.next()).unDress(graphics);
	}
	
	public Child getHandle() {
		return handle;
	}
	
	public double getValue() {
		return location.getX();
	}

	public void setHandle(Child handle) {
		this.handle.removeCostume(location);
		this.handle = handle;
		handle.addCostume(location);
	}
	
	public void setValue(double value) {
		location.setX(value);
	}
}
