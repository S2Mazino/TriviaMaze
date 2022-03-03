package Maze;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import org.sqlite.SQLiteDataSource;

/**
 * @author Nordine
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//=============================================================================================
		// I am running in to a problem where I am trying to print the different questions but it is only printing the last question. It is a mess now but I will fix it when I solve the problem. 
		//Adding questions
//		SQLiteDataSource ds = null;
//		Connection conn = null;
//		 try {
//	            ds = new SQLiteDataSource();
//	            ds.setUrl("jdbc:sqlite:triviaMaze.db");
//	             conn = ds.getConnection();
//	     		Question question = new Question();
//	    		question.createTable("QuestionTable", ds);
//	    		question.addQuestion("QuestionTable", "question", "choice", "choice", ds);
//	    		question.addQuestion("QuestionTable", "question1", "choice1", "choice1", ds);
//	    		question.addQuestion("QuestionTable", "question2", "choice2", "choice2", ds);
//	    		question.addQuestion("QuestionTable", "What was the first computer ever built?", "The ENIAC (Electronic Numerical Integrator and Computer)", "The ENIAC (Electronic Numerical Integrator and Computer)", ds);
//	    		
//	    		question.getQuestionData("QuestionTable", ds);
//	    		question.setQuestionData();
//	    		System.out.println("Question 1:");
//	    		System.out.println(question.getQuestion());
//	    		System.out.println("Choices 1:");
//	    		question.printChoices();
//	    		System.out.println("IsCorrect: ");
//	    		question.setChoice("The ENIAC (Electronic Numerical Integrator and Computer)");
//	    		System.out.println(question.isCorrect());
//	    		System.out.println();
//	    		question.setQuestionData();
//	    		System.out.println("Question 2:");
//	    		System.out.println(question.getQuestion());
//	    		System.out.println("Choices 2:");
//	    		question.printChoices();
//	    		System.out.println("IsCorrect: ");
//	    		question.setChoice("choice2");
//	    		System.out.println(question.isCorrect());
//	    		System.out.println();
//	    		question.setQuestionData();
//	    		System.out.println("Question 3:");
//	    		System.out.println(question.getQuestion());
//	    		System.out.println("Choices 3:");
//	    		question.printChoices();
//	    		System.out.println("IsCorrect: ");
//	    		question.setChoice("choice1");
//	    		System.out.println(question.isCorrect());
//	    		System.out.println();
//	    		question.setQuestionData();
//	    		System.out.println("Question 4:");
//	    		System.out.println(question.getQuestion());
//	    		System.out.println("Choices 4:");
//	    		question.printChoices();
//	    		System.out.println("IsCorrect: ");
//	    		question.setChoice("choice1");
//	    		System.out.println(question.isCorrect());
//	             
//	             
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
		System.out.print("Input amount of rows(between 4 and 10): ");
		myRows = myInput.nextInt();
		System.out.print("Input amount of cols(between 4 and 10): ");
		myCols = myInput.nextInt();
		
		maze = new Maze(myRows, myCols);
		
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
		System.out.println("move? " + maze.move("S"));
		maze.displayMaze();
		System.out.println(maze.availableRoom());

		
		myInput.close();
	}

}