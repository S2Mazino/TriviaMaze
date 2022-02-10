
import java.util.Scanner;

/**
 * @author Nordine
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final Scanner myInput = new Scanner(System.in);
		Maze maze;
		String rand;
		int myRows;
		int myCols;
		int myX;
		int myY;
		int myEndX;
		int myEndY;
		
		System.out.print("Maze randomize (y or n)? ");
		rand = myInput.next();
		if(rand.toLowerCase().equals("n")) {
			//will add valid input condition later
			System.out.print("Input amount of rows(between 4 and 10): ");
			myRows = myInput.nextInt();
			System.out.print("Input amount of rows(between 4 and 10): ");
			myCols = myInput.nextInt();
			System.out.print("Input starting row: ");
			myX = myInput.nextInt();
			System.out.print("Input starting col: ");
			myY = myInput.nextInt();
			System.out.print("Input ending row: ");
			myEndX = myInput.nextInt();
			System.out.print("Input ending row: ");
			myEndY = myInput.nextInt();
			
			maze = new Maze(myRows, myCols, myX, myY, myEndX, myEndY);
			maze = new Maze(4, 4, 0, 0, 3, 3);
		}else {
			maze = new Maze();
		}
		
		
		
		maze.displayMaze();
		
		System.out.println(maze.availableRoom());
		
		myInput.close();
	}

}