package com.gmail.sungmin0511a.gameConstituent;

/** {@link Unit}�� ���� � ���¿� �����ִ°��� ��Ÿ���� Ŭ���� �Դϴ�. <p> ���ӿ� �����ϴ�
 * ���ΰ��� �ູ�� �޾� ���ݷ��� �����ų�, ���ֿ� �ɷ� �̵��ӵ��� �������ų�, ġ������ ���� �ߵ��Ǿ�
 * ���������� ü���� �������⵵ �մϴ�. �̷��� (���� ����� ���۹��� �� ��� �����)�����̻��� ��Ÿ����
 * Ŭ�����Դϴ�.
 * 
 * @author �ż���
 * @version 0.39 */
public class Condition {
	protected String name;
	Unit unit;
	
	/** @param name */
	public Condition(String name) {
		super();
		this.name = name;
	}
	
	/** �� Condition�� � {@link Unit}�� �����ִ��� ��ȯ�մϴ�
	 * 
	 * @return the unit */
	public Unit getUnit() {
		return unit;
	}
	
	/** �����̻��� �����ɶ� � ȿ���� �����ϴ��� �����ϴ� �ż��� �Դϴ�. {@link Unit}��
	 * {@link Unit#removeCondition(String)}�� ȣ���Ҷ� �� �ż��尡
	 * ȣ��˴ϴ�. � ȿ���� �����Ǿ���Ҷ��� �� �ż��带 override�� ���ο� ȿ���� ���� ����
	 * �� ���� �̴ϴ�. ���� override���� �ʴ´ٸ�, �ƹ��� ��ɵ� ���� ���� ���Դϴ�. <p>
	 * ������, super.expire()�� ���� �ʾƵ� �ȴٴ� �ǹ̴� �ƴմϴ�. �� �ż���� �ش�����
	 * {@link UnitListener}��
	 * {@link UnitListener#conditionExpire(Condition)}��
	 * ȣ���մϴ�. */
	protected void expire() {
		unit.unitListener.conditionExpire(this);
	}
	
	/** �����̻��� �߻��Ҷ� � ȿ���� �����ϴ��� �����ϴ� �ż��� �Դϴ�. �����̻��� �ٷ� �ߵ��Ǵ°���
	 * �ƴ϶� {@link Unit}�� �߰��ɶ� �۵��ϱ� �����մϴ�. � ȿ���� �ߵ��ؾ��Ҷ��� ��
	 * �ż��带 override�� ���ο� ȿ���� ���� ���� �� ���� �̴ϴ�. ���� override����
	 * �ʴ´ٸ�, �ƹ��� ��ɵ� ���� ���� ���Դϴ�. <p> ������, super.occur()�� ����
	 * �ʾƵ� �ȴٴ� �ǹ̴� �ƴմϴ�. �� �ż���� �ش�����{@link UnitListener}��
	 * {@link UnitListener#conditionOccur(Condition)}��
	 * ȣ���մϴ�. */
	protected void occur() {
		unit.unitListener.conditionOccur(this);
	}
}
