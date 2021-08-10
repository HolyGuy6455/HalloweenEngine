/**
 * 
 */
package com.gmail.sungmin0511a.costume;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Graphics2D;

import com.gmail.sungmin0511a.drawAbles.Child;
import com.gmail.sungmin0511a.drawAbles.Party;

/** {@link Child}�� �����ϰ� �׷����� �մϴ�. <p> Opacity�� 0���� 1������ �Ǽ�����
 * ������, �ڽ��� ���� {@link Child}�� ���� �����ϰ� �׷��� ������, Ȥ�� ���� �������ϰ�
 * �׷��� ������, �ƴϸ� ���� �����ϰ� �׷��� ������ �����մϴ�. <p> �� {@link Child}��
 * ���ԵǾ��ִ� {@link Party}�� �̹� ���� ���������ִٸ� ��ø�Ǿ� �����ϰ� �� ������, �ƴϸ�
 * ������ �������� �����ϰ� �������� �������� ���������� ������ �� �ֽ��ϴ�.
 * 
 * @author �ż��� */
public class Opacity implements Costume {
	protected double transparency;
	int priority;
	private Composite beforeComposite;
	private boolean beingPiled;
	
	/** �ƹ��� ���ھ��� �����Ұ��, �������� 1��(��������������), ��ø���δ� ����Ѱ����� �����˴ϴ�. */
	public Opacity() {
		this(1.0, true);
	}
	
	/** �������� ��ø���θ� ������ ������ �Դϴ�. �� ���, ��ø�� ����� ������ �����˴ϴ�.
	 * 
	 * @param transparency
	 *        �������� ��Ÿ���� ��ǥ�Դϴ�. */
	public Opacity(double transparency) {
		this(transparency, true);
	}

	/** �⺻�������Դϴ�. transparency�� 0�� 1������ ������ �����ؾ��ϸ�, 0���� �����ϸ�
	 * ������ ����, 1�� �����ϸ� ���� ���������°� �˴ϴ�. beingPiled�� true�� �ϸ�
	 * {@link Graphics2D}�� ������ ������ �ִ� �������� ��ø�Ǿ� ����������,
	 * false�� ��� ������ �������� �����մϴ�.
	 * 
	 * @param transparency
	 *        �������� ��Ÿ���� ��ǥ�Դϴ�.
	 * @param beingPiled
	 *        �������� ��ø���θ� �����մϴ�. */
	public Opacity(double transparency, boolean beingPiled) {
		super();
		this.transparency = transparency;
		this.beingPiled = beingPiled;
	}
	
	/** {@link Graphics2D}�� ���� ������ �ִ� �������� ����� �ڿ�,
	 * {@link Child}�� �� Opacity�� ���� �������� ������ �����ϰ� ��µǵ��� �մϴ�. */
	@Override
	public void dressUp(Graphics2D graphics) {
		if (transparency != 1.0) {
			beforeComposite = graphics.getComposite();
			if (beingPiled)
				graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
						(float) (transparency * ((AlphaComposite) beforeComposite).getAlpha())));
			else
				graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
						(float) transparency));
		}
	}
	
	@Override
	public int getPriority() {
		return priority;
	}
	
	/** �� Opacity�� ������ �ִ� �������� ��ȯ�մϴ�.
	 * 
	 * @return 0~1������ �Ǽ����Դϴ�. */
	public double getTransparency() {
		return transparency;
	}
	
	@Override
	public void setPriority(int priority) {
		this.priority = priority;
	}

	/** �� Opacity�� ��������� �������� ���������� �����մϴ�.
	 * 
	 * @param transparency
	 *        �־��� 0~1�� �Ǽ������� �������� �����˴ϴ�. */
	public void setTransparency(double transparency) {
		this.transparency = transparency;
	}
	
	/** ���� {@link Graphics2D}�� ������ �ִ� �������� ������ ���ͽ�ŵ�ϴ�. */
	@Override
	public void unDress(Graphics2D graphics) {
		if (transparency != 1.0)
			graphics.setComposite(beforeComposite);
	}
}