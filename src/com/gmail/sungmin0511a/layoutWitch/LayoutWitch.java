package com.gmail.sungmin0511a.layoutWitch;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.Map;

import com.gmail.sungmin0511a.drawAbles.Child;
import com.gmail.sungmin0511a.drawAbles.Party;

/** {@link com.gmail.sungmin0511a.drawAbles.Child Child}���� ��ġ��
 * �����ϴ� LayoutWitch���� ����Ŭ�����Դϴ�.
 * {@link com.gmail.sungmin0511a.drawAbles.Party Stage}���� ���
 * Child���� �� Stage�� LayoutWitch�� ���� ������
 * {@link com.gmail.sungmin0511a.drawAbles.Position
 * Position}���� �ϴ��Ϸ� �Ҵ�Ǹ�, LayoutWitch�� �� Position���� ��ġ��
 * �����մϴ�. ���������� ������ ���ϴ� LayoutWitch�� �ֽ��ϴ�.
 * 
 * @author �ż��� */
public abstract class LayoutWitch<E extends LayoutPosition> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8449206750174247720L;
	/** �� LayoutWitch�� ����ϴ� Stage�� �����մϴ�. */
	protected Party party;
	/** Position���� �����ϴ� Map�Դϴ�. key���� �ش� Position�� ����ϴ�
	 * Child�Դϴ�. */
	protected Map<Child, E> positions;
	
	/** LayoutWitch�� �����ϴ� �⺻�������Դϴ�. */
	public LayoutWitch() {
		positions = new Hashtable<Child, E>();
		party = null;
	}
	
	/** �����ϴ� Child�� �߰��մϴ�.
	 * 
	 * @param child */
	public abstract void addChild(Child child);
	
	/** �����ϴ� Child�� �߰��մϴ�.
	 * 
	 * @param child
	 * @param Option */
	public abstract void addChild(Child child, Object option);
	
	/** �־����� Child�� �����ϴ� Position�� ��ȯ�մϴ�.
	 * 
	 * @param child
	 * @return �ش� child�� �����ϴ� Position�Դϴ�. */
	public E getPosition(Child child) {
		return positions.get(child);
	}

	/** �� Child���� ��ġ�� �����մϴ�. */
	public void locate() {
		try {
			sortChilds();
			synchronized (positions) {
				Object[] eArray = positions.values().toArray();
				for (Object e : eArray) {
					if (e instanceof LayoutPosition)
						((LayoutPosition) e).post();
				}
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}
	
	/** �־����� Child�� ������󿡼� �����մϴ�
	 * 
	 * @param child */
	public void removeChild(Child child) {
		//		if (positions.containsKey(child)) {
		//			if (child.getPosition() != null) {
		child.getPosition().forgot();
		//			}
		child.setPosition(null);
		positions.remove(child);
		//		}
	}
	
	public void setParty(Party party) {
		this.party = party;
	}
	
	protected abstract void sortChilds();
}