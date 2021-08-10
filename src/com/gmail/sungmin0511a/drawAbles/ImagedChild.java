package com.gmail.sungmin0511a.drawAbles;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;

import com.gmail.sungmin0511a.etc.ImageStorage;

public class ImagedChild extends Child {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7120430604946824600L;
	protected Dimension bounds;
	protected transient Image image;
	protected String skin;
	boolean middle;
	boolean reverse;
	
	/** @param location
	 * @param skin */
	public ImagedChild(String skin) {
		super();
		this.skin = skin;
		ImageStorage observer = ImageStorage.getImageStorage();
		image = observer.image(skin);
		reverse = false;
		middle = false;
	}
	
	/** @param skin */
	public void changeImage(String skin) {
		image = null;
		this.skin = skin;
		image = ImageStorage.getImageStorage().image(skin);
	}

	@Override
	public boolean contains(Child child) {
		if (bounds == null)
			return false;
		if (child.contains(new Point(0, 0)) || child.contains(new Point(bounds.width, 0))
			|| child.contains(new Point(bounds.width, bounds.height))
			|| child.contains(new Point(0, bounds.height))) {
			return true;
		} else
			return false;
	}

	@Override
	public boolean contains(Point point) {
		if (bounds == null)
			return false;
		if ((point.x > 0 && point.x <= bounds.width) && (point.y > 0 && point.y <= bounds.height)) {
			return true;
		} else
			return false;
	}

	@Override
	public void draw(Graphics2D g) {
		ImageStorage observer = ImageStorage.getImageStorage();
		if (image == null) {
			image = observer.image(skin);
		}
		if (bounds == null && observer.isImageLoaded(skin))
			bounds = new Dimension(image.getWidth(observer), image.getHeight(observer));
		if (middle)
			g.translate(-bounds.width / 2, -bounds.height / 2);
		if (reverse)
			g.drawImage(image, bounds.width, 0, 0, bounds.height, 0, 0, image.getWidth(observer),
					image.getHeight(observer), observer);
		else {
			if (image != null && observer != null) {
				g.drawImage(image, 0, 0, bounds.width, bounds.height, observer);
			}
		}
		if (middle)
			g.translate(bounds.width / 2, bounds.height / 2);
	}
	
	public boolean isMiddle() {
		return middle;
	}

	/** @return */
	public boolean isReverse() {
		return reverse;
	}

	public void setBounds(Dimension bounds) {
		this.bounds = bounds;
	}
	
	public void setMiddle(boolean middle) {
		this.middle = middle;
	}

	/** @param reverse */
	public void setReverse(boolean reverse) {
		this.reverse = reverse;
	}
}
