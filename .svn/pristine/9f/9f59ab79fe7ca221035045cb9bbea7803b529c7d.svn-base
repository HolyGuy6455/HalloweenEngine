package com.gmail.sungmin0511a.drawAbles;

import java.awt.Graphics2D;
import java.awt.Point;

public class Animation extends Child {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5601156064442216970L;
	Child frames[];
	int nowPage;
	boolean play;
	int playSpeed;
	boolean replay;

	public Animation() {
		super();
		nowPage = 0;
		playSpeed = 1;
		play = true;
		replay = false;
		frames = new Child[0];
	}
	
	@Override
	public void draw(Graphics2D graphics) {
		frames[nowPage / 1000].draw(graphics);
		if (play)
			nowPage += playSpeed;
		if (nowPage / 1000 >= frames.length) {
			if (replay)
				nowPage = 0;
			else {
				nowPage = (frames.length - 1) * 1000;
				play = false;
			}
		}
	}

	public void play() {
		play = true;
	}

	public void putFrame(Child newFrame) {
		Child result[] = new Child[frames.length + 1];
		for (int i = 0; i < frames.length; i++) {
			result[i] = frames[i];
		}
		result[frames.length] = newFrame;
		frames = result;
	}

	public void putFrame(Child newFrames[]) {
		Child result[] = new Child[frames.length + newFrames.length];
		for (int i = 0; i < frames.length; i++) {
			result[i] = frames[i];
		}
		for (int i = 0; i < frames.length + newFrames.length; i++) {
			result[i + frames.length] = newFrames[i];
		}
		frames = result;
	}

	public void replay(boolean value) {
		replay = value;
	}

	public void setPlaySpeed(int playSpeed) {
		this.playSpeed = playSpeed;
	}

	public void stop() {
		play = false;
	}

	@Override
	public boolean contains(Child child) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean contains(Point point) {
		// TODO Auto-generated method stub
		return false;
	}
}
