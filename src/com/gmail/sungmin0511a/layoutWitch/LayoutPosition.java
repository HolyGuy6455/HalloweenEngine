package com.gmail.sungmin0511a.layoutWitch;

import java.io.Serializable;

import com.gmail.sungmin0511a.drawAbles.Child;

public abstract class LayoutPosition implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3730769389301543863L;
	private Child posedChild;
	

	LayoutPosition(Child child) {
		posedChild = child;
		child.setPosition(this);
	}
	
	public Child getChild() {
		return posedChild;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#finalize()
	 */
	@Override
	protected void finalize() throws Throwable {
		forgot();
		// super.finalize();
	}

	protected abstract void post();
	
	void forgot() {
		posedChild = null;
	}
}