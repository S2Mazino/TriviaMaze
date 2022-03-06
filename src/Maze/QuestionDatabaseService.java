package Maze;

/**
 * @author David, Nordine, Boda, Brianna
 *
 */

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.sqlite.SQLiteDataSource;


public class QuestionDatabaseService {

	private Map<Integer, QuestionBean> myQuestions = new HashMap<Integer, QuestionBean>();
	private Integer myQuestionNumber = 0;
	private SQLiteDataSource myDs = null;
	private Connection myConn = null;

	enum ColumnName {
		QUESTION,
		CHOICES,
		ANSWER;
	}
	
	/**
	 *  Creates an iterator that can be used. 
	 * @return An Iterator<QuestionBean>
	 */
	public Iterator<QuestionBean> iterator(){
		return myQuestions.values().iterator();
		
//		(new ArrayList<QuestionBean>()).sort(new Comparator(){
//
//			@Override
//			public int compare(Object arg0, Object arg1) {
//				arg0.getQuestionNumber.compareTo(arg1.getQuestionNumber());
//			}});
	}
	
	/**
	 * Constructor to create all the connections to the database. 
	 */
	public QuestionDatabaseService() {
		try {
			myDs = new SQLiteDataSource();
			myDs.setUrl("jdbc:sqlite:triviaMaze.db");
			myConn = myDs.getConnection();
		} catch ( SQLException e ) {
			e.printStackTrace();
		} finally {
			if(myConn != null) {
				try {
					myConn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Chooses A question from the myQuestions map and returns it. 
	 * @return A chosen QuestionBean
	 * @throws SQLException
	 */
	public QuestionBean getQuestionBean() throws SQLException {
		QuestionBean q = null;
		for(int i = 0; i < myQuestions.size(); i++) {
			q = myQuestions.get(i);
			if(!q.isAsked()) {
				q.setAsked();
				return q;
			}
		}
		q = new QuestionBean("No more questions in the database. ", "No more questions in the database. ", "No more questions in the database. ");
		return q;
	}
	/**
	 * Grabs all the questions from the question database and stores them in the map myQuestions. 
	 * 
	 * @param theTableName String representing the table name. 
	 * @param theDs the SQLiteDataSource. 
	 */
	public void getQuestionDataFromDatabase() {
		String query = "SELECT * FROM QuestionTable";
		try ( Connection conn = myDs.getConnection();
				Statement stmt = conn.createStatement(); ) {			
			ResultSet rs = stmt.executeQuery(query);
			while ( rs.next() ) { 
				Integer questionNumber = rs.getInt("QUESTIONNUMBER");
				QuestionBean q = new QuestionBean(rs.getString(ColumnName.QUESTION.name()), rs.getString(ColumnName.CHOICES.name()),rs.getString(ColumnName.ANSWER.name()));
				myQuestions.put(questionNumber, q);
			}
		} catch ( SQLException e ) {
			e.printStackTrace();
		} finally {
			if(myConn != null) {
				try {
					myConn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}
	/**
	 * Creates a table and populates it with question rows. It sets up the game for the user. 
	 */
	public void gameStartUp() {
		createTable("QuestionTable");
 		addQuestion("QuestionTable", "True or False, all programming languages become assemly code when compiled.", "True,False", "False");
 		addQuestion("QuestionTable", "What was the first commercially available computer programming language?", "FORTRAN,Java,C,LISP", "FORTRAN");
 		addQuestion("QuestionTable", "True or False, Python was released before Java.", "True,False", "True");
 		addQuestion("QuestionTable", "Who first coined the term Computer scientist?", "Steve Jobs,George Forsythe,Howard Aiken,Charles Bachman", "George Forsythe");
 		addQuestion("QuestionTable", "When was Java initially released?", "2004,1991,2000,1995", "1995");
 		addQuestion("QuestionTable", "What was the first computer ever built?", "The ENIAC (Electronic Numerical Integrator and Computer)", "The ENIAC (Electronic Numerical Integrator and Computer)");
 		getQuestionDataFromDatabase();
	}
	/**
	 * Creates a Question table with the given table name. 
	 * 
	 * @param theTableName is a String representing the table name. 
	 * @param theDs is the data source. 
	 */
	public void createTable(String theTableName) {
		String query = "CREATE TABLE IF NOT EXISTS " + theTableName + "( " + "QUESTIONNUMBER INTEGER NOT NULL, " + ColumnName.QUESTION.name() + " TEXT NOT NULL, " + ColumnName.CHOICES.name() + " TEXT NOT NULL, " +  ColumnName.ANSWER.name() + " TEXT NOT NULL )";
		try ( Connection conn = myDs.getConnection();
				Statement stmt = conn.createStatement(); ) {
			stmt.executeUpdate( query );
		} catch ( SQLException e ) {
			e.printStackTrace();
		} finally {
			if(myConn != null) {
				try {
					myConn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
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
		String query = "INSERT INTO " + theTableName + " ( QUESTIONNUMBER, " + ColumnName.QUESTION.name() + ", " + ColumnName.CHOICES.name() + ", " + ColumnName.ANSWER.name() + " ) VALUES ( '" + myQuestionNumber + "', '" + theQuestion + "', '" + theChoices + "', '" + theAnswer + "' )";
		myQuestionNumber++;
		try ( Connection conn = myDs.getConnection();
				Statement stmt = conn.createStatement(); ) {
			stmt.executeUpdate( query );
		} catch ( SQLException e ) {
			e.printStackTrace();
			System.exit( 0 );
		} finally {
			if(myConn != null) {
				try {
					myConn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
