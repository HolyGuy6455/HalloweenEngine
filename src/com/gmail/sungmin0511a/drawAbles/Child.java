package com.gmail.sungmin0511a.drawAbles;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.*;

import com.gmail.sungmin0511a.costume.Costume;
import com.gmail.sungmin0511a.costume.PointCostume;
import com.gmail.sungmin0511a.layoutWitch.LayoutPosition;

/** 화면에 표시될 수 있는 모든것의 상위객체입니다. {@link #draw(Graphics2D)}매서드로
 * 화면에 그려질 수 있습니다. <p> 아이템, 케릭터, 버튼, 게이지 등 화면상에 그려지는 모든것은 이
 * Child를 상속해야합니다. Child는 다른 {@link Party}에 포함되기도 하며, 이
 * Party로 부터 이벤트를 받아 가지고있는 이벤트리스너에게 그 이벤트를 전달할 수도 있습니다.
 * 
 * @author 신성민 */
public abstract class Child implements Serializable, drawAble {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5551784747275550378L;
	
	public static boolean overLap(Child child1, Child child2) {
		if (child1.contains(child2) || child2.contains(child1))
			return true;
		else
			return false;
	}

	protected Party belong;
	protected LinkedList<Costume> costumes;
	protected ArrayList<EventListener> eventListeners;
	private boolean focus;
	private boolean mouseOn;
	private LayoutPosition position;
	private boolean visible;
	
	/** 기본형태의 생성자입니다. 어느 {@link Party}에도 속해있지 않고,
	 * {@link Costume}을 하나도 갖지 않은 Child를 하나 생성합니다. */
	public Child() {
		super();
		belong = null;
		eventListeners = new ArrayList<EventListener>();
		costumes = new LinkedList<Costume>();
		mouseOn = false;
		position = null;
		visible = true;
		focus = false;
	}
	
	/** {@link Costume}을 하나 추가합니다. 추가된 {@link Costume}은 이
	 * Child가 화면상에 출력될때( {@link #draw(Graphics2D)}가 실행될때)영향을
	 * 줍니다.
	 * 
	 * @param costume */
	public void addCostume(Costume costume) {
		costumes.add(costume);
		sortCostume();
	}
	
	/** {@link EventListener}를 하나 추가합니다. {@link MouseListener}
	 * , {@link MouseMotionListener}등을 이 매서드로 추가할 수 있습니다.
	 * 
	 * @param eventListener */
	public void addEventListener(EventListener eventListener) {
		eventListeners.add(eventListener);
	}
	
	/** 이 Child와 주어진 Child가 겹치는지에 대해 확인합니다. 대개 이 Child의 특정한 몇
	 * 좌표가 주어진 Child에 겹치는지만 확인합니다.
	 * 
	 * @param child
	 *        이 Child와 겹치는지 확인 할 Child입니다.
	 * @return 만약 두 Child가 겹친다고 판단되면 true를 반환하고, 아니면 false를
	 *         반환합니다. */
	abstract public boolean contains(Child child);
	
	/** double 두개로 주어진 좌표가 이 Child와 겹치는지 확인합니다. 만약 Child가
	 * 그려지는 범위 내에 주어진 좌표가 있다면 참값을 반환할 것입니다.
	 * 
	 * @param x
	 *        x좌표입니다.
	 * @param y
	 *        y좌표입니다.
	 * @return 주어진 좌표가 이 Child와 겹친다면, true를 반환하고 아니라면 false를
	 *         반환합니다. */
	public boolean contains(double x, double y) {
		return contains(new Point((int) x, (int) y));
	}
	
	/** int 두개로 주어진 좌표가 이 Child와 겹치는지 확인합니다. 만약 Child가 그려지는
	 * 범위 내에 주어진 좌표가 있다면 참값을 반환할 것입니다.
	 * 
	 * @param x
	 *        x좌표입니다.
	 * @param y
	 *        y좌표입니다.
	 * @return 주어진 좌표가 이 Child와 겹친다면, true를 반환하고 아니라면 false를
	 *         반환합니다. */
	public boolean contains(int x, int y) {
		return contains(new Point(x, y));
	}
	
