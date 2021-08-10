package com.gmail.sungmin0511a.gameConstituent;

/** 한계가 있는 자연수의 정보를 저장할때 쓰입니다.<p>유닛의 HP는 음수가 되거나 과충전 될 수
 * 없습니다. 또한, 공격력도 마찬가지로 0 아래로 내려가거나 어떤 한계치를 넘을 수 없습니다. 사용법과
 * 기능은 단순하지만, 그 활용은 HP나 무기의 내구도, 베터리의 충전량, 공격력, 재산의 표시 등등
 * 무궁무진합니다.
 * 
 * @author 신성민 */
public class Status {
	Unit unit;
	private int level;
	private int limit;
	private String name;
	
	/** @param level
	 * @param limit
	 * @param name
	 * @param unit */
	public Status(String name, int level, int limit) {
		super();
		this.level = level;
		this.limit = limit;
		this.name = name;
	}
	
	/** 이 Status의 값에 주어진 value만큼 더합니다. value가 음수로 들어오면 차감합니다.
	 * 값은 limit을 넘을 수 없으며, 만약 주어진 매개변수와 이 Status의 값을 더해
	 * limit을 넘기면 이 Status가 속해있는 {@link Unit}의
	 * {@link UnitListener#statusCharged(Status)}를 호출합니다.
	 * 마찬가지로 차감해서 음수가 될 수 없으며, 차감한 결과가 0보다 작으면
	 * {@link UnitListener#statusEmpty(Status)}를 호출합니다.
	 * 
	 * @param value */
	public void add(int value) {
		int result = level + value;
		if (result >= limit) {
			level = limit;
			unit.unitListener.statusCharged(this);
		} else if (result <= 0) {
			level = 0;
			unit.unitListener.statusEmpty(this);
		} else
			level = result;
	}
	
	/** @return the limit */
	public int getLimit() {
		return limit;
	}
	
	/** 이 Status의 이름을 반환합니다
	 * 
	 * @return name. 이 Status의 이름입니다 */
	public String getName() {
		return name;
	}
	
	/** @return the level */
	public int getValue() {
		return level;
	}
	
	/** @param limit
	 *        the limit to set */
	public void setLimit(int limit) {
		this.limit = limit;
	}
	
	/** 이 Status의 값이 주어진 value와 같게 됩니다. 값은 limit을 넘을 수 없으며,
	 * 만약 주어진 매개변수가 limit을 넘기면 이 Status가 속해있는 {@link Unit}의
	 * {@link UnitListener#statusCharged(Status)}를 호출합니다.
	 * 마찬가지로 차감해서 음수가 될 수 없으며, 매개변수가 0보다 작으면
	 * {@link UnitListener#statusEmpty(Status)}를 호출합니다.
	 * 
	 * @param value */
	public void setValue(int value) {
		if (value > limit) {
			level = limit;
			unit.unitListener.statusCharged(this);
		} else if (value <= 0) {
			level = 0;
			unit.unitListener.statusEmpty(this);
		} else {
			level = value;
		}
	}
}
