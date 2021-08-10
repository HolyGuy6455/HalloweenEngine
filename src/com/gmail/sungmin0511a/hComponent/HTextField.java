package com.gmail.sungmin0511a.hComponent;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;

import com.gmail.sungmin0511a.costume.Costume;
import com.gmail.sungmin0511a.costume.Painting;
import com.gmail.sungmin0511a.drawAbles.Child;
import com.gmail.sungmin0511a.drawAbles.StringChild;

public class HTextField extends Child {
	/**	 */
	private static final long serialVersionUID = -1660025976626297411L;
	protected int cursorAt;
	protected int height;
	protected Painting painting;
	protected StringChild stringChild;
	protected int width;
	private StringBuilder builder;
	private int count;
	private KeyListener keyListener;
	
	public HTextField() {
		this("");
	}

	public HTextField(String label) {
		this(label, 100, 15);
	}
	
	public HTextField(String label, int width, int height) {
		this.width = width;
		this.height = height;
		count = 0;
		builder = new StringBuilder(label);
		stringChild = new StringChild(label);
		stringChild.addCostume(new Painting(Color.black));
		cursorAt = label.length();
		keyListener = new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (isFocus()) {
					switch (e.getKeyCode()) {
						case KeyEvent.VK_BACK_SPACE:
							if (cursorAt > 0)
								builder.deleteCharAt(--cursorAt);
							break;
						case KeyEvent.VK_DELETE:
							if (cursorAt > 0)
								builder.deleteCharAt(cursorAt);
							break;
						case KeyEvent.VK_HOME:
							cursorAt = 0;
							break;
						case KeyEvent.VK_END:
							cursorAt = builder.length();
							break;
						case KeyEvent.VK_LEFT:
							if (cursorAt > 0)
								cursorAt--;
							break;
						case KeyEvent.VK_RIGHT:
							if (cursorAt < builder.length())
								cursorAt++;
							break;
						case KeyEvent.VK_ESCAPE:
							loseFocus();
						case KeyEvent.VK_SHIFT:
						case KeyEvent.VK_CAPS_LOCK:
						case KeyEvent.VK_ENTER:
						case KeyEvent.VK_DOWN:
						case KeyEvent.VK_UP:
						case KeyEvent.VK_CONTROL:
						case KeyEvent.VK_ALT:
							break;
						default:
							builder.insert(cursorAt, e.getKeyChar());
							cursorAt++;
							break;
					}
					stringChild.setString(builder.toString());
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
			}
			
			@Override
			public void keyTyped(KeyEvent e) {
			}
		};
		addEventListener(keyListener);
		addEventListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				if (!isFocus())
					requestFocus();
				//				Point2D point = globalToLocal(arg0.getPoint());
			}
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
		});
		painting = new Painting(Color.black);
		addCostume(painting);
	}
	
	@Override
	public boolean contains(Child child) {
		return false;
		// TODO HTextField.contains()
	}
	
	@Override
	public boolean contains(Point point) {
		return (point.x < width && point.x > 0 && point.y < height && point.y > 0);
	}
	
	@Override
	public void draw(Graphics2D graphics) {
		graphics.drawRect(0, 0, width, height);
		graphics.translate(3, 0);
		if (isFocus()) {
			count++;
			if (count % 100 < 50) {
				int a = (int) graphics
						.getFont()
						.getStringBounds(builder.substring(0, cursorAt),
								graphics.getFontRenderContext()).getWidth();
				graphics.drawLine(a, 2, a, height - 2);
			}
		}
		Iterator<Costume> iterator2 = stringChild.getCostumes().iterator();
		while (iterator2.hasNext())
			((Costume) iterator2.next()).dressUp(graphics);
		;
		stringChild.draw(graphics);
		;
		iterator2 = stringChild.getCostumes().descendingIterator();
		while (iterator2.hasNext())
			((Costume) iterator2.next()).unDress(graphics);
		graphics.translate(-3, 0);
	}
	
	public int getCursorAt() {
		return cursorAt;
	}
	
	public int getHeight() {
		return height;
	}
	
	public KeyListener getKeyListener() {
		return keyListener;
	}

	public Painting getPainting() {
		return painting;
	}
	
	public String getString() {
		return builder.toString();
	}

	public StringChild getStringChild() {
		return stringChild;
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setCursorAt(int cursorAt) {
		this.cursorAt = cursorAt;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public void setStringChild(StringChild stringChild) {
		this.stringChild = stringChild;
	}
	
	public void setValue(String string) {
		builder.delete(0, builder.length());
		builder.append(string);
		stringChild.setString(string);
		cursorAt = builder.length();
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
}
