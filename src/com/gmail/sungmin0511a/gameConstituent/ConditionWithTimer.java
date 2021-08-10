/**
 * 
 */
package com.gmail.sungmin0511a.gameConstituent;

import java.awt.event.ActionListener;

import javax.swing.Timer;

/** �ð��� ������ ���� ȿ���� �ߵ��ϴ� Condition�� �����մϴ�. <p> ���ΰ��� ���� �ɷ� ����
 * �ð����� �ֱ������� ü���� �������ų�, �����ð��� ���� �� �˾Ƽ� ������� ȿ���� �ִ� �����̻���
 * �����Ҷ� �� Ŭ������ �����ϰ� ���� ���Դϴ�.
 * 
 * @author �ż��� */
public class ConditionWithTimer extends Condition {
	ActionListener actionListener;
	Timer timer;
	
	/** @param name */
	public ConditionWithTimer(String name, int delay, ActionListener actionListener) {
		super(name);
		timer = new Timer(delay, actionListener);
	}
	
	/** @return the actionListener */
	public ActionListener getActionListener() {
		return actionListener;
	}
	
	/** @param actionListener
	 *        the actionListener to set */
	public void setActionListener(ActionListener actionListener) {
		timer.removeActionListener(this.actionListener);
		timer.addActionListener(actionListener);
		this.actionListener = actionListener;
	}
	
	/** @param delay */
	public void setDelay(int delay) {
		timer.setDelay(delay);
	}
	
	/** @see com.gmail.sungmin0511a.gameConstituent.Condition#expire() */
	@Override
	protected void expire() {
		super.expire();
		timer.stop();
	}
	
	/** @see com.gmail.sungmin0511a.gameConstituent.Condition#occur() */
	@Override
	protected void occur() {
		super.occur();
		timer.start();
	}
}
