package test;

import javax.swing.JFrame;

import com.gmail.sungmin0511a.hComponent.HScroll;
import com.gmail.sungmin0511a.hComponent.HTextField;
import com.gmail.sungmin0511a.layoutWitch.GridWitch.GridPosition;
import com.gmail.sungmin0511a.major.Halloween;
import com.gmail.sungmin0511a.sound.Sound;

public class Test7 {
	public static void main(String[] args) {
		new Test7();
	}
	
	JFrame frame;
	Halloween halloween;
	
	public Test7() {
		halloween = new Halloween();
		frame = halloween.getFrame();
		HTextField hTextField = new HTextField("dddd");
		GridPosition hTextPosition = (GridPosition) halloween.getCurrentParty().add(hTextField);
		hTextPosition.setPosition(10, 10);
		HTextField hTextField2 = new HTextField("aaaa");
		GridPosition hTextPosition2 = (GridPosition) halloween.getCurrentParty().add(hTextField2);
		hTextPosition2.setPosition(10, 20);
		;
		HScroll hScroll = new HScroll();
		GridPosition hScrollPosition = (GridPosition) halloween.getCurrentParty().add(hScroll);
		hScrollPosition.setPosition(12, 15);
		;
		Sound sound = new Sound("./wav/sound2.wav");
		sound.setRepeat(true);
		//		while (true) {
		//			try {
		//				sound.setVloume(1.0);
		//				Thread.sleep(5000);
		//				sound.setVloume(0.3);
		//				Thread.sleep(1000);
		//			} catch (InterruptedException e) {
		//				e.printStackTrace();
		//			}
		//		}
	}
}
