package com.gmail.sungmin0511a.gameConstituent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.Timer;

import com.gmail.sungmin0511a.costume.Opacity;
import com.gmail.sungmin0511a.drawAbles.Child;

/** 게임에 등장하는 유닛을 정의하는 클래스입니다. <p> 이 유닛은 다양한 {@link Status}와
 * {@link Condition}을 가질 수 있습니다.
 * 
 * @author 신성민
 * @version 0.39 */
public class Unit {
	protected Child body;
	HashMap<String, Condition> conditionsMap;
	HashMap<String, Status> statusMap;
	UnitListener unitListener;
	
	public Unit(Child body) {
		this.body = body;
		statusMap = new HashMap<String, Status>();
		conditionsMap = new HashMap<String, Condition>();
		unitListener = new UnitListener() {
			@Override
			public void conditionExpire(Condition s) {
			}
			
			@Override
			public void conditionOccur(Condition s) {
			}
			
			@Override
			public void die() {
			}
			
			@Override
			public void statusCharged(Status e) {
			}
			
			@Override
			public void statusEmpty(Status e) {
			}
		};
	}
	
	/** 새로운 {@link Condition}을 추가하는 매서드입니다. 해당
	 * {@link Condition}의 {@link Condition#occur()}를 실행할
	 * 것입니다.
	 * 
	 * @see Condition#occur()
	 * @param condition */
	public void addCondition(Condition condition) {
		this.conditionsMap.put(condition.name, condition);
		condition.unit = this;
		condition.occur();
	}

	/** 새로운 {@link Status}를 추가하는 매서드 입니다.
	 * 
	 * @param status */
	public void addStatus(Status status) {
		this.statusMap.put(status.getName(), status);
		status.unit = this;
	}
	
	/** 케릭터가 죽을때 서서히 사라지는 효과를 보여줍니다. */
	public void die() {
		unitListener.die();
		class DieEffect {
			Opacity opacity;
			Timer timer;
			
			public DieEffect() {
				opacity = new Opacity(1.0);
				body.addCostume(opacity);
				timer = new Timer(10, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						opacity.setTransparency(opacity.getTransparency() - 0.05);
						if (opacity.getTransparency() <= 0) {
							timer.stop();
						}
					}
				});
				timer.start();
			}
		}
		new DieEffect();
	}
	
	/** @return the body */
	public Child getBody() {
		return body;
	}
	
	/** @param name
	 * @return */
	public Condition getCondition(String name) {
		return conditionsMap.get(name);
	}
	
	/** @param name
	 * @return */
	public Status getStatus(String name) {
		return statusMap.get(name);
	}
	
	/** @return the unitListener */
	public UnitListener getUnitListener() {
		return unitListener;
	}
	
	/** @param name
	 * @return */
	public boolean hasCondition(String name) {
		return conditionsMap.containsKey(name);
	}
	
	/** @param name
	 * @return */
	public boolean removeCondition(String name) {
		if (hasCondition(name)) {
			getCondition(name).expire();
			conditionsMap.remove(name);
			return true;
		} else
			return false;
	}

	/** @param unitListener
	 *        the unitListener to set */
	public void setUnitListener(UnitListener unitListener) {
		this.unitListener = unitListener;
	}
}