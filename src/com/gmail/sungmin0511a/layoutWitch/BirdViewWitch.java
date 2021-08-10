/**
 * 
 */
package com.gmail.sungmin0511a.layoutWitch;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.gmail.sungmin0511a.costume.Location;
import com.gmail.sungmin0511a.costume.Rotation;
import com.gmail.sungmin0511a.costume.Scale;
import com.gmail.sungmin0511a.costume.Skew;
import com.gmail.sungmin0511a.drawAbles.Child;
import com.gmail.sungmin0511a.layoutWitch.BirdViewWitch.BirdViewPosition;

/** @author �ż��� */
public class BirdViewWitch extends LayoutWitch<BirdViewPosition> {
	public class BirdViewFlat extends BirdViewPosition {
		/**
		 * 
		 */
		private static final long serialVersionUID = 4046306306461658984L;
		double rotateDegree;
		Rotation rotation;
		
		// Scale scale;
		public BirdViewFlat(Child child) {
			super(child);
			rotation = new Rotation();
			// scale = new Scale();
			child.addCostume(totalScale);
			child.addCostume(rotation);
		}
		
		/** @return the rotateDegree */
		public double getRotateDegree() {
			return rotateDegree;
		}
		
		/** @param rotateDegree
		 *        the rotateDegree to set */
		public void setRotateDegree(double rotateDegree) {
			this.rotateDegree = rotateDegree;
		}
		
		@Override
		protected void post() {
			rotation.setDegree(-totalRotateDegree - rotateDegree);
			super.post();
		}
	}
	
	/** @author �ż��� */
	public class BirdViewPosition extends LayoutPosition {
		/**
		 * 
		 */
		private static final long serialVersionUID = -5986177422829990190L;
		Location childLocation;		// Child�� ��ġ
		// boolean flat;
		double locationX;			// �� Position�� ������ġ X��ǥ��
		double locationY;			// �� Position�� ������ġ Y��ǥ��
		double locationZ;			// �� Position�� ������ġ Z��ǥ��
		
		public BirdViewPosition(Child child) {
			super(child);
			locationX = 0;
			locationY = 0;
			locationZ = 0;
			childLocation = new Location();
			child.addCostume(childLocation);
			// flat = false;
		}
		
		/** @return the locationX */
		public double getLocationX() {
			return locationX;
		}
		
		/** @return the locationY */
		public double getLocationY() {
			return locationY;
		}
		
		public void setLocation(double x, double y) {
			locationX = x;
			locationY = y;
		}
		
		public void setLocation(double x, double y, double z) {
			locationX = x;
			locationY = y;
			locationZ = z;
		}
		
		@Override
		protected void post() {
			childLocation.setX((Math.cos(totalRotateDegree) * (locationX - totalLocationX) + Math
					.sin(totalRotateDegree) * (locationY - totalLocationY))
								* scaleX);
			childLocation.setY(-((Math.sin(totalRotateDegree) * (locationX - totalLocationX) - Math
					.cos(totalRotateDegree) * (locationY - totalLocationY)) * scaleY)
								* Math.cos(lookDegree)
								- (locationZ - totalLocationZ)
								* scaleZ
								* Math.sin(lookDegree));
		}
	}
	
	public class BirdViewWall extends BirdViewPosition {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1821712082017642349L;
		double rotateDegree;
		Scale rotateScale;
		Skew rotateSkew;
		
		public BirdViewWall(Child child) {
			super(child);
			rotateDegree = 0;
			rotateSkew = new Skew();
			rotateScale = new Scale();
			getChild().addCostume(rotateSkew);
			getChild().addCostume(rotateScale);
		}
		
		/** @return the rotateDegree */
		public double getRotateDegree() {
			return rotateDegree;
		}
		
		/** @param rotateDegree
		 *        the rotateDegree to set */
		public void setRotateDegree(double rotateDegree) {
			this.rotateDegree = rotateDegree;
		}
		
		/* (non-Javadoc)
		 * @see com.gmail.sungmin0511a.layoutWitch.BirdViewWitch.BirdViewPosition#post()
		 */
		@Override
		protected void post() {
			super.post();
			rotateSkew.setY(Math.tan(-totalRotateDegree + rotateDegree) * Math.cos(lookDegree));
			rotateScale.setWidth(Math.cos(totalRotateDegree - rotateDegree));
			rotateScale.setHeight(Math.sin(lookDegree));
		}
	}
	
	enum BirdViewType {
		FLAT, NORMAL, WALL
	}
	
	public final static BirdViewType FLAT = BirdViewType.FLAT;
	public final static BirdViewType NORMAL = BirdViewType.NORMAL;
	public final static BirdViewType WALL = BirdViewType.WALL;
	/**
	 * 
	 */
	private static final long serialVersionUID = 5677363341624393667L;
	public double lookDegree;
	public double scaleX;
	public double scaleY;
	public double scaleZ;
	public double totalLocationX;
	public double totalLocationY;
	public double totalLocationZ;
	public double totalRotateDegree;
	private Scale totalScale;
	
