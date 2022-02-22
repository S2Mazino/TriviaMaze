
import java.util.Scanner;

/**
 * @author Nordine
 *
 */
public class Main {

	/**
	 * @param args
	 */
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		final Scanner myInput = new Scanner(System.in);
		Maze maze = null;
		int myRows;
		int myCols;
		
		//will add valid input condition later
		System.out.print("Input amount of rows(between 4 and 10): ");
		myRows = myInput.nextInt();
		System.out.print("Input amount of cols(between 4 and 10): ");
		myCols = myInput.nextInt();
		
		maze = new Maze(myRows, myCols);
		
		maze.lockRoom(2, 1);
//		maze.lockRoom(2, 2);
//		maze.lockRoom(2, 3);
//		maze.lockRoom(2, 4);
		
		maze.displayMaze();
		System.out.println(maze.availableRoom());
		
		System.out.println("move? " + maze.move("S"));
		maze.displayMaze();
		System.out.println(maze.availableRoom());

		
		myInput.close();
	} */

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final Scanner myInput = new Scanner(System.in);
		Maze maze = null;
		int myRows;
		int myCols;
		boolean myGameDone = false;
		
		while (!myGameDone) {
			System.out.println("Hello, welcome to the Trivia Maze, please type in" +
								" a valid number to select your options:");
			System.out.println("1. New Game");
			System.out.println("2. Load Saved Game");
			System.out.println("3. Help");
			System.out.println("4. Cheats Menu");
			System.out.println("5. Exit");
			
			if (myInput.nextInt() == 1) {
				//put the original main code here or call a helper method that does that
			}
			
			else if (myInput.nextInt() == 2) {
				//loading and saving games are not implemented yet.
			}
			
			else if (myInput.nextInt() == 3) {
				//This is where a series of instructions are printed onto the console
				//before sending you back to the main menu options.
			}
			
			else if (myInput.nextInt() == 4) {
				//This is where a text-based cheats menu would be
				System.out.println("You picked the cheats menu when there's nothing here!");
			}
			
			else if (myInput.nextInt() == 5) {
				myGameDone = true;
			}
			
			else {
				System.out.print("Invalid input, please try again.");
				System.out.print("1. New Game");
				System.out.print("2. Load Saved Game");
				System.out.print("3. Help");
				System.out.print("4. Cheats Menu");
				System.out.print("5. Exit");
			}
		}
		
		
	}
}