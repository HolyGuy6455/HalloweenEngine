package com.gmail.sungmin0511a.layoutWitch;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

public class PositionPair<E extends LayoutPosition> {
	@SuppressWarnings("unchecked")
	public static <E extends LayoutPosition> Set<PositionPair<E>> getPairs(Collection<E> positions) {
		LayoutPosition palette[] = new LayoutPosition[0];
		palette = positions.toArray(palette);
		Set<PositionPair<E>> result = new LinkedHashSet<PositionPair<E>>();
		for (int i = 0; i < palette.length; i++)
			for (int j = i + 1; j < palette.length; j++)
				result.add(new PositionPair<E>((E) palette[i], (E) palette[j]));
		return result;
	}
	
	private E position1, position2;
	
	public PositionPair(E position1, E position2) {
		this.position1 = position1;
		this.position2 = position2;
	}
	
	public E getPosition1() {
		return position1;
	}
	
	public E getPosition2() {
		return position2;
	}
	
	@Override
	public String toString() {
		return super.toString() + " [position1 : " + position1.toString() + ", position2 : "
				+ position2.toString() + "]";
	}
}
