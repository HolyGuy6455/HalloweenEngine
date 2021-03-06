package com.gmail.sungmin0511a.layoutWitch;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.Map;

import com.gmail.sungmin0511a.drawAbles.Child;
import com.gmail.sungmin0511a.drawAbles.Party;

/** {@link com.gmail.sungmin0511a.drawAbles.Child Child}들의 위치를
 * 조정하는 LayoutWitch들의 상위클래스입니다.
 * {@link com.gmail.sungmin0511a.drawAbles.Party Stage}안의 모든
 * Child들은 그 Stage의 LayoutWitch에 의해 고유의
 * {@link com.gmail.sungmin0511a.drawAbles.Position
 * Position}들이 일대일로 할당되며, LayoutWitch는 이 Position들의 위치를
 * 변경합니다. 물리엔진의 역할을 겸하는 LayoutWitch도 있습니다.
 * 
 * @author 신성민 */
public abstract class LayoutWitch<E extends LayoutPosition> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8449206750174247720L;
	/** 이 LayoutWitch가 담당하는 Stage를 설정합니다. */
	protected Party party;
	/** Position들을 저장하는 Map입니다. key값은 해당 Position이 담당하는
	 * Child입니다. */
	protected Map<Child, E> positions;
	
	/** LayoutWitch를 생성하는 기본생성자입니다. */
	public LayoutWitch() {
		positions = new Hashtable<Child, E>();
		party = null;
	}
	
	/** 관리하는 Child를 추가합니다.
	 * 
	 * @param child */
	public abstract void addChild(Child child);
	
	/** 관리하는 Child를 추가합니다.
	 * 
	 * @param child
	 * @param Option */
	public abstract void addChild(Child child, Object option);
	
	/** 주어지는 Child를 관리하는 Position을 반환합니다.
	 * 
	 * @param child
	 * @return 해당 child를 관리하는 Position입니다. */
	public E getPosition(Child child) {
		return positions.get(child);
	}

	/** 각 Child들의 위치를 조정합니다. */
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
	
	/** 주어지는 Child를 관리대상에서 제외합니다
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
