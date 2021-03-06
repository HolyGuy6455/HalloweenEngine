package com.gmail.sungmin0511a.layoutWitch;

import java.awt.geom.Point2D;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import com.gmail.sungmin0511a.costume.Location;
import com.gmail.sungmin0511a.drawAbles.Child;
import com.gmail.sungmin0511a.layoutWitch.OceanballWitch.OceanballPosition;

public class OceanballWitch extends LayoutWitch<OceanballPosition> {
	public interface OceanballListener {
		public void arrive();
		
		public void moving();
		
		public void nudge();

		public void start();
	}
	
	public class OceanballPosition extends LayoutPosition {
		/**
		 * 
		 */
		private static final long serialVersionUID = 4992260749088228531L;
		boolean arrive;
		int attractive;
		boolean collision;
		OceanballGroup group;
		OceanballListener listener;
		Location location;
		double objectX, objectY;
		double radius;
		double speed;
		double x, y;
		
		/** @param child
		 * @param x
		 * @param y */
		public OceanballPosition(Child child, int x, int y) {
			super(child);
			this.x = objectX = x;
			this.y = objectY = y;
			attractive = 500;
			speed = 1;
			radius = 10;
			child.addCostume(location = new Location(x, y));
			arrive = true;
			collision = true;
			listener = new OceanballListener() {
				@Override
				public void arrive() {
				}

				@Override
				public void moving() {
				}
				
				@Override
				public void nudge() {
				}
				
				@Override
				public void start() {
				}
			};
			//			group = sequence.get((int) (x / sieve), (int) (y / sieve));
			updateGroup();
		}
		
		/** @return the attractive */
		public int getAttractive() {
			return attractive;
		}
		
		/** @return the listener */
		public OceanballListener getListener() {
			return listener;
		}
		
		/** @return the location */
		public Location getLocation() {
			return location;
		}
		
		/** @return the objectX */
		public double getObjectX() {
			return objectX;
		}
		
		/** @return the objectY */
		public double getObjectY() {
			return objectY;
		}
		
		/** @return the radius */
		public double getRadius() {
			return radius;
		}
		
		/** @return the speed */
		public double getSpeed() {
			return speed;
		}
		
		/** @return the x */
		public double getX() {
			return x;
		}
		
		/** @return the y */
		public double getY() {
			return y;
		}
		
		/** @return the arrive */
		public boolean isArrive() {
			return arrive;
		}
		
		/** @return the collision */
		public boolean isCollision() {
			return collision;
		}
		
		public void moveTo(double x, double y) {
			this.objectX = x;
			this.objectY = y;
			arrive = false;
			if (listener != null)
				listener.start();
		}
		
		/** @param attractive
		 *        the attractive to set */
		public void setAttractive(int attractive) {
			this.attractive = attractive;
		}
		
		/** @param collision
		 *        the collision to set */
		public void setCollision(boolean collision) {
			this.collision = collision;
		}
		
		/** @param listener
		 *        the listener to set */
		public void setListener(OceanballListener listener) {
			this.listener = listener;
		}
		
		public void setLocation(double x, double y) {
			this.x = objectX = x;
			this.y = objectY = y;
			updateGroup();
		}
		
		public void setLocation(int x, int y) {
			setLocation((double) x, (double) y);
		}
		
		/** @param radius
		 *        the radius to set */
		public void setRadius(double radius) {
			this.radius = radius;
		}
		
		/** @param speed
		 *        the speed to set */
		public void setSpeed(double speed) {
			this.speed = speed;
		}
		
		@Override
		protected void post() {
			if (!arrive) {
				if (listener != null)
					listener.moving();
				double distance = Point2D.distance(x, y, objectX, objectY);
				if (distance > speed) {
					x += speed * (objectX - x) / distance;
					y += speed * (objectY - y) / distance;
					if (collision) {
						list.add(this);
						updateGroup();
					}
				} else {
					x = objectX;
					y = objectY;
					if (Point2D.distance(x, y, location.getX(), location.getY()) < radius) {
						if (listener != null)
							listener.arrive();
						arrive = true;
						updateGroup();
					}
				}
			}
			location.setX(location.getX() + (this.x - location.getX()) * this.attractive / 1000);
			location.setY(location.getY() + (this.y - location.getY()) * this.attractive / 1000);
		}
		
