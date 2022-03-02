
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
		//Adding questions
		SQLiteDataSource ds = null;
		Connection conn = null;
		 try {
	            ds = new SQLiteDataSource();
	            ds.setUrl("jdbc:sqlite:triviaMaze.db");
	             conn = ds.getConnection();
//	     		Question question = new Question();
	     		QuestionsUtil util = new QuestionsUtil();
	     		util.createTable("QuestionTable");
	     		util.addQuestion("QuestionTable", "True or false, all programming languages become assemly code when compiled.", "True,False", "False");
	     		util.addQuestion("QuestionTable", "What was the first commercially available computer programming language?", "FORTRAN,Java,C,LISP", "FORTRAN");
	     		util.addQuestion("QuestionTable", "True or false, Python was released before Java.", "True,False", "True");
	     		util.addQuestion("QuestionTable", "Who first coined the term Computer scientist?", "Steve Jobs,George Forsythe,Howard Aiken,Charles Bachman", "George Forsythe");
	     		util.addQuestion("QuestionTable", "When was Java initially released?", "2004,1991,2000,1995", "1995");
	     		util.addQuestion("QuestionTable", "What was the first computer ever built?", "The ENIAC (Electronic Numerical Integrator and Computer)", "The ENIAC (Electronic Numerical Integrator and Computer)");
//	    		

	     		Question question = new Question();
//	     		util.getQuestionData();
//	    		question.setQuestionData();
	    		System.out.println("Question 1:");
	    		System.out.println(question.getQuestion());
	    		System.out.println("Choices 1:");
	    		question.printChoices();
	    		System.out.println("IsCorrect: ");
	    		question.setChoice("The ENIAC (Electronic Numerical Integrator and Computer)");
	    		System.out.println(question.isCorrect());
	    		System.out.println();
//	    		question.setQuestionData();
	    		System.out.println("Question 2:");
	    		System.out.println(question.getQuestion());
	    		System.out.println("Choices 2:");
	    		question.printChoices();
	    		System.out.println("IsCorrect: ");
	    		question.setChoice("1995");
	    		System.out.println(question.isCorrect());
	    		System.out.println();
//	    		question.setQuestionData();
	    		System.out.println("Question 3:");
	    		System.out.println(question.getQuestion());
	    		System.out.println("Choices 3:");
	    		question.printChoices();
	    		System.out.println("IsCorrect: ");
	    		question.setChoice("George Forsythe");
	    		System.out.println(question.isCorrect());
	    		System.out.println();           
	             
	        } catch ( SQLException e ) {
	            e.printStackTrace();
	            System.exit(0);
	        }finally {
	        	if(conn != null) {
	        		try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
	        	}
	        }
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
	}

}