
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
	
	//constructor
	public Room() {
		myLocked = false;
	}
	
	//getters and setters
	public boolean isLocked() {
		return myLocked;
	}
	
	public void lockRoom() {
		myLocked = true;
	}
	
}

