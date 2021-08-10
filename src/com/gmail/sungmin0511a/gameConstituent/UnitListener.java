package com.gmail.sungmin0511a.gameConstituent;

import java.util.EventListener;

/** {@link Unit}에 어떤 이벤트가 발생했을때 그 상황을 받습니다.
 * 
 * @author 신성민 */
public interface UnitListener extends EventListener {
	/** {@link Condition}이 제거되어 효과가 사라질때 호출됩니다.
	 * 
	 * @param c */
	void conditionExpire(Condition c);
	
	/** {@link Condition}이 추가되어 효과가 발생할때 호출됩니다.
	 * 
	 * @param c */
	void conditionOccur(Condition c);
	
	/** 유닛이 죽을때 호출됩니다. */
	void die();
	
	/** {@link Status}가 가득 찼을때 호출됩니다.
	 * 
	 * @param s */
	void statusCharged(Status s);
	
	/** {@link Status}가 하나도 남지 않았을때 호출됩니다.
	 * 
	 * @param s */
	void statusEmpty(Status s);
}
