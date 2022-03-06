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
		//testing printing question and its data

	     		QuestionDatabaseService util = new QuestionDatabaseService();
	     		util.gameStartUp();

//	     		util.getQuestionData();

	     		
	    		QuestionBean q1 = util.getQuestionBean();

	    		System.out.println("Question 1:");
	    		System.out.println(q1.getQuestion());
	    		System.out.println("Choices 1:");
	    		q1.printChoices();
	    		System.out.println("IsCorrect: ");
	    		q1.setChoice("FaLse");
	    		System.out.println(q1.isCorrect());
	    		System.out.println();

	    		QuestionBean q2 = util.getQuestionBean();
	    		System.out.println("Question 1:");
	    		System.out.println(q2.getQuestion());
	    		System.out.println("Choices 1:");
	    		q2.printChoices();
	    		System.out.println("IsCorrect: ");
	    		q2.setChoice("FORTRAN");
	    		System.out.println(q2.isCorrect());
	    		System.out.println();
	    		
	    		QuestionBean q3 = util.getQuestionBean();
	    		System.out.println("Question 1:");
	    		System.out.println(q3.getQuestion());
	    		System.out.println("Choices 1:");
	    		q3.printChoices();
	    		System.out.println("IsCorrect: ");
	    		q3.setChoice("True");
	    		System.out.println(q3.isCorrect());
	    		System.out.println();
         
		 //=======================================================================================================
		 
		 
		// TODO Auto-generated method stub
		final Scanner myInput = new Scanner(System.in);
		Maze maze = null;
		int myRows;
		int myCols;
		
		
		//will add valid input condition later

		
		maze = new Maze();
		
		if(!maze.win()) {
			//if player hasnt won game yet
			//display maze
			//display the options(rooms)
			//they choose which direction
			//if(canMove(reponse))
			//ask question
			//display question option
			//ask for input to question
			//correct, move function
			//islocked() function
			//else reponse again
		}
		
		
		
//		maze.lockRoom(2, 1);
//		maze.lockRoom(2, 2);
//		maze.lockRoom(2, 3);
//		maze.lockRoom(2, 4);
		
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
		
		System.out.println("move? " + maze.canMove("S"));
		if(maze.canMove("S")) {
			maze.move("S");
		}
		
		maze.displayMaze();
		System.out.println(maze.availableRoom());

		
		myInput.close();
	}

}