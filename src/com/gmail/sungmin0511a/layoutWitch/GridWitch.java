/**
 * 
 */
package com.gmail.sungmin0511a.layoutWitch;

import java.util.ArrayList;
import java.util.Iterator;

import com.gmail.sungmin0511a.costume.Location;
import com.gmail.sungmin0511a.drawAbles.Child;
import com.gmail.sungmin0511a.etc.ValueException;
import com.gmail.sungmin0511a.layoutWitch.GridWitch.GridPosition;

/** @author �ż��� */
public class GridWitch extends LayoutWitch<GridPosition> {
	public class GridPosition extends LayoutPosition {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1077347606367692435L;
		int attractive;
		Location location;
		int width, height;
		int x, y;
		
		/** @param child */
		public GridPosition(Child child) {
			super(child);
			x = 0;
			y = 0;
			width = 1;
			height = 1;
			attractive = 800;
			location = new Location(x * space, y * space);
			child.addCostume(location);
		}
		
		/** @return */
		public int getX() {
			return x;
		}
		
		/** @return */
		public int getY() {
			return y;
		}
		
		/** @param x
		 * @param y
		 * @param width
		 * @param height
		 * @return */
		public boolean overlaped(int x, int y, int width, int height) {
			if (this.x <= x + width - 1 && this.x + this.width - 1 >= x)
				if (this.y <= y + height - 1 && this.y + this.height - 1 >= y)
					return true;
			return false;
		}
		
		/** @param x
		 * @param y */
		public void setPosition(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		/** @param attractive
		 * @throws ValueException */
		public void setSpeed(int attractive) throws ValueException {
			if (attractive <= 1000 && attractive >= 0)
				this.attractive = attractive;
			else
				throw new ValueException(ValueException.ExceptionKey.Between, 0, 1000);
		}
		
		@Override
		protected void post() {
			location.translate((this.x * space - location.getX()) * this.attractive / 1000,
					(this.y * space - location.getY()) * this.attractive / 1000);
		}
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 747210840174897067L;
	boolean overlap;
	int space;
	boolean totalFit;
	
	/**
	 * 
	 */
	public GridWitch() {
		this(false, false, 16);
	}
	
	/** @param overlap
	 * @param totalFit
	 * @param space */
	public GridWitch(boolean overlap, boolean totalFit, int space) {
		super();
		this.overlap = overlap;
		this.totalFit = totalFit;
		this.space = space;
	}
	
	@Override
	public void addChild(Child child) {
		positions.put(child, new GridPosition(child));
	}
	
	@Override
	public void addChild(Child child, Object option) {
		addChild(child);
	}

	/** @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return */
	public GridPosition[] overlapedChilds(int x, int y, int width, int height) {
		Iterator<GridPosition> iterator = positions.values().iterator();
		ArrayList<GridPosition> gridPositions = new ArrayList<GridWitch.GridPosition>();
		while (iterator.hasNext()) {
			GridPosition gridPosition = iterator.next();
			if (gridPosition.overlaped(x, y, width, height))
				gridPositions.add(gridPosition);
		}
		return (GridPosition[]) gridPositions.toArray();
	}
	
	/** @param space */
	public void setSpace(int space) {
		this.space = space;
	}
	
	@Override
	protected void sortChilds() {
		// TODO LayoutWitch<GridPosition>.sortChilds()
	}
}
