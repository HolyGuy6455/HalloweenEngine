/**
 * 
 */
package com.gmail.sungmin0511a.costume;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;

import com.gmail.sungmin0511a.drawAbles.Child;

/** {@link Child}가 그려질 위치를 정해줍니다. <p> Location은 {@link Child}가
 * 그려질때, 위치를 기억해 두었다가 {@link Child} 가 그 좌표에 그려질 수 있도록 돕습니다.
 * 
 * @author 신성민 */
public class Location extends PointCostume {
	int priority;
	private Point2D point;
	
	/** 좌표 (0,0)에 새로운 Location을 생성합니다. */
	public Location() {
		this(new Point2D.Double());
	}
	
	/** 주어진 두 정수로 새로운 {@link Point}를 생성해 그것으로 Location을
	 * 생성합니다.
	 * 
	 * @param x
	 *        이 Location의 x좌표가 됩니다.
	 * @param y
	 *        이 Location의 x좌표가 됩니다. */
	public Location(double x, double y) {
		this(new Point2D.Double(x, y));
	}
	
	/** 기본생성자입니다. 주어지는 {@link Point}를 좌표삼아, 새 Location을
	 * 생성합니다. 파라매터로 주어진 {@link Point}의 x값, y값이 변하면 이
	 * Location의 좌표도 변경됩니다.
	 * 
	 * @param point
	 *        이 Location의 좌표가 될 Point입니다.
	 * @throws NoninvertibleTransformException */
	public Location(Point2D point) {
		super(AffineTransform.getTranslateInstance(point.getX(), point.getY()));
		this.point = point;
	}
	
	/** Child가 이 Location이 지시하는 곳에 그려지도록 합니다. 주어지는
	 * {@link Graphics2D}의
	 * {@link Graphics2D#translate(int, int) translate(int,
	 * int)}를 실행해, Child가 그려질 위치를 결정합니다. */
	@Override
	public void dressUp(Graphics2D graphics) {
		graphics.translate(point.getX(), point.getY());
	}
	
	/**
	 *
	 */
	@Override
	public void dressUp(Point2D position) {
		position.setLocation(position.getX() + point.getX(), position.getY() + point.getY());
	}

	/** 이 Location이 가리키는 좌표를 반환합니다.
	 * 
	 * @return 현재 Location이 있는 좌표{@link Point}입니다. */
	public Point2D getPoint() {
		return point;
	}

	@Override
	public int getPriority() {
		return priority;
	}
	
	/** x좌표의 값을 반환합니다.
	 * 
	 * @return */
	public double getX() {
		return point.getX();
	}
	
	/** y좌표의 값을 반환합니다.
	 * 
	 * @return */
	public double getY() {
		return point.getY();
	}
	
	/** 주어진 두개의 값으로 좌표를 결정합니다
	 * 
	 * @param x
	 * @param y */
	public void set(double x, double y) {
		point.setLocation(x, y);
	}
	
	/** 주어진 Point2D로 좌표를 결정합니다
	 * 
	 * @param point */
	public void setPoint(Point2D point) {
		this.point = point;
	}
	
	@Override
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	/** x좌표를 결정합니다.
	 * 
	 * @param x
	 *        x좌표로 설정될 값입니다 */
	public void setX(double x) {
		point.setLocation(x, point.getY());
	}
	
	/** y좌표를 결정합니다.
	 * 
	 * @param y
	 *        y좌표로 설정될 값입니다 */
	public void setY(double y) {
		point.setLocation(point.getX(), y);
	}
	
	/** 주어진 파라매터만큼 좌표를 옮깁니다. 주어진 정수로 값이 새롭게 정해지는 것이 아니라, 기존의
	 * x,y값에 더해집니다.
	 * 
	 * @param x
	 *        변경하고싶은 x좌표 변화량의 크기입니다.
	 * @param y
	 *        변경하고싶은 y좌표 변화량의 크기입니다. */
	public void translate(double x, double y) {
		point.setLocation(point.getX() + x, point.getY() + y);
	}
	
	/** Child가 그려진 뒤 좌표를 원래대로 합니다. */
	@Override
	public void unDress(Graphics2D graphics) {
		graphics.translate(-point.getX(), -point.getY());
	}
	
	@Override
	public void unDress(Point2D position) {
		position.setLocation(position.getX() - point.getX(), position.getY() - point.getY());
	}
}
