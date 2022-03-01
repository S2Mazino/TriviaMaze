import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.sqlite.SQLiteDataSource;

public class QuestionsUtil {

	private ArrayList<Integer> myUsedQuestions = new ArrayList<Integer>();
	private ArrayList<String[]> myQuestions = new ArrayList<String[]>();
	private Integer myQuestionNumber = 0;
	private Integer num;
	private SQLiteDataSource ds = null;
	private Connection conn = null;

	public QuestionsUtil() {
		try {
			ds = new SQLiteDataSource();
			ds.setUrl("jdbc:sqlite:triviaMaze.db");
			conn = ds.getConnection();
		} catch ( SQLException e ) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	public String[] getQuestion() {
		String[] q = new String[3];
		for(int i = 0; i < myQuestions.size(); i++) {
			int num2 = num;
			if(!myQuestions.isEmpty()) {
				if(myUsedQuestions.contains(num)) {
					myUsedQuestions.remove(num);
					q = myQuestions.remove(num2);
					num = num - 1;
					System.out.println("=================");
					return q;
				}
			}
		}
		q[0] = "No more questions in the database. ";
		q[1] = "No more questions in the database. ";
		q[2] = "No more questions in the database. ";
		return q;
	}
	/**
	 * Grabs all the questions from the question database and stores them in an ArrayList. 
	 * 
	 * @param theTableName String representing the table name. 
	 * @param theDs the SQLiteDataSource. 
	 */
	public void getQuestionData() {
		String query = "SELECT * FROM QuestionTable";
		try ( Connection conn = ds.getConnection();
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
	 * Creates a Question table with the given table name. 
	 * 
	 * @param theTableName is a String representing the table name. 
	 * @param theDs is the data source. 
	 */
	public void createTable(String theTableName) {
		String query = "CREATE TABLE IF NOT EXISTS " + theTableName + "( " + "QUESTIONNUMBER INTEGER NOT NULL, " + "QUESTION TEXT NOT NULL, " + "CHOICES TEXT NOT NULL, " + "ANSWER TEXT NOT NULL )";
		try ( Connection conn = ds.getConnection();
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
	public void addQuestion(String theTableName, String theQuestion, String theChoices, String theAnswer) {
		String query = "INSERT INTO " + theTableName + " ( QUESTIONNUMBER, QUESTION, CHOICES, ANSWER ) VALUES ( '" + myQuestionNumber + "', '" + theQuestion + "', '" + theChoices + "', '" + theAnswer + "' )";
		myQuestionNumber++;
		try ( Connection conn = ds.getConnection();
				Statement stmt = conn.createStatement(); ) {
			stmt.executeUpdate( query );
		} catch ( SQLException e ) {
			e.printStackTrace();
			System.exit( 0 );
		}
	}
}
