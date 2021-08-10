package test6;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.gmail.sungmin0511a.costume.Painting;
import com.gmail.sungmin0511a.drawAbles.Party;
import com.gmail.sungmin0511a.drawAbles.ShapedChild;
import com.gmail.sungmin0511a.layoutWitch.BirdViewWitch;
import com.gmail.sungmin0511a.layoutWitch.BirdViewWitch.BirdViewPosition;
import com.gmail.sungmin0511a.layoutWitch.BirdViewWitch.BirdViewWall;

public class Cube {
	private static Party party;
	private static Cube[][] station;
	private final static int TOTALLY_HEIGHT;
	private final static int TOTALLY_WIDTH;
	// private static BirdViewWitch witch;
	static {
		TOTALLY_WIDTH = 15;
		TOTALLY_HEIGHT = 15;
		station = new Cube[TOTALLY_WIDTH][TOTALLY_HEIGHT];
	}
	
	public static void connect() {
		for (int i = 0; i < TOTALLY_WIDTH; i++) {
			for (int j = 0; j < TOTALLY_HEIGHT; j++) {
				if (i < TOTALLY_WIDTH - 1) {// 횡방향
					Cube left = station[i][j];
					Cube right = station[i + 1][j];
					left.westCube = right;
					right.eastCube = left;
				}
				if (j < TOTALLY_HEIGHT - 1) {// 종방향
					Cube up = station[i][j];
					Cube down = station[i][j + 1];
					up.northCube = down;
					down.southCube = up;
				}
			}
		}
	}

	/** @return the party */
	public static Party getParty() {
		return party;
	}
	
	/** @return the station */
	public static Cube[][] getStation() {
		return station;
	}

	/** @return the totallyHeight */
	public static int getTotallyHeight() {
		return TOTALLY_HEIGHT;
	}
	
	/** @return the totallyWidth */
	public static int getTotallyWidth() {
		return TOTALLY_WIDTH;
	}

	/** @param party
	 *        the party to set */
	public static void setParty(Party party) {
		Cube.party = party;
		// witch = (BirdViewWitch) party.getWitch();
	}
	
	ShapedChild cover;
	BirdViewPosition coverPosition;
	//
	Cube eastCube;
	BirdViewWall eastPosition;
	ShapedChild eastWall;
	//
	ShapedChild floor;
	BirdViewPosition floorPosition;
	//
	Cube northCube;
	BirdViewWall northPosition;
	ShapedChild northWall;
	//
	Cube southCube;
	BirdViewWall southPosition;
	ShapedChild southWall;
	//
	Cube westCube;
	BirdViewWall westPosition;
	ShapedChild westWall;
	int x, y;
	private boolean down;
	
	public Cube(int x, int y) {
		if (x < 0 || x >= TOTALLY_WIDTH || y < 0 || y >= TOTALLY_HEIGHT)
			throw new IllegalArgumentException();
		this.x = x;
		this.y = y;
		{
			final int length = 30;
			final int tum = 5;
			cover = new ShapedChild(new Rectangle(-length / 2, -length / 2, length, length));
			cover.addCostume(new Painting(Color.gray));
			cover.setFilled(true);
			coverPosition = (BirdViewPosition) party.add(cover, BirdViewWitch.FLAT);
			coverPosition.setLocation(x * (length + tum), y * (length + tum), length / 2);
			;
			eastWall = new ShapedChild(new Rectangle(-length / 2, -length / 2, length, length));
			eastWall.addCostume(new Painting(Color.red));
			eastWall.setFilled(true);
			eastPosition = (BirdViewWall) party.add(eastWall, BirdViewWitch.WALL);
			eastPosition.setLocation(x * (length + tum) - length / 2, y * (length + tum), 0);
			eastPosition.setRotateDegree(Math.PI / 2);
			;
			westWall = new ShapedChild(new Rectangle(-length / 2, -length / 2, length, length));
			westWall.addCostume(new Painting(Color.blue));
			westWall.setFilled(true);
			westPosition = (BirdViewWall) party.add(westWall, BirdViewWitch.WALL);
			westPosition.setLocation(x * (length + tum) + length / 2, y * (length + tum), 0);
			westPosition.setRotateDegree(Math.PI / 2);
			;
			southWall = new ShapedChild(new Rectangle(-length / 2, -length / 2, length, length));
			southWall.addCostume(new Painting(Color.green));
			southWall.setFilled(true);
			southPosition = (BirdViewWall) party.add(southWall, BirdViewWitch.WALL);
			southPosition.setLocation(x * (length + tum), y * (length + tum) - length / 2, 0);
			;
			northWall = new ShapedChild(new Rectangle(-length / 2, -length / 2, length, length));
			northWall.addCostume(new Painting(Color.yellow));
			northWall.setFilled(true);
			northPosition = (BirdViewWall) party.add(northWall, BirdViewWitch.WALL);
			northPosition.setLocation(x * (length + tum), y * (length + tum) + length / 2, 0);
			;
			floor = new ShapedChild(new Rectangle(-length / 2, -length / 2, length, length));
			floor.addCostume(new Painting(Color.lightGray));
			floor.setFilled(true);
			floorPosition = (BirdViewPosition) party.add(floor, BirdViewWitch.FLAT);
			floorPosition.setLocation(x * (length + tum), y * (length + tum), -length / 2);
			floor.addEventListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent arg0) {
					setDown(false);
					if (eastCube != null)
						eastCube.setDown(false);
					if (westCube != null)
						westCube.setDown(false);
					if (southCube != null)
						southCube.setDown(false);
					if (northCube != null)
						northCube.setDown(false);
				}
				
				@Override
				public void mouseExited(MouseEvent arg0) {
					setDown(true);
					if (eastCube != null)
						eastCube.setDown(true);
					if (westCube != null)
						westCube.setDown(true);
					if (southCube != null)
						southCube.setDown(true);
					if (northCube != null)
						northCube.setDown(true);
				}
			});
		}
		station[x][y] = this;
	}
	
	/** @return the down */
	public boolean isDown() {
		return down;
	}
	
	/** @param down
	 *        the down to set */
	public void setDown(boolean down) {
		this.down = down;
		cover.setVisible(down);
		eastWall.setVisible(down);
		westWall.setVisible(down);
		southWall.setVisible(down);
		northWall.setVisible(down);
	}
}
