package test;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import com.gmail.sungmin0511a.costume.Location;
import com.gmail.sungmin0511a.costume.Painting;
import com.gmail.sungmin0511a.drawAbles.Party;
import com.gmail.sungmin0511a.drawAbles.ShapedChild;
import com.gmail.sungmin0511a.layoutWitch.PerspectiveWitch;
import com.gmail.sungmin0511a.layoutWitch.PerspectiveWitch.PerspectivePosition;
import com.gmail.sungmin0511a.major.Halloween;

public class Test3 {
	public static void main(String[] args) {
		new Test3();
	}
	
	Halloween halloween;
	Party stage;
	PerspectiveWitch witch;

	public Test3() {
		halloween = new Halloween();
		halloween.getFrame();
		stage = new Party("test3");
		stage.addCostume(new Location(300, 200));
		halloween.getCurrentParty().add(stage);
		halloween.setBackgroundColor(Color.white);
		witch = new PerspectiveWitch();
		stage.setWitch(witch);
		final int a = 5;
		for (int k = 0; k < 200 / a; k += 2) {
			Painting painting = new Painting(new Color(k * a, k * a, k * a));
			for (int i = 0; i < 15; i++) {
				for (int j = 0; j < 13; j++) {
					ShapedChild child = new ShapedChild(new Rectangle(-15, -15, 30, 30));
					stage.add(child);
					child.setFilled(true);
					child.addCostume(painting);
					// child.addCostume(new Opacity(0.5));
					PerspectivePosition position = ((PerspectivePosition) child.getPosition());
					position.setX(i * 50);
					position.setY(j * 50);
					position.setDepth((double) (k + 5) / 20);
				}
			}
		}
		halloween.addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseDragged(MouseEvent arg0) {
			}
			
			@Override
			public void mouseMoved(MouseEvent mouseEvent) {
				witch.setTotalLocationX(mouseEvent.getX());
				witch.setTotalLocationY(mouseEvent.getY());
			}
		});
	}
}
