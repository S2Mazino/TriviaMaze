package Maze;

import java.sql.SQLException;
import java.util.Scanner;


/**
 * @author Nordine, David, Boda, Brianna
 *
 */
public class Main {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		 
		// TODO Auto-generated method stub
		final Scanner myInput = new Scanner(System.in);
		String response = "placeholder";
		String directionHolder;
		Maze maze = null;
		int timesPlayed = 0;
		int winCounter = 0;
		final String NEWGAMESELECT = "1";
		final String LOADGAMESELECT = "2";
		final String HELPSELECT = "3";
		final String CHEATSELECT = "4";
		final String EXITGAME = "5";
		boolean cheatFlag = false;
		boolean myGameDone = false;
		
		while (!myGameDone) {
			
			displayIntroMenu();	
			response = myInput.nextLine();
			
			if (response.equals(NEWGAMESELECT)) {
				boolean cheatUsed = false;
				//initialize the maze
				maze = new Maze();
				//increment the times played counter
				timesPlayed++;
				//Initialize the question database
				QuestionDatabaseService QuestionBase = new QuestionDatabaseService();
				QuestionBase.gameStartUp();
				QuestionBean question;
				//while the maze is not set to a win state and there's still a path availible
				while(!maze.win() && maze.hasPath()) {
					//display the maze
					maze.displayMaze();
					//show all available pathways
					System.out.println(maze.availableRoom());
					//take in the user's input on where they want to go
					System.out.println("Where would you like to move?: ");
					response = myInput.nextLine();
					directionHolder = response;
					//call the move function in order to move.
					//if there is a path in this maze
					if (maze.canMove(directionHolder)) {
						//get the question, display the question, and allow the user to input their answer.
						question = QuestionBase.getQuestionBean();
						System.out.println(question.getQuestion());
						question.printChoices();
						//take in the user's input
						response = myInput.nextLine();
						//set the user input to check if it's the correct answer
						question.setChoice(response);
						//if the question is incorrect, check which direction the
						//user was trying to go to, and lock off that pathway.
						System.out.println(question.isCorrect());
						if (!question.isCorrect()) {
							System.out.println("I'm sorry, that answer is incorrect.");
							if (directionHolder.equals("N")) {
								maze.lockRoom(maze.getMyRow() - 1, maze.getMyCol());
							}
							if (directionHolder.equals("S")) {
								maze.lockRoom(maze.getMyRow() + 1, maze.getMyCol());
							}
							if (directionHolder.equals("E")) {
								maze.lockRoom(maze.getMyRow(), maze.getMyCol() + 1);
							}
							if (directionHolder.equals("W")) {
								maze.lockRoom(maze.getMyRow(), maze.getMyCol() - 1);
							}
						}
						//if the user was correct, then they can move to their desired room.
						else {
							if (cheatFlag && !cheatUsed) {
								maze.move("S");
								maze.move("S");
								maze.move("S");
								maze.move("E");
								maze.move("E");
								cheatUsed = true;
							}
							else {
								maze.move(directionHolder);
							}
						}
						//end of checking HasPath()
					}
					//run through the while loop again.
				}
				//if the game was ended with the win flag being set to true
				//congratulate the player. Otherwise, acknowledge the loss.
				if (maze.win()) {
					System.out.println("Congratulations, you won the Trivia Maze!");
					//maze.resetVisit();
					winCounter++;
					System.out.println("You've won a total of: " + winCounter + " times!");
					System.out.println("You've played a total of: " + timesPlayed + " times!");
					boolean validAnswer = false;
					while (!validAnswer) {
						displayRetryMainGame();
						response = myInput.nextLine();
						if (response.equals("X")) {
							validAnswer = true;
							response = EXITGAME;
						}
						else if (response.equals("P")) {
							validAnswer = true;
							continue;
						}
					}
				}
				else {
					System.out.println("Game Over! You didn't beat the Trivia Maze.");
					System.out.println("You've played a total of: " + timesPlayed + " times!");
					boolean validAnswer = false;
					while (!validAnswer) {
						displayRetryMainGame();
						response = myInput.nextLine();
						if (response.equals("X")) {
							validAnswer = true;
							response = EXITGAME;
						}
						else if (response.equals("P")) {
							validAnswer = true;
							continue;
						}
					}
				}
			}
			
			else if (response.equals(LOADGAMESELECT)) {
				//loading and saving games are not implemented yet.
			}
			
			else if (response.equals(HELPSELECT)) {
				//This is where a series of instructions are printed onto the console
				//before sending you back to the main menu options.
				boolean helpmenudone = false;
				while (!helpmenudone) {
					displayOpeningInstr();
					response = myInput.nextLine();
					System.out.println("Press N to continue.");
					if (response.equals("N")) {
						displayPlayerInstr();
						System.out.println("Press N to continue.");
						response = myInput.nextLine();
						if (response.equals("N")) {
							displayDoorInstr();
							System.out.println("Press N to continue.");
							response = myInput.nextLine();
							if (response.equals("N")) {
								displaySaveLoadInstr();
								System.out.println("Press N to go back to the main menu.");
								response = myInput.nextLine();
								if (response.equals("N")) {
									helpmenudone = true;
								}
							}
						}
					}
				}
			}
			
			else if (response.equals(CHEATSELECT)) {
				//This is where a text-based cheats menu would be
				System.out.println("Would you like to turn on 'Just Two Questions?'");
				if (!cheatFlag) {
					System.out.println("Just Two Questions is currently not active."); 
				}
				else {
					System.out.println("Just Two Questions is currently active."); 
				}
				System.out.println("This cheat allows you to only need two questions to finish the maze.");
				System.out.println("Press Y in order to activate or deactivate this cheat!");
				System.out.println("Otherwise, press any key to go back to the main menu!");
				response = myInput.nextLine();
				if (response.equals("Y")) {
					if (!cheatFlag) {
						cheatFlag = true; 
					}
					else {
						cheatFlag = false; 
					}
				}
				else {
					continue;
				}
			}
			
			else if (response.equals(EXITGAME)) {
				myGameDone = true;
			}
			
			else {
				System.out.println("Invalid input, please try again.");
			}
		}
		myInput.close();
	}
	
	/**
	 * This function is the main menu screen, and displays all possible options
	 * for the player.
	 */
	public static void displayIntroMenu() {
		System.out.println("Hello, welcome to the Trivia Maze, please type in" +
				" a valid number to select your options:");
		System.out.println("1. New Game");
		System.out.println("2. Load Saved Game");
		System.out.println("3. Help");
		System.out.println("4. Cheats Menu");
		System.out.println("5. Exit");
	}
	
	/**
	 * This function explains the player controls for navigating the
	 * Trivia maze.
	 */
	public static void displayPlayerInstr() {
		System.out.println("The starting point is located at the top-right corner.");
		System.out.println("The exit is located at the farthest bottom-left corner.");
		System.out.println("You are represented by the 'u' avatar! You can move this " +
							"avatar through doors.");
		System.out.println("Press the N key for moving up, press the S key to move down.");
		System.out.println("Press the E key for moving left, press the W key to move right.");
	}
	
	/**
	 * This function displays text that describes how to the doors work in the
	 * Trivia maze, and the conditions for losing in the Trivia maze.
	 */
	public static void displayDoorInstr() {
		System.out.println("When you go through a door (represented by a 'o') you will be asked a question!");
		System.out.println("Select your answer with the shown key.");
		System.out.println("If you select the right answer, you will go through the door and into the room.");
		System.out.println("Select the wrong answer and the door will be blocked (represented by an 'x') and "
							+ " you will be unable to pass through the door. These doors cannot be opened again!");
		System.out.println("If you cannot get to the exit or you become entrapped in a room, it's game over!");
	}
	
	/**
	 * This function displays text that describes how to play the trivia game and
	 * the size of the trivia maze you'll be going through.
	 */
	public static void displayOpeningInstr() {
		System.out.println("To play the Trivia Maze, you can select a New Game" +
				" to start a new game file and start playing the maze.");
		System.out.println("The Trivia Maze will be a 4 by 4 square" +
				" and the goal is the reach the End Room at the bottom right corner to win the game!");
	}
	
	/**
	 * This function displays text that describes saving and loading data
	 * and how to do that.
	 */
	public static void displaySaveLoadInstr() {
		System.out.println("To save a current game, while playing the game, press the" +
				" 'F' key in order to save your progress..");
		System.out.println("To load your last save, go back to the main menu" +
				" and select Load Saved Game.");
		System.out.println("While playing the game, press the 'X' key to leave the current game" +
				" without saving your progress.");
	}
	
	/**
	 * This function exists to display the text describing how to return to the main
	 * menu or to exit the game after playing through the Trivia Maze, win or lose.
	 */
	public static void displayRetryMainGame() {
		System.out.println("Would you like to return to the main menu?");
		System.out.println("To go back to the main menu, press P.");
		System.out.println("Otherwise, press X to exit this screen and close the game!");
	}
}