		private void updateGroup() {
			if (group == null) {
				group = sequence.get((int) (x / sieve), (int) (y / sieve));
				group.add(this);
			} else if (group.index != (int) (y / sieve) || group.root.index != (int) (x / sieve)) {
				group.remove(this);
				group = sequence.get((int) (x / sieve), (int) (y / sieve));
				group.add(this);
			}
		}
	}
	
	enum OceanballType {
		NORMAL, PASS
	}
	
	public final static OceanballType NORMAL = OceanballType.NORMAL;
	public final static OceanballType PASS = OceanballType.PASS;
	private static final long serialVersionUID = -5546573374413757772L;
	// boolean change;
	// Set<PositionPair<OceanballPosition>> list;
	Queue<OceanballPosition> list;
	private OceanballGroupSequence sequence;
	private int sieve;
	
	public OceanballWitch() {
		super();
		sequence = new OceanballGroupSequence();
		list = new LinkedList<OceanballPosition>();
		sieve = 20;
	}
	
	/** @see LayoutWitch#addChild(Child) */
	@Override
	public void addChild(Child child) {
		positions.put(child, new OceanballPosition(child, 0, 0));
	}
	
	/** @see LayoutWitch#addChild(Child, Object) */
	@Override
	public void addChild(Child child, Object option) {
		if (option instanceof OceanballType) {
			OceanballType optionType = (OceanballType) option;
			switch (optionType) {
				case NORMAL:
					addChild(child);
					break;
				case PASS:
					addChild(child);
					((OceanballPosition) (child.getPosition())).setCollision(false);
					break;
				default:
					break;
			}
		}
	}
	
	/** @see com.gmail.sungmin0511a.layoutWitch.LayoutWitch#locate() */
	@Override
	public synchronized void locate() {
		super.locate();
		synchronized (positions) {
			while (list.size() > 0) {
				OceanballPosition position = list.poll();
				@SuppressWarnings("unchecked")
				Set<OceanballPosition> sets[] = new Set[4];
				/*  ?? ?????? ???? 50???? ???? ?????? ???? ????????, ?????? ??????????
				 *  ?? ?????? sieve????(?????? ???????? 100?????? ????)??
				 *  ???????? ???????? ?????? ??????.
				 *  ?????? OceanballPosition(???? ???????????? ????????)?? ?? ???? ??????
				 *  ??????, ?????? ??, ???? ????, ?????? ????
				 *  ?? ?? ???? ??????????. ???????? ???? ???? ?????? ??????. ??????
				 *  sets[0]?? ???????? ???? ????
				 *  sets[1]?? ???????? ???? ???? ????
				 *  sets[2]?? ???????? ???? ???? ????
				 *  sets[3]?? ???????? ???? ???? ???? ??
				 *  ?? ?????????? ????????
				 *  ???? ?????? ????????, for (Set<OceanballPosition> set : sets) ?? ???? ?? ???? ???? ????
				 */
				sets[0] = position.group.points;
				int index = position.group.index;
				// [0]?? ?????? ???? ????
				if ((position.y % sieve) < sieve / 2) {		// ?????????
					// [1]?? ????
					if (position.group.before != null && position.group.before.index == index - 1)
						sets[1] = position.group.before.points;
					;
					OceanballGroupRoot root;
					if ((position.x % sieve) < sieve / 2)
						root = position.group.root.before;
					else
						root = position.group.root.next;
					if (root != null)
						if (root.contain(index)) {
							// [2]?? ???? ??
							OceanballGroup sideGroup = root.getGroup(index);
							sets[2] = sideGroup.points;
							if (sideGroup.before != null && sideGroup.before.index == index - 1)
								// [3]?? ?? ????
								sets[3] = sideGroup.before.points;
						} else if (root.contain(index - 1))
							// [3]?? ?? ????
							sets[3] = root.getGroup(index).before.points;
				} else {								// ???????????
					// [1]?? ??????
					if (position.group.next != null && position.group.next.index == index + 1)
						sets[1] = position.group.next.points;
					;
					OceanballGroupRoot root;
					if ((position.x % sieve) < sieve / 2)
						root = position.group.root.before;
					else
						root = position.group.root.next;
					if (root != null)
						if (root.contain(index)) {
							// [2]?? ???? ??
							OceanballGroup sideGroup = root.getGroup(index);
							sets[2] = sideGroup.points;
							if (sideGroup.next != null && sideGroup.next.index == index + 1)
								// [3]?? ?? ??????
								sets[3] = sideGroup.next.points;
						} else if (root.contain(index + 1))
							// [3]?? ?? ??????
							sets[3] = root.getGroup(index).next.points;
				}
				// System.out.println(sets[0]);
				// System.out.println(sets[1]);
				// System.out.println(sets[2]);
				// System.out.println(sets[3]);
				for (Set<OceanballPosition> set : sets) {
					if (set == null)
						continue;
					for (OceanballPosition position2 : set) {
						// for (OceanballPosition position2
						// : positions.values()) {
						if (position.equals(position2) || !position.collision
							|| !position2.collision)
							continue;
						double distance = Point2D.distance(position.x, position.y, position2.x,
								position2.y);
						//						System.out.println(distance);
						if (distance < position.radius + position2.radius) {
							//							System.out.println("asdfgh");
							double x = (position2.x - position.x) / 3;
							double y = (position2.y - position.y) / 3;
							synchronized (positions) {
								synchronized (position) {
									position.x -= x;
									position.y -= y;
									position.arrive = false;
									if (position.listener != null)
										position.listener.nudge();
									list.add(position);
								}
								synchronized (position2) {
									position2.x += x;
									position2.y += y;
									position2.arrive = false;
									if (position2.listener != null)
										position2.listener.nudge();
									list.add(position2);
								}
							}
						}
					}
				}
			}
		}
	}
	
