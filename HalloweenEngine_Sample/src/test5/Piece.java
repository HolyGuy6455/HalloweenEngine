package test5;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.gmail.sungmin0511a.costume.Painting;
import com.gmail.sungmin0511a.costume.Rotation;
import com.gmail.sungmin0511a.drawAbles.Party;
import com.gmail.sungmin0511a.drawAbles.ShapedChild;
import com.gmail.sungmin0511a.gameConstituent.Status;
import com.gmail.sungmin0511a.gameConstituent.Unit;
import com.gmail.sungmin0511a.layoutWitch.OceanballWitch.OceanballPosition;

public class Piece extends Unit {
	final static int m = 7;
	static int n = 0;
	ShapedChild body;
	Status HP;
	Rotation lookTo;
	OceanballPosition position;
	boolean selected;

	public Piece(Party stage) {
		super(new ShapedChild(new Rectangle(-5, -6, 10, 12)));
		this.body = ((ShapedChild) getBody());
		body.setFilled(true);
		n++;
		body.addCostume(new Painting(new Color(n % 255, (n / m) % 255, ((n / m) / m) % 255)));
		;
		lookTo = new Rotation();
		lookTo.setPriority(1);
		body.addCostume(lookTo);
		;
		HP = new Status("HP", 20, 20);
		addStatus(HP);
		stage.add(body);
		position = (OceanballPosition) body.getPosition();
		position.setSpeed(3);
		position.setAttractive(800);
		position.setRadius(5);
		;
		body.addEventListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent event) {
				if (event.getSource().equals(body)) {
					UnitControler.getInstance().addPiece(Piece.this);
					selected = true;
				}
			}
		});
	}
	
	public void moveTo(double x, double y) {
		double width = position.getX() - x;
		double height = position.getY() - y;
		if (height == 0)
			lookTo.setDegree(0);
		else
			lookTo.setDegree(-Math.atan(width / height));
		position.moveTo(x, y);
	}
}
