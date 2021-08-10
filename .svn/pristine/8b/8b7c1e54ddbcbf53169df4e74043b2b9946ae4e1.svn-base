package com.gmail.sungmin0511a.hComponent;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.EventListener;
import java.util.Iterator;

import com.gmail.sungmin0511a.costume.Costume;
import com.gmail.sungmin0511a.costume.Painting;
import com.gmail.sungmin0511a.drawAbles.Child;
import com.gmail.sungmin0511a.drawAbles.ShapedChild;

public class HButton extends Child {
	private final static int DISABLE = 0;
	private final static int MOUSE_ON = 1;
	private final static int MOUSE_OVER = 2;
	private final static int MOUSE_PRESSED = 3;
	/**
	 * 
	 */
	private static final long serialVersionUID = 3656563497527889817L;
	Child disableChild;
	Child mouseOnChild;
	Child mouseOverChild;
	Child mousePressedChild;
	Child shapeChild;
	private boolean able;
	private int nowWhat;
	
	public HButton() {
		super();
		nowWhat = MOUSE_OVER;
		able = true;
		{
			mouseOverChild = new ShapedChild(new Rectangle(100, 50));
			mouseOverChild.addCostume(new Painting(Color.darkGray));
			((ShapedChild) mouseOverChild).setFilled(true);
		}
		{
			mouseOnChild = new ShapedChild(new Rectangle(100, 50));
			mouseOnChild.addCostume(new Painting(Color.red));
			((ShapedChild) mouseOnChild).setFilled(true);
		}
		{
			mousePressedChild = new ShapedChild(new Rectangle(100, 50));
			mousePressedChild.addCostume(new Painting(Color.blue));
			((ShapedChild) mousePressedChild).setFilled(true);
		}
		{
			disableChild = new ShapedChild(new Rectangle(100, 50));
			disableChild.addCostume(new Painting(Color.gray));
			((ShapedChild) disableChild).setFilled(true);
		}
		shapeChild = mouseOverChild;
		;
		addEventListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (able)
					for (EventListener eventListener : eventListeners) {
						if (eventListener instanceof ActionListener) {
							ActionListener actionListener = (ActionListener) eventListener;
							actionListener.actionPerformed(null);
						}
					}
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				if (able)
					nowWhat = MOUSE_ON;
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				if (able && nowWhat != 3)
					nowWhat = MOUSE_OVER;
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				if (able)
					nowWhat = MOUSE_PRESSED;
			}
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if (able)
					nowWhat = MOUSE_ON;
			}
		});
	}
	
	@Override
	public boolean contains(Child child) {
		return shapeChild.contains(child);
	}
	
	@Override
	public boolean contains(Point point) {
		return shapeChild.contains(point);
	}
	
	@Override
	public void draw(Graphics2D graphics) {
		Child child = null;
		switch (nowWhat) {
			case DISABLE:
				child = disableChild;
				break;
			case MOUSE_OVER:
				child = mouseOverChild;
				break;
			case MOUSE_ON:
				child = mouseOnChild;
				break;
			case MOUSE_PRESSED:
				child = mousePressedChild;
				break;
			default:
				break;
		}
		if (child == null || !child.isVisible())
			return;
		// ÄÚ½ºÆ¬ dressUp
		Iterator<Costume> iterator2 = child.getCostumes().iterator();
		while (iterator2.hasNext())
			((Costume) iterator2.next()).dressUp(graphics);
		// Child.draw(Graphics);
		child.draw(graphics);
		// ÄÚ½ºÆ¬ unDress
		iterator2 = child.getCostumes().descendingIterator();
		while (iterator2.hasNext())
			((Costume) iterator2.next()).unDress(graphics);
	}
	
	public Child getDisableChild() {
		return disableChild;
	}
	
	public Child getMouseOnChild() {
		return mouseOnChild;
	}
	
	public Child getMouseOverChild() {
		return mouseOverChild;
	}
	
	public Child getMousePressedChild() {
		return mousePressedChild;
	}
	
	public Child getShapeChild() {
		return shapeChild;
	}
	
	public boolean isAble() {
		return able;
	}
	
	public void setAble(boolean able) {
		this.able = able;
		if (able)
			nowWhat = MOUSE_OVER;
		else
			nowWhat = DISABLE;
	}
	
	public void setDisableChild(Child disableChild) {
		this.disableChild = disableChild;
	}
	
	public void setMouseOnChild(Child mouseOnChild) {
		this.mouseOnChild = mouseOnChild;
	}
	
	public void setMouseOverChild(Child mouseOverChild) {
		this.mouseOverChild = mouseOverChild;
	}
	
	public void setMousePressedChild(Child mousePressedChild) {
		this.mousePressedChild = mousePressedChild;
	}
	
	public void setShapeChild(Child shapeChild) {
		this.shapeChild = shapeChild;
	}
}
