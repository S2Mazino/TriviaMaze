import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;

import org.sqlite.SQLiteDataSource;

/**
 * @author Nordine, David, Boda, Brianna
 *
 */
public class Question {
	private String myQuestion = "";
	private String myCorrectAnswer = "";
	private String myUserAnswer = "";
	private String[] myChoices = new String[4];
	private Integer myQuestionNumber = 1;
	private ArrayList<Integer> myUsedQuestions = new ArrayList<Integer>();
	private ArrayList<String[]> myQuestions = new ArrayList<String[]>();
	private Integer num;

	/**
	 * gets myQuestion. 
	 * 
	 * @return the question to be displayed. 
	 */
	public String getQuestion() {
		setQuestionData();
		return myQuestion;
	}
	/**
	 * gets myChoices. 
	 * 
	 * @return the answer choices to be displayed. 
	 */
	public String[] getChoices() {
		return myChoices;
	}

	public void printChoices() {
		for(int i = 0; i < myChoices.length; i++) {
			System.out.println(myChoices[i]);
		}
	}
	/**
	 * Grabs all the questions from the question database and stores them in an ArrayList. 
	 * 
	 * @param theTableName String representing the table name. 
	 * @param theDs the SQLiteDataSource. 
	 */
	public void getQuestionData(String theTableName, SQLiteDataSource theDs) {
		String query = "SELECT * FROM " + theTableName;
		try ( Connection conn = theDs.getConnection();
				Statement stmt = conn.createStatement(); ) {			
			ResultSet rs = stmt.executeQuery(query);
			while ( rs.next() ) {
				String[] tempArr = new String[3];
				myUsedQuestions.add(rs.getInt("QUESTIONNUMBER"));
				tempArr[0] = rs.getString("QUESTION");
				tempArr[1] = rs.getString("CHOICES");
				tempArr[2] = rs.getString("ANSWER");
				myQuestions.add(tempArr);
			}
			num = myQuestions.size() - 1;

		} catch ( SQLException e ) {
			e.printStackTrace();
			System.exit( 0 );
		}

	}

	/**
	 * Picks a question and sets the data for that question. 
	 */
	private void setQuestionData() {
		for(int i = 0; i < myQuestions.size(); i++) {
			int num2 = num;
			if(!myQuestions.isEmpty()) {
				if(myUsedQuestions.contains(num)) {
					myUsedQuestions.remove(num);
					myQuestion = myQuestions.get(num2)[0];
					String answerChoices = myQuestions.get(num2)[1];
					myChoices = answerChoices.split("[,]", 0);
					myCorrectAnswer = myQuestions.get(num2)[2];
					myQuestions.remove(num2);
					num = num - 1;
					return;
				}
			}
		}
		myQuestion = "There is no more questions in the dataBase. ";
		myChoices[0] = "There is no more questions in the dataBase. ";
		myCorrectAnswer = "There is no more questions in the dataBase. ";
	}
	/**
	 * Sets the answer variable with the users answer. 
	 * 
	 * @param theChoice
	 */
	public void setChoice(String theChoice) {
		myUserAnswer = theChoice;
	}
	
	/**
	 * checks to see if the user provided answer is correct. 
	 * 
	 * @return Boolean representing if the answer is correct. 
	 */
	public boolean isCorrect() {
		System.out.println("correct: " + myCorrectAnswer);
		System.out.println("user: " + myUserAnswer);
		if(myCorrectAnswer != null && !myCorrectAnswer.isEmpty() && myUserAnswer != null && !myUserAnswer.isEmpty()){
			if(myUserAnswer.equals(myCorrectAnswer)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * Generates a String that will be used to display the question and the choices. 
	 * 
	 * @return A String representing the question display. 
	 *
	 */
	public String display() {
		String display = "";
		display += (myQuestion + "\n");
		for(int i = 0; i < myChoices.length; i++) {
			display += (myChoices[i] + "\n");
		}
		return display;
	}
	/**
	 * Creates a Question table with the given table name. 
	 * 
	 * @param theTableName is a String representing the table name. 
	 * @param theDs is the data source. 
	 */
	public void createTable(String theTableName, SQLiteDataSource theDs) {
		String query = "CREATE TABLE IF NOT EXISTS " + theTableName + "( " + "QUESTIONNUMBER INTEGER NOT NULL, " + "QUESTION TEXT NOT NULL, " + "CHOICES TEXT NOT NULL, " + "ANSWER TEXT NOT NULL )";
		try ( Connection conn = theDs.getConnection();
				Statement stmt = conn.createStatement(); ) {
			stmt.executeUpdate( query );
		} catch ( SQLException e ) {
			e.printStackTrace();
			System.exit( 0 );
		}
	}
	/**
	 * Adds a question to the table with the given table name. 
	 * 
	 * @param theTableName is a String representing the table name. 
	 * @param theQuestion is a String representing the question. 
	 * @param theChoices is a comma separates String of answer choices. 
	 * @param theAnswer is a String representing the answer. 
	 * @param theDs is the data source. 
	 */
	public void addQuestion(String theTableName, String theQuestion, String theChoices, String theAnswer, SQLiteDataSource theDs) {
		String query = "INSERT INTO " + theTableName + " ( QUESTIONNUMBER, QUESTION, CHOICES, ANSWER ) VALUES ( '" + myQuestionNumber + "', '" + theQuestion + "', '" + theChoices + "', '" + theAnswer + "' )";
		myQuestionNumber++;
		try ( Connection conn = theDs.getConnection();
				Statement stmt = conn.createStatement(); ) {
			stmt.executeUpdate( query );
		} catch ( SQLException e ) {
			e.printStackTrace();
			System.exit( 0 );
		}
	}
}