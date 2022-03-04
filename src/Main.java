
import java.util.Scanner;

/**
 * @author Nordine, Brianna
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
		String response;
		Maze maze = null;
		Question question;
		int myRows;
		int myCols;
		final String NEWGAMESELECT = "1";
		final String LOADGAMESELECT = "2";
		final String HELPSELECT = "3";
		final String CHEATSELECT = "4";
		final String EXITGAME = "5";
		boolean myGameDone = false;
		
		
		while (!myGameDone) {
			System.out.println("Hello, welcome to the Trivia Maze, please type in" +
								" a valid number to select your options:");
			System.out.println("1. New Game");
			System.out.println("2. Load Saved Game");
			System.out.println("3. Help");
			System.out.println("4. Cheats Menu");
			System.out.println("5. Exit");
			
			response = myInput.next();
			if (response == NEWGAMESELECT) {
				//put the original main code here or call a helper method that does that
				System.out.print("Input amount of rows(between 4 and 10): ");
				myRows = myInput.nextInt();
				System.out.print("Input amount of cols(between 4 and 10): ");
				myCols = myInput.nextInt();
				maze = new Maze(myRows, myCols);
				while (!maze.win()) {
					maze.displayMaze();
					System.out.println(maze.availableRoom());
					
					System.out.println("move? " + maze.move("S"));
					maze.displayMaze();
					System.out.println(maze.availableRoom());
					response = myInput.next();
					maze.move(response);
					if (maze.hasPath()) {
						//question.getQuestionData(tableName, ds);
						question.display();
						response = myInput.next();
						question.setChoice(response);
						if (!question.isCorrect()) {
							//maze.lockRoom(theX, theY);
						}
					}
					
				}
			}
			
			else if (response == LOADGAMESELECT) {
				//loading and saving games are not implemented yet.
			}
			
			else if (response == HELPSELECT) {
				//This is where a series of instructions are printed onto the console
				//before sending you back to the main menu options.
				boolean helpmenudone = false;
				while (!helpmenudone) {
					displayOpeningInstr();
					response = myInput.next();
					System.out.println("Press N to continue.");
					if (response == "N") {
						displayPlayerInstr();
						System.out.println("Press N to continue.");
						response = myInput.next();
						if (response == "N") {
							displayDoorInstr();
							System.out.println("Press N to continue.");
							response = myInput.next();
							if (response == "N") {
								displaySaveLoadInstr();
								System.out.println("Press N to go back to the main menu.");
								response = myInput.next();
								if (response == "N") {
									helpmenudone = true;
								}
							}
						}
					}
				}
			}
			
			else if (response == CHEATSELECT) {
				//This is where a text-based cheats menu would be
				System.out.println("You picked the cheats menu when there's nothing here!");
			}
			
			else if (response == EXITGAME) {
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
	
	public static void displayPlayerInstr() {
		System.out.println("The starting point is located at the top-right corner.");
		System.out.println("The exit is located at the farthest bottom-left corner.");
		System.out.println("You are represented by the 'u' avatar! You can move this " +
							"avatar through doors.");
		System.out.println("Press the N key for moving up, press the S key to move down.");
		System.out.println("Press the E key for moving left, press the W key to move right.");
	}
	
	public static void displayDoorInstr() {
		System.out.println("When you go through a door (represented by a 'o') you will be asked a question!");
		System.out.println("Select your answer with the shown key.");
		System.out.println("If you select the right answer, you will go through the door and into the room.");
		System.out.println("Select the wrong answer and the door will be blocked (represented by an 'x') and "
							+ "you will be unable to pass through the door. These doors cannot be opened again!");
		System.out.println("If you cannot get to the exit or you become entrapped in a room, it's game over!");
	}
	
	public static void displayOpeningInstr() {
		System.out.println("To play the Trivia Maze, you can select a New Game" +
				" to start a new game file and start playing the maze.");
		System.out.println("The first number you enter when starting a new game" +
				"determines the number of rows your maze has.");
		System.out.println("The second number you enter when starting a new game" +
				"determines the number of columns your maze has.");
		System.out.println("The Trivia Maze will be created based on the parameters" +
				"And the goal is the reach the End Room to wind the game!");
	}
	
	public static void displaySaveLoadInstr() {
		System.out.println("To save a current game, while playing the game, press the" +
				"'F' key in order to save your progress..");
		System.out.println("To load your last save, go back to the main menu" +
				"and select Load Saved Game.");
		System.out.println("While playing the game, press the 'X' key to leave the current game" +
				" without saving your progress.");
	}
}