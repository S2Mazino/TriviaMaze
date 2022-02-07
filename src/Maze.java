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
	 * Constructor 4,4 when user wants to use the randomize function.
	 */
	public Maze() {
		Random rand = new Random();
		myX = rand.nextInt(4);
		myY = rand.nextInt(4);
		myEndX = rand.nextInt(4);
		myEndY = rand.nextInt(4);
		myMaze = generateMaze(4, 4);
	}
	
	/**
	 * Constructor when user wants to specify starting and ending points.
	 * 
	 * @param theUserX
	 * @param theUserY
	 * @param theEndX
	 * @param theEndY
	 */
	public Maze(final int theRow, final int theCol, final int theUserX, final int theUserY, final int theEndX, final int theEndY) {
		myX = theUserX;
		myY = theUserY;
		myEndX = theEndX;
		myEndY = theEndY;
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
		Room[][] arr = new Room[theRow][theCol];
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[0].length; j++) {
				arr[i][j] = new Room();
			}
		}
		return arr;
	}
	
	/**
	 * Displays a visual representation of the maze where "u" is the user, "o" is a walkable path, 
	 * and "x" is not a walkable path.
	 */
	public void displayMaze() {
		for(int i = 0; i < myMaze.length; i++) {
			for(int j = 0; j < myMaze[0].length; j++) {
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

}
