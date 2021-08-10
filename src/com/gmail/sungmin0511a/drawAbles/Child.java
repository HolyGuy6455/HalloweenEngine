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

/** ȭ�鿡 ǥ�õ� �� �ִ� ������ ������ü�Դϴ�. {@link #draw(Graphics2D)}�ż����
 * ȭ�鿡 �׷��� �� �ֽ��ϴ�. <p> ������, �ɸ���, ��ư, ������ �� ȭ��� �׷����� ������ ��
 * Child�� ����ؾ��մϴ�. Child�� �ٸ� {@link Party}�� ���ԵǱ⵵ �ϸ�, ��
 * Party�� ���� �̺�Ʈ�� �޾� �������ִ� �̺�Ʈ�����ʿ��� �� �̺�Ʈ�� ������ ���� �ֽ��ϴ�.
 * 
 * @author �ż��� */
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
	
	/** �⺻������ �������Դϴ�. ��� {@link Party}���� �������� �ʰ�,
	 * {@link Costume}�� �ϳ��� ���� ���� Child�� �ϳ� �����մϴ�. */
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
	
	/** {@link Costume}�� �ϳ� �߰��մϴ�. �߰��� {@link Costume}�� ��
	 * Child�� ȭ��� ��µɶ�( {@link #draw(Graphics2D)}�� ����ɶ�)������
	 * �ݴϴ�.
	 * 
	 * @param costume */
	public void addCostume(Costume costume) {
		costumes.add(costume);
		sortCostume();
	}
	
	/** {@link EventListener}�� �ϳ� �߰��մϴ�. {@link MouseListener}
	 * , {@link MouseMotionListener}���� �� �ż���� �߰��� �� �ֽ��ϴ�.
	 * 
	 * @param eventListener */
	public void addEventListener(EventListener eventListener) {
		eventListeners.add(eventListener);
	}
	
	/** �� Child�� �־��� Child�� ��ġ������ ���� Ȯ���մϴ�. �밳 �� Child�� Ư���� ��
	 * ��ǥ�� �־��� Child�� ��ġ������ Ȯ���մϴ�.
	 * 
	 * @param child
	 *        �� Child�� ��ġ���� Ȯ�� �� Child�Դϴ�.
	 * @return ���� �� Child�� ��ģ�ٰ� �ǴܵǸ� true�� ��ȯ�ϰ�, �ƴϸ� false��
	 *         ��ȯ�մϴ�. */
	abstract public boolean contains(Child child);
	
	/** double �ΰ��� �־��� ��ǥ�� �� Child�� ��ġ���� Ȯ���մϴ�. ���� Child��
	 * �׷����� ���� ���� �־��� ��ǥ�� �ִٸ� ������ ��ȯ�� ���Դϴ�.
	 * 
	 * @param x
	 *        x��ǥ�Դϴ�.
	 * @param y
	 *        y��ǥ�Դϴ�.
	 * @return �־��� ��ǥ�� �� Child�� ��ģ�ٸ�, true�� ��ȯ�ϰ� �ƴ϶�� false��
	 *         ��ȯ�մϴ�. */
	public boolean contains(double x, double y) {
		return contains(new Point((int) x, (int) y));
	}
	
	/** int �ΰ��� �־��� ��ǥ�� �� Child�� ��ġ���� Ȯ���մϴ�. ���� Child�� �׷�����
	 * ���� ���� �־��� ��ǥ�� �ִٸ� ������ ��ȯ�� ���Դϴ�.
	 * 
	 * @param x
	 *        x��ǥ�Դϴ�.
	 * @param y
	 *        y��ǥ�Դϴ�.
	 * @return �־��� ��ǥ�� �� Child�� ��ģ�ٸ�, true�� ��ȯ�ϰ� �ƴ϶�� false��
	 *         ��ȯ�մϴ�. */
	public boolean contains(int x, int y) {
		return contains(new Point(x, y));
	}
	
	/** {@link Point}�� �־��� ��ǥ�� �� Child�� ��ġ���� Ȯ���մϴ�. ���� Child��
	 * �׷����� ���� ���� �־��� ��ǥ�� �ִٸ� ������ ��ȯ�� ���Դϴ�.
	 * 
	 * @param point
	 *        �� Child�� ��ġ���� Ȯ���� ��ǥ�� Point�Դϴ�.
	 * @return �־��� ��ǥ�� �� Child�� ��ģ�ٸ�, true�� ��ȯ�ϰ� �ƴ϶�� false��
	 *         ��ȯ�մϴ�. */
	abstract public boolean contains(Point point);
	
	/** Graphics2D�� �� Child�� �׸��ϴ�.
	 * 
	 * @param graphics
	 *        �� Child�� �׷��� Graphic2D��ü�Դϴ�. */
	public abstract void draw(Graphics2D graphics);
	
	/** �� Child�� �����ִ� Party�� ��ȯ�մϴ�.
	 * 
	 * @return �� Child�� �����ִ� Party�Դϴ�.
	 * @see Party */
	public Party getBelong() {
		return belong;
	}
	
	/** �� Child�� ������ �ִ� {@link Costume}���� ��ȯ�մϴ�. ���� ��
	 * {@link Costume}�� �� Child�� ȭ��� �׷����� ������ ��Ĩ�ϴ�.
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

	/** �� Child���� �־��� {@link Costume}�� �����մϴ�. ���� ��
	 * {@link Costume}�� �� Child �� ������ ��ĥ �� �����ϴ�.
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
	
	/** �־��� {@link Party}�ȿ� �� Child�� �ֽ��ϴ�.
	 * {@link Party#draw(Graphics2D)}�� ����ɶ�, ������ �ִ� ���
	 * {@link Costume}���� {@link Costume#dressUp(Graphics2D)}
	 * �� ��� ����� ��, Child�� draw()�� ����˴ϴ�.
	 * 
	 * @param belong
	 *        �� Child�� ���Ե� Container�Դϴ�. */
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