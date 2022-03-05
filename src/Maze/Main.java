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
		//=============================================================================================
		//Adding questions
//		SQLiteDataSource ds = null;
//		Connection conn = null;
//		 try {
//	            ds = new SQLiteDataSource();
//	            ds.setUrl("jdbc:sqlite:triviaMaze.db");
//	             conn = ds.getConnection();
//	     		Question question = new Question();
	     		QuestionDatabaseService util = new QuestionDatabaseService();
	     		util.gameStartUp();

	     		Question question = new Question();
//	     		util.getQuestionData();
	    		question.setQuestionData();
	    		System.out.println("Question 1:");
	    		System.out.println(question.getQuestion());
	    		System.out.println("Choices 1:");
	    		question.printChoices();
	    		System.out.println("IsCorrect: ");
	    		question.setChoice("The ENIAC (Electronic Numerical Integrator and Computer)");
	    		System.out.println(question.isCorrect());
	    		System.out.println();

	    		question.setQuestionData();
	    		System.out.println("Question 2:");
	    		System.out.println(question.getQuestion());
	    		System.out.println("Choices 2:");
	    		question.printChoices();
	    		System.out.println("IsCorrect: ");
	    		question.setChoice("1995");
	    		System.out.println(question.isCorrect());
	    		System.out.println();

	    		question.setQuestionData();
	    		System.out.println("Question 3:");
	    		System.out.println(question.getQuestion());
	    		System.out.println("Choices 3:");
	    		question.printChoices();
	    		System.out.println("IsCorrect: ");
	    		question.setChoice("George Forsythe");
	    		System.out.println(question.isCorrect());
	    		System.out.println();           
	             
//	        } catch ( SQLException e ) {
//	            e.printStackTrace();
//	            System.exit(0);
//	        }finally {
//	        	if(conn != null) {
//	        		try {
//						conn.close();
//					} catch (SQLException e) {
//						e.printStackTrace();
//					}
//	        	}
//	        }
		 //=======================================================================================================
		 
		 
		// TODO Auto-generated method stub
		final Scanner myInput = new Scanner(System.in);
		String response;
		String directionHolder;
		Maze maze = null;
		final int SET_ROWS = 4;
		final int SET_COLS = 4;
		final String NEWGAMESELECT = "1";
		final String LOADGAMESELECT = "2";
		final String HELPSELECT = "3";
		final String CHEATSELECT = "4";
		final String EXITGAME = "5";
		boolean myGameDone = false;
		

		
		
		maze.displayMaze();
		System.out.println(maze.availableRoom());
		//ask for which direction
		//can move?
		//ask(display) the question
		//ask for answer input
		//correct answer?
		//move
		//if not, tell them wrong answer lock the room and ask for another direction
		//if no more room, lost the game
		//System.out.println("move? " + maze.move("S"));
		maze.displayMaze();
		System.out.println(maze.availableRoom());
		
		
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
				//initialize the maze
				maze = new Maze(SET_ROWS, SET_COLS);
				//while the maze is not set to a win state
				while (!maze.win()) {
					//display the maze
					maze.displayMaze();
					//show all available pathways
					System.out.println(maze.availableRoom());
					//take in the user's input on where they want to go
					System.out.println("Where would you like to move?: ");
					response = myInput.next();
					directionHolder = response;
					//call the move function in order to move.
					//if there is a path in this maze
					if (maze.hasPath()) {
						//display the question and allow the user to input their answer.
						//question.getQuestionData(tableName, ds);
						question.display();
						//take in the user's input
						response = myInput.next();
						//set the user input to check if it's the correct answer
						question.setChoice(response);
						//if the question is incorrect, check which direction the
						//user was trying to go to, and lock off that pathway.
						if (!question.isCorrect()) {
							System.out.println("I'm sorry, that answer is incorrect.");
							if (directionHolder == "N") {
								maze.lockRoom(maze.getMyRow() - 1, maze.getMyCol());
							}
							if (directionHolder == "S") {
								maze.lockRoom(maze.getMyRow() + 1, maze.getMyCol());
							}
							if (directionHolder == "E") {
								maze.lockRoom(maze.getMyRow(), maze.getMyCol() + 1);
							}
							if (directionHolder == "W") {
								maze.lockRoom(maze.getMyRow(), maze.getMyCol() - 1);
							}
						}
						//if the user was correct, then they can move to their desired room.
						else {
							if (maze.canMove(response))
							maze.move(response);
						}
						//end of checking HasPath()
					}
					//last message it executes before the function ends.
					if (maze.win()) {
						System.out.println("Congratulations, you won the Trivia Maze!");
					} 
					//run through the while loop again.
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
		myInput.close();
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
