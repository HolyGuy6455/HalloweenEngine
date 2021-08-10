/**
 * 
 */
package com.gmail.sungmin0511a.costume;

import java.awt.Graphics2D;

import com.gmail.sungmin0511a.drawAbles.Child;

/** Child들을 꾸며주는 역할을 하는 Costume을 정의합니다. <p> 출력되기 전
 * {@link Graphics2D}에 별다른 처리를 하지 않는다면, {@link Child} 들은 자신이
 * 설정된
 * 
 * @author 신성민 */
public interface Costume {
	/** Child가 그려지기 전에 하는 작업을 정의합니다. <p> 만약 이 매서드를 override해서
	 * {@link Graphics2D}의 설정을 변경한다면,
	 * {@link #unDress(Graphics2D)}매서드에 정반대로 기능하게 기술해야 합니다.
	 * 이 매서드로 인해 변경된 {@link Graphics2D}의 설정이 이후 다른 Child에게
	 * 영향을 끼치지 않게 하기 위함입니다.
	 * 
	 * @param graphics
	 *        이 Costume이 포함된 {@link Child}가 출력될
	 *        {@link Graphics2D}입니다. 이 매서드의 역할은, 이
	 *        {@link Graphics2D}의 기능 및 설정을 변경하는것입니다. */
	public abstract void dressUp(Graphics2D graphics);
	
	/** 이 Costume의 중요도를 반환합니다.
	 * 
	 * @return */
	public int getPriority();
	
	/** 이 Costume의 중요도를 설정합니다. 값이 작을수록 먼저 실행됩니다.
	 * 
	 * @param priority */
	public void setPriority(int priority);

	/** Child가 그려진 후 원래대로 정리하는 작업을 정의합니다. <p> 만약 이 매서드를
	 * override한다면, {@link #dressUp(Graphics2D)}과 정반대로 기능하게
	 * 해야 합니다. {@link #dressUp(Graphics2D)}으로 변경해놓은
	 * {@link Graphics2D} 의 설정을 원래대로 되돌려 놓는 역할을 해야 합니다.
	 * 
	 * @param graphics
	 *        이 Costume이 포함된 {@link Child}가 출력될
	 *        {@link Graphics2D}입니다. 이 매서드의 역할은, 이
	 *        {@link Graphics2D}의 기능 및 설정을 원래대로 복구하는것입니다. */
	public abstract void unDress(Graphics2D graphics);
}