	/** {@link Point}로 주어진 좌표가 이 Child와 겹치는지 확인합니다. 만약 Child가
	 * 그려지는 범위 내에 주어진 좌표가 있다면 참값을 반환할 것입니다.
	 * 
	 * @param point
	 *        이 Child와 겹치는지 확인할 좌표의 Point입니다.
	 * @return 주어진 좌표가 이 Child와 겹친다면, true를 반환하고 아니라면 false를
	 *         반환합니다. */
	abstract public boolean contains(Point point);
	
	/** Graphics2D에 이 Child를 그립니다.
	 * 
	 * @param graphics
	 *        이 Child가 그려질 Graphic2D객체입니다. */
	public abstract void draw(Graphics2D graphics);
	
	/** 이 Child가 속해있는 Party를 반환합니다.
	 * 
	 * @return 이 Child가 속해있는 Party입니다.
	 * @see Party */
	public Party getBelong() {
		return belong;
	}
	
	/** 이 Child가 가지고 있는 {@link Costume}들을 반환합니다. 이후 이
	 * {@link Costume}은 이 Child가 화면상에 그려질때 영향을 끼칩니다.
	 * 
	 * @return */
	public LinkedList<Costume> getCostumes() {
		return costumes;
	}
	
	/** @return the position */
	public LayoutPosition getPosition() {
		return position;
	}

	public LinkedList<Party> getSociety() {
		LinkedList<Party> result = new LinkedList<Party>();
		return belong.ancestor(result);
	}
	
	public Point2D globalToLocal(Point2D point) {
		Point2D result = new Point2D.Double(point.getX(), point.getY());
		if (belong != null)
			result = belong.globalToLocal(point);
		Iterator<Costume> iterator = getCostumes().iterator();
		while (iterator.hasNext()) {
			Costume costume = iterator.next();
			if (costume instanceof PointCostume) {
				((PointCostume) costume).unDress(result);
			}
		}
		return result;
	}
	
	public boolean isFocus() {
		return focus;
	}

	/** @return the visible */
	public boolean isVisible() {
		return visible;
	}

	public Point2D.Double localToGlobal(Point2D.Double point) {
		Point2D.Double result = new Point2D.Double(point.x, point.y);
		Iterator<Costume> iterator = getCostumes().descendingIterator();
		while (iterator.hasNext()) {
			Costume costume = iterator.next();
			if (costume instanceof PointCostume)
				((PointCostume) costume).dressUp(result);
		}
		return result;
	}
	
	public void loseFocus() {
		focus = false;
	}

	/** 이 Child에서 주어진 {@link Costume}을 제거합니다. 이후 이
	 * {@link Costume}은 이 Child 에 영향을 끼칠 수 없습니다.
	 * 
	 * @param costume */
	public void removeCostume(Costume costume) {
		costumes.remove(costume);
	}
	
	public void requestFocus() {
		focus = true;
		if (belong != null) {
			belong.focusedMember(this);
		}
	}

	/** @param position
	 *        the position to set */
	public void setPosition(LayoutPosition position) {
		this.position = position;
	}
	
	/** 주어진 {@link Party}안에 이 Child를 넣습니다.
	 * {@link Party#draw(Graphics2D)}가 실행될때, 가지고 있는 모든
	 * {@link Costume}들의 {@link Costume#dressUp(Graphics2D)}
	 * 이 모두 실행된 후, Child의 draw()도 실행됩니다.
	 * 
	 * @param belong
	 *        이 Child가 포함될 Container입니다. */
	public void setRoot(Party belong) {
		this.belong = belong;
	}
	
	/** @param visible
	 *        the visible to set */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	@Override
	public String toString() {
		return super.toString();
		/*try {
			return super.toString() + " (belong : " + belong.toString() + ")";
		} catch (NullPointerException ne) {
			return super.toString();
		}*/
	}
	
