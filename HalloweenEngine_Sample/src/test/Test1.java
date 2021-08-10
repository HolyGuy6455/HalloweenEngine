package test;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;

import javax.swing.JFrame;

import com.gmail.sungmin0511a.costume.*;
import com.gmail.sungmin0511a.drawAbles.ShapedChild;
import com.gmail.sungmin0511a.drawAbles.StringChild;
import com.gmail.sungmin0511a.major.Halloween;

public class Test1 {
	public static void main(String[] args) {
		new Test1();
	}

	JFrame frame;
	Halloween halloween;
	Location location;

	public Test1() {
		halloween = new Halloween();
		frame = halloween.getFrame();
		ShapedChild child = new ShapedChild(new Rectangle(30, 30));
		final ShapedChild child2 = new ShapedChild(new Rectangle(new Dimension(640, 480)));
		ShapedChild child3 = new ShapedChild(new Rectangle(new Dimension(30, 30)));
		ShapedChild child4 = new ShapedChild(new Rectangle(40, 40));
		StringChild child5 = new StringChild("asdf");
		halloween.getCurrentParty().add(child);
		halloween.getCurrentParty().add(child2);
		halloween.getCurrentParty().add(child3);
		halloween.getCurrentParty().add(child4);
		halloween.getCurrentParty().add(child5);
		child.addCostume(new Location(100, 100));
		child.addCostume(new Scale(3.0));
		child.addCostume(new Painting(Color.black));
		child.addCostume(new Rotation(0.5));
		child.addCostume(new LineStyle(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		;
		child2.addCostume(new Location(300, 100));
		child2.addCostume(new Scale(0.3));
		child2.addCostume(new Painting(Color.orange));
		child2.addCostume(new LineStyle(30, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL));
		;
		location = new Location(200, 70);
		child3.addCostume(location);
		child3.addCostume(new Painting(Color.blue));
		child3.addCostume(new Opacity(0.5));
		child3.addCostume(new LineStyle(4, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER));
		;
		child4.addCostume(new Location(100, 200));
		child4.addCostume(new Painting(Color.green));
		;
		child5.addCostume(new Location(300, 20));
		child5.addCostume(new Painting(Color.DARK_GRAY));
		child.addEventListener(new MouseMotionListener() {
			@Override
			public void mouseDragged(MouseEvent arg0) {
			}
			
			@Override
			public void mouseMoved(MouseEvent mouseEvent) {
				System.out.println(mouseEvent);
			}
		});
		halloween.addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseDragged(MouseEvent arg0) {
			}
			
			@Override
			public void mouseMoved(MouseEvent arg0) {
				Point2D point = child2.globalToLocal(new Point2D.Double((double) halloween
						.getMousePosition().x, (double) halloween.getMousePosition().y));
				location.setX(point.getX());
				location.setY(point.getY());
			}
		});
	}
}
