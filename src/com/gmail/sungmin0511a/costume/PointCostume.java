/**
 * 
 */
package com.gmail.sungmin0511a.costume;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;

/** @author ½Å¼º¹Î */
public class PointCostume implements Costume {
	protected AffineTransform affineTransform;
	protected AffineTransform inverseAffineTransform;
	
	int priority;

	/** @param affineTransform
	 * @throws NoninvertibleTransformException */
	public PointCostume(AffineTransform affineTransform) {
		super();
		this.affineTransform = affineTransform;
		try {
			inverseAffineTransform = affineTransform.createInverse();
		} catch (NoninvertibleTransformException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void dressUp(Graphics2D graphics) {
		graphics.transform(affineTransform);
	}

	/** @param position */
	public void dressUp(Point2D position) {
		affineTransform.transform(position, position);
	}
	
	@Override
	public int getPriority() {
		return priority;
	}
	
	@Override
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	@Override
	public void unDress(Graphics2D graphics) {
		graphics.transform(inverseAffineTransform);
	}
	
	/** @param position */
	public void unDress(Point2D position) {
		inverseAffineTransform.transform(position, position);
	}
}
