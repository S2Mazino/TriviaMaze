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
