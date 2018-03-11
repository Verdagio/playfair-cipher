package ie.gmit.sw;

/**
 * The Position object will direct a character to an x & y position within a 2
 * dimensional table
 * 
 * @author Daniel Verdejo - G00282931
 *
 */
public class Position {

	private int x;
	private int y;

	public Position () {}
	
	private Position(int x, int y) {
		this.x = x;
		this.y = y;
	}// constructor

	public static Position getPosition(char target, String[][] cipherTable) {

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (target == cipherTable[i][j].charAt(0)) {
					return new Position(i, j);
				}
			}
		}
		return null;
	}
	
	public int getPosX() {
		return this.x;
	}
	
	public int getPosY() {
		return this.y;
	}
	
}
