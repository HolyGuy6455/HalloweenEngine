package test5;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import com.gmail.sungmin0511a.drawAbles.Party;
import com.gmail.sungmin0511a.drawAbles.ShapedChild;
import com.gmail.sungmin0511a.layoutWitch.OceanballWitch;
import com.gmail.sungmin0511a.major.Halloween;

public class Test5 {
	public static void main(String[] args) {
		new Test5();
	}
	
	Halloween halloween;
	ArrayList<Piece> pieces;
	Party stage;
	
	public Test5() {
		halloween = new Halloween();
		halloween.setSize(new Dimension(1200, 700));
		halloween.getFrame();
		// halloween.setTime(100);
		stage = new Party("Stage");
		halloween.setCurrentParty(stage);
		stage.setWitch(new OceanballWitch());
		pieces = new ArrayList<Piece>();
		final int number = 2000;
		final int width = 60;
		for (int i = 0; i < number; i++) {
			Piece piece = new Piece(stage);
			int x = (i % width);
			int y = (i / width);
			piece.position.setLocation(y * 100, x * 60);
			//			piece.moveTo(x * 20 + 10, y * 20 + 10);
			pieces.add(piece);
		}
		halloween.getComponent().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					Point point = halloween.getMousePosition();
					UnitControler.getInstance().moveTo(point.x, point.y);
				} else if (e.getButton() == MouseEvent.BUTTON1) {
					if (!(e.getSource() instanceof ShapedChild))
						UnitControler.getInstance().reset();
				} else {
					int i = 0;
					switch ((int) (Math.random() * 4)) {
						case 0:
							i = 0;
							for (Piece piece : pieces) {
								i++;
								piece.moveTo(i / width * 20, i % width * 20);
							}
							break;
						case 1:
							i = 0;
							for (Piece piece : pieces) {
								i++;
								piece.moveTo(500 - (i % width * 20), i / width * 20);
							}
							break;
						case 2:
							i = 0;
							for (Piece piece : pieces) {
								i++;
								piece.moveTo(i * Math.cos(i) + 300, i * Math.sin(i) + 200);
							}
							break;
						case 3:
							i = 0;
							for (Piece piece : pieces) {
								i++;
								piece.moveTo((i % width * 20) + 10, (i / width * 20) + 10);
							}
							break;
						default:
							break;
					}
				}
			}
		});
	}
}
