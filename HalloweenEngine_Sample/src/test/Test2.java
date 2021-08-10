package test;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.Point2D;

import com.gmail.sungmin0511a.costume.*;
import com.gmail.sungmin0511a.drawAbles.Party;
import com.gmail.sungmin0511a.drawAbles.ShapedChild;
import com.gmail.sungmin0511a.layoutWitch.BirdViewWitch;
import com.gmail.sungmin0511a.layoutWitch.BirdViewWitch.BirdViewFlat;
import com.gmail.sungmin0511a.major.Halloween;

public class Test2 {
	public static void main(String[] args) {
		new Test2();
	}
	
	MouseAdapter adapter;
	double beforeDegree;
	Point beforeMouse;
	double beforeY;
	BirdViewWitch birdViewWitch;
	double d;
	Halloween halloween;
	Location location;
	int mouseButton;
	Party stage;
	Scale zoom;
	
	public Test2() {
		halloween = new Halloween();
		stage = new Party("test2");
		halloween.getCurrentParty().add(stage);
		halloween.setSize(new Dimension(1200, 700));
		halloween.getFrame();
		location = new Location(500, 350);
		zoom = new Scale();
		stage.addCostume(location);
		stage.addCostume(zoom);
		birdViewWitch = new BirdViewWitch();
		stage.setWitch(birdViewWitch);
		for (int i = 0; i < 500; i++) {
			ShapedChild child = new ShapedChild(new Rectangle(-15, -15, 30, 30));
			// BirdViewPosition birdViewFlat =
			// (BirdViewPosition) stage.add(child);
			BirdViewFlat birdViewFlat = (BirdViewFlat) stage.add(child, BirdViewWitch.FLAT);
			int a = 10;
			int R = (int) (Math.random() * a) * 250 / a;
			int G = (int) (Math.random() * a) * 250 / a;
			int B = (int) (Math.random() * a) * 250 / a;
			child.addCostume(new Opacity(Math.random()));
			birdViewFlat.setLocation(R * 3, G * 3, B * 3);
			// birdViewFlat.setRotateDegree(Math.random() *
			// Math.PI);
			child.addCostume(new Painting(new Color(R, G, B)));
			child.addCostume(new LineStyle(45, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));
		}
		adapter = new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent mouseEvent) {
				Point2D point = halloween.getMousePosition();
				if (mouseButton == MouseEvent.BUTTON1) {
					birdViewWitch.setRotateDegree((beforeDegree + (point.getX() - beforeMouse
							.getX()) / 300 * Math.PI / zoom.getWidth()));
					birdViewWitch.setLookDegree(beforeY
												+ ((beforeMouse.getY() - point.getY()) / 150 / zoom
														.getWidth()));
				} else if (mouseButton == MouseEvent.BUTTON3) {
					birdViewWitch.move((beforeMouse.getX() - point.getX()) / zoom.getWidth(),
							(beforeMouse.getY() - point.getY()) / zoom.getHeight());
					beforeMouse.setLocation(point);
				}
			}
			
			@Override
			public void mousePressed(MouseEvent mouseEvent) {
				beforeMouse = halloween.getMousePosition();
				mouseButton = mouseEvent.getButton();
				beforeDegree = birdViewWitch.getRotateDegree();
				beforeY = birdViewWitch.getLookDegree();
			}
			
			@Override
			public void mouseWheelMoved(MouseWheelEvent mouseEvent) {
				double diff = zoom.getHeight() * Math.pow(1.5, -mouseEvent.getWheelRotation());
				zoom.setHeight(diff);
				zoom.setWidth(diff);
			}
		};
		halloween.addMouseMotionListener(adapter);
		halloween.addMouseListener(adapter);
		halloween.addMouseWheelListener(adapter);
	}
}