	/** @see LayoutWitch#sortChilds() */
	@Override
	protected void sortChilds() {
	}
	/*
	private OceanballGroup registration(OceanballPosition position, int rootIndex, int groupIndex) {
		OceanballGroupRoot root = group.next(rootIndex);
		if (root == null) {
			OceanballGroupRoot before = group;
			while (before.right.index < rootIndex) {
				before = before.right;
			}
			OceanballGroupRoot result = new OceanballGroupRoot(before);
			result.trunk = new OceanballGroup(null);
			result.trunk.add(position);
			return result.trunk;
		} else {
			OceanballGroup trunk;
			try {
				trunk = root.trunk.next(groupIndex);
			} catch (NullPointerException npe) {
				trunk = null;
			}
			if (trunk == null) {
				OceanballGroup before = root.trunk;
				if (before == null) {
					OceanballGroup result = new OceanballGroup(null);
					root.trunk = result;
					result.add(position);
					return result;
				} else {
					while (before.down.index < groupIndex) {
						before = before.down;
					}
					OceanballGroup result = new OceanballGroup(before);
					result.add(position);
					return result;
				}
			} else {
				trunk.add(position);
				return trunk;
			}
		}
	}
	*/
}

class OceanballGroup {
	static int numbering = 0;
	OceanballGroup before;
	int index;
	OceanballGroup next;
	int num;;
	HashSet<OceanballPosition> points;
	OceanballGroupRoot root;
	
	OceanballGroup(OceanballGroupRoot root, int index) {
		num = numbering++;
		this.root = root;
		this.index = index;
		points = new HashSet<OceanballPosition>();
		if (root.head == null)
			root.head = root.tail = this;
		else if (root.head.index > this.index) {
			// GroupRoot?? head???? index?? ???? ????
			this.next = root.head;
			root.head.before = this;
			root.head = this;
		} else if (root.tail.index < this.index) {
			// GroupRoot?? tail???? index?? ?? ????
			this.before = root.tail;
			root.tail.next = this;
			root.tail = this;
		} else {
			// ???????? ????
			OceanballGroup group = root.head;
			while (group.index < index)
				group = group.next;
			if (group.index != index) {
				this.before = group.before;
				this.next = group;
				group.before.next = this;
				group.before = this;
			}
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof OceanballGroup)
			return ((OceanballGroup) obj).index == this.index;
		else
			return super.equals(obj);
	}
	
	@Override
	public String toString() {
		return "Group" + num;
	}
	
	void add(OceanballPosition t) {
		points.add(t);
	}
	
