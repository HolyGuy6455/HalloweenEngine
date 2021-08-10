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

/** {@link Child}�� �׷��� ��ġ�� �����ݴϴ�. <p> Location�� {@link Child}��
 * �׷�����, ��ġ�� ����� �ξ��ٰ� {@link Child} �� �� ��ǥ�� �׷��� �� �ֵ��� �����ϴ�.
 * 
 * @author �ż��� */
public class Location extends PointCostume {
	int priority;
	private Point2D point;
	
	/** ��ǥ (0,0)�� ���ο� Location�� �����մϴ�. */
	public Location() {
		this(new Point2D.Double());
	}
	
	/** �־��� �� ������ ���ο� {@link Point}�� ������ �װ����� Location��
	 * �����մϴ�.
	 * 
	 * @param x
	 *        �� Location�� x��ǥ�� �˴ϴ�.
	 * @param y
	 *        �� Location�� x��ǥ�� �˴ϴ�. */
	public Location(double x, double y) {
		this(new Point2D.Double(x, y));
	}
	
	/** �⺻�������Դϴ�. �־����� {@link Point}�� ��ǥ���, �� Location��
	 * �����մϴ�. �Ķ���ͷ� �־��� {@link Point}�� x��, y���� ���ϸ� ��
	 * Location�� ��ǥ�� ����˴ϴ�.
	 * 
	 * @param point
	 *        �� Location�� ��ǥ�� �� Point�Դϴ�.
	 * @throws NoninvertibleTransformException */
	public Location(Point2D point) {
		super(AffineTransform.getTranslateInstance(point.getX(), point.getY()));
		this.point = point;
	}
	
	/** Child�� �� Location�� �����ϴ� ���� �׷������� �մϴ�. �־�����
	 * {@link Graphics2D}��
	 * {@link Graphics2D#translate(int, int) translate(int,
	 * int)}�� ������, Child�� �׷��� ��ġ�� �����մϴ�. */
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

	/** �� Location�� ����Ű�� ��ǥ�� ��ȯ�մϴ�.
	 * 
	 * @return ���� Location�� �ִ� ��ǥ{@link Point}�Դϴ�. */
	public Point2D getPoint() {
		return point;
	}

	@Override
	public int getPriority() {
		return priority;
	}
	
	/** x��ǥ�� ���� ��ȯ�մϴ�.
	 * 
	 * @return */
	public double getX() {
		return point.getX();
	}
	
	/** y��ǥ�� ���� ��ȯ�մϴ�.
	 * 
	 * @return */
	public double getY() {
		return point.getY();
	}
	
	/** �־��� �ΰ��� ������ ��ǥ�� �����մϴ�
	 * 
	 * @param x
	 * @param y */
	public void set(double x, double y) {
		point.setLocation(x, y);
	}
	
	/** �־��� Point2D�� ��ǥ�� �����մϴ�
	 * 
	 * @param point */
	public void setPoint(Point2D point) {
		this.point = point;
	}
	
	@Override
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	/** x��ǥ�� �����մϴ�.
	 * 
	 * @param x
	 *        x��ǥ�� ������ ���Դϴ� */
	public void setX(double x) {
		point.setLocation(x, point.getY());
	}
	
	/** y��ǥ�� �����մϴ�.
	 * 
	 * @param y
	 *        y��ǥ�� ������ ���Դϴ� */
	public void setY(double y) {
		point.setLocation(point.getX(), y);
	}
	
	/** �־��� �Ķ���͸�ŭ ��ǥ�� �ű�ϴ�. �־��� ������ ���� ���Ӱ� �������� ���� �ƴ϶�, ������
	 * x,y���� �������ϴ�.
	 * 
	 * @param x
	 *        �����ϰ���� x��ǥ ��ȭ���� ũ���Դϴ�.
	 * @param y
	 *        �����ϰ���� y��ǥ ��ȭ���� ũ���Դϴ�. */
	public void translate(double x, double y) {
		point.setLocation(point.getX() + x, point.getY() + y);
	}
	
	/** Child�� �׷��� �� ��ǥ�� ������� �մϴ�. */
	@Override
	public void unDress(Graphics2D graphics) {
		graphics.translate(-point.getX(), -point.getY());
	}
	
	@Override
	public void unDress(Point2D position) {
		position.setLocation(position.getX() - point.getX(), position.getY() - point.getY());
	}
}
