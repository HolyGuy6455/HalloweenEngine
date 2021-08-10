package test5;

import java.util.HashSet;

public class UnitControler {
	private static UnitControler instance = new UnitControler();
	
	/** @return the instance */
	public static UnitControler getInstance() {
		return instance;
	}
	
	private HashSet<Piece> hashSet;

	private UnitControler() {
		hashSet = new HashSet<Piece>();
	}
	
	public void addPiece(Piece piece) {
		hashSet.add(piece);
	}
	
	public void moveTo(int x, int y) {
		for (Piece piece : hashSet)
			piece.moveTo(x, y);
	}
	
	public void reset() {
		for (Piece piece : hashSet)
			piece.selected = false;
		hashSet.clear();
	}
}
