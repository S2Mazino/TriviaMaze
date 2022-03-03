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
	private int myX;
	private int myY;
	
	//constructor
	public Room(int theX, int theY) {
		myX = theX;
		myY = theY;
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
	
	public int getX() {
		return myX;
	}
	
	public int getY() {
		return myY;
	}
	
}

