/**
 * 
 */
package com.gmail.sungmin0511a.layoutWitch;

import java.util.ArrayList;
import java.util.Iterator;

import com.gmail.sungmin0511a.costume.Location;
import com.gmail.sungmin0511a.drawAbles.Child;
import com.gmail.sungmin0511a.layoutWitch.FallingWitch.FallingPosition;

/** @author ½Å¼º¹Î */
public class FallingWitch extends LayoutWitch<FallingPosition> {
	/** @author ½Å¼º¹Î */
	public class FallingPosition extends LayoutPosition {
		/**
		 * 
		 */
		private static final long serialVersionUID = 5332069775528123593L;
		Location childLocation;
		boolean moving;
		double speedX;
		double speedY;

		/** @param child */
		public FallingPosition(Child child) {
			super(child);
			childLocation = new Location();
			child.addCostume(childLocation);
			moving = true;
		}
		
		public double getX() {
			return childLocation.getX();
		}
		
		public double getY() {
			return childLocation.getY();
		}

		/** @return the moving */
		public boolean isMoving() {
			return moving;
		}
		
		public void move(int x, int y) {
			childLocation.translate(x, y);
		}
		
		public void push(int x, int y) {
			speedX += x;
			speedY += y;
		}

		public void setLocation(int x, int y) {
			childLocation.setX(x);
			childLocation.setY(y);
		}
		
		/** @param moving
		 *        the moving to set */
		public void setMoving(boolean moving) {
			this.moving = moving;
			if (moving) {
				if (!positions.containsKey(getChild())) {
					positions.put(getChild(), this);
				}
				fixedPositions.remove(this);
			} else {
				if (!fixedPositions.contains(this)) {
					fixedPositions.add(this);
				}
				positions.remove(getChild());
			}
		}
		
		@Override
		protected void post() {
			speedX += gravity * (Math.sin(direction));
			speedY += gravity * (Math.cos(direction));
			childLocation.translate(speedX, speedY);
		}
	}
	
	enum FallingType {
		Falling, Fixed
	}
	
	final static FallingType FALLING = FallingType.Falling;
	final static FallingType FIXED = FallingType.Fixed;
	/**
	 * 
	 */
	private static final long serialVersionUID = -5970640410811032653L;
	double direction;
	ArrayList<FallingPosition> fixedPositions;

	double gravity;
	
	/**
	 * 
	 */
	public FallingWitch() {
		gravity = 1;
		direction = 0;
		fixedPositions = new ArrayList<FallingWitch.FallingPosition>();
	}
	
	@Override
	public void addChild(Child child) {
		positions.put(child, new FallingPosition(child));
	}
	
	@Override
	public void addChild(Child child, Object option) {
		// TODO LayoutWitch<FallingPosition>.addChild()
	}

	/* (non-Javadoc)
	 * @see com.gmail.sungmin0511a.layoutWitch.LayoutWitch#locate()
	 */
	@Override
	public void locate() {
		super.locate();
		Iterator<FallingPosition> iterator = positions.values().iterator();
		while (iterator.hasNext()) {
			FallingPosition fallingPosition = (FallingPosition) iterator.next();
			Iterator<FallingPosition> iterator2 = fixedPositions.iterator();
			Location L = fallingPosition.childLocation;
			while (iterator2.hasNext()) {
				FallingPosition fixedPosition = (FallingPosition) iterator2.next();
				Location L2 = fixedPosition.childLocation;
				if (fixedPosition.getChild().contains(L.getX() - L2.getX(), L.getY() - L2.getY())) {
					while (fixedPosition.getChild().contains(L.getX() - L2.getX(),
							L.getY() - L2.getY())) {
						fallingPosition.move(0, -1);
					}
					iterator2 = fixedPositions.iterator();
					fallingPosition.speedY = -1;
				}
			}
		}
	}

	@Override
	protected void sortChilds() {
	}
}