	/**
	 * 
	 */
	public BirdViewWitch() {
		super();
		scaleX = 1.0;
		scaleY = 1.0;
		scaleZ = 1.0;
		totalRotateDegree = 0.0;
		lookDegree = 0.0;
		totalLocationX = 0.0;
		totalLocationY = 0.0;
		totalLocationZ = 0.0;
		// totalRotation = new Rotation();
		totalScale = new Scale();
	}
	
	@Override
	public synchronized void addChild(Child child) {
		positions.put(child, new BirdViewPosition(child));
	}
	
	@Override
	public synchronized void addChild(Child child, Object option) {
		BirdViewPosition birdViewPosition = null;
		if (option instanceof BirdViewType) {
			if (option.equals(FLAT))
				birdViewPosition = new BirdViewFlat(child);
			else if (option.equals(WALL))
				birdViewPosition = new BirdViewWall(child);
			else
				birdViewPosition = new BirdViewPosition(child);
		} else {
			throw new IllegalArgumentException();
		}
		positions.put(child, birdViewPosition);
	}
	
	/** @return the lookDegree */
	public double getLookDegree() {
		return lookDegree;
	}
	
	/** @return the rotateDegree */
	public double getRotateDegree() {
		return totalRotateDegree;
	}
	
	/** @return the scaleX */
	public double getScaleX() {
		return scaleX;
	}
	
	/** @return the scaleY */
	public double getScaleY() {
		return scaleY;
	}
	
	/** @return the scaleZ */
	public double getScaleZ() {
		return scaleZ;
	}
	
	/** @return the totalLocationX */
	public double getTotalLocationX() {
		return totalLocationX;
	}
	
	/** @return the totalLocationY */
	public double getTotalLocationY() {
		return totalLocationY;
	}
	
	/** @return the totalLocationZ */
	public double getTotalLocationZ() {
		return totalLocationZ;
	}
	
	/* (non-Javadoc)
	 * @see com.gmail.sungmin0511a.layoutWitch.LayoutWitch#locate()
	 */
	@Override
	public void locate() {
		// totalRotation.setDegree(-totalRotateDegree);
		totalScale.setHeight(Math.cos(lookDegree));
		super.locate();
	}
	
	public void move(double x, double y) {
		double Y = y * (-Math.cos(lookDegree));
		totalLocationX += ((Math.cos(-totalRotateDegree) * x * scaleX - Math
				.sin(-totalRotateDegree) * Y * scaleY));
		totalLocationY -= ((Math.sin(-totalRotateDegree) * x * scaleX + Math
				.cos(-totalRotateDegree) * Y * scaleY));
		totalLocationZ -= y * Math.sin(lookDegree);
	}
	
	/** @param lookDegree
	 *        the lookDegree to set */
	public void setLookDegree(double lookDegree) {
		if (lookDegree > Math.PI)
			this.lookDegree = Math.PI;
		else if (lookDegree < 0)
			this.lookDegree = 0;
		else
			this.lookDegree = lookDegree;
	}
	
	/** @param rotateDegree
	 *        the rotateDegree to set */
	public void setRotateDegree(double rotateDegree) {
		this.totalRotateDegree = rotateDegree;
	}
	
	/** @param scaleX
	 *        the scaleX to set */
	public void setScaleX(double scaleX) {
		this.scaleX = scaleX;
	}
	
	/** @param scaleY
	 *        the scaleY to set */
	public void setScaleY(double scaleY) {
		this.scaleY = scaleY;
	}
	
	/** @param scaleZ
	 *        the scaleZ to set */
	public void setScaleZ(double scaleZ) {
		this.scaleZ = scaleZ;
	}
	
	public void setTotalLocation(double x, double y) {
		setTotalLocation(x, y, totalLocationZ);
	}
	
	/** @param totalLocation
	 *        the totalLocation to set */
	public void setTotalLocation(double x, double y, double z) {
		totalLocationX = x;
		totalLocationY = y;
		totalLocationZ = z;
	}
	
	protected void sortChilds() {
		final double dx = -Math.sin(totalRotateDegree);
		final double dy = Math.cos(totalRotateDegree);
		final double dz = Math.cos(lookDegree);
		//		System.out.println("---------------------");
		// System.out.println(totalRotateDegree);
		//		System.out.println(-Math.sin(totalRotateDegree));
		//		System.out.println(Math.cos(totalRotateDegree));
		//		System.out.println(Math.cos(lookDegree));
		List<Child> childs = party.getMembers();
		try {
			synchronized (childs) {
				Collections.sort(childs, new Comparator<Child>() {
					@Override
					public int compare(Child child1, Child child2) {
						BirdViewPosition position1 = (BirdViewPosition) child1.getPosition();
						BirdViewPosition position2 = (BirdViewPosition) child2.getPosition();
						double dx1 = position1.locationX;
						double dy1 = position1.locationY;
						double dz1 = position1.locationZ;
						double dx2 = position2.locationX;
						double dy2 = position2.locationY;
						double dz2 = position2.locationZ;
						double dia1 = dx1 * dx + dy1 * dy + dz1 * dz;
						double dia2 = dx2 * dx + dy2 * dy + dz2 * dz;
						return (int) (dia1 - dia2);
					}
				});
			}
		} catch (IllegalArgumentException iae) {
		}
	}
}
