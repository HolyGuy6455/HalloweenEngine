package test6;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import javax.swing.JFrame;

import com.gmail.sungmin0511a.costume.Location;
import com.gmail.sungmin0511a.costume.Painting;
import com.gmail.sungmin0511a.drawAbles.Child;
import com.gmail.sungmin0511a.drawAbles.Party;
import com.gmail.sungmin0511a.drawAbles.ShapedChild;
import com.gmail.sungmin0511a.layoutWitch.BirdViewWitch;
import com.gmail.sungmin0511a.major.Halloween;

public class Test6 {
	public static void main(String[] args) {
		new Test6();
	}
	
	MouseAdapter adapter;
	double beforeDegree;
	Point beforeMouse;
	double beforeY;
	JFrame frame;
	Halloween halloween;
	int mouseButton;
	BirdViewWitch witch;
	
	public Test6() {
		halloween = new Halloween();
		halloween.setSize(600, 600);
		frame = halloween.getFrame();
		witch = new BirdViewWitch();
		Party party = new Party("test6");
		halloween.getCurrentParty().add(party);
		party.addCostume(new Location(300, 300));
		party.setWitch(witch);
		Cube.setParty(party);
		// for (int i = 0; i < 6; i++) {
		// Cube cube = new Cube(i, 0);
		// cube.setDown(false);
		// }
		// new Cube(0, 0);
		for (int i = 0; i < Cube.getTotallyWidth(); i++) {
			for (int j = 0; j < Cube.getTotallyHeight(); j++) {
				new Cube(i, j);
			}
		}
		Cube.connect();
		Child child = new ShapedChild(new Rectangle(-10, -10, 20, 20));
		child.addCostume(new Painting(Color.black));
		;
		adapter = new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent mouseEvent) {
				Point2D point = halloween.getMousePosition();
				if (mouseButton == MouseEvent.BUTTON1) {
					witch.setRotateDegree((beforeDegree + (point.getX() - beforeMouse.getX()) / 300
															* Math.PI));
					witch.setLookDegree(beforeY + ((beforeMouse.getY() - point.getY()) / 150));
				} else if (mouseButton == MouseEvent.BUTTON3) {
					witch.move((beforeMouse.getX() - point.getX()),
							(beforeMouse.getY() - point.getY()));
					beforeMouse.setLocation(point);
				}
			}
			
			@Override
			public void mousePressed(MouseEvent mouseEvent) {
				beforeMouse = halloween.getMousePosition();
				mouseButton = mouseEvent.getButton();
				beforeDegree = witch.getRotateDegree();
				beforeY = witch.getLookDegree();
			}
		};
		halloween.addMouseMotionListener(adapter);
		halloween.addMouseListener(adapter);
	}
}
