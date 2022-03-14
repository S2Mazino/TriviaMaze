
/**
 * A room in a maze that contains the status of a room.
 * 
 * @author Nordine, David, Boda, Brianna
 *
 */
package Maze;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Room implements Serializable {
	//fields
	private boolean myLocked;
	private boolean myVisit;
	private int myRow;
	private int myCol;
	
	//constructor
	public Room(final int theRow, final int theCol) {
		myRow = theRow;
		myCol = theCol;
		myLocked = false;
		myVisit = false;
	}
	
	/**
	 * return's the room availability current state.
	 * @return returns the state of the room.
	 */
	public boolean isLocked() {
		return myLocked;
	}
	
	/**
	 * sets the room current state to lock.
	 */
	public void lockRoom() {
		myLocked = true;
	}
	
	/**
	 * Sets the room's visit to true or false, used in a BFS algorithm to calculate path by not revisiting the
	 * same room.
	 * @return state of visit
	 */
	public boolean getVisit() {
		return myVisit;
	}
	
	/**
	 * Sets the room's visit to true or false, used in a BFS algorithm to calculate path.
	 * @param T/F 
	 */
	public void setVisit(final boolean theBoolean) {
		myVisit = theBoolean; 
	}
	
	/**
	 *  The current room's row in the maze
	 * @return the current room's row
	 */
	public int getRow() {
		return myRow;
	}
	
	/**
	 * The current room's column in the maze.
	 * @return the current room's column
	 */
	public int getCol() {
		return myCol;
	}
	
	
	
	
	public Room() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static void main(String[]args)
	{
		
		Room room = new Room();
		
		room.myCol = 10;
		room.myLocked = true;
		room.myRow = 10;
		room.myVisit = false;
		
		
		try {
			
			File roomUser = new File("Room.txt");
			
			FileOutputStream file = new FileOutputStream(roomUser);
			
			ObjectOutputStream out = new ObjectOutputStream(file);
			
			out.writeObject(room);
			
			out.flush();
			
			out.close();
			
			
			System.out.println("Success Object has been Serialized");
			
			
			
			
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}