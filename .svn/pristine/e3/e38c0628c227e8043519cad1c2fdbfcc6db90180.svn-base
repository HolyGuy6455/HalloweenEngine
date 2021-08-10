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

/** 다른 Child들을 포함할 수 있습니다.
 * 
 * @author 신성민 */
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
	
	/** {@link Child}를 추가합니다. 추가된 {@link Child}의
	 * {@link Child#belong}은 이 Container가 됩니다.
	 * 
	 * @param child
	 *        추가할 Child입니다. */
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
	
	/** 특성을 가진 {@link Child}를 추가합니다. option객체는 이
	 * {@link Child}가 어떠한 특성을 가지고 있는지 서술합니다. option에 들어갈 객체는
	 * LayoutWitch의 종류에 따라 다릅니다
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
	
	/** 좌표가 겹치는지 확인합니다. 만약 포함되어있는 Child중 범위 내에 해당좌표가 포함되어있다면
	 * 참값을 반환할 것입니다.
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
	
	/** Child들을 그립니다. 주어진 Graphics에 포함되어있는 Child들을 차례대로 그립니다.
	 * 
	 * @see Child#draw(Graphics2D) */
	@Override
	public void draw(Graphics2D graphics) {
		synchronized (members) {
			for (int i = 0; i < members.size(); i++) {
				Child child = (Child) members.get(i);
				if (!child.isVisible())
					continue;
				// 코스튬 dressUp
				Iterator<Costume> iterator2 = child.getCostumes().iterator();
				while (iterator2.hasNext())
					((Costume) iterator2.next()).dressUp(graphics);
				// Child.draw(Graphics);
				child.draw(graphics);
				// 코스튬 unDress
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

	/** 마우스 이벤트를 받습니다. 자신뿐만 아니라 포함하고 있는 Child에게도 마우스 이벤트를
	 * 전달합니다. */
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
	
	/** Child를 제외시킵니다. 제외된 Child의 belong은 null이 됩니다.
	 * 
	 * @param child
	 *        제외시킬 Child입니다. */
	public void remove(Child child) {
		if (members.contains(child)) {
			child.belong = null;
			members.remove(child);
			witch.removeChild(child);
		}
	}
	
	/** 모든 Child를 제외시킵니다. */
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
	
	/** 현재 가지고 있는 child들의 갯수를 반환합니다.
	 * 
	 * @return 가지고 있는 Child들의 갯수입니다. */
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
