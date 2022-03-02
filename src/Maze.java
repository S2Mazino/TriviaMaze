
import java.util.LinkedList;
import java.util.Queue;


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
		myEndX = theCol;
		myEndY = theRow;
		generateMaze(theRow, theCol);
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
	public void lockRoom(final int theY, final int theX) {
		myMaze[theY][theX].lockRoom();
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
		if(!myMaze[myY-1][myX].isLocked()) {
			sb.append("N,");
		}
		//Right
		if(!myMaze[myY][myX+1].isLocked()) {
			sb.append("E,");
		}
		//Down
		if(!myMaze[myY+1][myX].isLocked()) {
			sb.append("S,");
		}
		//Left
		if(!myMaze[myY][myX-1].isLocked()) {
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
		q.add(myMaze[myY][myX]);
		
		while(q.size() != 0) {
			Room curr = q.remove();
			int i = curr.getY();
			int j = curr.getX();
			
			if(i == myEndY && j == myEndX) {
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
	 * set all room visit = false
	 */
	private void resetVisit() {
		for(int i = 1; i < myMaze.length-1; i++) {
			for(int j = 1; j < myMaze[0].length-1; j++) {
				myMaze[i][j].setVisit(false);
			}
		}
	}
	
	/**
	 * Moves in desired direction and return true if the move was successful,
	 * otherwise false.
	 * @param theDirection the desired direction to move in
	 * @return T/F if move was successful
	 */
	public boolean move(final String theDirection) {
		boolean moved = false;
		if(theDirection.equals("N") && (myY-1 > 0) && !myMaze[myY-1][myX].isLocked()) {
			myY--;
			moved = true;
		}else if(theDirection.equals("E") && (myX+1 < myMaze[0].length) && !myMaze[myY][myX+1].isLocked()) {
			myX++;
			moved = true;
		}else if(theDirection.equals("S") && (myY+1 < myMaze.length) && !myMaze[myY+1][myX].isLocked()) {
			myY++;
			moved = true;
		}else if(theDirection.equals("W") && (myX-1 > 0) && !myMaze[myY][myX-1].isLocked()) {
			myX--;
			moved = true;
		}
		return moved;
	}
	
	/**
	 * for testing purpose.
	 */
	public Room[][] getRooms(){
		return myMaze;
	}
	
	public int getMyX() {
		return myX;
	}
	
	public int getMyY() {
		return myY;
	}

}
