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

	/**
	 * 
	 * @return the question to be displayed. 
	 */
	public String getQuestion() {
		return myQuestion;
	}
	/**
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
	 * 
	 * Grabs a question from the database and assigns it to the question variable. 
	 *
	 */
	public void getQuestionData(String theTableName, SQLiteDataSource theDs) {
		//String countQuery = "SELECT COUNT(*) FROM " + theTableName;
		String query = "SELECT * FROM " + theTableName;
		try ( Connection conn = theDs.getConnection();
			Statement stmt = conn.createStatement(); ) {
			ResultSet rs = stmt.executeQuery(query);
			String[] tempArr = new String[3];
			while ( rs.next() ) {
				myUsedQuestions.add(rs.getInt("QUESTIONNUMBER"));
				tempArr[0] = rs.getString("QUESTION");
				tempArr[1] = rs.getString("CHOICES");
				tempArr[2] = rs.getString("ANSWER");
				myQuestions.add(tempArr);
//				Integer questionNum = rs.getInt("QUESTIONNUMBER");
//				if(!myUsedQuestions.contains(questionNum)) {
//					myUsedQuestions.add(questionNum);
//					myQuestion = rs.getString("QUESTION");
//					myCorrectAnswer = rs.getString("ANSWER");
//					String answerChoices = rs.getString("CHOICES");
//					myChoices = answerChoices.split("[,]", 0);
//				} else {
//					myQuestion = "There is no more questions in the dataBase. ";
//				}
			}
//			for(int i = 0; i < myQuestions.size(); i++) {
//				int num =0; 
//						//(int) ((Math.random() * (myQuestions.size() - 0)) + 0);
//				if(myUsedQuestions.contains(num)) {
//					myUsedQuestions.remove(num);
//					myQuestion = myQuestions.get(num)[0];
//	 				myCorrectAnswer = myQuestions.get(num)[1];
//					String answerChoices = myQuestions.get(num)[2];
//					myChoices = answerChoices.split("[,]", 0);
//					myQuestions.remove(num);
//					return;
//				}
//			}
//			myQuestion = "There is no more questions in the dataBase. ";
		} catch ( SQLException e ) {
			e.printStackTrace();
			System.exit( 0 );
		}
		
	}
	
	public void setQuestionData() {
		System.out.println("==================================");
		System.out.println(myUsedQuestions.size());
		System.out.println("==================================");
		for(int i = 0; i < myQuestions.size(); i++) {
			Integer num = 1;
					//(int) ((Math.random() * (myQuestions.size() - 0)) + 0); 
			int num2 = num;
					//(int) ((Math.random() * (myQuestions.size() - 0)) + 0);
			if(!myQuestions.isEmpty()) {
				if(myUsedQuestions.contains(num)) {
					myUsedQuestions.remove(num);
					myQuestion = myQuestions.get(num2)[0];
					String answerChoices = myQuestions.get(num2)[1];
					myChoices = answerChoices.split("[,]", 0);
	 				myCorrectAnswer = myQuestions.get(num2)[2];
					String[] temp = myQuestions.remove(num2);
					return;
				}
			}
		}
		myQuestion = "There is no more questions in the dataBase. ";
		myChoices[0] = "There is no more questions in the dataBase. ";
			myCorrectAnswer = "There is no more questions in the dataBase. ";
	}
	/**
	 * 
	 * Sets the answer variable with the users answer. 
	 * @param theChoice
	 */
	public void setChoice(String theChoice) {
		myUserAnswer = theChoice;
	}
	/**
	 * 
	 * checks to see if the user provided answer is correct. 
	 * @return Boolean representing if the answer is correct. 
	 */
	public boolean isCorrect() {
		if(myCorrectAnswer != null && !myCorrectAnswer.isEmpty() && myUserAnswer != null && !myUserAnswer.isEmpty() && myUserAnswer.equals(myCorrectAnswer)) {
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
		display += (myQuestion + "\n");
		for(int i = 0; i < myChoices.length; i++) {
			display += (myChoices[i] + "\n");
		}
		return display;
	}
	/**
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