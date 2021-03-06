package com.gmail.sungmin0511a.layoutWitch;

/*
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;

import com.gmail.sungmin0511a.drawAbles.Child;
import com.gmail.sungmin0511a.etc.ValueException;
import com.gmail.sungmin0511a.gameEvent.OverlapEvent;
import com.gmail.sungmin0511a.gameEvent.OverlapListener;
import com.gmail.sungmin0511a.shapeConstituent.Line;

public class GravityWitch extends LayoutWitch {
	public class GravityGhost extends LayoutWitch.Ghost {

		/**
		 * 
		 *
		private static final long serialVersionUID = 1793045920052492658L;
		int attractive;
		double x, y;

		public GravityGhost(Child child) {
			super(child);
			// 일단 지금은 졸라 귀찮으니 아이템은 공중에 고정하는걸로
			x = child.getLocation().x;
			y = child.getLocation().y;
			attractive = 900;
		}

		@Override
		protected void post() {
			synchronized (ghosts) {
				getChild().getLocation().x += (int) ((this.x - getChild().getLocation().x)
														* this.attractive / 1000);
				getChild().getLocation().y += (int) ((this.y - getChild().getLocation().y)
														* this.attractive / 1000);
			}
		}

	}

	public class GravityPosition extends LayoutWitch.Position {
		/**
		 * 
		 *
		private static final long serialVersionUID = -7999968958851561166L;
		int attractive;
		int balanceX, balanceY;
		int elasticity;
		transient ArrayList<Force2D> forces;
		boolean mobile;
		double speedX, speedY;
		double x, y;
		boolean standing;

		public GravityPosition(Child child) {
			super(child);
			x = child.getLocation().x;
			y = child.getLocation().y;
			balanceX = 0;
			balanceY = 0;
			speedX = 0;
			speedY = 0;
			attractive = 900;
			mobile = true;
			forces = new ArrayList<Force2D>();
			elasticity = 600;
			standing = false;
		}

		public void addForce(Force2D force) {
			forces.add(force);
		}

		@Override
		protected void findOverlapedGhost() {
			if (overlapListeners.size() == 0)
				return;
			Point point = new Point((int) x, (int) y);
			synchronized (ghosts) {
				Iterator<GravityGhost> iterator = ghosts.iterator();
				while (iterator.hasNext()) {
					LayoutWitch.Ghost ghost = iterator.next();
					if (!(ghost.isOverlap()) && ghost.getChild().contains(point)) {
						Iterator<OverlapListener> iterator2 = overlapListeners.iterator();
						while (iterator2.hasNext())
							iterator2.next().overlapEntered(new OverlapEvent(ghost));
						ghost.setOverlap(true);
					} else if (ghost.isOverlap() && !(ghost.getChild().contains(point))) {
						Iterator<OverlapListener> iterator2 = overlapListeners.iterator();
						while (iterator2.hasNext())
							iterator2.next().overlapExited(new OverlapEvent(ghost));
						ghost.setOverlap(false);
					}
				}
			}
		}

		public int getAttractive() {
			return attractive;
		}

		public int getElasticity() {
			return elasticity;
		}

		public Point getReckonedPoint() {
			return new Point((int) x, (int) y);
		}

		public double getSpeedX() {
			return speedX;
		}

		public double getSpeedY() {
			return speedY;
		}
		
		public boolean isStanding() {
			return standing;
		}

		@Override
		protected void post() {
			if (mobile) {
				Iterator<Force2D> iterator;
				try {
					iterator = forces.iterator();
					while (iterator.hasNext()) {
						Force2D force = iterator.next();
						speedX += force.width;
						speedY += force.height;
					}
				} catch (NullPointerException ne) {
				}
				forces = new ArrayList<Force2D>();
				speedY += (double) gravity / 1000;
				if ((gravity > 0 && speedY > limit) || (gravity < 0 && speedY < limit))
					speedY = limit;
				if ((gravity > 0 && speedY < -limit) || (gravity < 0 && speedY > -limit))
					speedY = -limit;
				x += speedX;
				y += speedY;

			}
			getChild().getLocation().x += (int) ((this.x - getChild().getLocation().x + balanceX)
													* this.attractive / 1000);
			getChild().getLocation().y += (int) ((this.y - getChild().getLocation().y + balanceY)
													* this.attractive / 1000);
		}

		public void setAttractive(int attractive) throws ValueException {
			if (attractive <= 1000 && attractive >= 0)
				this.attractive = attractive;
			else
				throw new ValueException(ValueException.ExceptionKey.Between, 0, 1000);
		}

		public void setBalance(int balanceX, int balanceY) {
			this.balanceX = balanceX;
			this.balanceY = balanceY;
		}

		public void setElasticity(int elasticity) {
			this.elasticity = elasticity;
		}

		public void setLocation(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public void setMobile(boolean mobile) {
			this.mobile = mobile;
		}

		public void setSpeedX(double speedX) {
			this.speedX = speedX;
		}

		public void setSpeedX(int speedX) {
			this.speedX = speedX;
		}

		public void setSpeedY(double speedY) {
			this.speedY = speedY;
		}

		public void setSpeedY(int speedY) {
			this.speedY = speedY;
		}

	}

	/**
	 * 
	 *
	private static final long serialVersionUID = -7370184746852091619L;

	ArrayList<GravityGhost> ghosts;
	int gravity;
	int limit;
	ArrayList<GravityPosition> positions;

	public GravityWitch() {
		super();
		gravity = 500;
		limit = 50;
		this.positions = new ArrayList<GravityPosition>();
		this.ghosts = new ArrayList<GravityGhost>();
	}

	@Override
	public void addChild(Child child) {
		positions.add(new GravityPosition(child));
	}

	@Override
	public void addGhost(Child child) {
		ghosts.add(new GravityGhost(child));
	}

	private Point border(Point point1, Point point2, Child child) {
		double distanceX = point2.x - point1.x;
		double distanceY = point2.y - point1.y;
		Point result = new Point(point2);
		for (int i = 0; i < 10; i++) {
			distanceX /= 2;
			distanceY /= 2;
			if (child.contains(result)) {
				result.x -= distanceX;
				result.y -= distanceY;
			} else {
				result.x += distanceX;
				result.y += distanceY;
			}
		}
		return result;
	}

	public GravityGhost getGhost(Child child) {
		Iterator<GravityGhost> iterator;
		try {
			iterator = ghosts.iterator();
		} catch (NullPointerException e) {
			return null;
		}
		while (iterator.hasNext()) {
			GravityGhost ghost = iterator.next();
			if (ghost.getChild().equals(child))
				return ghost;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public GravityPosition getPosition(Child child) {
		Iterator<GravityPosition> iterator;
		try {
			iterator = positions.iterator();
		} catch (NullPointerException e) {
			return null;
		}
		while (iterator.hasNext()) {
			GravityPosition position = iterator.next();
			if (position.getChild().equals(child))
				return position;
		}
		return null;
	}

	@Override
	public void locate() {
		Iterator<GravityPosition> iterator = positions.iterator();
		while (iterator.hasNext()) {
			GravityPosition gravityPosition = iterator.next();
			gravityPosition.post();
			if (Math.abs(gravityPosition.speedY) > 3)
				gravityPosition.standing = false;
		}
		iterator = positions.iterator();
		while (iterator.hasNext()) {
			GravityPosition gravityPosition = iterator.next();
			Iterator<GravityPosition> iterator3 = positions.iterator();
			while (iterator3.hasNext()) {
				GravityPosition gP = iterator3.next();
				if (gravityPosition.equals(gP))
					continue;
				Child child = gP.getChild();
				if (child.contains(gravityPosition.getReckonedPoint())) {
					gravityPosition.standing = true;
					gP.standing = true;
					/* 이 부분은 지랄맞아서 주석은 안남길래야 안남길수가 없겠다
					 * 설명하지. 대체 이곳의 이 정신나간 Point들이 다 뭘 하고 있는건지
					 * after		: 이동 한 후의 위치. child와 충돌함.
					 * before		: 이동하기 전 위치. after가 움직인 거리만큼 빼서 만든다.
					 * 				  child와 충돌하지 않았다는 전제가 있다.
					 * center		: 두 점  사이에 충돌과 충돌하지 않는 부분을 나누는 경계
					 * X , Y		: 이동한 X거리, Y거리
					 * tangent:	: 여기서는 접선이라는 뜻
					 * 		접선 만들어지는 과정
					 * 			0) after과 before 사이에 가상의 직선을 L이라고 하자.
					 * 			   L의 기울기는 Y/X일 것이다  
					 * 			1) after를 지나고 L에 수직인 직선위의 점 a1과 a2를 구한다.
					 * 			2) before를 지나고 L에 수직인 직선위의 점 b1과 b2를 구한다.
					 * 			3) a1과 b1 사이의 border인 c1과
					 * 			   a2와 b2 사이의 border인 c2를 구한다
					 * 			4) c1과 c2를 지나는 선이 접선이다.
					 * 		여담 : 난 그냥 after, before, center라고 지었을 뿐인데
					 * 				이것들의 머릿글자가 a,b,c었을 줄은 상상도 못했다
					 * 				와 쩐다 기가막히고 코가막히네
					 * 
					 * X, Y			: 재활용. 이번엔 접선의 너비, 높이
					 * node		: 이후 서술할 가상의 직선 L1, L2의 교점 
					 * 					L1 : 접선과 평행하고 before를 지나는 직선
					 * 					L2 : 접선과 수직이고 after를 지나는 직선
					 * X, Y			: 또 재활용. 이번엔 after와 node 사이의 거리
					 *
					Point after = gravityPosition.getReckonedPoint();
					int X = (int) gravityPosition.speedX;
					int Y = (int) gravityPosition.speedY;
					Point before = new Point(after.x - X, after.y - Y);
					Point center = border(before, after, child);

					Line tangent = new Line(border(new Point(before.x - Y / 10, before.y + X / 10),
							new Point(after.x - Y / 10, after.y + X / 10), child), border(
							new Point(before.x + Y / 10, before.y - X / 10), new Point(after.x + Y
																						/ 10,
									after.y - X / 10), child));
					X = tangent.point2().x - tangent.point1().x;
					Y = tangent.point2().y - tangent.point1().y;
					Point node = Line.cross(
							new Line(before.x, before.y, before.x + X, before.y + Y), new Line(
									after.x, after.y, after.x - Y, after.y + X), true);
					try {
						X = after.x - node.x;
						Y = after.y - node.y;
					} catch (NullPointerException ne) {
						X = 0;
						Y = 2;
					}
					if (gravityPosition.forces != null && gP.forces != null) {
						gravityPosition.forces.add(new Force2D(-X
																* (1000 + gravityPosition
																		.getElasticity()) / 1000,
								-Y * (1000 + gravityPosition.getElasticity()) / 1000));
						gP.forces.add(new Force2D(X * (1000 + gravityPosition.getElasticity())
													/ 1000, Y
															* (1000 + gravityPosition
																	.getElasticity()) / 1000));
					} else {
						if (gravityPosition.forces == null)
							gravityPosition.forces = new ArrayList<Force2D>();
						if (gP.forces == null)
							gP.forces = new ArrayList<Force2D>();
					}
					gravityPosition.setLocation(center.x, center.y);
				}
			}
		}
		iterator = positions.iterator();
		while (iterator.hasNext()) {
			GravityWitch.GravityPosition gravityPosition2 = iterator.next();
			gravityPosition2.findOverlapedGhost();
		}
	}

	@Override
	public void removeChild(Child child) {
		synchronized (child) {
			positions.remove(getPosition(child));
		}
	}

	@Override
	public void removeGhost(Child child) {
		synchronized (ghosts) {
			ghosts.remove(getGhost(child));
		}
	}

	public void setGravity(int gravity) {
		this.gravity = gravity;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

}
*/