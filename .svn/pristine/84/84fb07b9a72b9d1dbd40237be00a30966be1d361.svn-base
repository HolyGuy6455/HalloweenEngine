package com.gmail.sungmin0511a.shapeConstituent;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.io.Serializable;

public class Line implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4694234621727788937L;

	public static Point cross(Line line1, Line line2, boolean rangeOut) {
		try {
			if ((line1.Yquant / line1.Xquant) == (line2.Yquant / line2.Xquant)) {
				return null;
			}
		} catch (ArithmeticException e) {
			if (line1.Xquant == 0 && line2.Xquant == 0) {
				return null;
			} else if (line1.Xquant == 0) {
				return new Point(line1.p1.x, (int) line2.getYfromX(line1.p1.x));
			} else if (line2.Xquant == 0) {
				return new Point(line2.p1.x, (int) line1.getYfromX(line2.p1.x));
			}
		}
		double X = ((line1.p2.x * line1.p1.y - line1.p1.x * line1.p2.y) / (line1.Xquant) - (line2.p2.x
																										* line2.p1.y - line2.p1.x
																														* line2.p2.y)
																									/ (line2.Xquant))
					/ (((double) line1.Yquant / line1.Xquant) - ((double) line2.Yquant / line2.Xquant));
		if (rangeOut || (line1.p1.x - X) * (line1.p2.x) < 0)
			return new Point((int) X, (int) line1.getYfromX(X));
		else
			return null;
	}

	int constant;
	Point p1, p2;
	int Xquant;
	int Yquant;

	public Line(int x1, int y1, int x2, int y2) {
		this(new Point(x1, y1), new Point(x2, y2));
	}

	public Line(Point point, Point point2) {
		p1 = point;
		p2 = point2;
		rearrange();
	}
	
	public double getDistance(int x, int y) {
		rearrange();
		if (Xquant == 0)
			return Math.abs(x - p1.x);
		return Math.abs((Xquant * y - Yquant * x - constant)
						/ Math.sqrt(Xquant * Xquant + Yquant * Yquant));
	}
	public double getLength() {
		return Point2D.distance(p1.x, p1.y, p2.x, p2.y);
	}

	public double getYfromX(double x) {
		rearrange();
		if (Xquant == 0) {
			return p1.y;
		} else {
			return (Yquant * x + constant) / Xquant;
		}
	}

	public Point point1() {
		return p1;
	}

	public Point point2() {
		return p2;
	}

	public void rearrange() {
		Xquant = p1.x - p2.x;
		Yquant = p1.y - p2.y;
		constant = Xquant * p1.y - Yquant * p1.x;
	}

	public double tri_cos() {
		rearrange();
		return Xquant / getLength();
	}

	public double tri_sin() {
		rearrange();
		return Yquant / getLength();
	}

	public double tri_tan() {
		rearrange();
		return (double) Yquant / Xquant;
	}
}
