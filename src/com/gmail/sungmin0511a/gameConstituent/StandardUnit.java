/**
 * 
 */
package com.gmail.sungmin0511a.gameConstituent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.gmail.sungmin0511a.drawAbles.Child;

/** @author ½Å¼º¹Î */
public class StandardUnit extends Unit {
	Status attack;
	Status HP;
	UnitListener unitListener;

	/** @param body */
	public StandardUnit(Child body) {
		super(body);
		HP = new Status("HP", 500, 500);
		attack = new Status("attack", 10, 500);
		addStatus(HP);
		addCondition(new ConditionWithTimer("Hill", 100, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				HP.add(10);
			}
		}));
		unitListener = new UnitListener() {
			@Override
			public void conditionExpire(Condition c) {
			}
			
			@Override
			public void conditionOccur(Condition c) {
			}
			
			@Override
			public void die() {
			}
			
			@Override
			public void statusCharged(Status s) {
			}
			
			@Override
			public void statusEmpty(Status s) {
				if (s.equals(HP))
					die();
			}
		};
	}
	
	public void attact(StandardUnit enemy) {
		enemy.HP.add(-attack.getValue());
	}
}
