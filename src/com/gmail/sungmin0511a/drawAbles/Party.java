package com.gmail.sungmin0511a.drawAbles;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.*;

import com.gmail.sungmin0511a.costume.Costume;
import com.gmail.sungmin0511a.layoutWitch.GridWitch;
import com.gmail.sungmin0511a.layoutWitch.LayoutPosition;
import com.gmail.sungmin0511a.layoutWitch.LayoutWitch;
import com.gmail.sungmin0511a.major.Halloween;

/** �ٸ� Child���� ������ �� �ֽ��ϴ�.
 * 
 * @author �ż��� */
public class Party extends Child {
	private static final long serialVersionUID = -1553145451371694718L;
	protected List<Child> members;
	protected String name;
	private Child focus;
	private LayoutWitch<?> witch;
	
	/**
	 * 
	 */
	public Party(String name) {
		super();
		this.name = name;
		members = Collections.synchronizedList(new ArrayList<Child>());
		witch = new GridWitch();
	}
	
	/** {@link Child}�� �߰��մϴ�. �߰��� {@link Child}��
	 * {@link Child#belong}�� �� Container�� �˴ϴ�.
	 * 
	 * @param child
	 *        �߰��� Child�Դϴ�. */
	public LayoutPosition add(Child child) {
		if (child == null)
			return null;
		LayoutPosition result = witch.getPosition(child);
		if (result != null)
			return result;
		else {
			if (child.belong != null)
				child.belong.remove(child);
			members.add(child);
			child.belong = this;
			witch.addChild(child);
			result = witch.getPosition(child);
			return result;
		}
	}
	
	/** Ư���� ���� {@link Child}�� �߰��մϴ�. option��ü�� ��
	 * {@link Child}�� ��� Ư���� ������ �ִ��� �����մϴ�. option�� �� ��ü��
	 * LayoutWitch�� ������ ���� �ٸ��ϴ�
	 * 
	 * @param child
	 * @param option
	 * @return */
	public LayoutPosition add(Child child, Object option) {
		if (child == null)
			return null;
		LayoutPosition result = witch.getPosition(child);
		if (result != null)
			return result;
		else {
			if (child.belong != null)
				child.belong.remove(child);
			members.add(child);
			child.belong = this;
			witch.addChild(child, option);
			return witch.getPosition(child);
		}
	}
	
	/** @see Child#contains(Child) */
	@Override
	public boolean contains(Child child) {
		for (Child child2 : members) {
			if (child2 instanceof Child && (child2.contains(child) || child.contains(child2)))
				return true;
		}
		return false;
	}
	
	/** ��ǥ�� ��ġ���� Ȯ���մϴ�. ���� ���ԵǾ��ִ� Child�� ���� ���� �ش���ǥ�� ���ԵǾ��ִٸ�
	 * ������ ��ȯ�� ���Դϴ�.
	 * 
	 * @see Child#contains(Point) */
	@Override
	public boolean contains(Point point) {
		synchronized (members) {
			for (Child Child : members) {
				if (Child instanceof Child && Child.contains(point))
					return true;
			}
		}
		return false;
	}
	
	/** Child���� �׸��ϴ�. �־��� Graphics�� ���ԵǾ��ִ� Child���� ���ʴ�� �׸��ϴ�.
	 * 
	 * @see Child#draw(Graphics2D) */
	@Override
	public void draw(Graphics2D graphics) {
		synchronized (members) {
			for (int i = 0; i < members.size(); i++) {
				Child child = (Child) members.get(i);
				if (!child.isVisible())
					continue;
				// �ڽ�Ƭ dressUp
				Iterator<Costume> iterator2 = child.getCostumes().iterator();
				while (iterator2.hasNext())
					((Costume) iterator2.next()).dressUp(graphics);
				// Child.draw(Graphics);
				child.draw(graphics);
				// �ڽ�Ƭ unDress
				iterator2 = child.getCostumes().descendingIterator();
				while (iterator2.hasNext())
					((Costume) iterator2.next()).unDress(graphics);
			}
		}
	}
	
	/** @param index
	 * @return */
	public Child getContent(int index) {
		return members.get(index);
	}
	
	@Override
	public void requestFocus() {
		super.requestFocus();
		for (Child child : members) {
			if (child != focus) {
				child.loseFocus();
			}
		}
	}

	public List<Child> getMembers() {
		return members;
	}
	
	/** @return */
	public String getName() {
		return name;
	}
	
	/** @return */
	public LayoutWitch<?> getWitch() {
		return witch;
	}
	
	public void locate() {
		if (Halloween.playing())
			witch.locate();
		for (int i = 0; i < members.size(); i++) {
			Child child = (Child) members.get(i);
			if (child instanceof Party)
				((Party) child).locate();
		}
	}
	
	@Override
	public void loseFocus() {
		super.loseFocus();
		for (Child child : members) {
			child.loseFocus();
		}
	}
	
	@Override
	public void processKeyEvent(KeyEvent keyEvent, int keyType) {
		super.processKeyEvent(keyEvent, keyType);
		synchronized (members) {
			for (Child theDrawn : members) {
				theDrawn.processKeyEvent(keyEvent, keyType);
			}
		}
	}

	/** ���콺 �̺�Ʈ�� �޽��ϴ�. �ڽŻӸ� �ƴ϶� �����ϰ� �ִ� Child���Ե� ���콺 �̺�Ʈ��
	 * �����մϴ�. */
	@Override
	public Child processMouseEvent(MouseEvent mouseEvent, int mouseType) {
		// for (Child theDrawn : members) {
		// theDrawn.processMouseEvent(e, mouseType);
		// }
		// return super.processMouseEvent(e, mouseType);
		Child result = super.processMouseEvent(mouseEvent, mouseType);
		synchronized (members) {
			for (Child theDrawn : members) {
				Child c = theDrawn.processMouseEvent(mouseEvent, mouseType);
				if (c != null)
					result = c;
			}
		}
		return result;
	}
	
	/** Child�� ���ܽ�ŵ�ϴ�. ���ܵ� Child�� belong�� null�� �˴ϴ�.
	 * 
	 * @param child
	 *        ���ܽ�ų Child�Դϴ�. */
	public void remove(Child child) {
		if (members.contains(child)) {
			child.belong = null;
			members.remove(child);
			witch.removeChild(child);
		}
	}
	
	/** ��� Child�� ���ܽ�ŵ�ϴ�. */
	public void removeAll() {
		Iterator<Child> iterator = members.iterator();
		while (iterator.hasNext()) {
			Child child = (Child) iterator.next();
			child.belong = null;
			members.remove(child);
		}
	}
	
	/** @param witch */
	public void setWitch(LayoutWitch<?> witch) {
		this.witch = witch;
		witch.setParty(this);
	}
	
	/** ���� ������ �ִ� child���� ������ ��ȯ�մϴ�.
	 * 
	 * @return ������ �ִ� Child���� �����Դϴ�. */
	public int size() {
		return members.size();
	}
	
	@Override
	public String toString() {
		String result = super.toString() + "(members : ";
		Iterator<Child> iterator = members.iterator();
		while (iterator.hasNext()) {
			Child child = (Child) iterator.next();
			result += (Object) child.toString() + ", ";
		}
		return result.substring(0, result.length() - 2) + ")";
	}
	
	LinkedList<Party> ancestor(LinkedList<Party> list) {
		list.add(this);
		if (belong != null)
			return list;
		else {
			return belong.ancestor(list);
		}
	}
	
	void focusedMember(Child child) {
		if (members.contains(child)) {
			this.focus = child;
			requestFocus();
		}
	}
}