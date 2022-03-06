package Maze;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.sqlite.SQLiteDataSource;


public class QuestionDatabaseService {

	private Map<Integer, QuestionBean> questions = new HashMap<Integer, QuestionBean>();
	private Integer myQuestionNumber = 0;
	private SQLiteDataSource ds = null;
	private Connection conn = null;

	enum ColumnName {
		COL1("QUESTION"),
		COL2("CHOICES"),
		COL3("ANSWER");
		String name;
		ColumnName(String name){
			this.name = name;
		}
	}

	public QuestionDatabaseService() {
		try {
			ds = new SQLiteDataSource();
			ds.setUrl("jdbc:sqlite:triviaMaze.db");
			conn = ds.getConnection();
		} catch ( SQLException e ) {
			e.printStackTrace();
		} finally {
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public QuestionBean getQuestionBean() throws SQLException {
		QuestionBean q = null;
		for(int i = 0; i < questions.size(); i++) {
			q = questions.get(i);
			if(!q.isAsked()) {
				q.setAsked();
				return q;
			}
		}
		q = new QuestionBean("No more questions in the database. ", "No more questions in the database. ", "No more questions in the database. ");
		return q;
	}
	/**
	 * Grabs all the questions from the question database and stores them in an ArrayList. 
	 * 
	 * @param theTableName String representing the table name. 
	 * @param theDs the SQLiteDataSource. 
	 */
	public void getQuestionDataFromDatabase() {
		String query = "SELECT * FROM QuestionTable";
		try ( Connection conn = ds.getConnection();
				Statement stmt = conn.createStatement(); ) {			
			ResultSet rs = stmt.executeQuery(query);
			while ( rs.next() ) { 
				Integer questionNumber = rs.getInt("QUESTIONNUMBER");
				QuestionBean q = new QuestionBean(rs.getString(ColumnName.COL1.name), rs.getString(ColumnName.COL2.name),rs.getString(ColumnName.COL3.name));
				questions.put(questionNumber, q);
			}
		} catch ( SQLException e ) {
			e.printStackTrace();
		} finally {
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}
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
		String query = "CREATE TABLE IF NOT EXISTS " + theTableName + "( " + "QUESTIONNUMBER INTEGER NOT NULL, " + ColumnName.COL1.name + " TEXT NOT NULL, " + ColumnName.COL2.name + " TEXT NOT NULL, " +  ColumnName.COL3.name + " TEXT NOT NULL )";
		try ( Connection conn = ds.getConnection();
				Statement stmt = conn.createStatement(); ) {
			stmt.executeUpdate( query );
		} catch ( SQLException e ) {
			e.printStackTrace();
		} finally {
			if(conn != null) {
				try {
					conn.close();
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
		String query = "INSERT INTO " + theTableName + " ( QUESTIONNUMBER, " + ColumnName.COL1.name + ", " + ColumnName.COL2.name + ", " + ColumnName.COL3.name + " ) VALUES ( '" + myQuestionNumber + "', '" + theQuestion + "', '" + theChoices + "', '" + theAnswer + "' )";
		myQuestionNumber++;
		try ( Connection conn = ds.getConnection();
				Statement stmt = conn.createStatement(); ) {
			stmt.executeUpdate( query );
		} catch ( SQLException e ) {
			e.printStackTrace();
			System.exit( 0 );
		} finally {
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
