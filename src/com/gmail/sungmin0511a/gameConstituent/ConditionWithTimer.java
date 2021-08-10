/**
 * 
 */
package com.gmail.sungmin0511a.gameConstituent;

import java.awt.event.ActionListener;

import javax.swing.Timer;

/** 시간이 지남에 따라 효과가 발동하는 Condition을 정의합니다. <p> 주인공이 독에 걸려 일정
 * 시간마다 주기적으로 체력이 떨어지거나, 일정시간이 지난 뒤 알아서 사라지는 효과가 있는 상태이상을
 * 정의할때 이 클래스는 유용하게 쓰일 것입니다.
 * 
 * @author 신성민 */
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
