import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.sqlite.SQLiteDataSource;

/**
 * 
 */

/**
 * @author Nordine, David, Boda, Brianna
 *
 */
public class Question {
	private String question = "";
	private String correctAnswer = "";
	private String userAnswer = "";
	private String[] choices = new String[4];
	private Integer questionNumber = 1;
	ArrayList<Integer> usedQuestions = new ArrayList<Integer>();

	/**
	 * 
	 * Grabs a question from the database and assigns it to the question variable. 
	 *
	 */
	public void getQuestionData(String tableName, SQLiteDataSource ds) {
		String query = "SELECT * FROM " + tableName;
		try ( Connection conn = ds.getConnection();
				Statement stmt = conn.createStatement(); ) {

			ResultSet rs = stmt.executeQuery(query);
			while ( rs.next() ) {
				Integer questionNum = rs.getInt("QUESTIONNUMBER");
				if(usedQuestions.contains(questionNum)) {
					question = rs.getString("QUESTION");
					correctAnswer = rs.getString("ANSWER");
					String answerChoices = rs.getString("CHOICES");
					choices = answerChoices.split("[,]", 0);
				}
			}
			question = "There is no more questions in the dataBase. ";
		} catch ( SQLException e ) {
			e.printStackTrace();
			System.exit( 0 );
		}
		
	}
	/**
	 * 
	 * Sets the answer variable with the users answer. 
	 * @param choice
	 */
	public void setChoice(String choice) {
		userAnswer = choice;
	}
	/**
	 * 
	 * checks to see if the user provided answer is correct. 
	 * @return Boolean representing if the answer is correct. 
	 */
	public boolean isCorrect() {
		if(correctAnswer != null && !correctAnswer.isEmpty() && userAnswer != null && !userAnswer.isEmpty() && userAnswer.equals(correctAnswer)) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 
	 * Generates a String that will be used to display the question and the choices. 
	 * 
	 * @return A String representing the question display. 
	 *
	 */
	public String display() {
		String display = "";
		display += (question + "\n");
		for(int i = 0; i < choices.length; i++) {
			display += (choices[i] + "\n");
		}
		return display;
	}
	/**
	 * 
	 * @param tableName is a String representing the table name. 
	 * @param ds is the data source. 
	 */
	public void createTable(String tableName, SQLiteDataSource ds) {
		String query = "CREATE TABLE IF NOT EXISTS " + tableName + "( " + "QUESTIONNUMBER INTEGER NOT NULL, " + "QUESTION TEXT NOT NULL, " + "CHOICES TEXT NOT NULL, " + "ANSWER TEXT NOT NULL )";
        try ( Connection conn = ds.getConnection();
                Statement stmt = conn.createStatement(); ) {
              stmt.executeUpdate( query );
          } catch ( SQLException e ) {
              e.printStackTrace();
              System.exit( 0 );
          }
	}
	/**
	 * 
	 * @param tableName is a String representing the table name. 
	 * @param question is a String representing the question. 
	 * @param choices is a comma separates String of answer choices. 
	 * @param answer is a String representing the answer. 
	 * @param ds is the data source. 
	 */
	public void addQuestion(String tableName, String question, String choices, String answer, SQLiteDataSource ds) {
		 String query = "INSERT INTO " + tableName + " ( QUESTIONNUMBER, QUESTION, CHOICES, ANSWER ) VALUES ( '" + questionNumber + "', '" + question + "', '" + choices + "', '" + answer + "' )";
		 questionNumber++;
	        try ( Connection conn = ds.getConnection();
	              Statement stmt = conn.createStatement(); ) {
	            stmt.executeUpdate( query );
	        } catch ( SQLException e ) {
	            e.printStackTrace();
	            System.exit( 0 );
	        }
	}
}