package Maze;

/**
 * A room in a maze that contains the status of a room.
 */

/**
 * @author Nordine, David, Boda, Brianna
 *
 */
public class Room {
	//fields
	private boolean myLocked;
	private boolean myVisit;
	private int myRow;
	private int myCol;
	
	//constructor
	public Room(int theRow, int theCol) {
		myRow = theRow;
		myCol = theCol;
		myLocked = false;
		myVisit = false;
	}
	
	//getters and setters
	public boolean isLocked() {
		return myLocked;
	}
	
	public void lockRoom() {
		myLocked = true;
	}
	
	public boolean getVisit() {
		return myVisit;
	}
	
	public void setVisit(boolean theBoolean) {
		myVisit = theBoolean; 
	}
	
	public int getRow() {
		return myRow;
	}
	
	public int getCol() {
		return myCol;
	}
	
}