	OceanballGroup get(int index) {
		if (this.index < index) {
			if (next == null)
				return null;
			else
				return next.get(index);
		} else if (this.index == index)
			return this;
		else
			return null;
	}
	
	OceanballGroup next() {
		return next;
	}
	
	void remove() {
		if (before == null && next == null) {
			root.head = root.tail = null;
			root.delete();
		} else {
			if (before != null)
				before.next = this.next;
			else
				root.head = this.next;
			if (next != null)
				next.before = this.before;
			else
				root.tail = this.before;
		}
	}
	
	void remove(OceanballPosition point) {
		points.remove(point);
		if (points.size() == 0)
			if (before == null && next == null) {
				root.head = root.tail = null;
				root.delete();
			} else {
				if (before != null)
					before.next = this.next;
				else
					root.head = this.next;
				if (next != null)
					next.before = this.before;
				else
					root.tail = this.before;
			}
	}
}

class OceanballGroupRoot {
	OceanballGroupRoot before;
	OceanballGroup head;
	int index;
	OceanballGroupRoot next;
	OceanballGroupSequence sequence;
	OceanballGroup tail;
	
	OceanballGroupRoot(OceanballGroupSequence sequence, int index) {//
		this.index = index;
		this.sequence = sequence;
		if (sequence.tail == null)
			sequence.head = sequence.tail = this;
		else if (sequence.head.index > this.index) {
			// Sequence?? head???? index?? ???? ????
			this.next = sequence.head;
			sequence.head.before = this;
			sequence.head = this;
		} else if (sequence.tail.index < this.index) {
			// Sequence?? tail???? index?? ?? ????
			this.before = sequence.tail;
			sequence.tail.next = this;
			sequence.tail = this;
		} else {
			// ???????? ????
			OceanballGroupRoot root = sequence.head;
			while (root.index < index)
				root = root.next;
			if (root.index != index) {
				this.before = root.before;
				this.next = root;
				//				if (root.before == null) {
				//					System.out.println("head" + sequence.head.index);
				//					System.out.println("tail" + sequence.tail.index);
				//					System.out.println("root" + root.index);
				//					System.out.println("this" + index);
				//				}
				root.before.next = this;
				root.before = this;
			}
		}
	}
	
	boolean contain(int index) {
		if (head == null)
			return false;
		OceanballGroup result = head;
		while (result.index < index) {
			result = result.next();
			if (result == null)
				return false;
		}
		return (result.index == index);
	}
	
	void delete() {//
		if (sequence.head.equals(this) && sequence.tail.equals(this))
			return;
		if (before != null && (before.next != null && before.next.equals(this)))
			before.next = this.next;
		else if (sequence.head.equals(this))
			sequence.head = this.next;
		if (next != null && (next.before != null && next.before.equals(this)))
			next.before = this.before;
		else if (sequence.tail.equals(this))
			sequence.tail = this.before;
		this.head = null;
		this.tail = null;
	}
	
	OceanballGroupRoot get(int index) {//
		if (this.index == index)
			return this;
		else if (index > this.index) {
			if (next != null && next.index <= index)
				return next.get(index);
			else
				return new OceanballGroupRoot(sequence, index);
		} else if (before != null && before.index >= index)
			return before.get(index);
		else
			return new OceanballGroupRoot(sequence, index);
	}
	
	OceanballGroup getGroup(int index) {//
		if (head == null && tail == null)
			return new OceanballGroup(this, index);
		OceanballGroup result = head;
		while (result.index < index) {
			result = result.next();
			if (result == null)
				return new OceanballGroup(this, index);
		}
		if (result.index == index)
			return result;
		else
			return new OceanballGroup(this, index);
	}
}

class OceanballGroupSequence {
	OceanballGroupRoot head;
	OceanballGroupRoot tail;
	
	OceanballGroupSequence() {
		head = new OceanballGroupRoot(this, 0);
		tail = head;
	}
	
	void add(OceanballPosition t, int x, int y) {
		head.get(x).getGroup(y).add(t);
	}
	
	OceanballGroup get(int x, int y) {
		return head.get(x).getGroup(y);
	}
	
	void remove(OceanballPosition t, int x, int y) {
		head.get(x).getGroup(y).remove(t);
	}
}
