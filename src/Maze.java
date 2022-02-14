
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

/**
 * @author Nordine, David, Boda, Brianna
 *
 */
public class Maze {
	//fields
	private Room[][] myMaze;
	private int myX; //columns
	private int myY; //rows
	private int myEndX;
	private int myEndY;
	
	/**
	 * Constructor when user wants to specify starting and ending points.
	 * 
	 * @param theUserX
	 * @param theUserY
	 * @param theEndX
	 * @param theEndY
	 */
	public Maze(final int theRow, final int theCol) {
		myX = 1;
		myY = 1;
		myEndX = theRow;
		myEndY = theCol;
		myMaze = generateMaze(theRow, theCol);
	}
	
	
	/**
	 * Generates a maze of NxN.
	 * 
	 * @param theRow amount of rows
	 * @param theCol amount of columns
	 * @return the maze
	 */
	private Room[][] generateMaze(final int theRow, final int theCol){
		//implements minesweeper +2 method
		Room[][] arr = new Room[theRow+2][theCol+2];
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[0].length; j++) {
				arr[i][j] = new Room(i, j);
			}
		}
		//set first row and last row all to lock
		for(int i = 0; i < arr[0].length; i++) {
			arr[0][i].lockRoom();
			arr[arr.length-1][i].lockRoom();
		}
		//set first column and last column all to lock
		for(int j = 0; j < arr.length; j++) {
			arr[j][0].lockRoom();
			arr[j][arr[0].length-1].lockRoom();
		}
		
		return arr;
	}
	
	/**
	 * Displays a visual representation of the maze where "u" is the user, "o" is a walkable path, 
	 * and "x" is not a walkable path.
	 */
	public void displayMaze() {
		for(int i = 1; i < myMaze.length-1; i++) {
			for(int j = 1; j < myMaze[0].length-1; j++) {
				if(i == myX && j == myY) {
					System.out.print("u");
				}else if(i == myEndX && j == myEndY){
					System.out.print("e");
				}else if(!myMaze[i][j].isLocked()) {
					System.out.print("o");
				}else{
					System.out.print("x");
				}
			}
			System.out.println();
		}
		
	}
	
	/**
	 * Locks a room given the x and y.
	 * @param theX theRow
	 * @param theY theCol
	 */
	public void lockRoom(int theX, int theY) {
		myMaze[theX][theY].lockRoom();
	}
	
	/**
	 * Checks if the player reached the end goal.
	 * @return T/F if user's x and y is equal to the end's x and y
	 */
	public boolean win() {
		return myX == myEndX && myY == myEndY;
	}
	
	/**
	 * Returns all possible direction the user can move
	 * @return all the available location from the user's position
	 */
	public String availableRoom() {
		StringBuilder sb = new StringBuilder();
		//Up
		if(!myMaze[myX-1][myY].isLocked()) {
			sb.append("N,");
		}
		//Right
		if(!myMaze[myX][myY+1].isLocked()) {
			sb.append("E,");
		}
		//Down
		if(!myMaze[myX+1][myY].isLocked()) {
			sb.append("S,");
		}
		//Left
		if(!myMaze[myX][myY-1].isLocked()) {
			sb.append("W,");
		}
		return sb.length() == 0? sb.toString() : sb.substring(0, sb.length()-1).toString();
	}
	
	/**
	 * Checks if there is exist a valid path from current room to end room by
	 * utilizing BFS algorithm.
	 * @return T/F T = valid path exist, else F
	 */
	public boolean hasPath() {
		
		Queue<Room> q = new LinkedList<>();
		//current user's position
		q.add(myMaze[myX][myY]);
		
		while(q.size() != 0) {
			Room curr = q.remove();
			int i = curr.getX();
			int j = curr.getY();
			if( i <= 0 || i >= myMaze.length-1 || j <= 0 || j >= myMaze[0].length-1) {
				continue;
			}
			
			if(myMaze[i][j].getVisit() == true || myMaze[i][j].isLocked()) {
				continue;
			}
			
			if(i == myEndX && j == myEndY) {
				return true;
			}
			
			myMaze[i][j].setVisit(true);
			
			q.add(myMaze[i-1][j]); //left
			q.add(myMaze[i+1][j]); //right
			q.add(myMaze[i][j+1]); //up
			q.add(myMaze[i][j-1]); //down
			
		}
		
		resetVisit();
		
		return false;
	}
	
	/**
	 * set all room visit = false
	 */
	public void resetVisit() {
		for(int i = 1; i < myMaze.length-1; i++) {
			for(int j = 1; j < myMaze[0].length-1; j++) {
				myMaze[i][j].setVisit(false);
			}
		}
	}
	
	

}
