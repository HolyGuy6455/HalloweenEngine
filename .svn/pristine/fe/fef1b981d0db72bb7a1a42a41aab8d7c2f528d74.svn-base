/**
 * 
 */
package com.gmail.sungmin0511a.costume;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Graphics2D;

import com.gmail.sungmin0511a.drawAbles.Child;
import com.gmail.sungmin0511a.drawAbles.Party;

/** {@link Child}가 투명하게 그려지게 합니다. <p> Opacity는 0에서 1사이의 실수값을
 * 가지며, 자신이 속한 {@link Child}가 완전 투명하게 그려질 것인지, 혹은 완전 불투명하게
 * 그려질 것인지, 아니면 반쯤 투명하게 그려질 것인지 결정합니다. <p> 이 {@link Child}가
 * 포함되어있는 {@link Party}가 이미 반쯤 투명해져있다면 중첩되어 투명하게 할 것인지, 아니면
 * 기존의 투명도를 무시하고 독립적인 투명도를 가질것인지 설정할 수 있습니다.
 * 
 * @author 신성민 */
public class Opacity implements Costume {
	protected double transparency;
	int priority;
	private Composite beforeComposite;
	private boolean beingPiled;
	
	/** 아무런 인자없이 생성할경우, 투명도는 1로(완전불투명으로), 중첩여부는 허락한것으로 설정됩니다. */
	public Opacity() {
		this(1.0, true);
	}
	
	/** 투명도의 중첩여부를 생략한 생성자 입니다. 이 경우, 중첩을 허락한 것으로 설정됩니다.
	 * 
	 * @param transparency
	 *        투명도를 나타내는 지표입니다. */
	public Opacity(double transparency) {
		this(transparency, true);
	}

	/** 기본생성자입니다. transparency는 0과 1사이의 값으로 설정해야하며, 0으로 설정하면
	 * 완전한 투명, 1로 설정하면 완전 불투명상태가 됩니다. beingPiled를 true로 하면
	 * {@link Graphics2D}가 이전에 가지고 있던 투명도와 중첩되어 투명해지며,
	 * false일 경우 이전의 투명도는 무시합니다.
	 * 
	 * @param transparency
	 *        투명도를 나타내는 지표입니다.
	 * @param beingPiled
	 *        투명도의 중첩여부를 결정합니다. */
	public Opacity(double transparency, boolean beingPiled) {
		super();
		this.transparency = transparency;
		this.beingPiled = beingPiled;
	}
	
	/** {@link Graphics2D}가 본래 가지고 있던 투명도를 기억한 뒤에,
	 * {@link Child}가 이 Opacity가 가진 투명도의 정도로 투명하게 출력되도록 합니다. */
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
	
	/** 이 Opacity가 가지고 있는 투명도를 반환합니다.
	 * 
	 * @return 0~1사이의 실수값입니다. */
	public double getTransparency() {
		return transparency;
	}
	
	@Override
	public void setPriority(int priority) {
		this.priority = priority;
	}

	/** 이 Opacity가 어느정도의 투명도를 가질것인지 설정합니다.
	 * 
	 * @param transparency
	 *        주어진 0~1의 실수값으로 투명도가 결정됩니다. */
	public void setTransparency(double transparency) {
		this.transparency = transparency;
	}
	
	/** 본래 {@link Graphics2D}가 가지고 있던 투명도의 값으로 복귀시킵니다. */
	@Override
	public void unDress(Graphics2D graphics) {
		if (transparency != 1.0)
			graphics.setComposite(beforeComposite);
	}
}
