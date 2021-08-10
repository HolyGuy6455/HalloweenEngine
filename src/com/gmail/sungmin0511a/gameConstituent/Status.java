package com.gmail.sungmin0511a.gameConstituent;

/** �Ѱ谡 �ִ� �ڿ����� ������ �����Ҷ� ���Դϴ�.<p>������ HP�� ������ �ǰų� ������ �� ��
 * �����ϴ�. ����, ���ݷµ� ���������� 0 �Ʒ��� �������ų� � �Ѱ�ġ�� ���� �� �����ϴ�. ������
 * ����� �ܼ�������, �� Ȱ���� HP�� ������ ������, ���͸��� ������, ���ݷ�, ����� ǥ�� ���
 * ���ù����մϴ�.
 * 
 * @author �ż��� */
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
	
	/** �� Status�� ���� �־��� value��ŭ ���մϴ�. value�� ������ ������ �����մϴ�.
	 * ���� limit�� ���� �� ������, ���� �־��� �Ű������� �� Status�� ���� ����
	 * limit�� �ѱ�� �� Status�� �����ִ� {@link Unit}��
	 * {@link UnitListener#statusCharged(Status)}�� ȣ���մϴ�.
	 * ���������� �����ؼ� ������ �� �� ������, ������ ����� 0���� ������
	 * {@link UnitListener#statusEmpty(Status)}�� ȣ���մϴ�.
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
	
	/** �� Status�� �̸��� ��ȯ�մϴ�
	 * 
	 * @return name. �� Status�� �̸��Դϴ� */
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
	
	/** �� Status�� ���� �־��� value�� ���� �˴ϴ�. ���� limit�� ���� �� ������,
	 * ���� �־��� �Ű������� limit�� �ѱ�� �� Status�� �����ִ� {@link Unit}��
	 * {@link UnitListener#statusCharged(Status)}�� ȣ���մϴ�.
	 * ���������� �����ؼ� ������ �� �� ������, �Ű������� 0���� ������
	 * {@link UnitListener#statusEmpty(Status)}�� ȣ���մϴ�.
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
