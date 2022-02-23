
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
				System.out.println("To play the Trivia Maze, you can select a New Game" +
						" to start a new game file and start playing the maze.");
				System.out.println("The first number you enter when starting a new game" +
						"determines the number of rows your maze has.");
				System.out.println("The second number you enter when starting a new game" +
						"determines the number of columns your maze has.");
				System.out.println("The Trivia Maze will be created based on the parameters" +
						"And the goal is the reach the End Room to wind the game!");
				System.out.println("Press Enter to continue.");
				if (myInput.next() == " ") {
					System.out.println("The starting point is located at the top-right corner.");
					System.out.println("The exit is located at the farthest bottom-left corner.");
					System.out.println("You are represented by the 'u' avatar! You can move this " +
										"avatar through doors.");
					System.out.println("Press the N key for moving up, press the S key to move down.");
					System.out.println("Press the E key for moving left, press the W key to move right.");
					System.out.println("Press Enter to continue. Press B to go back to the previous page.");
				}
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