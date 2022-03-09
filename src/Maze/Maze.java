/**
 * A Maze comprised of rooms utilizing a 2D array of rooms with various utility methods.
 * 
 * @author Nordine, David, Boda, Brianna
 *
 */

package Maze;

import java.util.LinkedList;
import java.util.Queue;

public class Maze {
	//fields
	private Room[][] myMaze;
	private int myCol;
	private int myRow;
	private int myEndCol;
	private int myEndRow;
	
	
	/**
	 * Constructor when user wants to specify starting and ending points.
	 */
	public Maze() {
		myCol = 1;
		myRow = 1;
		myEndCol = 4;
		myEndRow = 4;
		generateMaze(myEndRow, myEndCol);
	}
	
	/**
	 * Displays a visual representation of the maze where "u" is the user, "o" is a walkable path, 
	 * and "x" is not a walkable path.
	 */
	public void displayMaze() {
		for(int i = 1; i < myMaze.length-1; i++) {
			for(int j = 1; j < myMaze[0].length-1; j++) {
				if(i == myRow && j == myCol) {
					System.out.print("u");
				}else if(i == myEndRow && j == myEndCol){
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
	 * @param theRow row
	 * @param theCol column
	 */
	public void lockRoom(final int theRow, final int theCol) {
		myMaze[theRow][theCol].lockRoom();
	}
	
	/**
	 * Checks if the player reached the end goal.
	 * @return T/F if user's x and y is equal to the end's x and y
	 */
	public boolean win() {
		return myRow == myEndRow && myCol == myEndCol;
	}
	
	/**
	 * Returns all possible direction the user can move.
	 * @return all the available location from the user's position
	 */
	public String availableRoom() {
		StringBuilder sb = new StringBuilder();
		//Up
		if(!myMaze[myRow-1][myCol].isLocked()) {
			sb.append("N,");
		}
		//Right
		if(!myMaze[myRow][myCol+1].isLocked()) {
			sb.append("E,");
		}
		//Down
		if(!myMaze[myRow+1][myCol].isLocked()) {
			sb.append("S,");
		}
		//Left
		if(!myMaze[myRow][myCol-1].isLocked()) {
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
		q.add(myMaze[myRow][myCol]);
		
		while(q.size() > 0) {
			Room curr = q.remove();
			int i = curr.getRow();
			int j = curr.getCol();
			
			if(i == myEndRow && j == myEndCol) {
				resetVisit();
				return true;
			}
			
			myMaze[i][j].setVisit(true);
			
			if(myMaze[i-1][j].getVisit() == false && !myMaze[i-1][j].isLocked()) {
				q.add(myMaze[i-1][j]); //up
			}
			
			if(myMaze[i+1][j].getVisit() == false && !myMaze[i+1][j].isLocked()) {
				q.add(myMaze[i+1][j]); //down
			}
			
			if(myMaze[i][j+1].getVisit() == false && !myMaze[i][j+1].isLocked()) {
				q.add(myMaze[i][j+1]); //right
			}
			
			if(myMaze[i][j-1].getVisit() == false && !myMaze[i][j-1].isLocked()) {
				q.add(myMaze[i][j-1]); //left
			}
			
		}
		
		resetVisit();
		
		return false;
	}
	
	/**
	 * Moves in desired direction. 
	 * @param theDirection the desired direction to move in
	 */
	public void move(final String theDirection) {
		if(theDirection.equals("N")) {
			myRow--;
		}else if(theDirection.equals("E")) {
			myCol++;
		}else if(theDirection.equals("S")) {
			myRow++;
		}else if(theDirection.equals("W")) {
			myCol--;
		}
	}
	
	/**
	 * Checks if the player can move in the desired direction, returns true if successful
	 * otherwise false.
	 * @param theDirection N,E,S,W 
	 * @return T/F if can move 
	 */
	public boolean canMove(final String theDirection) {
		boolean move = false;
		if(theDirection.equals("N") && (myRow-1 > 0) && !myMaze[myRow-1][myCol].isLocked()) {
			move = true;
		}else if(theDirection.equals("E") && (myCol+1 < myMaze[0].length) && !myMaze[myRow][myCol+1].isLocked()) {
			move = true;
		}else if(theDirection.equals("S") && (myRow+1 < myMaze.length) && !myMaze[myRow+1][myCol].isLocked()) {
			move = true;
		}else if(theDirection.equals("W") && (myCol-1 > 0) && !myMaze[myRow][myCol-1].isLocked()) {
			move = true;
		}
		return move;
	}
	
	/**
	 * 
	 * @return returns the maze.
	 */
	public Room[][] getRooms(){
		return myMaze;
	}
	
	/**
	 * @return returns the user's current column.
	 */
	public int getMyCol() {
		return myCol;
	}
	
	/**
	 * @return returns the user's current row.
	 */
	public int getMyRow() {
		return myRow;
	}
	
	/**
	 * Generates a maze of NxN.
	 * 
	 * @param theRow amount of rows
	 * @param theCol amount of columns
	 * @return the maze
	 */
	private void generateMaze(final int theRow, final int theCol){
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
		
		myMaze = arr;
	}
	
	/**
	 * set all room visit = false.
	 */
	private void resetVisit() {
		for(int i = 1; i < myMaze.length-1; i++) {
			for(int j = 1; j < myMaze[0].length-1; j++) {
				myMaze[i][j].setVisit(false);
			}
		}
	}
	
	


}