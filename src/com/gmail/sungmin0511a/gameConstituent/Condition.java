package com.gmail.sungmin0511a.gameConstituent;

/** {@link Unit}이 현재 어떤 상태에 빠져있는가를 나타내는 클래스 입니다. <p> 게임에 등장하는
 * 주인공은 축복을 받아 공격력이 오르거나, 저주에 걸려 이동속도가 느려지거나, 치명적인 독에 중독되어
 * 지속적으로 체력이 떨어지기도 합니다. 이러한 (좋은 방향과 나쁜방향 두 경우 모두의)상태이상을 나타내는
 * 클래스입니다.
 * 
 * @author 신성민
 * @version 0.39 */
public class Condition {
	protected String name;
	Unit unit;
	
	/** @param name */
	public Condition(String name) {
		super();
		this.name = name;
	}
	
	/** 이 Condition이 어떤 {@link Unit}에 속해있는지 반환합니다
	 * 
	 * @return the unit */
	public Unit getUnit() {
		return unit;
	}
	
	/** 상태이상이 해제될때 어떤 효과를 발휘하는지 정의하는 매서드 입니다. {@link Unit}의
	 * {@link Unit#removeCondition(String)}을 호출할때 이 매서드가
	 * 호출됩니다. 어떤 효과가 해제되어야할때는 이 매서드를 override해 새로운 효과를 적어 넣을
	 * 수 있을 겁니다. 만약 override하지 않는다면, 아무런 기능도 하지 않을 것입니다. <p>
	 * 하지만, super.expire()를 하지 않아도 된다는 의미는 아닙니다. 이 매서드는 해당유닛
	 * {@link UnitListener}의
	 * {@link UnitListener#conditionExpire(Condition)}를
	 * 호출합니다. */
	protected void expire() {
		unit.unitListener.conditionExpire(this);
	}
	
	/** 상태이상이 발생할때 어떤 효과를 발휘하는지 정의하는 매서드 입니다. 상태이상은 바로 발동되는것이
	 * 아니라 {@link Unit}에 추가될때 작동하기 시작합니다. 어떤 효과가 발동해야할때는 이
	 * 매서드를 override해 새로운 효과를 적어 넣을 수 있을 겁니다. 만약 override하지
	 * 않는다면, 아무런 기능도 하지 않을 것입니다. <p> 하지만, super.occur()를 하지
	 * 않아도 된다는 의미는 아닙니다. 이 매서드는 해당유닛{@link UnitListener}의
	 * {@link UnitListener#conditionOccur(Condition)}를
	 * 호출합니다. */
	protected void occur() {
		unit.unitListener.conditionOccur(this);
	}
}
