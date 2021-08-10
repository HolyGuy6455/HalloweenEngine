package test;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;

import com.gmail.sungmin0511a.costume.Opacity;
import com.gmail.sungmin0511a.costume.Painting;
import com.gmail.sungmin0511a.drawAbles.Party;
import com.gmail.sungmin0511a.drawAbles.ShapedChild;
import com.gmail.sungmin0511a.layoutWitch.*;
import com.gmail.sungmin0511a.layoutWitch.FallingWitch.FallingPosition;
import com.gmail.sungmin0511a.layoutWitch.GridWitch.GridPosition;
import com.gmail.sungmin0511a.layoutWitch.PerspectiveWitch.PerspectivePosition;
import com.gmail.sungmin0511a.major.Halloween;

public class Test4 {
	public static void main(String[] args) {
		new Test4();
	}
	
	Party back;
	Party cover;
	Party fallen;
	FallingWitch fallingWitch;
	Halloween halloween;
	ShapedChild hero;
	FallingPosition heroPosition;
	Party perspective;
	PerspectiveWitch perspectiveWitch;

	public Test4() {
		halloween = new Halloween();
		halloween.getFrame();
		perspective = new Party("perspectiveParty");
		fallen = new Party("fallingParty");
		cover = new Party("coverParty");
		back = new Party("backParty");
		fallingWitch = new FallingWitch();
		perspectiveWitch = new PerspectiveWitch();
		;
		halloween.getCurrentParty().add(perspective);
		perspective.setWitch(perspectiveWitch);
		fallen.setWitch(fallingWitch);
		((PerspectivePosition) perspective.add(back)).setDepth(1.5);
		((PerspectivePosition) perspective.add(fallen)).setDepth(1);
		((PerspectivePosition) perspective.add(cover)).setDepth(0.5);
		;
		for (int i = 0; i < 100; i++) {		// cover
			ShapedChild child = new ShapedChild(new Rectangle(-15, -15, 30, 30));
			int a = ((int) (Math.random() * 100));
			child.addCostume(new Painting(new Color(a, a + 155, a)));
			child.addCostume(new Opacity(0.5));
			child.setFilled(true);
			GridPosition childPosition = (GridPosition) cover.add(child);
			childPosition.setPosition((int) (Math.random() * 20), (int) (Math.random() * 7) + 10);
		}
		for (int i = 0; i < 100; i++) {		// back
			ShapedChild child = new ShapedChild(new Rectangle(-15, -15, 30, 30));
			int a = ((int) (Math.random() * 100));
			child.addCostume(new Painting(new Color(a, a, a)));
			child.setFilled(true);
			GridPosition childPosition = (GridPosition) back.add(child);
			childPosition.setPosition((int) (Math.random() * 50), (int) (Math.random() * 30));
		}
		for (int i = 0; i < 100; i++) {		// fallen
			if (Math.random() > 0.2) {
				ShapedChild child = new ShapedChild(new Rectangle(-15, -15, 30, 30));
				child.addCostume(new Opacity(0.9));
				child.addCostume(new Painting(new Color(((int) (Math.random() * 255)), 0, 0)));
				child.setFilled(true);
				FallingPosition position = (FallingPosition) fallen.add(child);
				position.setLocation((int) (Math.random() * 600), (int) (Math.random() * 400));
				position.setMoving(false);
			} else {
				ShapedChild child = new ShapedChild(new Ellipse2D.Double(-15, -15, 30, 30));
				child.addCostume(new Painting(new Color(((int) (Math.random() * 255)), ((int) (Math
						.random() * 255)), ((int) (Math.random() * 255)))));
				child.setFilled(true);
				FallingPosition position = (FallingPosition) fallen.add(child);
				position.setLocation((int) (Math.random() * 300), (int) (Math.random() * 300));
				position.setMoving(true);
			}
		}
		ShapedChild child = new ShapedChild(new Rectangle(600, 100));
		child.addCostume(new Painting(new Color(0, 0, 0)));
		child.setFilled(true);
		FallingPosition position = (FallingPosition) fallen.add(child);
		position.setLocation(0, 400);
		position.setMoving(false);
		;
		hero = new ShapedChild(new Ellipse2D.Double(-15, -15, 30, 30));
		hero.addCostume(new Painting(new Color(((int) (Math.random() * 255)),
				((int) (Math.random() * 255)), ((int) (Math.random() * 255)))));
		hero.setFilled(true);
		heroPosition = (FallingPosition) fallen.add(hero);
		;
		halloween.addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseDragged(MouseEvent arg0) {
			}
			
			@Override
			public void mouseMoved(MouseEvent event) {
				perspectiveWitch.setTotalLocationX(event.getX() - 300);
				heroPosition.move((int) (event.getX() - heroPosition.getX()), 0);
			}
		});
		halloween.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				heroPosition.push(0, -10);
			}
		});
	}
}