	void processKeyEvent(KeyEvent keyEvent, int keyType) {
		switch (keyType) {
			case KeyEvent.KEY_PRESSED:
				for (EventListener eventListener : eventListeners) {
					if (eventListener instanceof KeyListener)
						((KeyListener) eventListener).keyPressed(keyEvent);
				}
				break;
			case KeyEvent.KEY_RELEASED:
				for (EventListener eventListener : eventListeners) {
					if (eventListener instanceof KeyListener)
						((KeyListener) eventListener).keyReleased(keyEvent);
				}
				break;
			case KeyEvent.KEY_TYPED:
				for (EventListener eventListener : eventListeners) {
					if (eventListener instanceof KeyListener)
						((KeyListener) eventListener).keyTyped(keyEvent);
				}
				break;
			default:
				break;
		}
	}

	Child processMouseEvent(MouseEvent mouseEvent, int mouseType) {
		if (!visible)
			return null;
		Point2D localPoint = globalToLocal(new Point2D.Double((double) mouseEvent.getX(),
				(double) mouseEvent.getY()));
		if (mouseOn == false && contains(localPoint.getX(), localPoint.getY())) {
			mouseOn = true;
			processMouseEvent(mouseEvent, MouseEvent.MOUSE_ENTERED);
		} else if (mouseOn == true && !(contains(localPoint.getX(), localPoint.getY()))) {
			mouseOn = false;
			mouseEvent.setSource(this);
			for (EventListener eventListener : eventListeners) {
				if (eventListener instanceof MouseListener)
					((MouseListener) eventListener).mouseExited(mouseEvent);
			}
		}
		if (mouseOn) {
			mouseEvent.setSource(this);
			switch (mouseType) {
				case MouseEvent.MOUSE_CLICKED:
					for (EventListener eventListener : eventListeners) {
						if (eventListener instanceof MouseListener)
							((MouseListener) eventListener).mouseClicked(mouseEvent);
					}
					break;
				case MouseEvent.MOUSE_ENTERED:
					for (EventListener eventListener : eventListeners) {
						if (eventListener instanceof MouseListener)
							((MouseListener) eventListener).mouseEntered(mouseEvent);
					}
					break;
				case MouseEvent.MOUSE_PRESSED:
					for (EventListener eventListener : eventListeners) {
						if (eventListener instanceof MouseListener)
							((MouseListener) eventListener).mousePressed(mouseEvent);
					}
					break;
				case MouseEvent.MOUSE_RELEASED:
					for (EventListener eventListener : eventListeners) {
						if (eventListener instanceof MouseListener)
							((MouseListener) eventListener).mouseReleased(mouseEvent);
					}
					break;
				case MouseEvent.MOUSE_MOVED:
					for (EventListener eventListener : eventListeners) {
						if (eventListener instanceof MouseMotionListener)
							((MouseMotionListener) eventListener).mouseMoved(mouseEvent);
					}
					break;
				case MouseEvent.MOUSE_DRAGGED:
					for (EventListener eventListener : eventListeners) {
						if (eventListener instanceof MouseMotionListener)
							((MouseMotionListener) eventListener).mouseDragged(mouseEvent);
					}
					break;
				case MouseEvent.MOUSE_WHEEL:
					for (EventListener eventListener : eventListeners) {
						if (eventListener instanceof MouseWheelListener)
							((MouseWheelListener) eventListener)
									.mouseWheelMoved((MouseWheelEvent) mouseEvent);
					}
					break;
				default:
					break;
			}
			// event.translatePoint(event.getX() - (int)
			// localPoint.x, event.getY()
			// - (int) localPoint.y);
			return this;
		}
		return null;
	}

	private void sortCostume() {
		Collections.sort(costumes, new Comparator<Costume>() {
			@Override
			public int compare(Costume costume1, Costume costume2) {
				return costume1.getPriority() - costume2.getPriority();
			}
		});
	}
}
